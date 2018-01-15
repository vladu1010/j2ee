package ro.stoicaVlad.Dreamcar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.stoicaVlad.Dreamcar.domain.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
