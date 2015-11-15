package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gpl on 15/11/15.
 */
public class DiscuzThreadTest {
    @Test
    public void testIsValid() {
        DiscuzThread thread = new DiscuzThread();
        Assert.assertEquals(thread.isValid(), false);
    }
}
