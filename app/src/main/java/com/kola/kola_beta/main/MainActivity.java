package com.kola.kola_beta.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    private ArrayList<PostData> arr_post;
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

        // 데이터 할당
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
        arr_tag = dummyUser.get(my_user_id).getTag();
        adapter_tag = new TagAdapter(arr_tag);
        rv_tag.setAdapter(adapter_tag);

        // 리사이클러뷰 - 팔로우
        arr_follow = new ArrayList<>();
        my_follow = dummyUser.get(my_user_id).getFollow();
        for (int i=0; i<my_follow.size(); i++){
            arr_follow.add(new FollowData(
                    dummyUser.get(my_follow.get(i)).getProfile(),
                    dummyUser.get(my_follow.get(i)).getName()
            ));
        }
        adapter_follow = new FollowAdapter(arr_follow);
        rv_follow.setAdapter(adapter_follow);

        // 리사이클러뷰 - 포스트
        arr_post = new ArrayList<>();
        adapter_post = new PostAdapter(arr_post);
        rv_post.setAdapter(adapter_post);

        for(int i=0; i<dummyPost.size(); i++){
            arr_post.add(dummyPost.get(i));
        }

    }
}