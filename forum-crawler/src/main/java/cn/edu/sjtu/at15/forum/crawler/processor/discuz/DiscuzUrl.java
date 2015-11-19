package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import cn.edu.sjtu.at15.forum.common.StringUtils;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrl {
    private String baseUrl;

    public DiscuzUrl(String baseUrl) {
        this.baseUrl = StringUtils.trimLastSlash(baseUrl);
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

    // FIXME: this only works for one url
    public boolean isList(String url) {
        return url.startsWith(baseUrl + "/forum.php?mod=guide");
    }
}
