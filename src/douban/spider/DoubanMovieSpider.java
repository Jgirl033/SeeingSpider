/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.spider;

import common.file.Reader;
import common.spider.MovieSpider;
import common.web.WebConnect;
import common.web.WebPage;
import douban.entity.DoubanComment;
import douban.entity.DoubanCommentPK;
import douban.entity.DoubanMovie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬取豆瓣电影基本信息并提供通过类中的方法能够获取电影相关文件的本地存储路径
 *
 * @author admin Jgirl
 */
public class DoubanMovieSpider extends MovieSpider {

    private final String mid;
    private final DoubanMovie movie;
    private final String informationSourceCode;
    private final String commentSourceCode;

    /**
     * 使用豆瓣电影ID构造一个新的DoubanMovieSpider()
     *
     * @param mid 豆瓣电影唯一ID
     */
    public DoubanMovieSpider(String mid) {
        String filename = mid + ".txt";
        Reader rm = new Reader("doc/server/douban/movie/", filename);
        Reader rc = new Reader("doc/server/douban/comment/", filename);

        this.mid = mid;
        this.movie = new DoubanMovie();
        this.informationSourceCode = rm.read();
        this.commentSourceCode = rc.read();
    }

    /**
     * 通过电影名字获取某部电影的基本信息及评分，包括豆瓣、海报链接、导演、编剧、演员、风格、地区、语言、上映时间、片长、剧情简介、获奖情况、相似电影、电影相关信息文件存储本地路径
     *
     * @return Movie 某部电影的基本信息
     */
    @Override
    public DoubanMovie getMovie() {

        movie.setMid(Integer.parseInt(this.mid));

        Pattern patternName = Pattern.compile("<title>(.*?)</title>");
        Matcher matcherName = patternName.matcher(this.informationSourceCode);
        if (matcherName.find()) {
            movie.setName(matcherName.group(1).replaceAll("<.*?>", "").replace("(豆瓣)", "").trim());
        }

        movie.setImgSrc(this.getImage());

        Pattern patternDirector = Pattern.compile("<span class='pl'>导演</span>:(.*?)</span>");
        Matcher matcherDirector = patternDirector.matcher(this.informationSourceCode);
        if (matcherDirector.find()) {
            movie.setDirector(matcherDirector.group(1).replaceAll("<.*?>", "").trim());
        } else {
            patternDirector = Pattern.compile("<span class=\"pl\">导演</span>:(.*?)</span>");
            matcherDirector = patternDirector.matcher(this.informationSourceCode);
            if (matcherDirector.find()) {
                movie.setDirector(matcherDirector.group(1).replaceAll("<.*?>", "").trim());
            } else {
                movie.setDirector("");
            }
        }

        Pattern patternScreenwriter = Pattern.compile("<span class='pl'>编剧</span>:(.*?)</span>");
        Matcher matcherScreenwriter = patternScreenwriter.matcher(this.informationSourceCode);
        if (matcherScreenwriter.find()) {
            movie.setScreenwriter(matcherScreenwriter.group(1).replaceAll("<.*?>", "").trim());
        } else {
            patternScreenwriter = Pattern.compile("<span class=\"pl\">编剧</span>:(.*?)</span>");
            matcherScreenwriter = patternScreenwriter.matcher(this.informationSourceCode);
            if (matcherScreenwriter.find()) {
                movie.setScreenwriter(matcherScreenwriter.group(1).replaceAll("<.*?>", "").trim());
            } else {
                movie.setScreenwriter("");
            }
        }

        Pattern patternPerformance = Pattern.compile("<span class='pl'>主演</span>:(.*?)</span>");
        Matcher matcherPerformance = patternPerformance.matcher(this.informationSourceCode);
        String performance = "";
        if (matcherPerformance.find()) {
            performance = matcherPerformance.group(1).replaceAll("<.*?>", "").trim();
        } else {
            patternPerformance = Pattern.compile("<span class=\"pl\">主演</span>:(.*?)</span>");
            matcherPerformance = patternPerformance.matcher(this.informationSourceCode);
            if (matcherPerformance.find()) {
                performance = matcherPerformance.group(1).replaceAll("<.*?>", "").trim();
            }
        }
        movie.setPerformer(performance);

        Pattern patternStyle = Pattern.compile("<span class=\"pl\">类型:</span>(.*?)<span class=\"pl\">");
        Matcher matcherStyle = patternStyle.matcher(this.informationSourceCode);
        if (matcherStyle.find()) {
            movie.setStyle(matcherStyle.group(1).replaceAll("<.*?>", "").trim());
        } else {
            movie.setStyle("");
        }

        Pattern patternArea = Pattern.compile("<span class=\"pl\">制片国家/地区:(.*?)<br.*?/>");
        Matcher matcherArea = patternArea.matcher(this.informationSourceCode);
        if (matcherArea.find()) {
            movie.setArea(matcherArea.group(1).replaceAll("<.*?>", "").trim());
        } else {
            movie.setArea("");
        }

        Pattern patternLanguage = Pattern.compile("<span class=\"pl\">语言:</span>(.*?)<br.*?/>");
        Matcher matcherLanguage = patternLanguage.matcher(this.informationSourceCode);
        if (matcherLanguage.find()) {
            movie.setLanguage(matcherLanguage.group(1).replaceAll("<.*?>", "").trim());
        } else {
            movie.setLanguage("");
        }

        Pattern patternReleaseTime = Pattern.compile("<span class=\"pl\">上映日期:</span>(.*?)</span>");
        Matcher matcherReleaseTime = patternReleaseTime.matcher(this.informationSourceCode);
        if (matcherReleaseTime.find()) {
            movie.setReleaseTime(matcherReleaseTime.group(1).replaceAll("<.*?>", "").trim());
        } else {
            movie.setReleaseTime("");
        }

        Pattern patternRuntime = Pattern.compile("<span class=\"pl\">片长:</span>.*?<span property=\"v:runtime\".*?>(.*?)分钟.*?</span>");
        Matcher matcherRuntime = patternRuntime.matcher(this.informationSourceCode);
        if (matcherRuntime.find()) {
            movie.setRuntime(matcherRuntime.group(1).replaceAll("<.*?>", "").trim());
        } else {
            movie.setRuntime("");
        }

        Pattern patternSynopsis = Pattern.compile("v:summary.*?>(.*?)</span>");
        Matcher matcherSynopsis = patternSynopsis.matcher(this.informationSourceCode);
        if (matcherSynopsis.find()) {
            movie.setSynopsis(matcherSynopsis.group(1).replaceAll("<.*?>", "").trim());
        } else {
            movie.setSynopsis("");
        }

        String award = "";
        for (String item : this.getAwardsSituation()) {
            award += item.replaceAll("<.*?>", "").trim() + "#";
        }
        movie.setAward(award);

        String like = "";
        for (String item : this.getRecommendation()) {
            like += item.replaceAll("<.*?>", "").trim() + "#";
        }
        movie.setLike(like);

        movie.setJsonSrc("./" + this.mid);

        Pattern patternScore = Pattern.compile("<.*?rating_num.*?>(.*?)</strong>.*?v:votes.*?>(.*?)</span>.*?title=\"力荐.*?rating_per\">(.*?)%</span>.*?title=\"推荐.*?rating_per\">(.*?)%</span>.*?title=\"还行.*?rating_per\">(.*?)%</span>.*?title=\"较差.*?rating_per\">(.*?)%</span>.*?title=\"很差.*?rating_per\">(.*?)%</span>");
        Matcher matcherScore = patternScore.matcher(this.informationSourceCode);
        if (matcherScore.find()) {
            movie.setRating(Double.valueOf(matcherScore.group(1).trim()));
            movie.setEvaluationNumber(Integer.valueOf(matcherScore.group(2).trim()));
            movie.setFive(Double.valueOf(matcherScore.group(3).trim()));
            movie.setFour(Double.valueOf(matcherScore.group(4).trim()));
            movie.setThree(Double.valueOf(matcherScore.group(5).trim()));
            movie.setTwo(Double.valueOf(matcherScore.group(6).trim()));
            movie.setOne(Double.valueOf(matcherScore.group(7).trim()));
        } else {
            System.out.println("高能预警：解析电影评价情况的正则表达式匹配不正确！");
        }
        return this.movie;
    }

