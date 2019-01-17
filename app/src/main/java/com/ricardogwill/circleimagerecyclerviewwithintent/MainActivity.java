package com.ricardogwill.circleimagerecyclerviewwithintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

// This implements the interface defined near the bottom of "RecyclerViewAdapter.java".
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewListener {

    private static final String TAG = "MainActivity";

    // Variables
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();

    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://tse2.mm.bing.net/th?id=OIP.SQvOs-meO_oEGUUYOA1YsgHaE8&pid=15.1&P=0&w=283&h=190");
        mNames.add("Brown Falls");

        mImageUrls.add("https://tse2.mm.bing.net/th?id=OIP.B1XmHgqviGyKpRQ4JrJEOgHaE8&pid=15.1&P=0&w=283&h=190");
        mNames.add("Triangle Falls");

        mImageUrls.add("https://tse4.mm.bing.net/th?id=OIP.yxa7KEoqgMdQQcgLpj5i0wHaEK&pid=15.1&P=0&w=336&h=190");
        mNames.add("Heart Way");

        mImageUrls.add("https://tse3.mm.bing.net/th?id=OIP.uVAcDyj4Nk8_CXCtX78xpwHaEo&pid=15.1&P=0&w=302&h=190");
        mNames.add("Whisper Falls");

        mImageUrls.add("https://tse4.mm.bing.net/th?id=OIP.Ek91hrTwJgxECG-BItnozgHaEL&pid=15.1&P=0&w=298&h=169");
        mNames.add("Green Kingdom");

        mImageUrls.add("https://tse4.mm.bing.net/th?id=OIP.MyeKRlH2Oi1kEz0EF4PFMQHaE8&pid=15.1&P=0&w=290&h=194");
        mNames.add("Seaset");

        mImageUrls.add("https://tse3.mm.bing.net/th?id=OIP.5QxfQGTld0FhHNiCe-67owHaEo&pid=15.1&P=0&w=293&h=184");
        mNames.add("Autumnal Forest");

        mImageUrls.add("https://tse1.mm.bing.net/th?id=OIP.JnyfMgRwyumjd46DgBlr9wHaEK&pid=15.1&P=0&w=326&h=184");
        mNames.add("Lone Tree");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImageUrls, this, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    // This is a necessary method because this class implements "RecyclerViewAdapter.OnRecyclerViewListener".
    // Note that the interface is defined near the bottom of the "RecyclerViewAdapter.java" class.
    @Override
    public void onRecyclerViewClick(int i) {

        Log.d(TAG, "onRecyclerViewClick: clicked.");

        // mNames.get(i);
        // mImageUrls.get(i);

        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("image_url", mImageUrls.get(i));
        intent.putExtra("image_name", mNames.get(i));
        startActivity(intent);

    }
}
