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
    private ThreadParser subThreadParser;
    private MainThreadParser mainThreadParser;

    @Before
    public void setUp() {
        mainThreadContent = FileUtils.readFileAsString("fixture/thread-main.html", "GBK");
        subThreadContent = FileUtils.readFileAsString("fixture/thread-page-2.html", "GBK");
        subThreadParser = new ThreadParser(subThreadContent);
        mainThreadParser = new MainThreadParser(mainThreadContent);
    }

    @Test
    public void testParseTitle() {
        Assert.assertEquals("USC Viterbi School内转CS 的可行性求教！！", subThreadParser.getTitle());
        Assert.assertEquals(subThreadParser.getTitle(), mainThreadParser.getTitle());
    }

    @Test
    public void testAuthor() {
        Assert.assertEquals("ryanbia", mainThreadParser.getThread("dummy url").getAuthor());
        Assert.assertEquals(mainThreadParser.getPosts().get(0).getAuthor(),
                mainThreadParser.getThread("dummy").getAuthor());
    }
}
