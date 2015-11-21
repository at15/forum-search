package cn.edu.sjtu.at15.forum.common.entity;

/**
 * Created by at15 on 15-11-21.
 */
public class ForumMainThread extends ForumThread {
    protected Integer viewCount;
    protected Integer replyCount;

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
}
