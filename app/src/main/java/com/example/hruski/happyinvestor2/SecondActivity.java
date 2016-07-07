package com.example.hruski.happyinvestor2;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = new Bundle();
        arguments.putSerializable(StockDetailFragment.DATA,
                getIntent().getExtras().getSerializable(StockDetailFragment.DATA));

        Fragment fragment = new StockDetailFragment();
        fragment.setArguments(arguments);

        getFragmentManager().beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();
    }
}
