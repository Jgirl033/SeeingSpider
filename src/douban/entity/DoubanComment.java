/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.entity;

import common.entity.Comment;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "douban_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoubanComment.findAll", query = "SELECT d FROM DoubanComment d"),
    @NamedQuery(name = "DoubanComment.findByUid", query = "SELECT d FROM DoubanComment d WHERE d.doubanCommentPK.uid = :uid"),
    @NamedQuery(name = "DoubanComment.findByMid", query = "SELECT d FROM DoubanComment d WHERE d.doubanCommentPK.mid = :mid"),
    @NamedQuery(name = "DoubanComment.findBySource", query = "SELECT d FROM DoubanComment d WHERE d.source = :source"),
    @NamedQuery(name = "DoubanComment.findByStatus", query = "SELECT d FROM DoubanComment d WHERE d.status = :status"),
    @NamedQuery(name = "DoubanComment.findByRating", query = "SELECT d FROM DoubanComment d WHERE d.rating = :rating"),
    @NamedQuery(name = "DoubanComment.findByAgreement", query = "SELECT d FROM DoubanComment d WHERE d.agreement = :agreement"),
    @NamedQuery(name = "DoubanComment.findByTime", query = "SELECT d FROM DoubanComment d WHERE d.time = :time")})
public class DoubanComment extends Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DoubanCommentPK doubanCommentPK;
    @Basic(optional = false)
    @Column(name = "source")
    private int source;
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "rating")
    private double rating;
    @Column(name = "agreement")
    private Integer agreement;
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @Lob
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DoubanUser doubanUser;
    @JoinColumn(name = "mid", referencedColumnName = "mid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DoubanMovie doubanMovie;

    public DoubanComment() {
    }

    public DoubanComment(DoubanCommentPK doubanCommentPK) {
        this.doubanCommentPK = doubanCommentPK;
    }

    public DoubanComment(DoubanCommentPK doubanCommentPK, int source, double rating, Date time, String comment) {
        this.doubanCommentPK = doubanCommentPK;
        this.source = source;
        this.rating = rating;
        this.time = time;
        this.comment = comment;
    }

    public DoubanComment(String uid, int mid) {
        this.doubanCommentPK = new DoubanCommentPK(uid, mid);
    }

    public DoubanCommentPK getDoubanCommentPK() {
        return doubanCommentPK;
    }

    public void setDoubanCommentPK(DoubanCommentPK doubanCommentPK) {
        this.doubanCommentPK = doubanCommentPK;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getAgreement() {
        return agreement;
    }

    public void setAgreement(Integer agreement) {
        this.agreement = agreement;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DoubanUser getDoubanUser() {
        return doubanUser;
    }

    public void setDoubanUser(DoubanUser doubanUser) {
        this.doubanUser = doubanUser;
    }

    public DoubanMovie getDoubanMovie() {
        return doubanMovie;
    }

    public void setDoubanMovie(DoubanMovie doubanMovie) {
        this.doubanMovie = doubanMovie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doubanCommentPK != null ? doubanCommentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoubanComment)) {
            return false;
        }
        DoubanComment other = (DoubanComment) object;
        if ((this.doubanCommentPK == null && other.doubanCommentPK != null) || (this.doubanCommentPK != null && !this.doubanCommentPK.equals(other.doubanCommentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "douban.entity.DoubanComment[ doubanCommentPK=" + doubanCommentPK + " ]";
    }
    
}
