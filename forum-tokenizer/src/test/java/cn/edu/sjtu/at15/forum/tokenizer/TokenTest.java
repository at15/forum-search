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
        Assert.assertEquals(false,t.isValid());
    }
}
