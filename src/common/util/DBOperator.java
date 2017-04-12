/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util;

import common.entity.Comment;
import common.entity.Movie;
import common.entity.User;
import java.util.ArrayList;

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
}
