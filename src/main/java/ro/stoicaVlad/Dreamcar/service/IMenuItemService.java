package ro.stoicaVlad.Dreamcar.service;

import ro.stoicaVlad.Dreamcar.domain.MenuItem;

import java.util.List;

public interface IMenuItemService {

    List<MenuItem> findAll();

    MenuItem save(MenuItem menuItem);
}
