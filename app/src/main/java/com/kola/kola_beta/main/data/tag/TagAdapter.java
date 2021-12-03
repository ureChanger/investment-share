package com.kola.kola_beta.main.data.tag;

import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kola.kola_beta.R;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.CustomViewHolder>{

    private ArrayList<String> arrayList;
    private int isChecked;

    public TagAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.isChecked = 99999;
    }

    @NonNull
    @Override
    public TagAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_tag_item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapter.CustomViewHolder holder, int position) {
        holder.tv_tag.setText(arrayList.get(position));
        if (isChecked == position){
            holder.tv_tag.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.kola_blue));
            holder.tv_tag.setTypeface(holder.tv_tag.getTypeface(), Typeface.BOLD);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    // 아이템 클릭 리스너 인터페이스 초기화
    public interface OnItemClickEventListener{
        void onItemClick(View view, int position);
    }

    private OnItemClickEventListener listener;

    public void setOnItemClickListener(OnItemClickEventListener listener){
        this.listener = listener;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected android.widget.Button tv_tag;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_tag = itemView.findViewById(R.id.btn_tag);

            this.tv_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if (isChecked == position){
                        isChecked = 99999;
                    }else {
                        isChecked = position;
                    }
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(view, position);
                    }
                }
            });
        }
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
