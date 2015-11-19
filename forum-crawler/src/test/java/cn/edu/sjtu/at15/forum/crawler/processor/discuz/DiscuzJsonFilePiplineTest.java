package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import cn.edu.sjtu.at15.forum.crawler.discuz.JsonFilePipline;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gpl on 15/11/17.
 */
public class DiscuzJsonFilePiplineTest {
    @Test
    public void testWrite() throws IOException {
        DiscuzThread thread = new DiscuzThread();
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
