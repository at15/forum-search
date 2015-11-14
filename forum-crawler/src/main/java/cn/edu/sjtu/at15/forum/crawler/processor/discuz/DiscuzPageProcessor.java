package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzPageProcessor implements PageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscuzPageProcessor.class);
    private final DiscuzUrl discuzUrl;
    private Site site;

    public DiscuzPageProcessor(String baseUrl) {
        site = Site.me().setRetryTimes(3).setSleepTime(1000);
        discuzUrl = new DiscuzUrl(baseUrl);
    }

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        String currentUrl = page.getUrl().toString();
        LOGGER.debug("processing page : " + currentUrl);
        if (!discuzUrl.isInner(currentUrl)) {
            LOGGER.debug("not inner link : " + currentUrl);
            return;
        }

        // determine the type of this page
        if (discuzUrl.isThread(currentUrl)) {
            LOGGER.debug("processing thread");
            // TODO: parse thread content here
            // in fact we should handle all the comments as well, but this is not so important
            return;
        }

        // TODO: 2. if this is a list page, get all the links in this page, and add other pages
        // NOTE: discuzz will show the total page in the last of pagination
        if (discuzUrl.isList(currentUrl)) {
            LOGGER.debug("processing list page");
            // get the max page
            // 1. get the div for pagination
            // 2. get the max page number
            LOGGER.debug(page.getHtml().css("div.pg>label>span").toString());

            // loop through all thread links
//            for (String link : page.getHtml().links().all()) {
//                // ignore outer link
//                if (!discuzUrl.isInner(link)) {
//                    continue;
//                }
//                LOGGER.debug(link);
//                if (discuzUrl.isThread(link)) {
//                    page.addTargetRequest(link);
//                }
//                // we don't consider nested list.
//                // otherwise we would need to deal with duplicate links and circle refs
//            }
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        Spider.create(new DiscuzPageProcessor("http://www.1point3acres.com/bbs/"))
                .addUrl("http://www.1point3acres.com/bbs/forum.php?mod=guide&view=hot")
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
    }
}
