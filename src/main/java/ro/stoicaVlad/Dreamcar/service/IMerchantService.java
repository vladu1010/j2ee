package ro.stoicaVlad.Dreamcar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ro.stoicaVlad.Dreamcar.domain.Merchant;

public interface IMerchantService {

    Merchant findOne(Long id);

    Merchant save(Merchant merchant);

    @Transactional
    Page<Merchant> findAllPageable(Pageable pageable);
}
