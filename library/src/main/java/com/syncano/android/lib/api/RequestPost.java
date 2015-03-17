package com.syncano.android.lib.api;

import com.syncano.android.lib.Syncano;
import com.syncano.android.lib.utils.SyncanoHttpClient;

public class RequestPost<T> extends Request <T> {

    private Object data;

    public RequestPost(Class<T> responseType, String url, Syncano syncano, Object data) {
        super(responseType, url, syncano);
        this.data = data;
    }

    @Override
    public String getRequestMethod() {
        return SyncanoHttpClient.METHOD_POST;
    }

    @Override
    public String prepareParams() {

        if (data != null) {
            return gson.toJson(data);
        }

        return null;
    }
}