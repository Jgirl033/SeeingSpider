/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author admin
 */
@Embeddable
public class DoubanBookOfficePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "mid")
    private int mid;
    @Basic(optional = false)
    @Column(name = "week")
    private int week;

    public DoubanBookOfficePK() {
    }

    public DoubanBookOfficePK(int mid, int week) {
        this.mid = mid;
        this.week = week;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mid;
        hash += (int) week;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoubanBookOfficePK)) {
            return false;
        }
        DoubanBookOfficePK other = (DoubanBookOfficePK) object;
        if (this.mid != other.mid) {
            return false;
        }
        if (this.week != other.week) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "douban.entity.DoubanBookOfficePK[ mid=" + mid + ", week=" + week + " ]";
    }
    
}
