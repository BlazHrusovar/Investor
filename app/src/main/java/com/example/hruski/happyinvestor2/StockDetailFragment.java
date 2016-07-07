
package com.example.hruski.happyinvestor2;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hruski.happyinvestor2.model.StockData;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.IOException;


public class StockDetailFragment extends Fragment {

    public static final String DATA = "stock_data";

    public LinearLayout linearLayout;

    private StockData stockData;

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stock_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        imageView = (ImageView) view.findViewById(R.id.image_view);

        Bundle extras = getArguments();
        stockData = (StockData) extras.getSerializable(DATA);


        if (stockData != null) {
            getActivity().setTitle(stockData.getSymbol());

            addNewTextView("Bid: " + String.valueOf(stockData.getBid()));
            addNewTextView("Last trade date: " + String.valueOf(stockData.getLast_trade_date()));
            addNewTextView("Low: " + String.valueOf(stockData.getLow()));
            addNewTextView("High: " + String.valueOf(stockData.getHigh()));
            addNewTextView("Low 52 weeks: " + String.valueOf(stockData.getLow_52_weeks()));
            addNewTextView("High 52 weeks: " + String.valueOf(stockData.getHigh_52_weeks()));
            addNewTextView("Volume: " + String.valueOf(stockData.getVolume()));
            addNewTextView("Open: " + String.valueOf(stockData.getOpen()));
            addNewTextView("Close: " + String.valueOf(stockData.getClose()));
            addNewTextView("Ask: " + String.valueOf(stockData.getAsk()));

        }


    }

    private void addNewTextView(String string){

        TextView textView = new TextView(getContext());
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setText(string);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        linearLayout.addView(textView);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_photo:
                if (checkPermission()){
                    takePicture();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private File getFile(){
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(storageDir, "happy_investor2_" + stockData.getSymbol() + ".jpg");
    }

    private File createImageFile() throws IOException{
        File image = getFile();
        image.createNewFile();

        return image;
    }

    private boolean checkPermission(){
        if (getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            return false;
        } else {
            return true;
        }
    }

    private void takePicture() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {

            try {
                File photoFile = createImageFile();

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(intent, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    takePicture();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == Activity.RESULT_OK){
                    setImage();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void setImage() {
        File file = getFile();
        if (file.exists()) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        } else {
            imageView.setImageBitmap(null);
        }
    }
}

