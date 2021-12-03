package com.kola.kola_beta.main.data.tag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kola.kola_beta.R;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.CustomViewHolder>{

    private ArrayList<String> arrayList;

    public TagAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
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

        holder.tv_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = holder.tv_tag.getText().toString();
                Toast.makeText(v.getContext(), tag, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected android.widget.Button tv_tag;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_tag = itemView.findViewById(R.id.btn_tag);
        }
    }
}
