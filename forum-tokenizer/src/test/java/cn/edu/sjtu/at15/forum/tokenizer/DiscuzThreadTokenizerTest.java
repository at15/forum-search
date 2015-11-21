package cn.edu.sjtu.at15.forum.tokenizer;

import com.hankcs.hanlp.seg.common.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by gpl on 15/11/17.
 */
public class DiscuzThreadTokenizerTest {
//    @Test
//    public void testSegment() {
//        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
//        List<Term> terms = tokenizer.segment("我喜欢你");
//        Assert.assertEquals("我", terms.get(0).word);
//        Assert.assertEquals("我/r", terms.get(0).toString());
//        terms = tokenizer.segment("你是个好人");
//        Assert.assertEquals(0, terms.get(0).offset);
//        Assert.assertEquals("你/r", terms.get(0).toString());
//        terms = tokenizer.segment("你好我好大家好");
//        // This is the frequency in dictionary, not in text
////        Assert.assertEquals(3, terms.get(1).getFrequency());
//    }
//
//    @Test
//    public void testTokenize() {
//        String str = "我知道我出现了两次";
//        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
//        List<Token> tokens = tokenizer.tokenize(str);
//        Assert.assertEquals("我", tokens.get(0).getTerm());
//        Assert.assertEquals((long) tokens.get(0).getPosition(), 0);
//        Assert.assertEquals((long) tokens.get(1).getPosition(), 1);
//        // NOTE: the number of terms does not equals to number of word ... "出现" is one term
//        Assert.assertEquals((long) tokens.get(0).getRank(), 2000 / tokens.size());
//        Assert.assertEquals(false, tokens.get(0).isValid());
//        tokens.get(0).setUrl("http://www.baidu.com");
//        Assert.assertEquals(true, tokens.get(0).isValid());
//
//        tokens = tokenizer.tokenize(str, "http://www.baidu.com");
//        Assert.assertEquals(true, tokens.get(0).isValid());
//
//    }
//
//    @Test
//    public void testTokenizeThread() {
//        String json = "{\"url\":\"http://www.1point3acres.com/bbs/thread-147944-1-1.html\",\"title\":\"生娃答疑帖\",\"viewCount\":883,\"replyCount\":25,\"author\":\"qreety\",\"authorPost\":\"我是作者啊啊啊啊\"}";
//        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
//        List<Token> tokens = tokenizer.tokenizeThread(json);
//        Assert.assertEquals(true, tokens.get(0).isValid());
//        Assert.assertEquals("生", tokens.get(0).getTerm());
//    }

}
