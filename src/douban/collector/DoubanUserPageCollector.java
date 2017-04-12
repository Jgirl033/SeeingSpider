/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.collector;

import common.collector.PageCollector;
import static common.constant.Constant.doubanDR;
import common.file.Writer;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
}
