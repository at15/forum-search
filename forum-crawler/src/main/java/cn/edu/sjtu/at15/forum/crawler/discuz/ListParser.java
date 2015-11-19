package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.crawler.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by at15 on 15-11-20.
 */
public class ListParser extends Parser {
    private Integer maxPage;
    private static final Pattern maxPagePattern = Pattern.compile("(.*)\\s(\\d+)\\s(.*)");

    public ListParser(String html) {
        super(html);
    }

    public Integer getMaxPage() {
        if (maxPage != null) {
            return maxPage;
        }
        String pagination = document.select("div.pg>label>span").text();
        Matcher m = maxPagePattern.matcher(pagination);
        maxPage = (m.find()) ? Integer.valueOf(m.group(2)) : 0;
        return maxPage;
    }
}
