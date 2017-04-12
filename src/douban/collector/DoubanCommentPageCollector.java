/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.collector;

import common.collector.PageCollector;
import java.util.logging.Level;
import java.util.logging.Logger;

import static common.constant.Constant.doubanDR;
import common.file.Writer;
import java.io.File;
import java.util.ArrayList;

/**
 * 豆瓣电影短评源代码下载类
 *
 * @author admin Jgirl
 */
public class DoubanCommentPageCollector extends PageCollector {

    private final String mid;
    private final String status;
    private final int start;
    private final int end;

    /**
     * 使用豆瓣电影ID构造一个新的DoubanCommentPageCollector()
     *
     * @param mid 豆瓣电影ID
     * @param status 用户是否已观看电影
     * @param start 评论页面开始页数
     * @param end 评论页面结束页数
     */
    public DoubanCommentPageCollector(String mid, String status, int start, int end) {
        this.mid = mid;
        this.status = status;
        this.start = start;
        this.end = end;
    }

    @Override
    public void downloadPage() {
        
        //判断电影的评论页面是否已经爬取，如果是，则直接退出，否则，进行评论页面的爬取
        File[] dir = new File("doc/server/douban/comment/").listFiles();
        for(File file:dir){
            String tmp = file.getName().replace(".txt", "");
            if(tmp.equals(this.mid)){
                return;
            }
        }
        
        try {
            for (int i = this.start; i < this.end; i += 20) {
                //获取评论的网页源代码
                String url = "https://movie.douban.com/subject/" + this.mid + "/comments?start=" + Integer.toString(i) + "&limit=20&sort=new_score&status=" + this.status;
                doubanDR.get(url); //打开短评页面
                String sourceCode = doubanDR.getPageSource().replaceAll("\n", "");//使用浏览器获取的网页源代码中含有换行符，需要过滤掉;

                //将源代码写入文件
                Writer w = new Writer("doc/server/douban/comment/", this.mid + ".txt");
                w.write(sourceCode, true);

                //进程休眠，防止被反爬虫发现
                java.util.Random random = new java.util.Random();// 定义随机类
                int result = random.nextInt(3000) + 6000;
                Thread.sleep(result);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DoubanCommentPageCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
