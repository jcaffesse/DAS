package com.das.chat.backend;

import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.util.Log;


import com.das.chat.application.ChatApplication;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class ChatWSTask extends AsyncTask<WSParams, Void, String>
{
	private boolean logHttp = false;
    String token = null;
	private OnWSResponseListener<String> responseListener;

	public OnWSResponseListener<String> getResponseListener()
    {
		return responseListener;
	}

	public void setResponseListener(OnWSResponseListener<String> responseListener)
    {
		this.responseListener = responseListener;
	}

	@Override
	protected void onPreExecute()
    {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(WSParams... arg0)
	{

        HttpResponse httpResponse;

        InputStreamReader response = null;

        HttpParams httpParameters = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);

        HttpConnectionParams.setSoTimeout(httpParameters, 30000);

        HttpClient httpclient = new DefaultHttpClient(httpParameters);

        try
        {
            httpResponse = httpclient.execute(arg0[0].getRequest());

            Log.d("RESPONSE CODE", String.valueOf(httpResponse.getStatusLine().getStatusCode()));

            for (Header header : httpResponse.getAllHeaders()) {
                if(header.getName().compareTo("Auth-Token") == 0)
                {
                    token = "\"Auth-Token\":\"" + header.getValue() + "\"";
                }
                Log.d("HEADER", header.getName() + " : " + header.getValue());
            }


            if(httpResponse.getHeaders("Content-Encoding").length > 0)
            {
                InputStream is = new GZIPInputStream(httpResponse.getEntity().getContent());
                response = new InputStreamReader(is, "ISO-8859-1");

            }
            else
            {
                response = new InputStreamReader(httpResponse.getEntity().getContent(), "ISO-8859-1");
            }

            if(httpResponse.getStatusLine().getStatusCode() != 200)
            {
                String message = getStringFromInputStream(response);

                if (httpResponse.getStatusLine().getStatusCode() == 401)
                {
                    responseListener.onWSResponse(null, httpResponse.getStatusLine().getStatusCode(), "Acceso no autorizado");
                    return null;
                }

                try
                {
                    message = new JSONObject(message).get("Message").toString();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    responseListener.onWSResponse("Unknown error", httpResponse.getStatusLine().getStatusCode(), message);
                    return null;
                }

                responseListener.onWSResponse(null, httpResponse.getStatusLine().getStatusCode(), message);
                return null;
            }
            else
            {
                return getStringFromInputStream(response);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            responseListener.onWSResponse(null, 408, "Timeout");
            return null;
        }
	}

	@Override
	protected void onPostExecute(String result)
    {
		super.onPostExecute(result);

        if(result != null)
        {
            if (0 != (ChatApplication.getAppContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE))
            {
                Log.d("RESPONSE", result);
            }

            if(token != null)
            {
                result = result.replace("}", "," + token + "}");
            }

            responseListener.onWSResponse(result, 0, null);
        }
	}

    public static String getStringFromInputStream(InputStreamReader isr)
    {
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line = null;

        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            isr.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
	


