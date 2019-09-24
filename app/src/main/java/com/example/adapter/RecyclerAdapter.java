package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictonaryDTO.Sample;
import com.example.simpledictionary.R;
import com.example.simpledictionary.Sub01Activity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// RecyclerView의 Adapter를 구현하기 위해서 RecyclerView.Adapter를 상속받는다.
// RecyclerView는 추상클래스 즉, 해당 클래스의 추상메서드를 구현해야 한다.
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

    private List<Sample> sampleList = new LinkedList<>();

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
        holder.onBind(sampleList.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 item 수 반환
        return sampleList.size();
    }

    // 외부에서 item을 추가시킬 함수
    public void addItem(Sample sample) {
        sampleList.add(sample);
    }

    // 외부에서 item을 삭제시킬 함수
    public void delItem(int index) {
        sampleList.remove(index);
    }

    // RecyclerView의 핵심인 ViewHolder
    // 여기서 subView를 setting 합니다.
    // View.OnClickListener를 implements 받아 onClick 이벤트를 관리합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView pullName;
        private TextView contents;
        private ImageButton bookmark;

        // 상속받을 시 생성자 필수
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
            if(!sample.isBookmark()) {
                bookmark.setImageResource(R.drawable.ic_bookmark_off);
                bookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bookmark.setImageResource(R.drawable.ic_bookmark_on);
                    }
                });
            }else {
                bookmark.setImageResource(R.drawable.ic_bookmark_on);
                bookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bookmark.setImageResource(R.drawable.ic_bookmark_off);
                    }
                });
            }

            // OnBinc() 메서드가 호출 될 때 OnClickListener를 적용에 클릭 이벤트를 발생시킨다.
            itemView.setOnClickListener(this);
        }

        // View.OnClickListener를 implements를 받지 않을 경우 각 요소들에 각각 onClick 이벤트를 만들어 사용할 수 도 있음
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item:
                    Toast.makeText(v.getContext(), name.getText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
