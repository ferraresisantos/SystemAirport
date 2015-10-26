package com.usjtairport.systemairport;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rodrigo on 15/10/2015.
 */
public class FlightRequest {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Flight> getFlights(URL url) throws IOException {

        ArrayList<Flight> listFlight = new ArrayList<Flight>();



        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String resp = response.body().string();


        try {
            Gson g = new Gson();
            Type flightType = new TypeToken<Flight>() {
            }.getType();
            listFlight = g.fromJson(resp, flightType);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(listFlight.size() == 0)
                listFlight.add(new Flight());
        }
        return listFlight;
    }
}
