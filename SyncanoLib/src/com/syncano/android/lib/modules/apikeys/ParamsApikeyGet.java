package com.syncano.android.lib.modules.apikeys;

import com.syncano.android.lib.modules.Params;
import com.syncano.android.lib.modules.Response;

/**
 * Get API clients. Only Admin permission role can view other API clients.
 */
public class ParamsApikeyGet extends Params {
	/**
	 * Default constructor
	 */
	public ParamsApikeyGet() {
	}

	@Override
	public String getMethodName() {
		return "apikey.get";
	}

	@Override
	public Response instantiateResponse() {
		return new ResponseApikeyGet();
	}
}