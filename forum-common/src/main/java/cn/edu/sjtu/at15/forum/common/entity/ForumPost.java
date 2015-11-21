package cn.edu.sjtu.at15.forum.common.entity;

/**
 * Created by at15 on 15-11-21.
 * <p/>
 * ForumPost is the basic block in a thread, including author's post and all the comments
 */
public class ForumPost {
    protected String author;
    protected String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
