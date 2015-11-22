package cn.edu.sjtu.at15.forum.tokenizer;

import cn.edu.sjtu.at15.forum.common.entity.ForumPost;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import cn.edu.sjtu.at15.forum.common.entity.TermPosition;

import cn.edu.sjtu.at15.forum.tokenizer.hanlp.TermWithRank;
import cn.edu.sjtu.at15.forum.tokenizer.hanlp.TextRankTokenizer;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by at15 on 15-11-22.
 */
public class Tokenizer {
    public static List<Term> segment(String str) {
        // IndexTokenizer handles long text
        // ie: 主副食品 -> 主副食品, 副食品, 食品
        return IndexTokenizer.segment(str);
    }

    public static List<TermWithPosition> tokenize(ForumThread thread) {
        List<TermWithPosition> termWithPositionList = new ArrayList<TermWithPosition>();
        List<TermWithRank> termWithRankList;
        Float titleWeight = 5.0f;
        // deal with title
        termWithRankList = TextRankTokenizer.getTermAndRank(thread.getTitle());
        for (TermWithRank t : termWithRankList) {
            // add weight to title
            t.setRank(titleWeight * t.getRank());
            termWithPositionList.add(new TermWithPosition(t, TermPosition.TITLE));
        }
        // deal with all the posts
        ListIterator<ForumPost> iterator = thread.getPosts().listIterator();
        while (iterator.hasNext()) {
            termWithRankList = TextRankTokenizer.getTermAndRank(iterator.next().getContent());
            for (TermWithRank t : termWithRankList) {
                termWithPositionList.add(new TermWithPosition(t, iterator.previousIndex()));
            }
        }
        return termWithPositionList;
    }

}
