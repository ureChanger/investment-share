package com.kola.kola_beta.main.data.follow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kola.kola_beta.R;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.CustomViewHolder>{

    private ArrayList<FollowData> arrayList;

    public FollowAdapter(ArrayList<FollowData> arrayList) {
        this.arrayList = arrayList;
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


        holder.layout_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = holder.tv_follow.getText().toString();
                Toast.makeText(v.getContext(), name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
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
        }
    }
}
