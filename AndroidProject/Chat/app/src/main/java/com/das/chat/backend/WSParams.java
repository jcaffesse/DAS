package com.das.chat.backend;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;

public class WSParams 
{
	private String body;

    HttpRequestBase req;

    public void setRequest(HttpRequestBase req)
    {
        this.req = req;
    }

    public void setRequestWithBody(HttpRequestBase req, String body)
    {
        this.req = req;

        AbstractHttpEntity entity = null;
        try
        {
            entity = new ByteArrayEntity(body.getBytes("UTF8"));

            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded"));

            ((HttpPost)this.req).setEntity(entity);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public HttpRequestBase getRequest()
    {
        return this.req;
    }

}
