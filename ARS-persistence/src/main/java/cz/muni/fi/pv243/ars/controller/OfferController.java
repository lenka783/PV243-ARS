//package cz.muni.fi.pv243.ars.controller;
//
///**
// * Created by jsmolar on 6/23/18.
// */
//
//import java.io.IOException;
//import java.util.logging.Logger;
//
//import javax.enterprise.context.RequestScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import cz.muni.fi.pv243.ars.repository.OfferRepository;
//
//@Named
//@RequestScoped
//public class OfferController {
//
//    @Inject
//    private Logger log;
//
//    @Inject
//    private OfferRepository offerRepository;
//
//    public void openOfferDetailPage(int offer_id) throws IOException {
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        context.redirect("offerDetail.jsf?offer_id=" + offer_id);
//    }
//
//}
