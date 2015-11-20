package cn.edu.sjtu.at15.forum.crawler.discuz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import cn.edu.sjtu.at15.forum.common.entity.ForumThread;

/**
 * Created by at15 on 15-11-19.
 */
public class DiscuzPageProcessor implements PageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscuzPageProcessor.class);
    private final Url url;
    private Site site;

    public DiscuzPageProcessor(String baseUrl) {
        site = Site.me().setRetryTimes(3).setSleepTime(1000);
        url = new Url(baseUrl);
    }

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        String currentUrl = page.getUrl().toString();
        LOGGER.debug("processing page : " + currentUrl);
        if (!url.isInnerLink(currentUrl)) {
            LOGGER.debug("not inner link : " + currentUrl);
            return;
        }

        // deal with thread
        if (url.isThread(currentUrl)) {
            LOGGER.debug("processing thread");
            ForumThread thread = parseThread(page);
            page.putField("url", currentUrl);
            page.putField("thread", thread);
            return;
        }

        // TODO: 2. if this is a list page, get all the links in this page, and add other pages
        // NOTE: discuzz will show the total page in the last of pagination
        if (url.isList(currentUrl)) {
            LOGGER.debug("processing list page");
            // get the max page number
            ListParser listParser = new ListParser(page.getHtml().toString());
            Integer maxPage = listParser.getMaxPage();
            LOGGER.debug("max page : " + maxPage);
            // TODO: add all the page links, since webmagic will handle the duplicate url
            // TODO: handle maxPage == 0

            // loop through all thread links
            for (String link : page.getHtml().links().all()) {
                // ignore outer link
                if (!url.isInnerLink(link)) {
                    continue;
                }
                LOGGER.debug(link);
                if (url.isThread(link)) {
                    page.addTargetRequest(link);
                }
                // we don't consider nested list.
                // otherwise we would need to deal with duplicate links and circle refs
            }
            return;
        }
    }

    // TODO: should get all the comments as well, but we don't have much time, so.
    protected static ForumThread parseThread(Page page) {
        ForumThread thread = new ForumThread();
        ThreadParser threadParser = new ThreadParser(page.getHtml().toString());
        thread.setUrl(page.getUrl().toString());
        String title = threadParser.getTitle();
        LOGGER.debug("thread title : " + title);
        thread.setTitle(title);
        String author = threadParser.getAuthor();
        thread.setAuthor(author);
        LOGGER.debug("thread author : " + author);
        Integer viewCount = threadParser.getViewCount();
        thread.setViewCount(viewCount);
        LOGGER.debug("thread view count : " + viewCount);
        Integer replyCount = threadParser.getReplyCount();
        thread.setReplyCount(replyCount);
        LOGGER.debug("thread reply count : " + replyCount);
        String authorPost = threadParser.getAuthorPost();
        thread.setAuthorPost(authorPost);
        LOGGER.debug(authorPost);
        return thread;
    }

    public static void main(String[] args) throws Exception {
        Spider.create(new DiscuzPageProcessor("http://www.1point3acres.com/bbs/"))
//                .addUrl("http://www.1point3acres.com/bbs/forum.php?mod=guide&view=hot")
                .addUrl("http://www.1point3acres.com/bbs/thread-147944-1-1.html")
//                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipline("data"))
                .thread(5)
                .run();
    }
}
