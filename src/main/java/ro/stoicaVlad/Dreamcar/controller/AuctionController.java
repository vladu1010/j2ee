package ro.stoicaVlad.Dreamcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.DateUtils;
import ro.stoicaVlad.Dreamcar.domain.Auction;
import ro.stoicaVlad.Dreamcar.domain.Pager;
import ro.stoicaVlad.Dreamcar.service.IAuctionService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class AuctionController {

    @Autowired
    private IAuctionService auctionService;

    @GetMapping("/auction")
    public String newAuction(Model model) {
        model.addAttribute("auction", new Auction());
        return "new_auction";
    }

    @PostMapping("/newauction")
    public String save(@ModelAttribute Auction auction, Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        auction.setPublishDate(dateFormat.format(date));
        date.setTime(date.getTime() + auction.getExpirationTime()*100);
        auction.setExpirationDate(dateFormat.format(date));
        Auction savedAuction = auctionService.save(auction);
        model.addAttribute("auction", savedAuction);
        return "redirect:/";
    }

}
