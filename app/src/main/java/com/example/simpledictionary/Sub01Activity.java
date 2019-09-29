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
import com.example.custom.element.CustomEditText;
import com.example.dictonaryDTO.Sample;

public class Sub01Activity extends AppCompatActivity {
    private String setActionBarTitle;
    private ActionBar actionBar;
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
        actionBar = getSupportActionBar();
        actionBar.setTitle(setActionBarTitle);
    }

    // RecyclerView를 구현하기 위한 메서드
    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void addSample() {
        if(setActionBarTitle.equalsIgnoreCase("SAMPLE01")) {
            for (int i = 0; i < 20; i++) {
                Sample sample = new Sample();
                sample.setName(i+" "+"SAMPLE01");
                sample.setPullName(i+" "+"SAMPLE01");
                sample.setContents(i+ " contents");
                sample.setBookmark(false);
                recyclerAdapter.addItem(sample);
            }
        } else if(setActionBarTitle.equalsIgnoreCase("SAMPLE02")){
            for (int i = 0; i < 30; i++) {
                Sample sample = new Sample();
                sample.setName(i+" "+"SAMPLE02");
                sample.setPullName(i+" "+"Second SAMPLE");
                sample.setContents(i+ " contents");
                sample.setBookmark(false);
                recyclerAdapter.addItem(sample);
            }
        } else {
            for (int i = 0; i < 40; i++) {
                Sample sample = new Sample();
                sample.setName(i+" "+"SAMPLE03");
                sample.setPullName(i+" "+"SAMPLE03");
                sample.setContents(i+ " contents of Sample03");
                sample.setBookmark(false);
                recyclerAdapter.addItem(sample);
            }
        }
//        recyclerAdapter.notifyDataSetChanged();
    }
}
