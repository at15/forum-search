package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.FileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-11-21.
 */
public class PaginationParserTest {
    @Test
    public void testPagination() {
        PaginationParser parser = new PaginationParser("<div>M</div>");
        Assert.assertEquals(false, parser.hasPagination());

        parser = new PaginationParser(FileUtils.readFileAsString("fixture/list.html", "GBK"));
        Assert.assertEquals(true, parser.hasPagination());

        String link = parser.getPageLinks().get(0);
        Assert.assertEquals("http://www.1point3acres.com/bbs/forum-197-2.html", link);
    }
}
