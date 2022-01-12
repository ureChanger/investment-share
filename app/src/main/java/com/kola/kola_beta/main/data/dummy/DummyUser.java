package com.kola.kola_beta.main.data.dummy;

import com.kola.kola_beta.R;
import com.kola.kola_beta.main.data.user.UserData;

import java.util.ArrayList;
import java.util.Arrays;

public class DummyUser {
    private ArrayList<UserData> dummyUser;
    public DummyUser() {
        this.dummyUser = new ArrayList<>(
                Arrays.asList(
                        new UserData(
                                0,
                                "GM Chung",
                                R.drawable.ic_launcher_foreground,
                                // 소속된 Publication id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 포스팅한 Post id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 팔로우
                                new ArrayList<>(
                                        Arrays.asList(
                                                1, 2, 3
                                        )
                                ),
                                new ArrayList<>(
                                        Arrays.asList(
                                                "스택스", "솔라나", "비트코인"
                                        )
                                ),
                                "Medium member since June 2021·Editor of DeSpread Creative, " +
                                        "솔라나 한국, 스택스 한국 블로그, and 팬텀 파운데이션",
                                new ArrayList<>(
                                        Arrays.asList(
                                                "비트코인,30",
                                                "이더리움,20",
                                                "리플,50"
                                        )
                                )
                        ),
                        new UserData(
                                1,
                                "GM Chung",
                                R.drawable.drawable_user_0,
                                // 소속된 Publication id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 포스팅한 Post id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 팔로우
                                new ArrayList<>(
                                        Arrays.asList(
                                                1, 3
                                        )
                                ),
                                new ArrayList<>(
                                        Arrays.asList(
                                                "스택스", "ALEX", "비트코인"
                                        )
                                ),
                                "Medium member since June 2021·Editor of DeSpread Creative, " +
                                        "솔라나 한국, 스택스 한국 블로그, and 팬텀 파운데이션",
                                new ArrayList<>(
                                        Arrays.asList(
                                                "비트코인,30",
                                                "이더리움,20",
                                                "리플,50"
                                        )
                                )
                        ),
                        new UserData(
                                2,
                                "GM_Chung",
                                R.drawable.ic_launcher_foreground,
                                // 소속된 Publication id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 포스팅한 Post id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 팔로우
                                new ArrayList<>(
                                        Arrays.asList(
                                                1, 3
                                        )
                                ),
                                new ArrayList<>(
                                        Arrays.asList(
                                                "스택스", "ALEX", "비트코인"
                                        )
                                ),
                                "Medium member since June 2021·Editor of DeSpread Creative, " +
                                        "솔라나 한국, 스택스 한국 블로그, and 팬텀 파운데이션",
                                new ArrayList<>(
                                        Arrays.asList(
                                                "비트코인,30",
                                                "이더리움,20",
                                                "리플,50"
                                        )
                                )
                        ),
                        new UserData(
                                3,
                                "GM_Chung",
                                R.drawable.ic_launcher_foreground,
                                // 소속된 Publication id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 포스팅한 Post id
                                new ArrayList<>(
                                        Arrays.asList(
                                                0, 1, 2
                                        )
                                ),
                                // 팔로우
                                new ArrayList<>(
                                        Arrays.asList(
                                                1, 0, 2
                                        )
                                ),
                                new ArrayList<>(
                                        Arrays.asList(
                                                "스택스", "ALEX", "비트코인"
                                        )
                                ),
                                "Medium member since June 2021·Editor of DeSpread Creative, " +
                                        "솔라나 한국, 스택스 한국 블로그, and 팬텀 파운데이션",
                                new ArrayList<>(
                                        Arrays.asList(
                                                "비트코인,30",
                                                "이더리움,20",
                                                "리플,50"
                                        )
                                )
                        )
                )
        );
    }

    public ArrayList<UserData> getDummyUser() {
        return dummyUser;
    }

    public void setDummyUser(ArrayList<UserData> dummyUser) {
        this.dummyUser = dummyUser;
    }
}
