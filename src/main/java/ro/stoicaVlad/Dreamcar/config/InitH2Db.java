package ro.stoicaVlad.Dreamcar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.stoicaVlad.Dreamcar.domain.MenuItem;
import ro.stoicaVlad.Dreamcar.domain.Merchant;
import ro.stoicaVlad.Dreamcar.service.IMenuItemService;
import ro.stoicaVlad.Dreamcar.service.IMerchantService;

@Order(1)
@Component
public class InitH2Db implements CommandLineRunner{

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IMenuItemService menuItemService;

    @Override
    public void run(String... strings) throws Exception {
        merchantService.save(new Merchant("Jon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Qon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("mon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("fon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("eon Doe", "Bucharest", "Preciziei"));

        merchantService.save(new Merchant("Pon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Lon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Kon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Mon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Non Doe", "Bucharest", "Preciziei"));

        merchantService.save(new Merchant("Jon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Qon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("mon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("fon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("eon Doe", "Bucharest", "Preciziei"));

        merchantService.save(new Merchant("Pon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Lon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Kon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Mon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Non Doe", "Bucharest", "Preciziei"));

        merchantService.save(new Merchant("Jon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Qon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("mon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("fon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("eon Doe", "Bucharest", "Preciziei"));

        merchantService.save(new Merchant("Pon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Lon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Kon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Mon Doe", "Bucharest", "Preciziei"));
        merchantService.save(new Merchant("Non Doe", "Bucharest", "Preciziei"));

        menuItemService.save(new MenuItem("Home", "/"));
        menuItemService.save(new MenuItem("Merchant", "/newmerchant"));
        menuItemService.save(new MenuItem("List Merchants", "/merchants"));
    }
}
