package com.example.custom.element;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
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
        clearEditText();
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
                InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });
    }

    private void onEditText(){
        clearEditText();

    }



    // 텍스트가 변할 때 이벤트를 발생
    private void textChange() {
        // TextWatcher를 사용해 EditText가 변할 때 마다 이벤트 호출
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // 텍스트가 변경 될 때 마다 호출되는 메서드
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > -1){
                    clearBtn.setVisibility(RelativeLayout.VISIBLE);
                }else {
                    clearBtn.setVisibility(RelativeLayout.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // 텍스트를 비우는 메서드
    private void clearEditText() {
        clearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.clearFocus();
                searchBtn.setVisibility(RelativeLayout.VISIBLE);
            }
        });
    }
}
