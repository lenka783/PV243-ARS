package cz.muni.fi.pv243.ars.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cz.muni.fi.pv243.ars.validation.AddressConstraint;

/**
 * Created by jsmolar on 5/10/18.
 */
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 25)
    private String surName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @AddressConstraint
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    private Date dateOfBirth;

    private Boolean verified;

    private Boolean isActive;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
