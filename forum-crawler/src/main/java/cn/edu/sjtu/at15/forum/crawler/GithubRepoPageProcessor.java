package cn.edu.sjtu.at15.forum.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by gpl on 15/11/14.
 */
public class GithubRepoPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        // 官方文档里的xpath抽取不出来内容, 所以导致抓不出结果, name一直是null, 所以就都被skip了
        page.putField("name", page.getHtml().xpath("//h1[contains(@class, 'entry-title') and contains(@class, 'public')]/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("data/"))
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
    }
}
