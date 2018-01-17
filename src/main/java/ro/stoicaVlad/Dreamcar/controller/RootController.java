package ro.stoicaVlad.Dreamcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.stoicaVlad.Dreamcar.domain.User;
import ro.stoicaVlad.Dreamcar.service.IUserService;

@Controller
public class RootController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login_home")
    public String login(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "login_home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute User user, Model model) {
        User savedUser = userService.save(user);
        model.addAttribute("user", savedUser);
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

}
