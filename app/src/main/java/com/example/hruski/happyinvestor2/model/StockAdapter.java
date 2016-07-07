package com.example.hruski.happyinvestor2.model;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hruski.happyinvestor2.R;



public class StockAdapter extends ArrayAdapter<StockData> {

    private Resources resources;
    private StockData[] data;


    public StockAdapter(Context context, StockData[] stocks) {
        super(context, R.layout.custom_row, stocks);
        this.resources = context.getResources();
        this.data = stocks;
    }

    public void setData(StockData[] data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        StockData item = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(item.getFullName());

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        new ImageTask(resources, imageView).execute(item.getPicture());

        return view;
    }



    private static class ImageTask extends AsyncTask<Integer, Void, Drawable> {

        private final Resources resources;
        private final ImageView imageView;

        public ImageTask(Resources resources, ImageView imageView) {
            this.resources = resources;
            this.imageView = imageView;
        }

        @Override
        protected Drawable doInBackground(Integer... params) {
            return resources.getDrawable(params[0], null);
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }
    }
}
