package ro.stoicaVlad.Dreamcar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.stoicaVlad.Dreamcar.domain.Merchant;

@Repository
public interface MerchantRepository  extends JpaRepository<Merchant, Long> {
}
