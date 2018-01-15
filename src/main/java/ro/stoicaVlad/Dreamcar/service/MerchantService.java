package ro.stoicaVlad.Dreamcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.stoicaVlad.Dreamcar.dao.MerchantRepository;
import ro.stoicaVlad.Dreamcar.domain.Merchant;

@Service
public class MerchantService implements IMerchantService{

    @Autowired
    private MerchantRepository merchantRepository;

    public Merchant findOne(Long id){
        return merchantRepository.findOne(id);
    }

    @Transactional
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public Page<Merchant> findAllPageable(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }
}
