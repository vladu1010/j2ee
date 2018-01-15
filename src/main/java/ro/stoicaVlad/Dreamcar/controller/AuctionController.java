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
import org.springframework.web.bind.annotation.RequestParam;
import ro.stoicaVlad.Dreamcar.domain.Auction;
import ro.stoicaVlad.Dreamcar.domain.Merchant;
import ro.stoicaVlad.Dreamcar.domain.Pager;
import ro.stoicaVlad.Dreamcar.service.IAuctionService;

import java.util.Optional;

@Controller
public class AuctionController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @Autowired
    private IAuctionService auctionService;

    @GetMapping("/auction")
    public String newAuction(Model model) {
        model.addAttribute("auction", new Auction());
        return "new_auction";
    }

    @PostMapping("/newauction")
    public String save(@ModelAttribute Auction user, Model model) {
        Auction savedAuction = auctionService.save(user);
        model.addAttribute("auction", savedAuction);
        return "redirect:/";
    }

    @GetMapping("/auctions")
    public String showAuctionsPage(Model model) {
        int evalPageSize = INITIAL_PAGE_SIZE;
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = INITIAL_PAGE;

        Page<Auction> auctions = auctionService.findAllPageable(new PageRequest(evalPage, evalPageSize, Sort.Direction.DESC, "id"));
        Pager pager = new Pager(auctions.getTotalPages(), auctions.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("auctions", auctions);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        return "auctions";
    }
}
