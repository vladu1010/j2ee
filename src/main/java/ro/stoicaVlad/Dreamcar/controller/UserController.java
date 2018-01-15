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
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/newuser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/newuser")
    public String save(@ModelAttribute User user, Model model) {
        User savedUser = userService.save(user);
        model.addAttribute("user", savedUser);
        return "redirect:/";
    }
}
