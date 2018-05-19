package cz.muni.fi.pv243.ars.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Enumerated
    private Role role;

    @OneToMany
    private Set<Offer> offers = new HashSet<Offer>();

    @OneToMany
    private Set<Reservation> reservations = new HashSet<Reservation>();

}
