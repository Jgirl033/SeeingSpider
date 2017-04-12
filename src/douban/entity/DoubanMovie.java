/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package douban.entity;

import common.entity.Movie;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "douban_movie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoubanMovie.findAll", query = "SELECT d FROM DoubanMovie d"),
    @NamedQuery(name = "DoubanMovie.findByMid", query = "SELECT d FROM DoubanMovie d WHERE d.mid = :mid"),
    @NamedQuery(name = "DoubanMovie.findByName", query = "SELECT d FROM DoubanMovie d WHERE d.name = :name"),
    @NamedQuery(name = "DoubanMovie.findByImgSrc", query = "SELECT d FROM DoubanMovie d WHERE d.imgSrc = :imgSrc"),
    @NamedQuery(name = "DoubanMovie.findByDirector", query = "SELECT d FROM DoubanMovie d WHERE d.director = :director"),
    @NamedQuery(name = "DoubanMovie.findByScreenwriter", query = "SELECT d FROM DoubanMovie d WHERE d.screenwriter = :screenwriter"),
    @NamedQuery(name = "DoubanMovie.findByStyle", query = "SELECT d FROM DoubanMovie d WHERE d.style = :style"),
    @NamedQuery(name = "DoubanMovie.findByArea", query = "SELECT d FROM DoubanMovie d WHERE d.area = :area"),
    @NamedQuery(name = "DoubanMovie.findByLanguage", query = "SELECT d FROM DoubanMovie d WHERE d.language = :language"),
    @NamedQuery(name = "DoubanMovie.findByReleaseTime", query = "SELECT d FROM DoubanMovie d WHERE d.releaseTime = :releaseTime"),
    @NamedQuery(name = "DoubanMovie.findByRuntime", query = "SELECT d FROM DoubanMovie d WHERE d.runtime = :runtime"),
    @NamedQuery(name = "DoubanMovie.findByAward", query = "SELECT d FROM DoubanMovie d WHERE d.award = :award"),
    @NamedQuery(name = "DoubanMovie.findByLike", query = "SELECT d FROM DoubanMovie d WHERE d.like = :like"),
    @NamedQuery(name = "DoubanMovie.findByJsonSrc", query = "SELECT d FROM DoubanMovie d WHERE d.jsonSrc = :jsonSrc"),
    @NamedQuery(name = "DoubanMovie.findByRating", query = "SELECT d FROM DoubanMovie d WHERE d.rating = :rating"),
    @NamedQuery(name = "DoubanMovie.findByEvaluationNumber", query = "SELECT d FROM DoubanMovie d WHERE d.evaluationNumber = :evaluationNumber"),
    @NamedQuery(name = "DoubanMovie.findByOne", query = "SELECT d FROM DoubanMovie d WHERE d.one = :one"),
    @NamedQuery(name = "DoubanMovie.findByTwo", query = "SELECT d FROM DoubanMovie d WHERE d.two = :two"),
    @NamedQuery(name = "DoubanMovie.findByThree", query = "SELECT d FROM DoubanMovie d WHERE d.three = :three"),
    @NamedQuery(name = "DoubanMovie.findByFour", query = "SELECT d FROM DoubanMovie d WHERE d.four = :four"),
    @NamedQuery(name = "DoubanMovie.findByFive", query = "SELECT d FROM DoubanMovie d WHERE d.five = :five")})
public class DoubanMovie extends Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mid")
    private Integer mid;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "img_src")
    private String imgSrc;
    @Basic(optional = false)
    @Column(name = "director")
    private String director;
    @Basic(optional = false)
    @Column(name = "screenwriter")
    private String screenwriter;
    @Basic(optional = false)
    @Lob
    @Column(name = "performer")
    private String performer;
    @Basic(optional = false)
    @Column(name = "style")
    private String style;
    @Basic(optional = false)
    @Column(name = "area")
    private String area;
    @Basic(optional = false)
    @Column(name = "language")
    private String language;
    @Basic(optional = false)
    @Column(name = "release_time")
    private String releaseTime;
    @Basic(optional = false)
    @Column(name = "runtime")
    private String runtime;
    @Basic(optional = false)
    @Lob
    @Column(name = "synopsis")
    private String synopsis;
    @Column(name = "award")
    private String award;
    @Basic(optional = false)
    @Column(name = "like")
    private String like;
    @Basic(optional = false)
    @Column(name = "json_src")
    private String jsonSrc;
    @Basic(optional = false)
    @Column(name = "rating")
    private double rating;
    @Basic(optional = false)
    @Column(name = "evaluation_number")
    private int evaluationNumber;
    @Basic(optional = false)
    @Column(name = "one")
    private double one;
    @Basic(optional = false)
    @Column(name = "two")
    private double two;
    @Basic(optional = false)
    @Column(name = "three")
    private double three;
    @Basic(optional = false)
    @Column(name = "four")
    private double four;
    @Basic(optional = false)
    @Column(name = "five")
    private double five;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doubanMovie")
    private Collection<DoubanBookOffice> doubanBookOfficeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doubanMovie")
    private Collection<DoubanComment> doubanCommentCollection;

    public DoubanMovie() {
    }

    public DoubanMovie(Integer mid) {
        this.mid = mid;
    }

    public DoubanMovie(Integer mid, String name, String imgSrc, String director, String screenwriter, String performer, String style, String area, String language, String releaseTime, String runtime, String synopsis, String like, String jsonSrc, double rating, int evaluationNumber, double one, double two, double three, double four, double five) {
        this.mid = mid;
        this.name = name;
        this.imgSrc = imgSrc;
        this.director = director;
        this.screenwriter = screenwriter;
        this.performer = performer;
        this.style = style;
        this.area = area;
        this.language = language;
        this.releaseTime = releaseTime;
        this.runtime = runtime;
        this.synopsis = synopsis;
        this.like = like;
        this.jsonSrc = jsonSrc;
        this.rating = rating;
        this.evaluationNumber = evaluationNumber;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getJsonSrc() {
        return jsonSrc;
    }

    public void setJsonSrc(String jsonSrc) {
        this.jsonSrc = jsonSrc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getEvaluationNumber() {
        return evaluationNumber;
    }

    public void setEvaluationNumber(int evaluationNumber) {
        this.evaluationNumber = evaluationNumber;
    }

    public double getOne() {
        return one;
    }

    public void setOne(double one) {
        this.one = one;
    }

    public double getTwo() {
        return two;
    }

    public void setTwo(double two) {
        this.two = two;
    }

    public double getThree() {
        return three;
    }

    public void setThree(double three) {
        this.three = three;
    }

    public double getFour() {
        return four;
    }

    public void setFour(double four) {
        this.four = four;
    }

    public double getFive() {
        return five;
    }

    public void setFive(double five) {
        this.five = five;
    }

    @XmlTransient
    public Collection<DoubanBookOffice> getDoubanBookOfficeCollection() {
        return doubanBookOfficeCollection;
    }

    public void setDoubanBookOfficeCollection(Collection<DoubanBookOffice> doubanBookOfficeCollection) {
        this.doubanBookOfficeCollection = doubanBookOfficeCollection;
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
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoubanMovie)) {
            return false;
        }
        DoubanMovie other = (DoubanMovie) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "douban.entity.DoubanMovie[ mid=" + mid + " ]";
    }
    
}
