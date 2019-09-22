package com.example.simpledictionary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.adapter.RecyclerAdapter;
import com.example.dictonaryDTO.Sample;

public class Sub01Activity extends AppCompatActivity {
    private String setActionBarTitle;
    private ActionBar ab;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAcationBar();
        setContentView(R.layout.activity_sub01);
        initializeRecyclerView();
        addSample();
    }

    private void setAcationBar() {
        Intent sub01 = getIntent();
        setActionBarTitle = sub01.getStringExtra("ActionBar Title");
        ab = getSupportActionBar();
        ab.setTitle(setActionBarTitle);
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void addSample() {
        for (int i = 0; i < 20; i++) {
            Sample sample = new Sample();
            sample.setName(i+" "+setActionBarTitle);
            sample.setPullName(i+" "+setActionBarTitle);
            sample.setContents(i+ " contents");
            sample.setBookmark(false);
            recyclerAdapter.addItem(sample);
        }
        recyclerAdapter.notifyDataSetChanged();
    }
}
