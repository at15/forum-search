package cn.edu.sjtu.at15.forum.crawler.discuz;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by at15 on 15-11-20.
 */
public class ListParserTest {
    @Test
    public void testGetMaxPage() {
        String p = "<div class=\"pg\"><strong>1</strong><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=2\">2</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=3\">3</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=4\">4</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=5\">5</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=6\">6</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=7\">7</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=8\">8</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=9\">9</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=10\">10</a><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=12\" class=\"last\">... 12</a><label><input type=\"text\" name=\"custompage\" class=\"px\" size=\"2\" title=\"输入页码，按回车快速跳转\" value=\"1\" onkeydown=\"if(event.keyCode==13) {window.location='forum.php?mod=guide&amp;view=hot&amp;page='+this.value;; doane(event);}\"><span title=\"共 12 页\"> / 12 页</span></label><a href=\"http://www.1point3acres.com/bbs/forum.php?mod=guide&amp;view=hot&amp;page=2\" class=\"nxt\">下一页</a></div>";
        ListParser listParser = new ListParser(p);
        Assert.assertEquals(12, (long) listParser.getMaxPage());
        ListParser f = new ListParser("aaa");
        Assert.assertEquals(0, (long) f.getMaxPage());
    }

// example for use regexp in java
//        Pattern pattern = Pattern.compile("(.*)\\s(\\d+)\\s(.*)");
//        Matcher m = pattern.matcher(p);
//        if (m.find()) {
//            System.out.println(m.group());
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//        } else {
//            System.out.println("not found");
//        }
}
