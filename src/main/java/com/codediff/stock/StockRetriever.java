package com.codediff.stock;

import com.google.gson.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

public class StockRetriever {
    public static double getCurrentResponse(String ticker) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols="+ticker)
                .get()
                .addHeader("x-rapidapi-key", "8d1894121cmsh1e839afb82a7fcdp196250jsn3f32627c72f4")
                .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .build();
        Response response = client.newCall(request).execute();
        JsonArray address = new JsonParser().parse(response.body().string())
                .getAsJsonObject().get("quoteResponse")
                .getAsJsonObject().getAsJsonArray("result");
        var stock = address.get(0);
        var price = stock.getAsJsonObject().get("regularMarketPrice");
        return price.getAsDouble();
    }
}