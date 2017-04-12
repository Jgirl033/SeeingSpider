/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 文件读取类，实现对文本文件的读取
 *
 * @author admin Jgirl
 */
public class Reader {

    private final String filename;

    /**
     * 使用默认文件路径和文件名构造一个新的Reader()
     *
     * @param filename 指定文件名
     */
    public Reader(String filename) {
        this.filename = "doc/" + filename;
    }

    /**
     * 使用指定文件路径和文件名构造一个新的Reader()
     *
     * @param filePath 指定文件路径
     * @param filename 指定文件名
     */
    public Reader(String filePath, String filename) {
        this.filename = filePath + filename;
    }

    /**
     * 将指定文件的内容读取出来
     *
     * @return String 文件内容
     */
    public String read() {
        String content = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filename), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                content = content + line;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return content;
    }
}
