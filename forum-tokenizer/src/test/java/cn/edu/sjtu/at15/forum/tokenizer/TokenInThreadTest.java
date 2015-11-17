package cn.edu.sjtu.at15.forum.tokenizer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gpl on 15/11/17.
 */
public class TokenInThreadTest {
    @Test
    public void testAddTokens() {
        TokenInThread tokenInThread = new TokenInThread();
        Token t1 = new Token();
        Token t2 = new Token();
        t1.setTerm("a");
        t1.setRank(100);
        t1.setUrl("a.com");
        t1.setPosition(0);
        t2.setTerm("a");
        t2.setRank(300);
        t2.setUrl("a.com");
        t2.setPosition(1);
        tokenInThread.addToken(t1);
        tokenInThread.addToken(t2);
        Assert.assertEquals("a", tokenInThread.getTerm());
        Assert.assertEquals("a.com", tokenInThread.getUrl());
        Assert.assertEquals((long) 400, (long) tokenInThread.getRank());
        Assert.assertEquals((long) 0, (long) tokenInThread.getPositions().get(0));
        Assert.assertEquals((long) 1, (long) tokenInThread.getPositions().get(1));
        Assert.assertEquals("0,1", tokenInThread.getPositionsAsString());
        Assert.assertEquals("a.com;400;0,1",tokenInThread.toInfo());
    }
}
