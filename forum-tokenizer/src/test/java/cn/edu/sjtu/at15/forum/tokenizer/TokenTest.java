package cn.edu.sjtu.at15.forum.tokenizer;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by gpl on 15/11/17.
 */
public class TokenTest {
    @Test
    public void testToken() {
        Token t = new Token();
        t.setTerm("a");
        Assert.assertEquals("a", t.getTerm());
        Assert.assertEquals(false, t.isValid());
    }

    @Test
    public void testInfo() {
        Token t = new Token();
        t.setTerm("a");
        t.setUrl("a.com");
        t.setRank(100);
        t.setPosition(10);
        String s = t.toInfo();

        Assert.assertEquals("a.com;100;10", s);

        Token d = Token.fromInfo(s, "a");
        Assert.assertEquals(t.getUrl(), d.getUrl());
        Assert.assertEquals(t.getRank(), d.getRank());
        Assert.assertEquals(t.getPosition(), d.getPosition());
    }
}
