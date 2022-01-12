package com.kola.kola_beta.main.data.post;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.kola.kola_beta.main.data.tag.TagAdapter;

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
        PostData post = arrayList.get(position);
        holder.iv_profile.setImageResource(post.getProfile());
        holder.tv_name.setText(post.getName_editor());
        holder.tv_publication.setText(post.getName_publication());
        if(post.getThumbnail()!=0){
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                    holder.itemView.getResources().getDisplayMetrics());
            holder.iv_thumbnail.getLayoutParams().height = height;
            holder.iv_thumbnail.setImageResource(post.getThumbnail());
        }else {
            holder.iv_thumbnail.getLayoutParams().height = 0;
        }
        holder.tv_title.setText(post.getTitle());
        holder.tv_subtitle.setText(post.getSubtitle());
        int myidx_liked = post.getNumliked().indexOf(my_id);
        int myidx_copied = post.getNumcopied().indexOf(my_id);
        holder.isIn_liked = (myidx_liked!=-1)? true: false;
        holder.isIn_copied = (myidx_copied!=-1)? true:false;

        // "추천"에 따른 변화
        change_liked(holder.itemView, holder.isIn_liked, post.getNumliked().size(),
                holder.iv_liked, holder.tv_numliked);

        // "따라하기 수"에 따른 변화
        change_copied(holder.itemView, post.getIsPrivated(), holder.isIn_copied,
                post.getCopied_coin(), post.getCopied_decision(), post.getNumcopied().size(),
                holder.iv_copied, holder.tv_copied, holder.tv_numcopied);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    // 추천 아이템 클릭 리스너 인터페이스 초기화
    public interface OnLikeItemClickEventListener{
        void onLikeItemClick(View view, int position);
    }

    private OnLikeItemClickEventListener listener_like;

    public void setOnLikeItemClickListener(OnLikeItemClickEventListener listener){
        this.listener_like = listener;
    }

    // 따라하기 아이템 클릭 리스너 인터페이스 초기화
    public interface OnCopyItemClickEventListener{
        void onCopyItemClick(View view, int position);
    }

    private OnCopyItemClickEventListener listener_copy;

    public void setOnCopytItemClickListener(OnCopyItemClickEventListener listener){
        this.listener_copy = listener;
    }

    // 1객체당 뷰홀더는 1번 생성
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
        protected Boolean isIn_liked;
        protected Boolean isIn_copied;

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
            this.isIn_liked = false;
            this.isIn_copied = false;

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
                    PostData current_post = arrayList.get(currentPos);
                    String url = current_post.getUrl();
                    if (!url.equals("none")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(current_post.getUrl()));
                        view.getContext().startActivity(intent);
                    }
                }
            });

            // "추천" 클릭리스너 추가
            this.layout_liked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener_like.onLikeItemClick(view, position);
                    }
                }
            });

            // "따라했어요" 클릭 리스너 추가
            this.layout_copied.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener_copy.onCopyItemClick(view, position);
                    }
                }
            });
        }
    }

    public void change_liked(View view, Boolean isIn, int num, ImageView iv, TextView tv){
        if (isIn){
            tv.setText(""+(num));
            tv.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_blue));
            iv.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else {
            tv.setText(""+(num));
            tv.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_black));
            iv.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
    }

    private void change_copied(View view, Boolean isPrivate, Boolean isIn, String coin, String decision, int num,
                               ImageView iv, TextView tv_copied, TextView tv_numcopied){
        if (isPrivate){
            iv.setImageResource(R.drawable.ic_baseline_people_alt_24);
            tv_copied.setTextColor(ContextCompat.getColor(view.getContext(), R.color.kola_black));
            tv_copied.setText("친구에게만 투자 결정 공개");
            tv_numcopied.setVisibility(View.INVISIBLE);
            return;
        }else {
            tv_numcopied.setVisibility(View.VISIBLE);
        }

        int img;
        int color;

        if (isIn){
             img = R.drawable.ic_baseline_check_circle_24;
             color = ContextCompat.getColor(view.getContext(), R.color.kola_blue);
        }else {
            img = R.drawable.ic_baseline_check_circle_outline_24;
            color = ContextCompat.getColor(view.getContext(), R.color.kola_black);
        }

        iv.setImageResource(img);
        tv_copied.setTextColor(color);
        tv_numcopied.setTextColor(color);

        String coin_decision = "";
        if (coin.equals("none")){
            coin_decision += "투자 결정 없음";
        }else {
            coin_decision += coin+" "+decision+" "+"결정";
        }
        tv_copied.setText(coin_decision);
        tv_numcopied.setText(num+"명이 따라했어요");
    }
}
