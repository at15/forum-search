package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzStringUtils {
    private static final Pattern maxPagePattern = Pattern.compile("(.*)\\s(\\d+)\\s(.*)");

    public static Integer getMaxPage(String pagination) {
        Matcher m = maxPagePattern.matcher(pagination);
        if (m.find()) {
            return Integer.valueOf(m.group(2));
        }
        return 0;
    }
}
