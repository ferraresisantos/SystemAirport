package com.usjtairport.systemairport.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.usjtairport.systemairport.Model.Flight;
import com.usjtairport.systemairport.Network.FlightRequest;
import com.usjtairport.systemairport.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String servidor = "10.0.2.2:9090";
    private Spinner spnBoarding;
    private Spinner spnLanding;
    private Button btSearch;
    private String boarding;
    private FlightRequest flightRequest;
    ArrayList<Flight>  flightBoardings = new ArrayList<Flight>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boarding = "";
        spnBoarding     = (Spinner)findViewById(R.id.spnBoarding);
        spnLanding      = (Spinner)findViewById(R.id.spnLanding);
        btSearch        = (Button)findViewById(R.id.btSearch);

        flightRequest = new FlightRequest();
        if(flightRequest.isConnected(this)) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        flightBoardings = flightRequest.getBoardings("http://" + servidor + "/searchBoardingsJSon.json");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }

        ArrayAdapter<Flight>  boardingArrayAdapter = new ArrayAdapter<Flight>(this,android.R.layout.simple_spinner_dropdown_item,flightBoardings);
        ArrayAdapter<Flight>  spinnerBoardingArrayAdapter = boardingArrayAdapter;
        spinnerBoardingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBoarding.setAdapter(spinnerBoardingArrayAdapter);




        //spnBoarding.setAdapter(karant_adapter);


    }
/*
    private class FillLandingSpinner implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            boarding = (String) parent.getItemAtPosition(position).toString();
            ArrayList<Flight> flightLandings = new ArrayList<Flight>();
            try {
                flightLandings = flightRequest.getLandings("http://192.168.10.48:8080/searchLandingsJSon.json",boarding);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayAdapter<Flight>  landingArrayAdapter = new ArrayAdapter<Flight>(getParent(),android.R.layout.simple_spinner_dropdown_item,flightLandings);
            ArrayAdapter<Flight>  spinnerLandingArrayAdapter = landingArrayAdapter;
            spinnerLandingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnLanding.setAdapter(spinnerLandingArrayAdapter);

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }
*/
}
