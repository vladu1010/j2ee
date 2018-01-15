package ro.stoicaVlad.Dreamcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.stoicaVlad.Dreamcar.dao.AuctionRepository;
import ro.stoicaVlad.Dreamcar.domain.Auction;

@Service
public class AuctionService implements IAuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public Auction findOne(Long id) {
        return auctionRepository.findOne(id);
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    @Override
    public Page<Auction> findAllPageable(Pageable pageable) {
        return auctionRepository.findAll(pageable);
    }
}
