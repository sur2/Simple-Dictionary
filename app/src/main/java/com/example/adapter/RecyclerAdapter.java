package com.example.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictonaryDTO.Sample;
import com.example.simpledictionary.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

// RecyclerView의 Adapter를 구현하기 위해서 RecyclerView.Adapter를 상속받는다.
// RecyclerView는 추상클래스 즉, 해당 클래스의 추상메서드를 구현해야 한다.
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private List<Sample> sampleList = new LinkedList<>();
    private List<Sample> searchList = new LinkedList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // LayoutInflater를 이용하여 item.xml을 inflate 한다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // item을 하나 씩 bind하는 함수입니다.
//        holder.onBind(sampleList.get(position));
        holder.onBind(searchList.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 item 수 반환
//        return sampleList.size();
        return searchList.size();
    }

    // 외부에서 item을 추가시킬 함수
    public void addItem(Sample sample) {
        sampleList.add(sample);
    }

    // 외부에서 item을 삭제시킬 함수
    public void delItem(int index) {
        sampleList.remove(index);
    }

    // 외부에서 추가된 sampleList를 searchList에 채워넣는 함수
    public void setSearchList() {
        searchList.addAll(sampleList);
    }

    // 해당 검색어만 보여주는 필터
    public void filter(String search) {
        Log.i("fillter", "실행 중");
        // search를 소문자로 변경(언어는 JVM에 따른 인스턴스화)
        search = search.toLowerCase(Locale.getDefault());

        searchList.clear();
        // 검색어가 없는 경우
        if(search.length() == 0) {
            searchList.addAll(sampleList);
        }
        else {
            for (Sample word : sampleList) {
                String name = word.getName();
                String contents = word.getContents();
                String pullName = word.getPullName();

                // 이름이나 내용에 검색어가 포함될 경우
                if (name.toLowerCase(Locale.getDefault()).contains(search)
                        || contents.toLowerCase(Locale.getDefault()).contains(search)
                        || pullName.toLowerCase(Locale.getDefault()).contains(search)){

                    searchList.add(word);
                    for (Sample sample:searchList) {
                        Log.i("검색된 단어", sample.getName());
                    }
                }
            }
        }
        Log.d("검색된 데이터", searchList.toString());
        notifyDataSetChanged();
    }

    // RecyclerView의 핵심인 ViewHolder
    // 여기서 subView를 setting 합니다.
    // View.OnClickListener를 implements 받아 onClick 이벤트를 관리합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        private TextView name;
        private TextView pullName;
        private TextView contents;
        private CheckBox bookmark;

        // 상속받을 시 생성자 필수(itmeView는 하나의 item)
        private ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            pullName = itemView.findViewById(R.id.pullName);
            contents = itemView.findViewById(R.id.content);
            bookmark = itemView.findViewById(R.id.bookmarkBtn);
        }

        private void onBind(Sample sample) {
            name.setText(sample.getName());
            pullName.setText(sample.getPullName());
            contents.setText(sample.getContents());
            bookmark.setChecked(sample.isBookmark());

            // OnBind() 메서드가 호출 될 때 OnClickListener를 적용에 클릭 이벤트를 발생시킨다.
            itemView.setOnClickListener(this);

            // OnBind() 메서드가 호출 될 때 OnCheckedChangeListener를 적용에 클릭 이벤트를 발생시킨다.
            bookmark.setOnCheckedChangeListener(this);

        }

        // View.OnClickListener를 implements를 받지 않을 경우 각 요소들에 각각 onClick 이벤트를 만들어 사용할 수 도 있음
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item:
                    Toast.makeText(v.getContext(), name.getText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            switch (compoundButton.getId()) {
                case R.id.bookmarkBtn:
                    if (isChecked){
                        Toast.makeText(compoundButton.getContext(), ""+isChecked, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(compoundButton.getContext(), ""+isChecked, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
