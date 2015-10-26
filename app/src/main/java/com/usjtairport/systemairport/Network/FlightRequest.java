package com.usjtairport.systemairport.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;


import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.usjtairport.systemairport.Model.Flight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Rodrigo on 15/10/2015.
 */
public class FlightRequest {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Flight> getBoardings(String url) throws IOException {

        ArrayList<Flight> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormEncodingBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        try {

            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++ ) {
                item = (JSONObject) root.get(i);
                int flightID = item.getInt("flightID");
                int airplaneID = item.getInt("airplaneID");
                String boardingFlight = item.getString("boardingFlight");
                String landingFlight = item.getString("landingFlight");
                String timeFlight = item.getString("timeFlight");
                String priceFlight = item.getString("priceFlight");
                BigDecimal bdPriceFlight = new BigDecimal(priceFlight);
                int qtySeatsAvailable = item.getInt("qtySeatsAvailable");

                lista.add(new Flight(flightID, airplaneID, boardingFlight, landingFlight, timeFlight, bdPriceFlight, qtySeatsAvailable));
            }

        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if(lista.size() == 0)
                lista.add(new Flight());
            //Log.v("FlightRequester", jsonStr);
        }
        return lista;
    }

    public ArrayList<Flight> getLandings(String url, String boardingFlightParm) throws IOException {

        ArrayList<Flight> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormEncodingBuilder()
                .add("boardingFlight",boardingFlightParm)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        try {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++ ) {
                item = (JSONObject)root.get(i);
                int flightID = item.getInt("flightID");
                int airplaneID = item.getInt("airplaneID");
                String boardingFlight = item.getString("boardingFlight");
                String landingFlight = item.getString("landingFlight");
                String timeFlight = item.getString("timeFlight");
                String priceFlight = item.getString("priceFlight");
                int qtySeatsAvailable = item.getInt("qtySeatsAvailable");

                lista.add(new Flight());
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if(lista.size() == 0)
                lista.add(new Flight());
            //Log.v("FlightRequester", jsonStr);
        }
        return lista;
    }

    public ArrayList<Flight> getFlights(String url, String boardingFlightParm, String landingFlightParm) throws IOException {

        ArrayList<Flight> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormEncodingBuilder()
                .add("boardingFlight",boardingFlightParm)
                .add("landingFlight",landingFlightParm)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        try {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++ ) {
                item = (JSONObject)root.get(i);
                int flightID = item.getInt("flightID");
                int airplaneID = item.getInt("airplaneID");
                String boardingFlight = item.getString("boardingFlight");
                String landingFlight = item.getString("landingFlight");
                String timeFlight = item.getString("timeFlight");
                String priceFlight = item.getString("priceFlight");
                int qtySeatsAvailable = item.getInt("qtySeatsAvailable");

                lista.add(new Flight());
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if(lista.size() == 0)
                lista.add(new Flight());
            //Log.v("FlightRequester", jsonStr);
        }
        return lista;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
