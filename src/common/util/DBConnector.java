/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接类
 *
 * @author admin
 */
public class DBConnector{

    private Connection conn;

    public DBConnector() {
        try {
            //加载驱动，这一句也可写为：Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spider?characterEncoding=utf-8", "root", "iiip");
            System.out.println("连接成功！");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println("混蛋 : " + ex.toString());
            System.out.println("连接失败！");
        }
    }

    public DBConnector(String str, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(str, user, password);
            System.out.println("连接成功！");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println("Error : " + ex.toString());
            System.out.println("连接失败！");
        }
    }

    public Connection getDBConnection() {
        return this.conn;
    }
}
