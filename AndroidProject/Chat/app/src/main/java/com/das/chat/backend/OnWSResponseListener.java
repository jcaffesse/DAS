package com.das.chat.backend;

public interface OnWSResponseListener<E> 
{
	/**
	 * Retrieve the response of the WS call.
	 * 
	 * @param response
	 *            E the response data if exist and there is no error. Null
	 *            if there is no data or there is an error
	 * @param errorCode
	 *            the error code
	 * @param errorMsg
	 *            the error msg. Null if there is no error.
	 */
	public void onWSResponse(E response, long errorCode, String errorMsg);

}