    /**
     * 获取某部电影的海报链接
     *
     * @return String 某部电影的海报路径
     */
    public String getImage() {
        String imagePath = "";
        Pattern pattern = Pattern.compile("<a class=\".*?\" href=\".*?\" title=\"点击看更多海报\">.*?<img src=\"(.*?)\" title=\"点击看更多海报\" alt=\".*?\" rel=\"v:image\" />.*?</a>");
        Matcher matcher = pattern.matcher(this.informationSourceCode);
        if (matcher.find()) {
            imagePath = matcher.group(1);
        }
        return imagePath;
    }

    /**
     * 获取看过某部电影的用户也喜欢看的相关电影
     *
     * @return ArrayList 电影获奖情况列表
     */
    public ArrayList<String> getRecommendation() {
        ArrayList<String> recommendation = new ArrayList<>();
        Pattern pattern = Pattern.compile("<div class=\"recommendations-bd\">(.*?)</div>");
        Matcher matcher = pattern.matcher(this.informationSourceCode);
        if (matcher.find()) {
            String tmp = matcher.group(1);
            pattern = Pattern.compile("<a href.*?>.*?<img.*?alt=\"(.*?)\"");
            matcher = pattern.matcher(tmp);
            while (matcher.find()) {
                recommendation.add(matcher.group(1));
            }
        }
        return recommendation;
    }

