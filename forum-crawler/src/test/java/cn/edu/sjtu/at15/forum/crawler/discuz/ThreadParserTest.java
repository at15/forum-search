package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by at15 on 15-11-21.
 */
public class ThreadParserTest {
    private String mainThreadContent;
    private String subThreadContent;

    @Before
    public void setUp() {
        mainThreadContent = FileUtils.readFileAsString("fixture/thread-main.html", "GBK");
        subThreadContent = FileUtils.readFileAsString("fixture/thread-page-2.html", "GBK");
    }

    @Test
    public void testParseTitle() {
        ThreadParser parser = new ThreadParser(subThreadContent);
        Assert.assertEquals("USC Viterbi School内转CS 的可行性求教！！", parser.getTitle());

        MainThreadParser mainThreadParser = new MainThreadParser(mainThreadContent);
        Assert.assertEquals(parser.getTitle(),mainThreadParser.getTitle());

    }
}
