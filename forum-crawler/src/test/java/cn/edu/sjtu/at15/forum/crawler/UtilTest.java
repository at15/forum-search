package cn.edu.sjtu.at15.forum.crawler;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-11-20.
 */
public class UtilTest {

    @Test
    public void testStripTags() {
        String p = "<span id=\"thread_subject\">新人攒分 - 最简要，提纲版</span>";
        Assert.assertEquals("新人攒分 - 最简要，提纲版", Util.stripTags(p));
    }
}
