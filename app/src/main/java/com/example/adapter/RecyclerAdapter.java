package com.example.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictonaryDTO.Sample;
import com.example.simpledictionary.R;

import java.util.LinkedList;
import java.util.List;

// RecyclerView의 Adapter를 구현하기 위해서 RecyclerView.Adapter를 상속받는다.
// RecyclerView는 추상클래스 즉, 해당 클래스의 추상메서드를 구현해야 한다.
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> implements Filterable {

    // adapter에 들어갈 리스트
    private List<Sample> sampleList = new LinkedList<>();
    private List<Sample> searchList = new LinkedList<>();

    private Context context;

    // Item의 클릭 상태를 저장할 array 객체
    // ItemViewHolder로 view를 재활용 할 때 UI에 변화를 줄 경우 다른 position의 item에 변화를 줄 수 있기 때문에 SparseBooleanArray의 item의 클릭 상태 저장
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    // 직전에 클릭했던 Item의 position
    private int prePosition = -1;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // LayoutInflater를 이용하여 item.xml을 inflate 한다.
        context = parent.getContext();
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

    // 외부에서 sampleList 추가
    public void setSampleList(List list) {
        sampleList.clear();
        sampleList.addAll(list);
    }

    // 외부에서 item을 삭제시킬 함수
    public void delItem(int index) {
        sampleList.remove(index);
    }

    // 외부에서 추가된 sampleList를 searchList에 채워넣는 함수
    public void setSearchList() {
        searchList.addAll(sampleList);
    }

    // 검색 : 내부 SQL에서 SELECT된 정보만 보여주는 함수
    public void selectByKeyword(List<Sample> select) {
        searchList.clear();
        searchList.addAll(select);
        notifyDataSetChanged();
    }

    // 검색 : Filterable interface를 구현하여 리사이클러 뷰 내부에서 item 필터
    @Override
    public Filter getFilter() {
        searchList.clear();
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String text = charSequence.toString();
                if(text.isEmpty()) {
                    searchList.addAll(sampleList);
                } else {
                    for (Sample name:sampleList) {
                        if (name.getName().toLowerCase().contains(text.toLowerCase())){
                            searchList.add(name);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = searchList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                searchList = (List<Sample>)filterResults.values;
                notifyDataSetChanged();
            }
        };
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
