package ro.stoicaVlad.Dreamcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.stoicaVlad.Dreamcar.dao.OfferRepository;
import ro.stoicaVlad.Dreamcar.domain.Auction;
import ro.stoicaVlad.Dreamcar.domain.Offer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

@Service
public class OfferService implements IOfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Offer findOne(Long id) {
        return offerRepository.findOne(id);
    }

    @Override
    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> findByItem(String item) {
        EntityManager em = emf.createEntityManager();
        List<Offer> offers = em
                .createNamedQuery(Offer.QUERY_BY_ITEM,
                        Offer.class)
                .setParameter("item", item).getResultList();
        em.close();
        return offers;
    }

    @Override
    public List<Offer> findAllByFlag() {
        EntityManager em = emf.createEntityManager();
        List<Offer> offers = em
                .createNamedQuery(Offer.QUERY_BY_FLAG,
                        Offer.class).getResultList();
        em.close();
        return offers;
    }

    @Override
    public void dezactivateByItem(String item) {
        EntityManager em = emf.createEntityManager();
        int result = em
                .createNamedQuery(Offer.DELETE,
                        Offer.class)
                .setParameter("item", item).getFirstResult();
        em.close();
    }

    @Override
    public Page<Offer> findAllPageable(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    @Override
    public Page<Offer> findAllByActiveFlagTrue(Specification<Offer> spec, Pageable pageable) {
        return offerRepository.findAll(spec, pageable);
    }

    @Override
    public List<Offer> findAllByActiveFlagTrue(Specification<Offer> spec) {
        return offerRepository.findAll(spec);
    }
}
