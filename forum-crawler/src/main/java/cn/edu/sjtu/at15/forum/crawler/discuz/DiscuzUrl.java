package cn.edu.sjtu.at15.forum.crawler.discuz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.sjtu.at15.forum.crawler.Url;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrl extends Url {
    public static final Pattern threadUrlPattern = Pattern.compile("/thread-(\\d+)-(\\d+)-(.*)");

    public DiscuzUrl(String baseUrl) {
        super(baseUrl);
    }

    public boolean isThread(String url) {
        return url.startsWith(baseUrl + "/thread-");
    }

    public boolean isMainThread(String url) {
        Matcher m = threadUrlPattern.matcher(url);
        if (!m.find() || m.group(2) == null) {
            return false;
        }
        String page = m.group(2);
        return page.equals("1");
    }

    public String getMainThreadUrl(String url) {
        Matcher m = threadUrlPattern.matcher(url);
        if (m.find()) {
            return baseUrl + "/thread-" + m.group(1) + "-1-" + m.group(3);

        } else {
            throw new IllegalArgumentException("not a valid sub thread url:" + url);
        }
    }
}
