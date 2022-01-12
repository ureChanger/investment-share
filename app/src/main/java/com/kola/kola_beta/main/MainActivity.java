package com.kola.kola_beta.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.kola.kola_beta.R;
import com.kola.kola_beta.main.data.publication.PublicationData;
import com.kola.kola_beta.main.data.user.UserData;
import com.kola.kola_beta.main.data.dummy.DummyPost;
import com.kola.kola_beta.main.data.dummy.DummyPublic;
import com.kola.kola_beta.main.data.dummy.DummyUser;
import com.kola.kola_beta.main.data.follow.FollowAdapter;
import com.kola.kola_beta.main.data.follow.FollowData;
import com.kola.kola_beta.main.data.post.PostAdapter;
import com.kola.kola_beta.main.data.post.PostData;
import com.kola.kola_beta.main.data.tag.TagAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity {
    private Integer my_user_id;
    private ArrayList<Integer> my_follow;
    private ArrayList<String> my_coin_upbit;
    private RecyclerView rv_tag, rv_follow, rv_post;
    private ArrayList<String> arr_tag;
    private ArrayList<FollowData> arr_follow;
    private ArrayList<PostData> arr_post, arr_current_post, arr_post_filter_tag, arr_post_filter_follow;
    private TagAdapter adapter_tag;
    private FollowAdapter adapter_follow;
    private PostAdapter adapter_post;
    private LinearLayoutManager lm_tag, lm_follow, lm_post;
    private ArrayList<UserData> dummyUser;
    private ArrayList<PublicationData> dummyPublic;
    private ArrayList<PostData> dummyPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 로그인한 유저(나)의 id
        my_user_id = 0;

        // 로그인한 유저(나)의 업비트 API 코인 거래 목록
        my_coin_upbit = new ArrayList<>(Arrays.asList("스택스", "솔라나"));

        // 더미 데이터 할당
        dummyUser = new DummyUser().getDummyUser();
        dummyPublic = new DummyPublic().getDummyPublic();
        dummyPost = new DummyPost().getDummyPost();

        // 외부 데이터 참조 입력
        for (int i=0; i<dummyPublic.size(); i++){
            dummyPublic.get(i).setId_posts(dummyPost, dummyUser);
        }
        for (int i=0; i<dummyPost.size(); i++){
            dummyPost.get(i).setName_editor(dummyUser);
            dummyPost.get(i).setProfile(dummyUser);
            dummyPost.get(i).setName_publication(dummyPublic);
        }

        // Post 배열 데이터 참조
        arr_post = dummyPost;
        arr_current_post = new ArrayList<>();
        arr_current_post.addAll(arr_post);

        // Tag 배열 데이터 참조
        arr_tag = dummyUser.get(my_user_id).getTag();

        // 팔로우 배열 데이터 할당
        arr_follow = new ArrayList<>();
        my_follow = dummyUser.get(my_user_id).getFollow();
        for (int i=0; i<my_follow.size(); i++){
            arr_follow.add(new FollowData(
                    my_follow.get(i),
                    dummyUser.get(my_follow.get(i)).getProfile(),
                    dummyUser.get(my_follow.get(i)).getName()
            ));
        }

        arr_post_filter_tag = new ArrayList<>();
        arr_post_filter_follow = new ArrayList<>();

        // 리사이클러뷰 할당
        rv_tag = findViewById(R.id.main_tag_listview);
        rv_follow = findViewById(R.id.main_follow_listview);
        rv_post = findViewById(R.id.main_post_listview);

        // layoutManager 초기화
        lm_tag = new LinearLayoutManager(getApplicationContext());
        lm_tag.setOrientation(LinearLayoutManager.HORIZONTAL);
        lm_follow = new LinearLayoutManager(getApplicationContext());
        lm_follow.setOrientation(LinearLayoutManager.HORIZONTAL);
        lm_post = new LinearLayoutManager(getApplicationContext());

        // tag, follow 리사이클러뷰 lm 설정
        rv_tag.setLayoutManager(lm_tag);
        rv_follow.setLayoutManager(lm_follow);
        rv_post.setLayoutManager(lm_post);

        // 리사이클러뷰 - 태그
        adapter_tag = new TagAdapter(arr_tag);
        // 아이템 클릭 이벤트 처리
        adapter_tag.setOnItemClickListener(new TagAdapter.OnItemClickEventListener(){
            @Override
            public void onItemClick(View view, int position) {
                arr_post_filter_tag.clear();

                String tag = arr_tag.get(position);

                for (int i=0; i<arr_post.size(); i++){
                    PostData current_post = arr_post.get(i);
                    if(current_post.getTag().indexOf(tag)!=-1){
                        arr_post_filter_tag.add(current_post);
                    }
                }

                arr_current_post.clear();
                if (adapter_tag.getIsChecked() != position){
                    arr_current_post.addAll(arr_post);
                }else {
                    adapter_follow.setIsChecked(99999);
                    arr_current_post.addAll(arr_post_filter_tag);
                }
                adapter_post.notifyDataSetChanged();
                rv_tag.setAdapter(adapter_tag);
                rv_follow.setAdapter(adapter_follow);
            }
        });
        rv_tag.setAdapter(adapter_tag);


        // 리사이클러뷰 - 팔로우
        adapter_follow = new FollowAdapter(arr_follow);
        rv_follow.setAdapter(adapter_follow);
        // 아이템 클릭 이벤트 처리
        adapter_follow.setOnItemClickListener(new FollowAdapter.OnItemClickEventListener() {
            @Override
            public void onItemClick(View view, int position) {
                arr_post_filter_follow.clear();

                for (int i=0; i<arr_post.size(); i++){
                    PostData current_post = arr_post.get(i);
                    if (current_post.getId_editor()==my_follow.get(position)){
                        arr_post_filter_follow.add(current_post);
                    }
                }

                arr_current_post.clear();
                if (adapter_follow.getIsChecked() != position){
                    arr_current_post.addAll(arr_post);
                }else {
                    adapter_tag.setIsChecked(99999);
                    arr_current_post.addAll(arr_post_filter_follow);
                }

                adapter_post.notifyDataSetChanged();
                rv_follow.setAdapter(adapter_follow);
                rv_tag.setAdapter(adapter_tag);
            }
        });


        // 리사이클러뷰 - 포스트
        adapter_post = new PostAdapter(arr_current_post);
        rv_post.setAdapter(adapter_post);
        // 아이템 클릭 이벤트 처리
        adapter_post.setOnLikeItemClickListener(new PostAdapter.OnLikeItemClickEventListener(){
            @Override
            public void onLikeItemClick(View view, int position) {
                int post_id = arr_current_post.get(position).getId_post();
                PostData current_post = arr_post.get(post_id);
                int myidx_liked = current_post.getNumliked().indexOf(my_user_id);
                Boolean isIn_liked = (myidx_liked!=-1)? true: false;

                if(isIn_liked){
                    current_post.getNumliked().remove(myidx_liked);
                }else {
                    current_post.getNumliked().add(my_user_id);
                }
                arr_current_post.set(position, arr_post.get(post_id));
                adapter_post.notifyDataSetChanged();
            }
        });

        adapter_post.setOnCopytItemClickListener(new PostAdapter.OnCopyItemClickEventListener() {
            @Override
            public void onCopyItemClick(View view, int position) {
                int post_id = arr_current_post.get(position).getId_post();
                PostData current_post = arr_post.get(post_id);
                String copied_coin = current_post.getCopied_coin();
                String copied_decision = current_post.getCopied_decision();

                int myidx_copied = current_post.getNumcopied().indexOf(my_user_id);
                Boolean isIn_copied = (myidx_copied!=-1)? true:false;

                if (current_post.getIsPrivated()){
                    return;
                }

                if (isIn_copied){
                    current_post.getNumcopied().remove(myidx_copied);
                }else {
                    if (copied_coin.equals("none")){
                    }else if (my_coin_upbit.indexOf(copied_coin)==-1){
                        Toast.makeText(getApplicationContext(), "업비트 API 연동 확인 실패 \uD83E\uDD26\u200D♂\n\n"
                                +"6시간 내 "+copied_coin+" "+copied_decision+"기록이\n"
                                +"있어야만 누를 수 있습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Toast.makeText(getApplicationContext(), "업비트 API 연동 확인 성공 ☺\n\n"
                                +"6시간 내 "+copied_coin+" "+copied_decision+"기록 확인", Toast.LENGTH_SHORT).show();
                    }

                    current_post.getNumcopied().add(my_user_id);
                }
                arr_current_post.set(position, arr_post.get(post_id));
                adapter_post.notifyDataSetChanged();
            }
        });
    }
}