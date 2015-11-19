package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.crawler.entity.ForumThread;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gpl on 15/11/17.
 */
public class JsonFilePiplineTest {
    @Test
    public void testWrite() throws IOException {
        ForumThread thread = new ForumThread();
        thread.setTitle("xiaoming");
        thread.setAuthor("xiaowang");
        thread.setViewCount(100);
        thread.setReplyCount(100);
        thread.setAuthorPost("this is some dummy content");
        JsonFilePipline pipline = new JsonFilePipline("data");
        pipline.write("a.baidu.com", thread);

        thread.setUrl("http://www.baidu.com");
        pipline.write(thread);
    }
}
