package ro.stoicaVlad.Dreamcar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.stoicaVlad.Dreamcar.domain.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

}
