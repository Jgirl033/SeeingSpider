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
public class DoubanCommentPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "uid")
    private String uid;
    @Basic(optional = false)
    @Column(name = "mid")
    private int mid;

    public DoubanCommentPK() {
    }

    public DoubanCommentPK(String uid, int mid) {
        this.uid = uid;
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        hash += (int) mid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoubanCommentPK)) {
            return false;
        }
        DoubanCommentPK other = (DoubanCommentPK) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        if (this.mid != other.mid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "douban.entity.DoubanCommentPK[ uid=" + uid + ", mid=" + mid + " ]";
    }
    
}
