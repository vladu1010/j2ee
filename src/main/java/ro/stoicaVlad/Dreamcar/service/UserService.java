package ro.stoicaVlad.Dreamcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.stoicaVlad.Dreamcar.dao.UserRepository;
import ro.stoicaVlad.Dreamcar.domain.User;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