    /**
     * 获取某部电影的获奖情况
     *
     * @return ArrayList 电影获奖情况列表
     */
    public ArrayList<String> getAwardsSituation() {
        String url = "https://movie.douban.com/subject/" + this.mid + "/awards/";
        ArrayList<String> awards = new ArrayList<>();
        WebPage awardsPage = new WebConnect(url).downloadPage();

        String awardsSourceCode = awardsPage.getSourceCode();
        Pattern patternContent = Pattern.compile("<div id=\"content\">(.*?)</div>");
        Matcher matcherContent = patternContent.matcher(awardsSourceCode);

        String content;
        if (matcherContent.find()) {
            content = matcherContent.group(1);
        } else {
            content = "";
        }

        Pattern pattern = Pattern.compile("<ul class=\"award\">.*?<li>(.*?)</li>.*?<li>(.*?)</li>.*?</ul>");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            awards.add(matcher.group(1).replaceAll("<.*?>", "").trim() + ":" + matcher.group(2).replaceAll("<.*?>", "").trim());
        }
        return awards;
    }

    /**
     * 获取某部电影的短评列表
     *
     * @param status 发表短评的用户是否看过电影
     * @return ArrayList 某部电影的短评列表
     */
    @Override
    public ArrayList getComment(String status) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<DoubanComment> commentList = new ArrayList<>();

        Pattern pattern = Pattern.compile("<div class=\"comment-item\".*?<a title=\"(.*?)\".*?href=\"https://www.douban.com/people/(.*?)/\">.*?<div class=\"comment\">.*?<span class=\"votes.*?\">(.*?)</span>.*?<span class=\"allstar(.*?) rating\" title=\".*?\"></span>.*?<span class=\"comment-time.*?\">(.*?)</span>.*?<p class=\"\">(.*?)</p>.*?</div>");
        Matcher matcher = pattern.matcher(this.commentSourceCode);
        while (matcher.find()) {
            DoubanCommentPK commentPK = new DoubanCommentPK();
            commentPK.setMid(Integer.parseInt(this.mid));
            commentPK.setUid(matcher.group(2).replaceAll("<.*?>", "").trim());
            DoubanComment comment = new DoubanComment();
            comment.setDoubanCommentPK(commentPK);
            comment.setStatus(status);
            comment.setSource(1);
            comment.setAgreement(Integer.parseInt(matcher.group(3)));
            comment.setRating(Double.parseDouble(matcher.group(4).replaceAll("<.*?>", "").trim()));
            try {
                comment.setTime(format.parse(matcher.group(5).replaceAll("<.*?>", "").trim()));
            } catch (ParseException ex) {
                Logger.getLogger(DoubanMovieSpider.class.getName()).log(Level.SEVERE, null, ex);
            }
            comment.setComment(matcher.group(6).replaceAll("<.*?>", ""));
            System.out.println("comment: " + comment.getComment());
            commentList.add(comment);
        }
        return commentList;
    }
}
