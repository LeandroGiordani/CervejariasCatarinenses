package com.example.a14201104.cervejariascatarinenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BreweryDetailActivity extends AppCompatActivity {

    private int breweryId;

    DataBrewery brewery;
    DataBrewery.Beers beer;
    private List<DataBrewery.Beers> beersList;

    private ImageView breweryImage;
    private TextView breweryDescription;
    private TextView beer1;
    private TextView beer2;
    private TextView beer3;
    private TextView beer4;
    private Button findBrewery;
    private Button videoBrewery;

    private int latitude;
    private int longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_detail);

        breweryImage = (ImageView) findViewById(R.id.brewery_image);
        breweryDescription = (TextView) findViewById(R.id.description);
        beer1 = (TextView) findViewById(R.id.beer1);
        beer2 = (TextView) findViewById(R.id.beer2);
        beer3 = (TextView) findViewById(R.id.beer3);
        beer4 = (TextView) findViewById(R.id.beer4);
        findBrewery = (Button) findViewById(R.id.find_brewery);
        videoBrewery = (Button) findViewById(R.id.video);

        beersList = new ArrayList<>();

        Intent intentFromMain = getIntent();

        if (intentFromMain.hasExtra("id"))
            breweryId = intentFromMain.getIntExtra("id", 0);

        try {
            JSONArray jsonArray = new JSONArray(loadJsonFromAsset());
            JSONObject breweryJson = jsonArray.getJSONObject(breweryId);
            breweryDescription.setText(breweryJson.getString("description"));


            for (int i = 0; i < jsonArray.length(); i++) {
                DataBrewery dataBrewery = new DataBrewery();
                dataBrewery.icon = breweryJson.getString("brewery-icon");
                dataBrewery.breweryName = breweryJson.getString("nome");
                //breweryList.add(dataBrewery);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String loadJsonFromAsset() {
        String json;

        try {
            InputStream inputStream = getAssets().open("brewery-list.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
