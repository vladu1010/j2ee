package ro.stoicaVlad.Dreamcar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import ro.stoicaVlad.Dreamcar.domain.Offer;

import java.util.List;

public interface IOfferService {

    Offer findOne(Long id);

    Offer save(Offer offer);

    List<Offer> findByItem(String item);

    void dezactivateByItem(String item);

    @Transactional
    Page<Offer> findAllPageable(Pageable pageable);

    Page<Offer> findAllByActiveFlagTrue(Specification<Offer> spec, Pageable pageable);

    List<Offer> findAllByActiveFlagTrue(Specification<Offer> spec);

    List<Offer> findAllByFlag();
}
