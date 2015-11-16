package cn.edu.sjtu.at15.forum.tokenizer;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by gpl on 15/11/17.
 */
public class DiscuzThreadTokenizer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscuzThreadTokenizer.class);

    // TODO: what's the difference when call HanLP and StandardTokenizer
    public List<Term> hanlp(String str){
        return HanLP.segment(str);
    }

    public List<Term> standard(String str) {
        return StandardTokenizer.segment(str);
    }

    public static void main(String[] args) throws Exception {
        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
        System.out.println(tokenizer.standard("我喜欢吃西瓜"));
        System.out.println(tokenizer.hanlp("我喜欢吃西瓜"));
    }
}
