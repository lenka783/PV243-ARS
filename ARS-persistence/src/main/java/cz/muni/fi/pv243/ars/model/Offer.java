package cz.muni.fi.pv243.ars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
public class Offer {

    @Id
    @GeneratedValue
    @Column(name = "offer_id")
    private Long id;

}
