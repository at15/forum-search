package cn.edu.sjtu.at15.forum.common.entity;

import org.junit.Assert;
import org.junit.Test;

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
}
