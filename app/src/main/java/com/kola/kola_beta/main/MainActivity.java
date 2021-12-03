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

public class MainActivity extends AppCompatActivity {
    private Integer my_user_id;
    private ArrayList<Integer> my_follow;
    private RecyclerView rv_tag, rv_follow, rv_post;
    private ArrayList<String> arr_tag;
    private ArrayList<FollowData> arr_follow;
    private ArrayList<PostData> arr_post, arr_current_post;
    private ArrayList<ArrayList<PostData>> arr_post_filter_tag, arr_post_filter_follow;
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

        // 더미 데이터 할당
        dummyUser = new DummyUser().getDummyUser();
        dummyPublic = new DummyPublic().getDummyPublic();
        dummyPost = new DummyPost().getDummyPost();

        // Post 배열 데이터 참조
        arr_post = dummyPost;
        arr_current_post = new ArrayList<>();
        arr_current_post.addAll(arr_post);

        // Tag 배열 데이터 참조
        arr_tag = dummyUser.get(my_user_id).getTag();

        // 태그 필터 기준 데이터 초기화
        arr_post_filter_tag = new ArrayList<>();
        for(int i=0; i<arr_tag.size(); i++){
            arr_post_filter_tag.add(new ArrayList<>());
            for(int j=0; j<arr_post.size(); j++){
                if (arr_post.get(j).getTag().indexOf(arr_tag.get(i))!=-1){
                    arr_post_filter_tag.get(i).add(arr_post.get(j));
                }
            }
        }

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

        // 팔로우 필터 기준 데이터 초기화
        arr_post_filter_follow = new ArrayList<>();
        for (int i=0; i<arr_follow.size(); i++){
            arr_post_filter_follow.add(new ArrayList<>());
        }

        for(int i=0; i<arr_post.size(); i++){
            int id_editor = arr_post.get(i).getId_editor();
            int idx = my_follow.indexOf(id_editor);
            if(idx!=-1){
                arr_post_filter_follow.get(idx)
                        .add(arr_post.get(i));
            }
        }

        // 외부 데이터 참조 입력
        for (int i=0; i<dummyPublic.size(); i++){
            dummyPublic.get(i).setId_posts(dummyPost, dummyUser);
        }
        for (int i=0; i<dummyPost.size(); i++){
            dummyPost.get(i).setName_editor(dummyUser);
            dummyPost.get(i).setProfile(dummyUser);
            dummyPost.get(i).setName_publication(dummyPublic);
        }

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
                arr_current_post.clear();
                if (adapter_tag.getIsChecked() != position){
                    arr_current_post.addAll(arr_post);
                }else {
                    adapter_follow.setIsChecked(99999);
                    arr_current_post.addAll(arr_post_filter_tag.get(position));
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
                arr_current_post.clear();
                if (adapter_follow.getIsChecked() != position){
                    arr_current_post.addAll(arr_post);
                }else {
                    adapter_tag.setIsChecked(99999);
                    arr_current_post.addAll(arr_post_filter_follow.get(position));
                }
                adapter_post.notifyDataSetChanged();
                rv_follow.setAdapter(adapter_follow);
                rv_tag.setAdapter(adapter_tag);
            }
        });

        // 리사이클러뷰 - 포스트
        adapter_post = new PostAdapter(arr_current_post);
        rv_post.setAdapter(adapter_post);

    }
}