/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.spider;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * 豆瓣电影登录类
 *
 * @author admin Jgirl
 */
public class DoubanLogin {

    /**
     * 启动chrome浏览器，用于手动登录豆瓣账号
     *
     * @return WebDriver
     */
    public static WebDriver getWebDriver() {
        File file = new File("exe\\chromedriver.exe"); //chromediriver的指定目录

        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(file).usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException ex) {
            Logger.getLogger(DoubanMovieSpider.class.getName()).log(Level.SEVERE, null, ex);
        }
        WebDriver dr = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        dr.get("https://accounts.douban.com/login?source=movie");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DoubanLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dr;
    }
}
