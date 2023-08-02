package demo2.registerandlogin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import demo2.registerandlogin.dto.UserDTO;
import demo2.registerandlogin.model.User;
import demo2.registerandlogin.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, UserDTO userDTO) {
        model.addAttribute("user", userDTO);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, UserDTO userDTO) {
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDTO userDTO, Model model) {
        User user = userService.findByUsername(userDTO.getUsername());
        if (user != null) {
            model.addAttribute("userexist", user);
            return "register";
        }
        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }

}
