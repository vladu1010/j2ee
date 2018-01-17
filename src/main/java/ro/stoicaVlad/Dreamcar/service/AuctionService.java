package ro.stoicaVlad.Dreamcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.stoicaVlad.Dreamcar.dao.AuctionRepository;
import ro.stoicaVlad.Dreamcar.domain.Auction;
import ro.stoicaVlad.Dreamcar.domain.Offer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class AuctionService implements IAuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Auction findOne(Long id) {
        return auctionRepository.findOne(id);
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    @Override
    public List<Auction> findByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<Auction> auctions = em
                .createNamedQuery(Auction.QUERY_BY_NAME,
                        Auction.class)
                .setParameter("name", name).getResultList();
        em.close();
        return auctions;
    }

    @Override
    public List<Auction> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Auction> auctions = em
                .createNamedQuery(Auction.QUERY_BY_ALL,
                        Auction.class).getResultList();
        em.close();
        return auctions;
    }

    @Override
    public Page<Auction> findAllPageable(Pageable pageable) {
        return auctionRepository.findAll(pageable);
    }
}
