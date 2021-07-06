package com.codediff.stock;

import com.google.gson.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class StockRetriever {
    public static double getCurrentResponse(String ticker) {
        /*
        IMPORTANT! It seems the API doesn't recognize '.' in a stock. For example,
        Berkshire Hathaway is known as BRK.A, but the API has it listed as BRK-A.
        Not sure if this applies to every stock.
         */
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols=" + ticker)
                    .get()
                    .addHeader("x-rapidapi-key", System.getenv("api_key"))
                    .addHeader("x-rapidapi-host", System.getenv("api_host"))
                    .build();
            Response response = client.newCall(request).execute();
            JsonArray address = new JsonParser().parse(response.body().string())
                    .getAsJsonObject().get("quoteResponse")
                    .getAsJsonObject().getAsJsonArray("result");
            var stock = address.get(0);
            var price = stock.getAsJsonObject().get("regularMarketPrice");
            String formatter = String.format("%.2f", price.getAsDouble());
            return Double.parseDouble(formatter);
        } catch (Exception e) {
            return -1;
        }
    }
}