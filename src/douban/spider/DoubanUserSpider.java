/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.spider;

import common.file.Reader;
import common.spider.UserSpider;
import douban.entity.DoubanCollect;
import douban.entity.DoubanWish;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 解析豆瓣用户主页源代码，获取用户基本信息
 *
 * @author admin Jgirl
 */
public class DoubanUserSpider extends UserSpider{

    private final String uid;
    private final String sourceCode;

    /**
     * 使用豆瓣用户ID构造一个新的DoubanSpider()
     *
     * @param uid 豆瓣用户唯一ID
     */
    public DoubanUserSpider(String uid) {
        this.uid = uid;
        String filename = this.uid + ".txt";
        System.out.println("该用户的源代码路径是：" + filename);
        Reader ru = new Reader("doc/server/douban/user/", filename);
        this.sourceCode = ru.read();
    }

    /**
     * 获取豆瓣用户昵称
     *
     * @return String 豆瓣用户昵称
     */
    @Override
    public String getName() {
        String name = "";
        Pattern pattern = Pattern.compile("<title>(.*?)</title>");
        Matcher matcher = pattern.matcher(this.sourceCode);
        if (matcher.find()) {
            name = matcher.group(1).trim();
        }
        return name;
    }

    /**
     * 获取豆瓣用户常居地信息
     *
     * @return String 豆瓣用户常居地
     */
    @Override
    public String getArea() {
        String area = "";
        Pattern pattern = Pattern.compile("常居.*?<a href=\".*?\">(.*?)</a>");
        Matcher matcher = pattern.matcher(this.sourceCode);
        if (matcher.find()) {
            area = matcher.group(1).trim();
        }
        return area;
    }

    /**
     * 获取用户看过电影
     *
     * @return ArrayList 用户看过电影列表
     */
    public ArrayList getCollectMovie() {
        String content = "";
        ArrayList<DoubanCollect> collectList = new ArrayList<>();
        int count = 1;
        Pattern pattern1 = Pattern.compile("<div class=\"substatus\">看过</div>(.*?)<div class=\"clear\"></div></div>");
        Matcher matcher1 = pattern1.matcher(this.sourceCode);
        if (matcher1.find()) {
            content = matcher1.group(1).trim();
        }

        Pattern pattern2 = Pattern.compile("<a href=\"(https://movie.douban.com/subject/.*?/)\" title=\"(.*?)\" target=\"_blank\">");
        Matcher matcher2 = pattern2.matcher(content);
        while (matcher2.find()) {
            switch (count) {
                case 1: {
                    DoubanCollect w = new DoubanCollect();
                    w.setOneUrl(matcher2.group(1).trim());
                    w.setOne(matcher2.group(2).trim());
                    collectList.add(w);
                    break;
                }
                case 2: {
                    DoubanCollect w = new DoubanCollect();
                    w.setTwoUrl(matcher2.group(1).trim());
                    w.setTwo(matcher2.group(2).trim());
                    collectList.add(w);
                    break;
                }
                case 3: {
                    DoubanCollect w = new DoubanCollect();
                    w.setThreeUrl(matcher2.group(1).trim());
                    w.setThree(matcher2.group(2).trim());
                    collectList.add(w);
                    break;
                }
                case 4: {
                    DoubanCollect w = new DoubanCollect();
                    w.setFourUrl(matcher2.group(1).trim());
                    w.setFour(matcher2.group(2).trim());
                    collectList.add(w);
                    break;
                }
                case 5: {
                    DoubanCollect w = new DoubanCollect();
                    w.setFiveUrl(matcher2.group(1).trim());
                    w.setFive(matcher2.group(2).trim());
                    collectList.add(w);
                    break;
                }

            }
            count++;
        }

        return collectList;
    }

    /**
     * 获取用户想看电影列表
     *
     * @return ArrayList 用户想看电影列表
     */
    public ArrayList getWishMovie() {
        String content = "";
        ArrayList<DoubanWish> wishList = new ArrayList<>();
        int count = 1;
        Pattern pattern1 = Pattern.compile("<div class=\"substatus\">想看</div>(.*?)<div class=\"clear\"></div></div>");
        Matcher matcher1 = pattern1.matcher(this.sourceCode);
        if (matcher1.find()) {
            content = matcher1.group(1).trim();
        }

        Pattern pattern2 = Pattern.compile("<a href=\"(https://movie.douban.com/subject/.*?/)\" title=\"(.*?)\" target=\"_blank\">");
        Matcher matcher2 = pattern2.matcher(content);
        while (matcher2.find()) {
            switch (count) {
                case 1: {
                    DoubanWish w = new DoubanWish();
                    w.setOneUrl(matcher2.group(1).trim());
                    w.setOne(matcher2.group(2).trim());
                    wishList.add(w);
                    break;
                }
                case 2: {
                    DoubanWish w = new DoubanWish();
                    w.setTwoUrl(matcher2.group(1).trim());
                    w.setTwo(matcher2.group(2).trim());
                    wishList.add(w);
                    break;
                }
                case 3: {
                    DoubanWish w = new DoubanWish();
                    w.setThreeUrl(matcher2.group(1).trim());
                    w.setThree(matcher2.group(2).trim());
                    wishList.add(w);
                    break;
                }
                case 4: {
                    DoubanWish w = new DoubanWish();
                    w.setFourUrl(matcher2.group(1).trim());
                    w.setFour(matcher2.group(2).trim());
                    wishList.add(w);
                    break;
                }
                case 5: {
                    DoubanWish w = new DoubanWish();
                    w.setFiveUrl(matcher2.group(1).trim());
                    w.setFive(matcher2.group(2).trim());
                    wishList.add(w);
                    break;
                }

            }
            count++;
        }
        return wishList;
    }

}
