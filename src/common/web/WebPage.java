/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.web;

/**
 * 网页实体类
 *
 * @author admin Jgirl
 */
public class WebPage {

    private boolean success = false;
    private String sourceCode = "";

    /**
     * 判断本次网页爬取是否成功
     *
     * @return boolean 表示爬取是否成功
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * 网页爬取是否成功的标志位设置
     *
     * @param success 是否成功标志位
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取网页源代码
     *
     * @return String 某个网页的源代码
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * 设置网页源代码
     *
     * @param sourceCode 某个网页的源代码
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

}
