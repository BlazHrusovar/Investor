package com.example.hruski.happyinvestor2.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.hruski.happyinvestor2.R;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class StockData implements Serializable {


    public String symbol;
    public String ask;
    public String bid;
    public String last_trade_date;
    public String low;
    public String high;
    public String low_52_weeks;
    public String high_52_weeks;
    public String volume;
    public String open;
    public String close;

    public StockData(String symbol, String ask, String bid, String last_trade_date, String low, String high, String low_52_weeks, String high_52_weeks, String volume, String open, String close) {
        this.symbol = symbol;
        this.ask = ask;
        this.bid = bid;
        this.last_trade_date = last_trade_date;
        this.low = low;
        this.high = high;
        this.low_52_weeks = low_52_weeks;
        this.high_52_weeks = high_52_weeks;
        this.volume = volume;
        this.open = open;
        this.close = close;
    }


    public String getSymbol() {
        return symbol;
    }

    public String getAsk() {
        return ask;
    }

    public String getBid() {
        return bid;
    }

    public String getLast_trade_date() {
        return last_trade_date;
    }

    public String getLow() {
        return low;
    }

    public String getHigh() {
        return high;
    }

    public String getLow_52_weeks() {
        return low_52_weeks;
    }

    public String getHigh_52_weeks() {
        return high_52_weeks;
    }

    public String getVolume() {
        return volume;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }


    public String getFullName(){
        if (getSymbol().equals("AAPL")){
            return "Apple";
        }
        if (getSymbol().equals("MSFT")) {
            return "Microsoft";
        }
        if (getSymbol().equals("GOOG")) {
            return "Google";
        }
        if (getSymbol().equals("FB")) {
            return "Facebook";
        }
        if (getSymbol().equals("GE")) {
            return "General electronic";
        }
        if (getSymbol().equals("AMZN")) {
            return "Amazon";
        }
        if (getSymbol().equals("LNKD")) {
            return "Linkedin";
        }
        if (getSymbol().equals("TSLA")) {
            return "Tesla";
        }
        if (getSymbol().equals("BAC")) {
            return "Bank of America";
        }
        return "No stocks found";
    }
    
    public int getPicture(){
        if (getSymbol().equals("AAPL")){
            return R.drawable.apple;
        }
        if (getSymbol().equals("MSFT")) {
            return R.drawable.microsoft;
        }
        if (getSymbol().equals("GOOG")) {
            return R.drawable.google;
        }
        if (getSymbol().equals("FB")) {
            return R.drawable.facebook;
        }
        if (getSymbol().equals("GE")) {
            return R.drawable.ge;
        }
        if (getSymbol().equals("AMZN")) {
            return R.drawable.amazon;
        }
        if (getSymbol().equals("LNKD")) {
            return R.drawable.linkedin;
        }
        if (getSymbol().equals("TSLA")) {
            return R.drawable.tesla;
        }
        if (getSymbol().equals("BAC")) {
            return R.drawable.bac;
        }
        
        return R.drawable.noimg;
        
    }

}

