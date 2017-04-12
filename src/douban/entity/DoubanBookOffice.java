/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "douban_book_office")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoubanBookOffice.findAll", query = "SELECT d FROM DoubanBookOffice d"),
    @NamedQuery(name = "DoubanBookOffice.findByMid", query = "SELECT d FROM DoubanBookOffice d WHERE d.doubanBookOfficePK.mid = :mid"),
    @NamedQuery(name = "DoubanBookOffice.findByWeek", query = "SELECT d FROM DoubanBookOffice d WHERE d.doubanBookOfficePK.week = :week"),
    @NamedQuery(name = "DoubanBookOffice.findByTime", query = "SELECT d FROM DoubanBookOffice d WHERE d.time = :time"),
    @NamedQuery(name = "DoubanBookOffice.findByBookOffice", query = "SELECT d FROM DoubanBookOffice d WHERE d.bookOffice = :bookOffice")})
public class DoubanBookOffice implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DoubanBookOfficePK doubanBookOfficePK;
    @Basic(optional = false)
    @Column(name = "time")
    private String time;
    @Basic(optional = false)
    @Column(name = "book_office")
    private double bookOffice;
    @JoinColumn(name = "mid", referencedColumnName = "mid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DoubanMovie doubanMovie;

    public DoubanBookOffice() {
    }

    public DoubanBookOffice(DoubanBookOfficePK doubanBookOfficePK) {
        this.doubanBookOfficePK = doubanBookOfficePK;
    }

    public DoubanBookOffice(DoubanBookOfficePK doubanBookOfficePK, String time, double bookOffice) {
        this.doubanBookOfficePK = doubanBookOfficePK;
        this.time = time;
        this.bookOffice = bookOffice;
    }

    public DoubanBookOffice(int mid, int week) {
        this.doubanBookOfficePK = new DoubanBookOfficePK(mid, week);
    }

    public DoubanBookOfficePK getDoubanBookOfficePK() {
        return doubanBookOfficePK;
    }

    public void setDoubanBookOfficePK(DoubanBookOfficePK doubanBookOfficePK) {
        this.doubanBookOfficePK = doubanBookOfficePK;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getBookOffice() {
        return bookOffice;
    }

    public void setBookOffice(double bookOffice) {
        this.bookOffice = bookOffice;
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
        hash += (doubanBookOfficePK != null ? doubanBookOfficePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoubanBookOffice)) {
            return false;
        }
        DoubanBookOffice other = (DoubanBookOffice) object;
        if ((this.doubanBookOfficePK == null && other.doubanBookOfficePK != null) || (this.doubanBookOfficePK != null && !this.doubanBookOfficePK.equals(other.doubanBookOfficePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "douban.entity.DoubanBookOffice[ doubanBookOfficePK=" + doubanBookOfficePK + " ]";
    }
    
}
