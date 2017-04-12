/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.console;

import static common.constant.Constant.doubanDR;
import douban.collector.DoubanCommentPageCollector;
import douban.collector.DoubanMoviePageCollector;
import douban.collector.DoubanUserPageCollector;
import douban.entity.DoubanComment;
import douban.spider.DoubanLogin;
import douban.spider.DoubanMovieSpider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author admin
 */
public class DoubanCollector {

    public static void main(String[] args) throws IOException {
        doubanDR = DoubanLogin.getWebDriver();

        String[] movieIDUnfinishedList = {"3868141"};
        for (String mid : movieIDUnfinishedList) {

            //下载电影主页源代码
            DoubanMoviePageCollector dmpc = new DoubanMoviePageCollector(mid);
            dmpc.downloadPage();
            System.out.println("movie page done!");
            
            //下载电影评论源代码
            DoubanCommentPageCollector dcpa = new DoubanCommentPageCollector(mid, "P", 1, 10000);
            dcpa.downloadPage();
            System.out.println("comment pages done!");

            //解析评论源代码获取评论
            DoubanMovieSpider dcs = new DoubanMovieSpider(mid);
            ArrayList<DoubanComment> commentList = dcs.getComment("P");
            System.out.println("comments get!");

            //去掉重复评论
            HashSet<DoubanComment> set = new HashSet<>(commentList);
            ArrayList<DoubanComment> commentSet = new ArrayList<>(set);

            //获取用户ID
            ArrayList<String> uidList = new ArrayList<>();
            for (DoubanComment comment : commentSet) {
                String uid = comment.getDoubanCommentPK().getUid();
                uidList.add(uid);
                System.out.println("user: " + uid + "published comment: " + comment.getComment());
            }
            System.out.println("users get!");

            //下载用户源代码
            DoubanUserPageCollector dupc = new DoubanUserPageCollector(uidList);
            dupc.downloadPage();
            System.out.println("users pages done!");

            //下载用户想看的电影或看过的电影的源代码
            dupc.downloadUserMoviePage();
            System.out.println("users' movies pages done!");
        }

    }
}
