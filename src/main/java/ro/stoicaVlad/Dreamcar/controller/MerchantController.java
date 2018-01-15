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
import ro.stoicaVlad.Dreamcar.domain.Merchant;
import ro.stoicaVlad.Dreamcar.domain.Pager;
import ro.stoicaVlad.Dreamcar.service.IMerchantService;

import java.util.Optional;

@Controller
public class MerchantController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @Autowired
    private IMerchantService merchantService;

    @GetMapping("/merchant")
    public String merchant() {
        return "merchant";
    }

    @GetMapping(value = "/merchant", params = "id")
    public String findOne(@RequestParam(required = false) Optional<Long> id, Model model) {
        if(id.isPresent())
            model.addAttribute("merchant", merchantService.findOne(id.get()));
        return "merchant";
    }

    @GetMapping("/newmerchant")
    public String newMerchant(Model model) {
        model.addAttribute("merchant", new Merchant());
        return "new_merchant";
    }

    @PostMapping("/newmerchant")
    public String save(@ModelAttribute Merchant merchant, Model model) {
        Merchant savedMerchant = merchantService.save(merchant);
        model.addAttribute("merchant", savedMerchant);
        return "redirect:/merchant?id=" + savedMerchant.getId();
    }

    @GetMapping("/merchants")
    public String showMerchantsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page, Model model) {
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Merchant> merchants = merchantService.findAllPageable(new PageRequest(evalPage, evalPageSize, Sort.Direction.DESC, "id"));
        Pager pager = new Pager(merchants.getTotalPages(), merchants.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("merchants", merchants);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        return "merchants";
    }
}
