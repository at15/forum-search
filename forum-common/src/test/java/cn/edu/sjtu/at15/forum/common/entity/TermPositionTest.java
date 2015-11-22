package cn.edu.sjtu.at15.forum.common.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by at15 on 15-11-22.
 */
public class TermPositionTest {
    @Test
    public void testIsTitle() {
        Assert.assertEquals((long) -1, (long) TermPosition.TITLE);
        TermPosition a = new TermPosition(-1, 10);
        Assert.assertEquals(true, a.isTitle());
        TermPosition b = new TermPosition(0, 123);
        Assert.assertEquals(false, b.isTitle());
    }

    @Test
    public void testJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TermPosition t = new TermPosition(-1, 10);
        System.out.println(mapper.writeValueAsString(t));
        Assert.assertEquals(false, mapper.writeValueAsString(t).contains("title"));
    }
}
