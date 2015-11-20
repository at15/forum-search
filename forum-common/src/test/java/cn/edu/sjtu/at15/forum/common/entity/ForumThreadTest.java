package cn.edu.sjtu.at15.forum.common.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-11-20.
 */
public class ForumThreadTest {
    @Test
    public void testIsValid() {
        ForumThread thread = new ForumThread();
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
