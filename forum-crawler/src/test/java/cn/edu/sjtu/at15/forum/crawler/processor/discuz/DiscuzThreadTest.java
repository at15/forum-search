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

        thread.setUrl("http://www.baidu.com");
        thread.setTitle("xiaoming");
        thread.setAuthor("xiaowang");
        thread.setViewCount(100);
        thread.setReplyCount(100);
        thread.setAuthorPost("this is some dummy content");
        Assert.assertEquals(thread.isValid(), true);
    }
}
