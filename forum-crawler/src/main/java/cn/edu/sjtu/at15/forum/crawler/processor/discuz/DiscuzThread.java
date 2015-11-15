package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

/**
 * Created by gpl on 15/11/15.
 * <p/>
 * pojo for discuz thread
 */
public class DiscuzThread {
    private String title;
    private Integer viewCount;
    private Integer replyCount;
    private String author;
    private String authorPost;

    public boolean isValid() {
        return (title != null) &&
                (viewCount != null) &&
                (replyCount != null) &&
                (author != null) &&
                (authorPost != null);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorPost() {
        return authorPost;
    }

    public void setAuthorPost(String authorPost) {
        this.authorPost = authorPost;
    }

    @Override
    public String toString() {
        return "\ntitle : " + getTitle() + "\n" +
                "author : " + getAuthor() + "\n" +
                "view count : " + getViewCount() + "\n" +
                "reply count : " + getReplyCount() + "\n" +
                "author post : " + getAuthorPost() + "\n";
    }
}
