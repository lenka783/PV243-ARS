package cz.muni.fi.pv243.ars.persistence.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jsmolar on 6/25/18.
 */
@Entity
@XmlRootElement
public class UserComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String comment;

    private LocalDate toDate;

    public Long getId() {
        return id;
    }

    public UserComment setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserComment setUser(User user) {
        this.user = user;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public UserComment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public UserComment setToDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }


}
