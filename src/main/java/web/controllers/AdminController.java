package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminController {
    private final RoleService roleService;
    private final UserService userService;
    
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }
    
    @GetMapping("")
    public String showAll(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }
    
    @GetMapping("/new")
    public String newUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }
    
    @PostMapping("")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit";
    }
    
    @GetMapping("/{id}")
    public String showInfo(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user";
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
