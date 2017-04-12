/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 文件写入类，实现将数据以多种形式写入文本文件中
 *
 * @author admin Jgirl
 */
public class Writer {

    private final String filename;
    private final File file;

    /**
     * 使用默认文件路径和文件名构造一个新的Writer()
     *
     * @param filename 指定文件名
     */
    public Writer(String filename) {
        this.filename = "doc/" + filename;
        this.file = new File(this.filename);
    }

    /**
     * 使用指定文件路径和文件名构造一个新的Writer()
     *
     * @param filePath 指定文件路径
     * @param filename 指定文件名
     */
    public Writer(String filePath, String filename) {
        this.filename = filePath + filename;
        this.file = new File(this.filename);
    }

    /**
     * 检查文件是否已存在
     */
    public void check() {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 对指定文件写入重写方式具体内容
     *
     * @param content 需要写入文件的内容
     */
    public void write(String content) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(this.filename))) {
            br.write(content);
            br.flush(); //刷新缓冲区的数据到文件
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 对指定文件以追加方式写入具体内容
     *
     * @param content 需要写入文件的内容
     * @param flag true则表示追加写入文件，false则表示重写文件
     */
    public void write(String content, boolean flag) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(this.filename, flag))) {
            br.write(content);
            br.flush(); //刷新缓冲区的数据到文件
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
