package com.example.custom.element;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.simpledictionary.R;

/**
 * res/layout/custom_edit_text.xml을 사용하기 위한 Java class
 */
public class CustomEditText extends RelativeLayout {

    private EditText editText;
    private Button searchBtn;
    private Button clearBtn;
    // 키보드 상태 객체 받기
    private InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

    public CustomEditText(Context context) {
        super(context);
        setLayoutInflater();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutInflater();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutInflater();
    }

    // 레이아웃을 설정
    private void setLayoutInflater() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            layoutInflater.inflate(R.layout.custom_edit_text,this,true);
        }

        editText = findViewById(R.id.search_edit);
        searchBtn = findViewById(R.id.search_button);
        clearBtn = findViewById(R.id.clearable_button);
        editText.setVisibility(INVISIBLE);
        clearBtn.setVisibility(INVISIBLE);
        onSearch();
    }

    // 검색 버튼 클릭 시 에디트텍스트 활성화
    private void onSearch(){
        searchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setVisibility(RelativeLayout.VISIBLE);
                searchBtn.setVisibility(RelativeLayout.INVISIBLE);
                editText.requestFocus();

                // 키보드 올리기
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                onEditText();
            }
        });
    }

    private void onEditText(){
        Log.d("에디트 포커스", editText.hasFocus()+"");
        if (editText.hasFocus()) {
            clearBtn.setVisibility(RelativeLayout.VISIBLE);
            clearEditText();
        }
    }

    // 텍스트를 비우는 메서드
    private void clearEditText() {
        clearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.clearFocus();
                editText.setVisibility(RelativeLayout.INVISIBLE);
                searchBtn.setVisibility(RelativeLayout.VISIBLE);
                clearBtn.setVisibility(INVISIBLE);

                // 키보드 내리기
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });
    }
}
