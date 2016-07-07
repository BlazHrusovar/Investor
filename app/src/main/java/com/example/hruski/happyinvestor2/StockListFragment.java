package com.example.hruski.happyinvestor2;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hruski.happyinvestor2.model.StockAdapter;
import com.example.hruski.happyinvestor2.model.StockData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class StockListFragment extends Fragment {

    private final OkHttpClient client = new OkHttpClient();

    public  ListView listView;

    public ProgressBar progressBar;

    public StockData[] stockData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stock_list_fragment, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String x = stockData[0].getAsk();

        listView = (ListView) view.findViewById(R.id.listView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        requestStocks();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("stock_data", stockData[position]);
                startActivity(intent);

            }
        });
    }


    private void requestStocks(){


        Request request = new Request.Builder()
                .url("http://www.enclout.com/api/v1/yahoo_finance/show.json?&auth_token=7f5yGKvfuSRz_AARUajG&text=AAPL%2C%20MSFT%2C%20GOOG%2C%20FB%2C%20GE%2C%20AMZN%2C%20LNKD%2C%20TSLA%2C%20BAC")
                .get()
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("unexpected code: " + response);
                }


                Gson gson = new Gson();
                stockData = gson.fromJson(response.body().string(), StockData[].class);
                final ListAdapter adapter = new StockAdapter(getActivity(), stockData);



                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        progressBar.setVisibility(View.GONE);
                        listView.setAdapter(adapter);
                        listView.setVisibility(View.VISIBLE);
                    }
                });

            }

        });
    }
}