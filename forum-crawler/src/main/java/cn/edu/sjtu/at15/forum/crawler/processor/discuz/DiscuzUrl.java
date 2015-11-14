package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrl {
    private String baseUrl;

    public DiscuzUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isInner(String url) {
        return url.startsWith(baseUrl);
    }

    public boolean isThread(String url) {
        return false;
    }

    public boolean isList(String url) {
        return false;
    }
}
