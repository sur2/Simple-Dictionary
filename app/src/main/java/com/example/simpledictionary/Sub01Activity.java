package com.example.simpledictionary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.adapter.RecyclerAdapter;
import com.example.custom.element.CustomEditText;
import com.example.dictonaryDTO.Sample;

import java.util.Locale;

public class Sub01Activity extends AppCompatActivity {
    private String setActionBarTitle;
    private ActionBar actionBar;
    private RecyclerAdapter recyclerAdapter;

    private EditText searchText;

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
        initElement();
    }

    private void initElement() {
        searchText = (EditText) findViewById(R.id.search_edit);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
                recyclerAdapter.filter(text);
            }
        });

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
