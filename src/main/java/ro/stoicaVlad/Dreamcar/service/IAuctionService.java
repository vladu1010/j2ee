package ro.stoicaVlad.Dreamcar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ro.stoicaVlad.Dreamcar.domain.Auction;
import ro.stoicaVlad.Dreamcar.domain.Offer;

import java.util.List;

public interface IAuctionService {
    Auction findOne(Long id);

    Auction save(Auction auction);

    List<Auction> findByName(String name);

    List<Auction> findAll();

    @Transactional
    Page<Auction> findAllPageable(Pageable pageable);
}
