package com.kola.kola_beta.main.data.follow;

public class FollowData {
    private int img_follow;
    private String name_follow;

    public FollowData(int img_follow, String name_follow) {
        this.img_follow = img_follow;
        this.name_follow = name_follow;
    }

    public int getImg_follow() {
        return img_follow;
    }

    public void setImg_follow(int img_follow) {
        this.img_follow = img_follow;
    }

    public String getName_follow() {
        return name_follow;
    }

    public void setName_follow(String name_follow) {
        this.name_follow = name_follow;
    }
}
