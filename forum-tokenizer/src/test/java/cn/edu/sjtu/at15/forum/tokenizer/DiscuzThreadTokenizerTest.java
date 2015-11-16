package cn.edu.sjtu.at15.forum.tokenizer;

import com.hankcs.hanlp.seg.common.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by gpl on 15/11/17.
 */
public class DiscuzThreadTokenizerTest {
    @Test
    public void testSegment() {
        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
        List<Term> terms = tokenizer.segment("我喜欢你");
        Assert.assertEquals("我",terms.get(0).word);
        Assert.assertEquals("我/r", terms.get(0).toString());
        terms = tokenizer.segment("你是个好人");
        Assert.assertEquals("你/r", terms.get(0).toString());
    }
}
