package ro.stoicaVlad.Dreamcar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ro.stoicaVlad.Dreamcar.domain.User;

import java.util.List;

public interface IUserService {

    User findOne(Long id);

    User save(User merchant);

    @Transactional
    Page<User> findAllPageable(Pageable pageable);

    List<User> listUsers();
}
