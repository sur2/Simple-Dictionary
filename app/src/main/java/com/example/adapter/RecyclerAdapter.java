package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictonaryDTO.Sample;
import com.example.simpledictionary.R;

import java.util.ArrayList;

// RecyclerView의 Adapter를 구현하기 위해서 RecyclerView.Adapter를 상속받는다.
// RecyclerView는 추상클래스 즉, 해당 클래스의 추상메서드를 구현해야 한다.
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

    private ArrayList<Sample> sampleList = new ArrayList<>();

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
    class ItemViewHolder extends RecyclerView.ViewHolder {

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
            bookmark.setImageResource(R.drawable.ic_bookmark_off);
        }
    }
}
