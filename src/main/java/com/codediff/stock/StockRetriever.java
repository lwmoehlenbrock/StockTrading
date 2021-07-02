package com.codediff.stock;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class StockRetriever {

    private final String key = "8d1894121cmsh1e839afb82a7fcdp196250jsn3f32627c72f4";
    private String host = "apidojo-yahoo-finance-v1.p.rapidapi.com";

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=tesla&region=US")
            .get()
            .addHeader("x-rapidapi-key", key)
            .addHeader("x-rapidapi-host", host)
            .build();

    Response response = client.newCall(request).execute();

    public StockRetriever() throws IOException {
    }
}