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
        Assert.assertEquals("我", terms.get(0).word);
        Assert.assertEquals("我/r", terms.get(0).toString());
        terms = tokenizer.segment("你是个好人");
        Assert.assertEquals(0, terms.get(0).offset);
        Assert.assertEquals("你/r", terms.get(0).toString());
        terms = tokenizer.segment("你好我好大家好");
        // This is the frequency in dictionary, not in text
//        Assert.assertEquals(3, terms.get(1).getFrequency());
    }

    @Test
    public void testTokenize() {
        String str = "我知道我出现了两次";
        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
        List<Token> tokens = tokenizer.tokenize(str);
        Assert.assertEquals("我",tokens.get(0).getTerm());
        Assert.assertEquals((long) tokens.get(0).getPosition(), 0);
        // NOTE: the number of terms does not equals to number of word ... "出现" is one term
        Assert.assertEquals((long) tokens.get(0).getRank(), 2000 / tokens.size());

    }
}
