package cn.edu.sjtu.at15.forum.crawler.discuz;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gpl on 15/11/14.
 */
public class UrlTest {
    private final Url url = new Url("http://www.1point3acres.com/bbs/");

    @Test
    public void testRemoveTrailingSlash() {
        Assert.assertEquals(url.getBaseUrl(), "http://www.1point3acres.com/bbs");
    }

    @Test
    public void testIsInnerLink() {
        Assert.assertEquals(url.isInnerLink("http://www.baidu.com/ads/123"), false);
        Assert.assertEquals(url.isInnerLink("http://www.1point3acres.com/bbs/forum.php"), true);
    }

    @Test
    public void testThreadUrl() {
        Assert.assertEquals(url.isThread("aaaa"), false);
        Assert.assertEquals(url
                .isThread("http://www.1point3acres.com/bbs/thread-147140-1-1.html"), true);
    }

    @Test
    public void testList() {
        Assert.assertEquals(url
                .isList("http://www.1point3acres.com/bbs/forum.php?mod=guide&view=hot"), true);
        Assert.assertEquals(url
                .isList("http://www.1point3acres.com/bbs/come_on_baby_lets_xx"), false);
    }
}
