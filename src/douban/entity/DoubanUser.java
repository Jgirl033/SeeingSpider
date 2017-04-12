/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.entity;

import common.entity.User;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "douban_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoubanUser.findAll", query = "SELECT d FROM DoubanUser d"),
    @NamedQuery(name = "DoubanUser.findByUid", query = "SELECT d FROM DoubanUser d WHERE d.uid = :uid"),
    @NamedQuery(name = "DoubanUser.findByName", query = "SELECT d FROM DoubanUser d WHERE d.name = :name"),
    @NamedQuery(name = "DoubanUser.findBySource", query = "SELECT d FROM DoubanUser d WHERE d.source = :source"),
    @NamedQuery(name = "DoubanUser.findBySex", query = "SELECT d FROM DoubanUser d WHERE d.sex = :sex"),
    @NamedQuery(name = "DoubanUser.findByConstellation", query = "SELECT d FROM DoubanUser d WHERE d.constellation = :constellation"),
    @NamedQuery(name = "DoubanUser.findByAge", query = "SELECT d FROM DoubanUser d WHERE d.age = :age"),
    @NamedQuery(name = "DoubanUser.findByArea", query = "SELECT d FROM DoubanUser d WHERE d.area = :area"),
    @NamedQuery(name = "DoubanUser.findByEducation", query = "SELECT d FROM DoubanUser d WHERE d.education = :education"),
    @NamedQuery(name = "DoubanUser.findByJob", query = "SELECT d FROM DoubanUser d WHERE d.job = :job")})
public class DoubanUser extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uid")
    private String uid;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "source")
    private int source;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "constellation")
    private Integer constellation;
    @Column(name = "age")
    private Integer age;
    @Column(name = "area")
    private String area;
    @Column(name = "education")
    private String education;
    @Column(name = "job")
    private String job;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "doubanUser")
    private DoubanWish doubanWish;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "doubanUser")
    private DoubanCollect doubanCollect;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doubanUser")
    private Collection<DoubanComment> doubanCommentCollection;

    public DoubanUser() {
    }

    public DoubanUser(String uid) {
        this.uid = uid;
    }

    public DoubanUser(String uid, String name, int source) {
        this.uid = uid;
        this.name = name;
        this.source = source;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getConstellation() {
        return constellation;
    }

    public void setConstellation(Integer constellation) {
        this.constellation = constellation;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public DoubanWish getDoubanWish() {
        return doubanWish;
    }

    public void setDoubanWish(DoubanWish doubanWish) {
        this.doubanWish = doubanWish;
    }

    public DoubanCollect getDoubanCollect() {
        return doubanCollect;
    }

    public void setDoubanCollect(DoubanCollect doubanCollect) {
        this.doubanCollect = doubanCollect;
    }

    @XmlTransient
    public Collection<DoubanComment> getDoubanCommentCollection() {
        return doubanCommentCollection;
    }

    public void setDoubanCommentCollection(Collection<DoubanComment> doubanCommentCollection) {
        this.doubanCommentCollection = doubanCommentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoubanUser)) {
            return false;
        }
        DoubanUser other = (DoubanUser) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "douban.entity.DoubanUser[ uid=" + uid + " ]";
    }
    
}
