package cn.edu.sjtu.at15.forum.tokenizer;

import cn.edu.sjtu.at15.forum.common.FileUtils;
import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import cn.edu.sjtu.at15.forum.common.entity.TermPosition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by at15 on 15-11-22.
 */
public class TokenizerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSegment() {
        List<Term> terms = Tokenizer.segment("我喜欢你");
        Assert.assertEquals("我", terms.get(0).word);
        Assert.assertEquals("我/r", terms.get(0).toString());
        Assert.assertEquals(0, terms.get(0).offset);
        Assert.assertEquals(1, terms.get(1).offset);
        Assert.assertEquals(3, terms.get(2).offset);

        terms = Tokenizer.segment("你是个好人");
        Assert.assertEquals(0, terms.get(0).offset);
        Assert.assertEquals("你/r", terms.get(0).toString());
    }

    @Test
    public void testTokenize() throws IOException {
        ForumThread forumThread = mapper.readValue(FileUtils.readFileAsString("fixtures/sub-thread.json"), ForumThread.class);
        Integer postLength = forumThread.getPosts().size();
        List<TermWithPosition> tokens = Tokenizer.tokenize(forumThread);
        Assert.assertEquals(TermPosition.TITLE, tokens.get(0).getPosition().postIndex);
        Assert.assertEquals((long) postLength - 1,
                (long) tokens.get(tokens.size() - 1).getPosition().postIndex);


        ForumMainThread mainThread = mapper.readValue(FileUtils.readFileAsString("fixtures/main-thread.json"), ForumMainThread.class);
        postLength = mainThread.getPosts().size();
        tokens = Tokenizer.tokenize(mainThread);
        Assert.assertEquals(TermPosition.TITLE, tokens.get(0).getPosition().postIndex);
        Assert.assertEquals((long) postLength - 1,
                (long) tokens.get(tokens.size() - 1).getPosition().postIndex);
    }
}
