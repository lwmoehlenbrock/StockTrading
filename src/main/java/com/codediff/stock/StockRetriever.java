package com.codediff.stock;

import com.fasterxml.jackson.core.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StockRetriever {

    public static Response getCurrentResponse(String ticker) throws IOException {
        String key = "8d1894121cmsh1e839afb82a7fcdp196250jsn3f32627c72f4";
        String host = "apidojo-yahoo-finance-v1.p.rapidapi.com";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols="+ticker)
                .get()
                .addHeader("x-rapidapi-key", key)
                .addHeader("x-rapidapi-host", host)
                .build();
        return client.newCall(request).execute();
    }

    public static void main(String[] args) throws IOException {
        Response response = getCurrentResponse("AAPL");
        System.out.print(response.body().string());
    }
}