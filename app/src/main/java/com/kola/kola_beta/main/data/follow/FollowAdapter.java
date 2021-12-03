package com.kola.kola_beta.main.data.follow;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kola.kola_beta.R;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.CustomViewHolder>{

    private ArrayList<FollowData> arrayList;
    private int isChecked;

    public FollowAdapter(ArrayList<FollowData> arrayList) {
        this.arrayList = arrayList;
        this.isChecked = 99999;
    }

    @NonNull
    @Override
    public FollowAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_follow_item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FollowAdapter.CustomViewHolder holder, int position) {
        holder.iv_follow.setImageResource(arrayList.get(position).getImg_follow());
        holder.tv_follow.setText(arrayList.get(position).getName_follow());
        if (isChecked == position){
            holder.iv_follow.setBorderWidth(5);
            holder.iv_follow.setBorderColor(ContextCompat
                    .getColor(holder.itemView.getContext(), R.color.kola_blue));
            holder.tv_follow.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.kola_blue));
            holder.tv_follow.setTypeface(holder.tv_follow.getTypeface(), Typeface.BOLD);
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

    private FollowAdapter.OnItemClickEventListener listener;

    public void setOnItemClickListener(FollowAdapter.OnItemClickEventListener listener){
        this.listener = listener;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected LinearLayout layout_follow;
        protected de.hdodenhof.circleimageview.CircleImageView iv_follow;
        protected TextView tv_follow;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layout_follow = itemView.findViewById(R.id.layout_follow);
            this.iv_follow = itemView.findViewById(R.id.iv_follow);
            this.tv_follow = itemView.findViewById(R.id.tv_follow);

            this.layout_follow.setOnClickListener(new View.OnClickListener() {
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
