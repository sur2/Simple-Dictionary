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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        addSample();
        recyclerAdapter.setSearchList();
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
        if (setActionBarTitle.equalsIgnoreCase("SAMPLE01")) {
            recyclerAdapter.addItem(new Sample("AWS", "Adverse weather system", "전천후 시스템"));
            recyclerAdapter.addItem(new Sample("AWP", "Awaiting parts", "공급 대기부품"));
            recyclerAdapter.addItem(new Sample("AWN", "Automatic weather network", "자동 기상망"));
            recyclerAdapter.addItem(new Sample("AWLS", "All-weather landing system", "전천후 착륙시스템"));
            recyclerAdapter.addItem(new Sample("AWL", "Automated wire list", "자동화 배선목록"));
            recyclerAdapter.addItem(new Sample("AWI", "Aircraft weight indicator", "항공기 중량 지시계"));
            recyclerAdapter.addItem(new Sample("AVC", "Automatic variable camber", "자동 가변 캠버"));
            recyclerAdapter.addItem(new Sample("GZD", "Grid zone designation", "그리드 구간 지시"));
            recyclerAdapter.addItem(new Sample("GWS", "Guided weapons system", "유도 무기체계"));
            recyclerAdapter.addItem(new Sample("GVT", "Ground vibration test", "지상 진동시험"));
            recyclerAdapter.addItem(new Sample("GUI", "Graphical user interface", "그래픽 사용자 인터페이스"));
            recyclerAdapter.addItem(new Sample("RWHR", "Radar warning and homing receiver", "레이더 경고 및 유도수신기"));
            recyclerAdapter.addItem(new Sample("RVR", "Runway visual range", "활주로 가시 범위"));
            recyclerAdapter.addItem(new Sample("RTSC", "Real-time simulation computer", "실시간 시뮬레이션 컴퓨터"));
            recyclerAdapter.addItem(new Sample("WSS", "Work share subcontract", "개발 분담 하도급계약"));
            recyclerAdapter.addItem(new Sample("WSDP", "Weapon system development plan", "무장 체계 개발계획"));
            recyclerAdapter.addItem(new Sample("ZCL", "Zero command line", "제로 명령 라인"));

        } else if (setActionBarTitle.equalsIgnoreCase("SAMPLE02")) {
            for (int i = 0; i < 30; i++) {
                Sample sample = new Sample();
                sample.setName(i + " " + "SAMPLE02");
                sample.setPullName(i + " " + "Second SAMPLE");
                sample.setContents(i + " contents");
                sample.setBookmark(false);
                recyclerAdapter.addItem(sample);
            }
        } else {
            for (int i = 0; i < 40; i++) {
                Sample sample = new Sample();
                sample.setName(i + " " + "SAMPLE03");
                sample.setPullName(i + " " + "SAMPLE03");
                sample.setContents(i + " contents of Sample03");
                sample.setBookmark(false);
                recyclerAdapter.addItem(sample);
            }
        }
//        recyclerAdapter.notifyDataSetChanged();
    }
}
