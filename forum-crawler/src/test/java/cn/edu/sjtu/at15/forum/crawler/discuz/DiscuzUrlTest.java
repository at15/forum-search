package cn.edu.sjtu.at15.forum.crawler.discuz;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrlTest {
    private final DiscuzUrl discuzUrl = new DiscuzUrl("http://www.1point3acres.com/bbs/");

    @Test
    public void testRemoveTrailingSlash() {
        Assert.assertEquals("http://www.1point3acres.com/bbs", discuzUrl.getBaseUrl());
    }

    @Test
    public void testIsInnerLink() {
        Assert.assertEquals(false, discuzUrl.isInnerLink("http://www.baidu.com/ads/123"));
        Assert.assertEquals(true, discuzUrl.isInnerLink("http://www.1point3acres.com/bbs/forum.php"));
    }

    @Test
    public void testThreadUrl() {
        String mainThreadUrl = "http://www.1point3acres.com/bbs/thread-147140-1-1.html";
        String subThreadUrl = "http://www.1point3acres.com/bbs/thread-147140-2-1.html";

        Assert.assertEquals(false, discuzUrl.isThread("aaaa"));
        Assert.assertEquals(true, discuzUrl
                .isThread(mainThreadUrl));
        Assert.assertEquals(true, discuzUrl
                .isMainThread(mainThreadUrl));
        Assert.assertEquals(false, discuzUrl
                .isMainThread(subThreadUrl));
    }

    @Test
    public void testList() {
        Assert.assertEquals(true, discuzUrl
                .isList("http://www.1point3acres.com/bbs/forum.php?mod=guide&view=hot"));
        Assert.assertEquals(false, discuzUrl
                .isList("http://www.1point3acres.com/bbs/come_on_baby_lets_xx"));
    }

    @Test
    public void testRegexp() {
//        String p = "http://www.1point3acres.com/bbs/thread-147140-1-1.html";
        String p = "http://www.1point3acres.com/bbs/thread-147140-2-1.html";
        Pattern pattern = Pattern.compile("/thread-(\\d+)-1-(\\d+)");
        Matcher m = pattern.matcher(p);
        if (m.find()) {
            System.out.println("got");
        } else {
            System.out.println("not found");
        }
    }
}
