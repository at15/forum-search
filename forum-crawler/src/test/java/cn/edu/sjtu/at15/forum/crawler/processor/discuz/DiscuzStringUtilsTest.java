package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import org.junit.Assert;
import org.junit.Test;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/**
 * Created by gpl on 15/11/15.
 */
public class DiscuzStringUtilsTest {
    @Test
    public void testStripTags() {
        String p = "<span id=\"thread_subject\">新人攒分 - 最简要，提纲版</span>";
        Assert.assertEquals(DiscuzStringUtils.stripTags(p), "新人攒分 - 最简要，提纲版");
    }

    @Test
    public void testGetMaxPage() {
        String p = "<span title=\"共 12 页\"> / 12 页</span>";
//        Pattern pattern = Pattern.compile("(.*)\\s(\\d+)\\s(.*)");
//        Matcher m = pattern.matcher(p);
//        if (m.find()) {
//            System.out.println(m.group());
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//        } else {
//            System.out.println("not found");
//        }
        Assert.assertEquals((long) DiscuzStringUtils.getMaxPage(p), 12);
        Assert.assertEquals((long) DiscuzStringUtils.getMaxPage("aaa"), 0);
    }
}