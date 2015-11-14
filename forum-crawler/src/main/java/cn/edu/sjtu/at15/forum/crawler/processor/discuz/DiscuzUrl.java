package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import org.apache.commons.lang.StringUtils;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrl {
    private String baseUrl;

    public DiscuzUrl(String baseUrl) {
        this.baseUrl = StringUtils.chomp(baseUrl, "/");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isInner(String url) {
        return url.startsWith(baseUrl);
    }

    public boolean isThread(String url) {
        return url.startsWith(baseUrl + "/thread-");
    }

    public boolean isList(String url) {
        return false;
    }
}
