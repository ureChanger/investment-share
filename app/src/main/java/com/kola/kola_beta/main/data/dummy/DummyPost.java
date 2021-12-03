package com.kola.kola_beta.main.data.dummy;

import com.kola.kola_beta.R;
import com.kola.kola_beta.main.data.post.PostData;

import java.util.ArrayList;
import java.util.Arrays;

public class DummyPost {
    private ArrayList<PostData> dummyPost;
    public DummyPost() {
        this.dummyPost = new ArrayList<>(
                Arrays.asList(
                        new PostData(
                                1,
                                0,
                                0,
                                R.drawable.ic_launcher_background,
                                "솔라나를 최초의 웹-스케일 블록체인으로 만들어준 8 가지 혁신",
                                "솔라나의 50,000 TPS 블록체인 네트워크를 가능하게 해준 혁신적인 기술들에 대해 알아봅시다",
                                new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)),
                                false,
                                "비트코인",
                                "매수",
                                new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)),
                                new ArrayList<String >(Arrays.asList("스택스"))
                        ),
                        new PostData(
                                1,
                                1,
                                0,
                                R.drawable.ic_launcher_background,
                                "솔라나를 최초의 웹-스케일 블록체인으로 만들어준 8 가지 혁신",
                                "솔라나의 50,000 TPS 블록체인 네트워크를 가능하게 해준 혁신적인 기술들에 대해 알아봅시다",
                                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
                                false,
                                "스택스",
                                "매수",
                                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
                                new ArrayList<String >(Arrays.asList("ALEX"))
                        ),
                        new PostData(
                                1,
                                2,
                                0,
                                R.drawable.ic_launcher_background,
                                "솔라나를 최초의 웹-스케일 블록체인으로 만들어준 8 가지 혁신",
                                "솔라나의 50,000 TPS 블록체인 네트워크를 가능하게 해준 혁신적인 기술들에 대해 알아봅시다",
                                new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)),
                                true,
                                "솔라나",
                                "매도",
                                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
                                new ArrayList<String >(Arrays.asList("스택스"))
                        ),
                        new PostData(
                                3,
                                3,
                                0,
                                R.drawable.ic_launcher_background,
                                "솔라나를 최초의 웹-스케일 블록체인으로 만들어준 8 가지 혁신",
                                "솔라나의 50,000 TPS 블록체인 네트워크를 가능하게 해준 혁신적인 기술들에 대해 알아봅시다",
                                new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)),
                                false,
                                "none",
                                "none",
                                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
                                new ArrayList<String >(Arrays.asList("비트코인"))
                        )
                )
        );
    }



    public ArrayList<PostData> getDummyPost() {
        return dummyPost;
    }

    public void setDummyPost(ArrayList<PostData> dummyPost) {
        this.dummyPost = dummyPost;
    }
}
