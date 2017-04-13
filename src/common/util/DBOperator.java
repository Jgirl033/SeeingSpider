/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util;

import common.entity.Comment;
import common.entity.Movie;
import common.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public abstract class DBOperator {
    
    public abstract boolean isUserExist(String uid);
    
    public abstract boolean isMovieExist(String mid);
    
    public abstract boolean isCommentExist(String uid, String mid);
    
    public abstract void saveMovie(Movie m);
    
    public abstract void saveComment(ArrayList<Comment> commentList);
    
    public abstract void saveUser(ArrayList<User> userList);
    
    public static void updateDatabase(String IP) {
        Connection dbc = new DBConnector().getDBConnection();
        String sql = "UPDATE task SET is_downloading =0 WHERE is_downloaded =0 and IP=?";
        try {
            PreparedStatement ps = dbc.prepareStatement(sql);
            ps.setString(0, IP);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
