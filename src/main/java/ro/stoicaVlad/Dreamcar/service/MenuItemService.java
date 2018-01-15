package ro.stoicaVlad.Dreamcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.stoicaVlad.Dreamcar.dao.MenuItemRepository;
import ro.stoicaVlad.Dreamcar.domain.MenuItem;

import java.util.List;

@Service
public class MenuItemService implements IMenuItemService{

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
}
