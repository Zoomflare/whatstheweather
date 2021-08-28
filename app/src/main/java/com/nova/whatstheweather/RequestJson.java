package com.nova.whatstheweather;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestJson {

    private static RequestJson instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private RequestJson(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestJson getInstance(Context context) {
        if (instance == null) {
            instance = new RequestJson(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
