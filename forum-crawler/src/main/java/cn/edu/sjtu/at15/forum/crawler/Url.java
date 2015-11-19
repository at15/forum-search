package cn.edu.sjtu.at15.forum.crawler;

import cn.edu.sjtu.at15.forum.common.StringUtils;

/**
 * Created by at15 on 15-11-19.
 */
public class Url {
    protected String baseUrl;

    public Url(String baseUrl) {
        this.baseUrl = StringUtils.trimLastSlash(baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isInnerLink(String url) {
        return url.startsWith(baseUrl);
    }
}
