package com.kola.kola_beta.main.data.user;

import java.util.ArrayList;

public class UserData {
    private int id_user;
    private String name;
    private int profile;
    private ArrayList<Integer> id_publication;
    private ArrayList<Integer> id_posts;
    private ArrayList<Integer> follower;
    private ArrayList<Integer> follow;
    private ArrayList<String> tag;
    private String description;
    private ArrayList<String> portfolio;

    public UserData(int id_user, String name, int profile, ArrayList<Integer> id_publication,
                    ArrayList<Integer> id_post, ArrayList<Integer> follow, ArrayList<String> tag,
                    String description, ArrayList<String> portfolio) {
        this.id_user = id_user;
        this.name = name;
        this.profile = profile;
        this.id_publication = id_publication;
        this.id_posts = id_post;
        this.follower = new ArrayList<>();
        this.follow = follow;
        this.tag = tag;
        this.description = description;
        this.portfolio = portfolio;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public ArrayList<Integer> getId_publication() {
        return id_publication;
    }

    public void setId_publication(ArrayList<Integer> id_publication) {
        this.id_publication = id_publication;
    }

    public void addId_publication(Integer id_publication){
        this.id_publication.add(id_publication);
    }

    public void deleteId_publication(Integer id_publication){
        this.id_publication.remove(id_publication);
    }

    public ArrayList<Integer> getIdPosts() {
        return id_posts;
    }

    public void setIdPosts(ArrayList<Integer> id_posts) {
        this.id_posts = id_posts;
    }

    public void addIdPosts(Integer id_posts){
        this.id_posts.add(id_posts);
    }

    public void deletePosts(Integer id_posts){
        this.id_posts.remove(id_posts);
    }

    public ArrayList<Integer> getFollow() {
        return follow;
    }

    public void setFollow(ArrayList<Integer> follow) {
        this.follow = follow;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    public ArrayList<Integer> getFollower() {
        return follower;
    }

    public void addFollower(int follower) {
        this.follower.add(follower);
    }

    public void deleteFollower(int follower) {
        this.follower.remove(follower);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArrayList<String> portfolio) {
        this.portfolio = portfolio;
    }
}
