package com.example.a14201104.cervejariascatarinenses;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class BreweryAdapter extends RecyclerView<BreweryAdapter.AdapterViewHolder> {

    private final Context mContext;

    private Cursor mCursor;

    public BreweryAdapter(@NonNull Context context) {
        mContext = context;
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView breweryName;
        final TextView breweryCity;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mCursor.moveToPosition(adapterPosition);
        }
    }
}
