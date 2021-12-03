package com.kola.kola_beta.main.data.post;

import com.kola.kola_beta.main.data.dummy.DummyPublic;
import com.kola.kola_beta.main.data.publication.PublicationData;
import com.kola.kola_beta.main.data.user.UserData;

import java.util.ArrayList;

public class PostData {
    private int id_editor;
    private int id_post;
    private int id_public;
    private int profile;
    private String name_editor;
    private String name_publication;
    private int thumbnail;
    private String title;
    private String subtitle;
    private ArrayList<Integer> numliked;
    private Boolean isPrivated;
    private String copied_coin;
    private String copied_decision;
    private ArrayList<Integer> numcopied;
    private ArrayList<String> tag;

    public PostData(int id_editor, int id_post, int id_public, int thumbnail, String title,
                    String subtitle, ArrayList<Integer> numliked, Boolean isPrivated,
                    String copied_coin, String copied_decision, ArrayList<Integer> numcopied,
                    ArrayList<String> tag) {
        this.id_editor = id_editor;
        this.id_post = id_post;
        this.id_public = id_public;
        this.thumbnail = thumbnail;
        this.title = title;
        this.subtitle = subtitle;
        this.numliked = numliked;
        this.isPrivated = isPrivated;
        this.copied_coin = copied_coin;
        this.copied_decision = copied_decision;
        this.numcopied = numcopied;
        this.tag = tag;
    }

    public int getId_editor() {
        return id_editor;
    }

    public void setId_editor(int id_editor) {
        this.id_editor = id_editor;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_public() {
        return id_public;
    }

    public void setId_public(int id_public) {
        this.id_public = id_public;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(ArrayList<UserData> userData) {
        this.profile = userData.get(id_editor).getProfile();
    }

    public String getName_editor() {
        return name_editor;
    }

    public void setName_editor(ArrayList<UserData> userData) {
        this.name_editor = userData.get(id_editor).getName();
    }

    public String getName_publication() {
        return name_publication;
    }

    public void setName_publication(ArrayList<PublicationData> publicationData) {
        this.name_publication = publicationData.get(id_public).getName();
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public ArrayList<Integer> getNumliked() {
        return numliked;
    }

    public void setNumliked(ArrayList<Integer> numliked) {
        this.numliked = numliked;
    }

    public Boolean getIsPrivated() {
        return isPrivated;
    }

    public void setIsPrivated(Boolean isPrivated) {
        this.isPrivated = isPrivated;
    }

    public String getCopied_coin() {
        return copied_coin;
    }

    public void setCopied_coin(String copied_coin) {
        this.copied_coin = copied_coin;
    }

    public String getCopied_decision() {
        return copied_decision;
    }

    public void setCopied_decision(String copied_decision) {
        this.copied_decision = copied_decision;
    }

    public ArrayList<Integer> getNumcopied() {
        return numcopied;
    }

    public void setNumcopied(ArrayList<Integer> numcopied) {
        this.numcopied = numcopied;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }
}
