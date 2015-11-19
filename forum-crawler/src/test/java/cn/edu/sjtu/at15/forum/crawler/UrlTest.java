package cn.edu.sjtu.at15.forum.crawler;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-11-20.
 */
public class UrlTest {
    private final Url url = new Url("http://www.1point3acres.com/bbs/");

    @Test
    public void testRemoveTrailingSlash() {
        Assert.assertEquals("http://www.1point3acres.com/bbs", url.getBaseUrl());
    }
}
