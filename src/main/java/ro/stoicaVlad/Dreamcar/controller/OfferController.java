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
import ro.stoicaVlad.Dreamcar.domain.Auction;
import ro.stoicaVlad.Dreamcar.domain.Offer;
import ro.stoicaVlad.Dreamcar.domain.Pager;
import ro.stoicaVlad.Dreamcar.service.IAuctionService;
import ro.stoicaVlad.Dreamcar.service.IOfferService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OfferController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    private IOfferService offerService;

    @Autowired
    private IAuctionService auctionService;

    @PostMapping("/new_bids")
    public String init(@ModelAttribute Auction bid, Model model) {
        List<Auction> lista = auctionService.findByName(bid.getName());
        Offer offer = new Offer();
        offer.setItem(lista.get(0).getName());
        offer.setCantity(lista.get(0).getCantity());
        model.addAttribute("auction", lista.get(0));
        model.addAttribute("offer", offer);
        return "new_bids";
    }

    @PostMapping("/newbids")
    public String save(@ModelAttribute Offer offer, Model model) throws ParseException {
        List<Offer> offers = offerService.findByItem(offer.getItem());
        List<Auction> auctions = auctionService.findByName(offer.getItem());
        for (Offer of : offers) {
            of.setActiveFlag(0);
            offerService.save(of);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date todayDate = new Date();
        Date historyDate = dateFormat.parse(auctions.get(0).getExpirationDate());

        if (auctions.get(0).getMinimPrice() >= offer.getPrice() ||
                todayDate.after(historyDate))
            offer.setActiveFlag(0);
        else
            offer.setActiveFlag(1);

        offerService.save(offer);

        return "redirect:/";
    }

    @PostMapping("/cancel_bid")
    public String cancelBid(@ModelAttribute Offer offer, Model model) {
        List<Offer> offers = offerService.findByItem(offer.getItem());
        for (Offer of : offers) {
            of.setActiveFlag(0);
            offerService.save(of);
        }
        return "redirect:/";
    }

    @GetMapping("/bids")
    public String showBidsPage(Model model) throws ParseException {
        List<Offer> bids = offerService.findAllByFlag();
        List<Auction> auctions = auctionService.findAll();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date todayDate = new Date();
        for (Auction auction : auctions) {
            Date historyDate = dateFormat.parse(auction.getExpirationDate());
            if (todayDate.after(historyDate)) {
                List<Offer> offer = offerService.findByItem(auction.getName());
                if (!offer.isEmpty()) {
                    offer.get(0).setActiveFlag(0);
                    offerService.save(offer.get(0));
                }
            }
        }

        model.addAttribute("offers", bids);
        model.addAttribute("offer", new Offer());
        return "bids";
    }

    @GetMapping("/newbid")
    public String showAuctionsPage(Model model) {
        int evalPageSize = INITIAL_PAGE_SIZE;
        int evalPage = INITIAL_PAGE;

        Page<Auction> bids = auctionService.findAllPageable(new PageRequest(evalPage, evalPageSize, Sort.Direction.DESC, "id"));

        Pager pager = new Pager(bids.getTotalPages(), bids.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("auctions", bids);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        model.addAttribute("bid", new Auction());
        return "list_bids2";
    }
}
