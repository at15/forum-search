package cn.edu.sjtu.at15.forum.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by at15 on 15-11-21.
 */
public class ForumMainThread extends ForumThread {
    protected Integer viewCount;
    protected Integer replyCount;

    // must have a empty constructor in order deserialize from json
    public ForumMainThread() {

    }

    public ForumMainThread(ForumThread thread) {
        setUrl(thread.getUrl());
        setTitle(thread.getTitle());
        setPosts(thread.getPosts());
    }

    // for main thread, it's url and main thread url is the same
    @Override
    public void setUrl(String url) {
        this.url = url;
        this.mainThreadUrl = url;
    }

    @Override
    public void setMainThreadUrl(String mainThreadUrl) {
        setUrl(mainThreadUrl);
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    // the first post in main thread is author post
    @JsonIgnore
    public ForumPost getAuthorPost() {
        return getPosts().get(0);
    }

    @JsonIgnore
    public String getAuthor() {
        return getAuthorPost().getAuthor();
    }
}
