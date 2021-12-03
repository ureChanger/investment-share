package com.kola.kola_beta.main.data.publication;

import com.kola.kola_beta.main.data.dummy.DummyPost;
import com.kola.kola_beta.main.data.dummy.DummyUser;
import com.kola.kola_beta.main.data.post.PostData;
import com.kola.kola_beta.main.data.user.UserData;

import java.util.ArrayList;

public class PublicationData {
    private int id_publication;
    private String name;
    private ArrayList<Integer> id_contributors;
    private ArrayList<Integer> id_posts;
    private ArrayList<Integer> follower;
    private String description;

    public PublicationData(int id_publication,
                           String name, ArrayList<Integer> id_contributor, String description) {
        this.id_publication = id_publication;
        this.name = name;
        this.id_posts = new ArrayList<>();
        this.id_contributors = id_contributor;
        this.follower = new ArrayList<>();
        this.description = description;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getContributors() {
        return id_contributors;
    }

    public void setContributors(ArrayList<Integer> contributor) {
        this.id_contributors = contributor;
    }

    public void addContributor(Integer contributor) { this.id_contributors.add(contributor);}

    public void deleteContributor(Integer contributor) { this.id_contributors.remove(contributor);}

    public ArrayList<Integer> getId_posts() {
        return id_posts;
    }

    public void setId_posts(ArrayList<PostData> dummyPost, ArrayList<UserData> dummyUser){
        for(int i=0; i< id_contributors.size();i++){
            ArrayList<Integer> id_posts = dummyUser
                    .get(id_contributors.get(i))
                    .getIdPosts();

            for(int j=0; j<id_posts.size(); j++){
                PostData id_post = dummyPost.get(id_posts.get(j));
                Integer id_public = id_post.getId_public();
                if(id_public==id_publication){
                    this.id_posts.add(id_post.getId_post());
                }
            }
        }
    }

    public void addId_posts(Integer id_posts){ this.id_posts.add(id_posts); }

    public void deleteId_posts(Integer id_posts){ this.id_posts.remove(id_posts); }

    public ArrayList<Integer> getFollower() {
        return follower;
    }

    public void setFollower(ArrayList follower) {
        this.follower = follower;
    }

    public void addFollower(Integer follower){ this.follower.add(follower); }

    public void deleteFollower(Integer follower){ this.follower.remove(follower); }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
