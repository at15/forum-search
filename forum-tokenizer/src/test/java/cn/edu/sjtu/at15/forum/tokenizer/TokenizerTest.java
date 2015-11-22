package cn.edu.sjtu.at15.forum.tokenizer;

import com.hankcs.hanlp.seg.common.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by at15 on 15-11-22.
 */
public class TokenizerTest {
    @Test
    public void testSegment() {
        Tokenizer tokenizer = new Tokenizer();
        List<Term> terms = tokenizer.segment("我喜欢你");
        Assert.assertEquals("我", terms.get(0).word);
        Assert.assertEquals("我/r", terms.get(0).toString());
        Assert.assertEquals(0, terms.get(0).offset);
        Assert.assertEquals(1,terms.get(1).offset);
        Assert.assertEquals(3,terms.get(2).offset);

        terms = tokenizer.segment("你是个好人");
        Assert.assertEquals(0, terms.get(0).offset);
        Assert.assertEquals("你/r", terms.get(0).toString());
//        terms = tokenizer.segment("你好我好大家好");
    }
}
