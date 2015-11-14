package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzUrlTest {
    @Test
    public void testThreadUrl() {
        DiscuzUrl discuzUrl = new DiscuzUrl();
        Assert.assertEquals(discuzUrl.isThread("aaaa"), false);
        Assert.assertEquals(discuzUrl.isList("aaaa"), false);
    }
}
