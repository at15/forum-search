package cn.edu.sjtu.at15.forum.tokenizer;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by at15 on 15-11-22.
 */
public class Tokenizer {
    public List<Term> segment(String str) {
        // IndexTokenizer handles long text
        // ie: 主副食品 -> 主副食品, 副食品, 食品
        return IndexTokenizer.segment(str);
    }

}
