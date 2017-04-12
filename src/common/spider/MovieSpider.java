/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.spider;

import common.entity.Comment;
import common.entity.Movie;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public abstract class MovieSpider {
    public abstract Movie getMovie();
    public abstract ArrayList<Comment> getComment(String status);
}
