package com.example.a14201104.cervejariascatarinenses;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class BreweryAdapter extends RecyclerView.Adapter<BreweryAdapter.AdapterViewHolder> {

    private final Context mContext;

    private Cursor mCursor;

    private LayoutInflater inflater;

    final private AdapterOnClickHandler mClickHandler;

    List<DataBrewery> dataBreweries = Collections.emptyList();
    DataBrewery current;
    int currentPos = 0;

    public interface AdapterOnClickHandler {
        void onClick(int id);
    }

    public BreweryAdapter(@NonNull Context context, List<DataBrewery> data, AdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
        inflater = LayoutInflater.from(context);
        dataBreweries = data;
    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.brewery_list_item, viewGroup, false);
        view.setFocusable(true);

        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder adapterViewHolder, int position) {
        mCursor.moveToPosition(position);

        current = dataBreweries.get(position);
        adapterViewHolder.breweryIcon.setImageResource(R.drawable.ic_launcher_background);
        adapterViewHolder.breweryName.setText(current.breweryName);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null)
            return 0;
        return mCursor.getCount();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView breweryIcon;
        final TextView breweryName;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            breweryIcon = (ImageView) itemView.findViewById(R.id.brewery_icon);
            breweryName = (TextView) itemView.findViewById(R.id.brewery_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mCursor.moveToPosition(adapterPosition);
        }
    }
}
