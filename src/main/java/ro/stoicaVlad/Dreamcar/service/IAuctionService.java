package ro.stoicaVlad.Dreamcar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ro.stoicaVlad.Dreamcar.domain.Auction;

public interface IAuctionService {
    Auction findOne(Long id);

    Auction save(Auction auction);

    @Transactional
    Page<Auction> findAllPageable(Pageable pageable);
}
