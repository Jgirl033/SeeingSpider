/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.collector;

import common.collector.PageCollector;
import static common.constant.Constant.doubanDR;
import common.file.Writer;
import douban.entity.DoubanCollect;
import douban.entity.DoubanWish;
import douban.spider.DoubanUserSpider;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.TimeoutException;

/**
 * 豆瓣电影用户主页源代码下载类
 *
 * @author admin Jgirl
 */
public class DoubanUserPageCollector extends PageCollector {

    private final ArrayList<String> uidList;

    /**
     * 构造一个新的DoubanUserPageCollector()
     *
     * @param uidList
     */
    public DoubanUserPageCollector(ArrayList<String> uidList) {
        this.uidList = uidList;
    }

    /**
     * 启动豆瓣用户主页网页源代码下载程序
     *
     */
    @Override
    public void downloadPage() {
        //过滤掉已经爬取的用户源代码
        HashSet<String> set = new HashSet<>(this.uidList);
        ArrayList<String> uidSet = new ArrayList<>(set);
        ArrayList<String> finishedUidUrlSet = new ArrayList<>();
        File[] dir = new File("doc/server/douban/user/").listFiles();
        for (File file : dir) {
            String uid = file.getName().replace(".txt", "");
            finishedUidUrlSet.add(uid);
        }
        uidSet.removeAll(finishedUidUrlSet);
        
        try {
            for (String uid : uidSet) {
                String userUrl = "https://www.douban.com/people/" + uid + "/";
                while (true) {
                    try {
                        doubanDR.get(userUrl);
                        break;
                    } catch (TimeoutException e) {
                        System.out.println(e.toString());
                    }
                }

                String sourceCode = doubanDR.getPageSource().replaceAll("\n", "");//使用浏览器获取的网页源代码中含有换行符，需要过滤掉;
                Writer w = new Writer("doc/server/douban/user/", uid + ".txt");
                w.write(sourceCode);

                java.util.Random random = new java.util.Random();// 定义随机类
                int result = random.nextInt(3000) + 6000;// 返回[0,10)集合中的整数，注意不包括10
                Thread.sleep(result);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DoubanUserPageCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void downloadUserMoviePage() {

        ArrayList<String> midUrlList = new ArrayList<>();
        for (String uid : this.uidList) {

            DoubanUserSpider doubanUserSpider = new DoubanUserSpider(uid);
            ArrayList<DoubanCollect> collectMovieList = doubanUserSpider.getCollectMovie();
            for (DoubanCollect dbc : collectMovieList) {
                if (dbc.getOneUrl() != null) {
                    midUrlList.add(dbc.getOneUrl());
                }
                if (dbc.getTwoUrl() != null) {
                    midUrlList.add(dbc.getTwoUrl());
                }
                if (dbc.getThreeUrl() != null) {
                    midUrlList.add(dbc.getThreeUrl());
                }
                if (dbc.getFourUrl() != null) {
                    midUrlList.add(dbc.getFourUrl());
                }
                if (dbc.getFiveUrl() != null) {
                    midUrlList.add(dbc.getFiveUrl());
                }
            }

            ArrayList<DoubanWish> wishMovieList = doubanUserSpider.getWishMovie();
            for (DoubanWish dbw : wishMovieList) {
                if (dbw.getOneUrl() != null) {
                    midUrlList.add(dbw.getOneUrl());
                }
                if (dbw.getTwoUrl() != null) {
                    midUrlList.add(dbw.getTwoUrl());
                }
                if (dbw.getThreeUrl() != null) {
                    midUrlList.add(dbw.getThreeUrl());
                }
                if (dbw.getFourUrl() != null) {
                    midUrlList.add(dbw.getFourUrl());
                }
                if (dbw.getFiveUrl() != null) {
                    midUrlList.add(dbw.getFiveUrl());
                }
            }

        }
        
        HashSet<String> set = new HashSet<>(midUrlList);
        ArrayList<String> midUrlSet = new ArrayList<>(set);

        ArrayList<String> finishedMidUrlSet = new ArrayList<>();
        File[] dir = new File("doc/server/douban/movie/").listFiles();
        for (File file : dir) {
            String mid = file.getName().replace(".txt", "");
            System.out.println(mid);
            finishedMidUrlSet.add(mid);
        }
        midUrlSet.removeAll(finishedMidUrlSet);

        for (String midUrl : midUrlSet) {
            String mid = "";
            Pattern pattern = Pattern.compile("https://movie.douban.com/subject/(.*?)/");
            Matcher matcher = pattern.matcher(midUrl);
            if (matcher.find()) {
                mid = matcher.group(1).trim();
            }
            DoubanMoviePageCollector movieCollector = new DoubanMoviePageCollector(mid);
            movieCollector.downloadPage();
        }
    }
    
    
}
