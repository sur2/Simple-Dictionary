package com.example.util.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.dictonaryDTO.Sample;

import java.util.ArrayList;
import java.util.List;

public class EmbeddedDatabaseConfig extends SQLiteOpenHelper {

    public EmbeddedDatabaseConfig(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // SQLiteOpenHelper에서 처음 호출되는 메서드
    // Database를 생성하기 위해 최초로 실행된다.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // CREATE Query
        sqLiteDatabase.execSQL("CREATE TABLE SAMPLE01(" +
                "sample_key INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name TEXT NOT NULL," +
                "pullName TEXT NOT NULL," +
                "contents TEXT NOT NULL," +
                "bookmakr INTEGER NOT NULL DEFAULT 0" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertSample() {
        SQLiteDatabase sample01DB = this.getWritableDatabase();

        ArrayList<Sample> sample01List = new ArrayList<>();
        sample01List.add(new Sample("AWS", "Adverse weather system", "전천후 시스템"));
        sample01List.add(new Sample("AWP", "Awaiting parts", "공급 대기부품"));
        sample01List.add(new Sample("AWN", "Automatic weather network", "자동 기상망"));
        sample01List.add(new Sample("AWLS", "All-weather landing system", "전천후 착륙시스템"));
        sample01List.add(new Sample("AWL", "Automated wire list", "자동화 배선목록"));
        sample01List.add(new Sample("AWI", "Aircraft weight indicator", "항공기 중량 지시계"));
        sample01List.add(new Sample("AVC", "Automatic variable camber", "자동 가변 캠버"));
        sample01List.add(new Sample("GZD", "Grid zone designation", "그리드 구간 지시"));
        sample01List.add(new Sample("GWS", "Guided weapons system", "유도 무기체계"));
        sample01List.add(new Sample("GVT", "Ground vibration test", "지상 진동시험"));
        sample01List.add(new Sample("GUI", "Graphical user interface", "그래픽 사용자 인터페이스"));
        sample01List.add(new Sample("RWHR", "Radar warning and homing receiver", "레이더 경고 및 유도수신기"));
        sample01List.add(new Sample("RVR", "Runway visual range", "활주로 가시 범위"));
        sample01List.add(new Sample("RTSC", "Real-time simulation computer", "실시간 시뮬레이션 컴퓨터"));
        sample01List.add(new Sample("WSS", "Work share subcontract", "개발 분담 하도급계약"));
        sample01List.add(new Sample("WSDP", "Weapon system development plan", "무장 체계 개발계획"));
        sample01List.add(new Sample("ZCL", "Zero command line", "제로 명령 라인"));

        // key(column)와 value를 저장
        ContentValues values = new ContentValues();
        for (Sample sample01 : sample01List) {
            // key & value
            values.put("name", sample01.getName());
            values.put("pullName", sample01.getPullName());
            values.put("contents", sample01.getContents());

            // INSERT Query
            sample01DB.insert("SAMPLE01", null, values);
        }
    }

    public ArrayList<Sample> selectByKeyword(String keyword) {
        SQLiteDatabase sample01DB = this.getWritableDatabase();
        // SELECT Query
        Cursor cursor = sample01DB.rawQuery("SELECT * FROM SAMPLE01 WHERE name Like '%" + keyword + "%';", null);
        ArrayList<Sample> selectList = new ArrayList<>();
        while (cursor.moveToNext()) {
            selectList.add(new Sample(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 0 ? false : true)
            );
        }
        Log.d("select query keyword", keyword +", "+ selectList.size());
        return selectList;
    }

    public List<Sample> selectAllSample01() {
        SQLiteDatabase sample01DB = this.getWritableDatabase();
        // SELECT Query
        Cursor cursor = sample01DB.rawQuery("SELECT * FROM SAMPLE01", null);
        ArrayList<Sample> selectList = new ArrayList<>();
        while (cursor.moveToNext()) {
            selectList.add(new Sample(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 0 ? false : true)
            );
        }
        return selectList;
    }
}
