package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by at15 on 15-11-21.
 */
public class ThreadListParserTest {
    private String listContent;
    private String mainThreadContent;
    private String baseUrl = "http://www.1point3acres.com/bbs/";

    @Before
    public void setUp() {
        listContent = FileUtils.readFileAsString("fixture/list.html", "GBK");
        mainThreadContent = FileUtils.readFileAsString("fixture/thread-main.html", "GBK");
    }

    @Test
    public void testParse() {
        ThreadListParser threadListParser = new ThreadListParser(listContent, baseUrl);
        Assert.assertEquals(true, threadListParser.hasThreads());

        threadListParser = new ThreadListParser(mainThreadContent,baseUrl);
        Assert.assertEquals(false,threadListParser.hasThreads());

    }
}
