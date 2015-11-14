package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrlTest {
    private final DiscuzUrl discuzUrl = new DiscuzUrl("http://www.1point3acres.com/bbs/");

    @Test
    public void testRemoveTrailingSlash() {
        Assert.assertEquals(discuzUrl.getBaseUrl(), "http://www.1point3acres.com/bbs");
    }

    @Test
    public void testInner() {
        Assert.assertEquals(discuzUrl.isInner("http://www.baidu.com/ads/123"), false);
        Assert.assertEquals(discuzUrl.isInner("http://www.1point3acres.com/bbs/forum.php"), true);
    }

    @Test
    public void testThreadUrl() {
        Assert.assertEquals(discuzUrl.isThread("aaaa"), false);
        Assert.assertEquals(discuzUrl
                .isThread("http://www.1point3acres.com/bbs/thread-147140-1-1.html"), true);
    }
}
