package com.kola.kola_beta.main.data.post;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kola.kola_beta.R;
import com.kola.kola_beta.detail.DetailEditorActivity;
import com.kola.kola_beta.detail.DetailPostActivity;
import com.kola.kola_beta.detail.DetailPublicActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CustomViewHolder>{
    private ArrayList<PostData> arrayList;
    private int my_id = 0;
    // 업비트 API 연동할 부분
    private ArrayList<String> data_coin = new ArrayList<>(Arrays.asList("" +
            "비트코인", "이더리움", "리플"));

    public PostAdapter(ArrayList<PostData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PostAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_post_item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.CustomViewHolder holder, int position) {
        holder.iv_profile.setImageResource(arrayList.get(position).getProfile());
        holder.tv_name.setText(arrayList.get(position).getName_editor());
        holder.tv_publication.setText(arrayList.get(position).getName_publication());
        holder.iv_thumbnail.setImageResource(arrayList.get(position).getThumbnail());
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_subtitle.setText(arrayList.get(position).getSubtitle());

        // "추천"에 따른 변화
        if (arrayList.get(position).getNumliked().indexOf(my_id)!=-1){
            holder.tv_numliked.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.kola_blue));
            holder.iv_liked.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else {
            holder.tv_numliked.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.kola_black));
            holder.iv_liked.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
        holder.tv_numliked.setText(""+arrayList.get(position).getNumliked().size());

        // "투자 결정"에 따른 변화
        if(arrayList.get(position).getCopied_coin().equals("none")){
            holder.tv_copied.setText("투자 결정 없음");
        }else {
            holder.tv_copied.setText(arrayList.get(position).getCopied_coin()+" "+
                    arrayList.get(position).getCopied_decision()+" 결정");
        }
        holder.tv_numcopied.setText(arrayList.get(position).getNumcopied().size()+ "명이 따라했어요");

        // "따라하기 수"에 따른 변화
        if (arrayList.get(position).getNumcopied().indexOf(my_id)!=-1){
            holder.tv_copied.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.kola_blue));
            holder.tv_numcopied.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.kola_blue));
            holder.iv_copied.setImageResource(R.drawable.ic_baseline_check_circle_24);
        }else {
            holder.tv_copied.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.tv_numcopied.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.iv_copied.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
        }

        // "공개"에 따른 변화
        if (arrayList.get(position).getIsPrivated()==true){
            holder.iv_copied.setImageResource(R.drawable.ic_baseline_people_alt_24);
            holder.tv_copied.setText("친구에게만 투자 결정 공개");
            holder.tv_numcopied.setVisibility(View.INVISIBLE);
            holder.layout_copied.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }else {
            holder.tv_numcopied.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected LinearLayout layout_editor;
        protected de.hdodenhof.circleimageview.CircleImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_publication;
        protected LinearLayout layout_post;
        protected ImageView iv_thumbnail;
        protected TextView tv_title;
        protected TextView tv_subtitle;
        protected LinearLayout layout_liked;
        protected ImageView iv_liked;
        protected TextView tv_numliked;
        protected LinearLayout layout_copied;
        protected ImageView iv_copied;
        protected TextView tv_copied;
        protected TextView tv_numcopied;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.layout_editor = itemView.findViewById(R.id.layout_main_post_editor);
            this.iv_profile = itemView.findViewById(R.id.iv_main_post_profile);
            this.tv_name = itemView.findViewById(R.id.tv_main_post_name);
            this.tv_publication = itemView.findViewById(R.id.tv_main_post_public);
            this.layout_post = itemView.findViewById(R.id.layout_main_post_post);
            this.iv_thumbnail = itemView.findViewById(R.id.iv_main_post_thumbnail);
            this.tv_title = itemView.findViewById(R.id.tv_main_post_title);
            this.tv_subtitle = itemView.findViewById(R.id.tv_main_post_subtitle);
            this.layout_liked = itemView.findViewById(R.id.layout_main_post_liked);
            this.iv_liked = itemView.findViewById(R.id.iv_main_post_liked);
            this.tv_numliked = itemView.findViewById(R.id.tv_main_post_numliked);
            this.layout_copied = itemView.findViewById(R.id.layout_main_post_copied);
            this.iv_copied = itemView.findViewById(R.id.iv_main_post_copied);
            this.tv_copied = itemView.findViewById(R.id.tv_main_post_copied);
            this.tv_numcopied = itemView.findViewById(R.id.tv_main_post_numcopied);

            // "에디터 이름" 클릭 리스너 추가
            this.layout_editor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), DetailEditorActivity.class);
                    int id_editor = arrayList.get(currentPos).getId_editor();
                    intent.putExtra("id_editor", id_editor);
                    view.getContext().startActivity(intent);
                }
            });

            // "출판사 이름" 클릭 리스너 추가
            this.tv_publication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), DetailPublicActivity.class);
                    intent.putExtra("id_public", arrayList.get(currentPos).getId_public());
                    view.getContext().startActivity(intent);
                }
            });

            // "게시글" 클릭 리스너 추가
            this.layout_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), DetailPostActivity.class);
                    intent.putExtra("id_post", arrayList.get(currentPos).getId_post());
                    view.getContext().startActivity(intent);
                }
            });

            // "추천" 클릭리스너 추가
            this.layout_liked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos = getLayoutPosition();
                    ArrayList<Integer> arr_liked = arrayList.get(currentPos).getNumliked();
                    int size_liked = arr_liked.size();
                    int myidx_liked = arr_liked.indexOf(my_id);

                    if(myidx_liked==-1){
                        arr_liked.add(my_id);
                        tv_numliked.setText(""+(size_liked+1));
                        tv_numliked.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_blue));
                        iv_liked.setImageResource(R.drawable.ic_baseline_favorite_24);
                    }else {
                        arr_liked.remove(myidx_liked);
                        tv_numliked.setText(""+(size_liked-1));
                        tv_numliked.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_black));
                        iv_liked.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    }
                }
            });

            // "따라했어요" 클릭 리스너 추가

            this.layout_copied.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos = getAdapterPosition();
                    ArrayList<Integer> arr_copied = arrayList.get(currentPos).getNumcopied();
                    int size_copied = arr_copied.size();
                    int myidx_copied = arr_copied.indexOf(my_id);

                    if(myidx_copied==-1){
                        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
                        Date mDate = new Date(System.currentTimeMillis());
                        if(data_coin.indexOf(arrayList.get(currentPos).getCopied_coin())!=-1
                                || arrayList.get(currentPos).getCopied_coin().equals("none")){
                            String message = "24시간 이내 "+arrayList.get(currentPos).getCopied_coin()
                                    +" "+arrayList.get(currentPos).getCopied_decision()+" 확인"+"\n\nUpit API 거래 내역 확인\n"
                                    + "("+mFormat.format(mDate)+" 기준)";
                            if (!arrayList.get(currentPos).getCopied_coin().equals("none")){
                                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                            }
                            arr_copied.add(my_id);
                            tv_numcopied.setText((size_copied+1)+"명이 따라했어요");
                            tv_copied.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_blue));
                            tv_numcopied.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_blue));
                            iv_copied.setImageResource(R.drawable.ic_baseline_check_circle_24);
                        }else {
                            Toast.makeText(view.getContext(), arrayList.get(currentPos).getCopied_coin()
                                    +" "+arrayList.get(currentPos).getCopied_decision()+"를 하고 눌러주세요!"+"\n\nUpit API 거래 내역 확인\n"
                                    + "("+mFormat.format(mDate)+" 기준)", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        arr_copied.remove(myidx_copied);
                        tv_numcopied.setText((size_copied-1)+"명이 따라했어요");
                        tv_copied.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_black));
                        tv_numcopied.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_black));
                        iv_copied.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                    }
                }
            });
        }
    }
}
