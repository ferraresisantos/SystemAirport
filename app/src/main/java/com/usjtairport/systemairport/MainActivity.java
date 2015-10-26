package com.usjtairport.systemairport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spnBoarding;
    private Spinner spnLanding;
    private Button btSearch;
    private String boarding;
    private FlightRequest flightRequest;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boarding = "";
        spnBoarding     = (Spinner)findViewById(R.id.spnBoarding);
        spnLanding      = (Spinner)findViewById(R.id.spnLanding);
        btSearch        = (Button)findViewById(R.id.btSearch);

        flightRequest = new FlightRequest();
        ArrayList<Flight>  flightBoardings = new ArrayList<Flight>();

        try {
            url = new URL("http://192.168.10.48:8080/AirportProjectWeb/webresources/AirportProject/flight/getListBoardings?");
            flightBoardings = flightRequest.getFlights();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<Flight>  boardingArrayAdapter = new ArrayAdapter<Flight>(this,android.R.layout.simple_spinner_dropdown_item,flightBoardings);
        ArrayAdapter<Flight>  spinnerBoardingArrayAdapter = boardingArrayAdapter;
        spinnerBoardingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBoarding.setAdapter(spinnerBoardingArrayAdapter);


    }

    private class FillLandingSpinner implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            boarding = (String) parent.getItemAtPosition(position).toString();
            ArrayList<Flight> flightLandings = new ArrayList<Flight>();
            try {
                url = new URL("http://192.168.10.48:8080/AirportProjectWeb/webresources/AirportProject/flight/getListBoardings/"+boarding);
                flightLandings = flightRequest.getFlights();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayAdapter<Flight>  landingArrayAdapter = new ArrayAdapter<Flight>(getParent(),android.R.layout.simple_spinner_dropdown_item,flightLandings);
            ArrayAdapter<Flight>  spinnerLandingArrayAdapter = landingArrayAdapter;
            spinnerLandingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnBoarding.setAdapter(spinnerLandingArrayAdapter);

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }

}
