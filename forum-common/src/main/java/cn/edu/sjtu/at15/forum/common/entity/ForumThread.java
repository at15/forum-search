package cn.edu.sjtu.at15.forum.common.entity;

import java.util.List;

/**
 * Created by at15 on 15-11-19.
 *
 * @TODO allow store comment
 */
public class ForumThread {
    protected String url;
    protected String mainThreadUrl;
    protected String title;
    protected List<ForumPost> posts;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMainThreadUrl() {
        return mainThreadUrl;
    }

    public void setMainThreadUrl(String mainThreadUrl) {
        this.mainThreadUrl = mainThreadUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<ForumPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ForumPost> posts) {
        this.posts = posts;
    }

//    @Override
//    public String toString() {
//        return "\ntitle : " + getTitle() + "\n" +
//                "author : " + getAuthor() + "\n" +
//                "view count : " + getViewCount() + "\n" +
//                "reply count : " + getReplyCount() + "\n" +
//                "author post : " + getAuthorPost() + "\n";
//    }
}
