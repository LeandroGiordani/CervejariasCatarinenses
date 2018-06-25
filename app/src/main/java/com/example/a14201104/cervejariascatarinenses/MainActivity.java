package com.example.a14201104.cervejariascatarinenses;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>, BreweryAdapter.AdapterOnClickHandler {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final int ID_BREWERY_LOADER = 7;

    private RecyclerView mRecyclerView;
    private BreweryAdapter mAdapter;
    private int mPosition = RecyclerView.NO_POSITION;
    private ProgressBar mLoadingIndicator;
    private List<DataBrewery> breweryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0f);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_lista_cervejarias);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        try {
            JSONArray jsonArray = new JSONArray(loadJsonFromAsset());
            Log.d(TAG, "json size: " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject breweryJson = jsonArray.getJSONObject(i);
                DataBrewery dataBrewery = new DataBrewery();
                dataBrewery.icon = breweryJson.getString("brewery-icon");
                dataBrewery.breweryName = breweryJson.getString("nome");
                breweryList.add(dataBrewery);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter = new BreweryAdapter(this, breweryList, this);
        mRecyclerView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(ID_BREWERY_LOADER, null, this);
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
        Log.d(TAG, "json oblect: " + json.length());
        return json;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onClick(int id) {

    }
}
