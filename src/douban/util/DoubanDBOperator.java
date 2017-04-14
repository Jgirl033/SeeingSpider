/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.util;

import common.entity.Comment;
import common.entity.Movie;
import common.entity.User;
import common.util.DBConnector;
import common.util.DBOperator;
import douban.entity.DoubanComment;
import douban.entity.DoubanMovie;
import douban.entity.DoubanUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 数据库插入类
 *
 * @author admin Jgirl
 */
public class DoubanDBOperator extends DBOperator {

    private final Connection dbc;
    private final String database;

    /**
     * 构造一个新的DBInsert()
     */
    public DoubanDBOperator() {
        this.database = "downloader";
        this.dbc = new DBConnector().getDBConnection();
    }

    /**
     * 使用豆瓣用户ID判断用户是否已在数据库中
     *
     * @param uid 豆瓣用户唯一ID
     * @return boolean 表示用户数据是否存在于数据库中
     */
    public boolean isUserExist(String uid) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM douban_user where uid = '" + uid + "'";
            PreparedStatement ps = this.dbc.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DoubanDBOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     *
     * @param uid
     * @param mid
     * @return
     */
    @Override
    public boolean isCommentExist(String uid, String mid) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM douban_comment where uid = '" + uid + "' and mid='" + mid + "'";
            PreparedStatement ps = this.dbc.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DoubanDBOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     *
     * @param mid
     * @return
     */
    @Override
    public boolean isMovieExist(String mid) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM douban_movie where mid = '" + mid + "'";
            PreparedStatement ps = this.dbc.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DoubanDBOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * 存储电影基本信息到数据库
     *
     * @param movie
     */
    @Override
    public void saveMovie(Movie movie) {

        DoubanMovie m = (DoubanMovie) movie;
        //往数据表movie插入数据
        try {
            //执行SQL语句
            String insql = "REPLACE INTO  `" + this.database + "`.`douban_movie` (`mid` ,`name` ,`img_src` ,`director` ,`screenwriter` ,`performer` ,`style`,`area` ,`language`,`release_time` ,`runtime` ,`synopsis` ,`award` ,`like` ,`json_src` ,`rating` ,`evaluation_number` ,`one` ,`two` ,`three` ,`four`,`five`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = this.dbc.prepareStatement(insql);
            ps.setInt(1, m.getMid());
            ps.setString(2, m.getName());
            ps.setString(3, m.getImgSrc());
            ps.setString(4, m.getDirector());
            ps.setString(5, m.getScreenwriter());
            ps.setString(6, m.getPerformer());
            ps.setString(7, m.getStyle());
            ps.setString(8, m.getArea());
            ps.setString(9, m.getLanguage());
            ps.setString(10, m.getReleaseTime());
            ps.setString(11, m.getRuntime());
            ps.setString(12, m.getSynopsis());
            ps.setString(13, m.getAward());
            ps.setString(14, m.getLike());
            ps.setString(15, m.getJsonSrc());
            ps.setDouble(16, m.getRating());
            ps.setInt(17, m.getEvaluationNumber());
            ps.setDouble(18, m.getOne());
            ps.setDouble(19, m.getTwo());
            ps.setDouble(20, m.getThree());
            ps.setDouble(21, m.getFour());
            ps.setDouble(22, m.getFive());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("插入movie表成功");
            }
        } catch (Exception ex) {
            Logger.getLogger(DoubanDBOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 批量存储电影短评到数据库
     *
     * @param commentList 某部电影的短评列表
     */
    @Override
    public void saveComment(ArrayList<Comment> commentList) {
        ArrayList<DoubanComment> doubanCommentList = new ArrayList<>();
        for (Comment comment : commentList) {
            doubanCommentList.add((DoubanComment) comment);
        }
        try {
            String insql = "REPLACE INTO  `" + this.database + "`.`douban_comment` (`mid` ,`uid`,`source` ,`status` ,`rating` ,`agreement` ,`time` ,`comment`) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement prest = this.dbc.prepareStatement(insql);
            System.out.println(commentList);
            for (DoubanComment comment : doubanCommentList) {
                if (isCommentExist(comment.getDoubanCommentPK().getUid(), String.valueOf(comment.getDoubanCommentPK().getMid()))) {
                    continue;
                }
                System.out.println(comment.getComment());
                prest.setInt(1, comment.getDoubanCommentPK().getMid());
                prest.setString(2, comment.getDoubanCommentPK().getUid());
                prest.setInt(3, 1);
                prest.setString(4, comment.getStatus());
                prest.setDouble(5, comment.getRating());
                prest.setInt(6, comment.getAgreement());
                java.sql.Date sqlDate = new java.sql.Date(comment.getTime().getTime());
                prest.setDate(7, sqlDate);
                prest.setString(8, comment.getComment());
                prest.addBatch();
            }
            prest.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(DoubanDBOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 批量存储发表短评的用户到数据库
     *
     * @param userList 某部电影发表短评的用户列表
     */
    @Override
    public void saveUser(ArrayList<User> userList) {

        ArrayList<DoubanUser> doubanUserList = new ArrayList<>();
        for (User user : userList) {
            doubanUserList.add((DoubanUser) user);
        }

        try {
            String insql;
            insql = "REPLACE INTO  `" + this.database + "`.`douban_user` (`uid` ,`name` ,`source`,`area`) VALUES (?,?,?,?);";
            PreparedStatement ps = this.dbc.prepareStatement(insql);
            for (DoubanUser user : doubanUserList) {
                ps.setString(1, user.getUid());
                ps.setString(2, user.getName());
                ps.setInt(3, user.getSource());
                ps.setString(4, user.getArea());
                System.out.println(user.getArea());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception ex) {
            Logger.getLogger(DoubanDBOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
