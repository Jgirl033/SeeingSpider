/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.collector;

import common.collector.PageCollector;
import static common.constant.Constant.doubanDR;
import common.file.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 豆瓣电影主页源代码下载类
 *
 * @author admin Jgirl
 */
public class DoubanMoviePageCollector extends PageCollector {

    private final String mid;

    /**
     * 使用豆瓣电影ID构造一个新的DoubanMoviePageCollector()
     *
     * @param mid 豆瓣电影ID
     */
    public DoubanMoviePageCollector(String mid) {
        this.mid = mid;
    }

    /**
     * 启动豆瓣电影网页源代码下载程序，对本地存储的电影信息源代码文本进行解析
     */
    @Override
    public void downloadPage() {
        try {
            String filename = this.mid + ".txt";
            String url = "https://movie.douban.com/subject/" + this.mid + "/";
            doubanDR.get(url);
            String sourceCode = doubanDR.getPageSource();

            Writer w = new Writer("doc/server/douban/movie/", filename);
            w.write(sourceCode);

            java.util.Random random = new java.util.Random();// 定义随机类
            int result = random.nextInt(10000) + 6000;// 返回[0,10)集合中的整数，注意不包括10
            Thread.sleep(result);
        } catch (Exception ex) {
            Logger.getLogger(DoubanUserPageCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
