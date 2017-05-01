/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.console;

import common.entity.Comment;
import common.entity.User;
import douban.entity.DoubanComment;
import douban.entity.DoubanMovie;
import douban.entity.DoubanUser;
import douban.spider.DoubanMovieSpider;
import douban.spider.DoubanUserSpider;
import douban.util.DoubanDBOperator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


/**
 *
 * @author admin
 */
public class DoubanSaver {

    public static void main(String[] args) throws IOException {

        String[] movieIDUnifinishedList = {"25726614", "25894431"};
        DoubanDBOperator dbi = new DoubanDBOperator();
        
        for (String mid : movieIDUnifinishedList) {
            DoubanMovieSpider dms = new DoubanMovieSpider(mid);
            
            DoubanMovie m = dms.getMovie();
            ArrayList<Comment> commentList = dms.getComment("P");
            HashSet<Comment> set = new HashSet<>(commentList);
            ArrayList<Comment> commentSet = new ArrayList<>(set);

            ArrayList<String> uidList = new ArrayList<>();
            for (Comment comment : commentSet) {
                String uid = ((DoubanComment)comment).getDoubanCommentPK().getUid();
                if (!dbi.isUserExist(uid)) {
                    uidList.add(uid);
                }
            }

            ArrayList<User> doubanUserList = new ArrayList<>();
            for (String uid : uidList) {
                DoubanUserSpider dup = new DoubanUserSpider(uid);
                DoubanUser doubanUser = new DoubanUser(uid);
                doubanUser.setName(dup.getName());
                doubanUser.setArea(dup.getArea());
                doubanUser.setSource(1);
                doubanUserList.add(doubanUser);
            }
            
            dbi.saveMovie(m);
            dbi.saveUser(doubanUserList);
            dbi.saveComment(commentList);
            dbi.saveUserMovie(doubanUserList);
        }
    }
}
