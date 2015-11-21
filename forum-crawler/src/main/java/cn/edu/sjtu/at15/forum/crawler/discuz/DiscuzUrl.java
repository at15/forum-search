package cn.edu.sjtu.at15.forum.crawler.discuz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.sjtu.at15.forum.crawler.Url;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrl extends Url {
    private static final Pattern mainThreadPattern = Pattern.compile("/thread-(\\d+)-1-(\\d+)");

    public DiscuzUrl(String baseUrl) {
        super(baseUrl);
    }

    // FIXME: http://www.1point3acres.com/bbs/thread-146437-5-1.html is not the first page for thread.
    // it's the comment page, so the parser will got error.
    public boolean isThread(String url) {
        return url.startsWith(baseUrl + "/thread-");
    }

    public boolean isMainThread(String url) {
        Matcher m = mainThreadPattern.matcher(url);
        return m.find();
    }

    // FIXME: this only works for one url
    public boolean isList(String url) {
        return url.startsWith(baseUrl + "/forum.php?mod=guide");
    }
}
