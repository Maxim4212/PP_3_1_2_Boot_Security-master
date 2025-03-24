package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String admin(Model model) {
        List<User> users = userService.getAllUsers();
        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);

        if (!model.containsAttribute("selectedUser")) {
            model.addAttribute("selectedUser", new User());
        }
        return "admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Пользователь успешно удален.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при удалении пользователя: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {

        User user = userService.getUserById(id);

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roles);
        model.addAttribute("selectedUser", user);

        return "admin";
    }

    @PostMapping("/save")
    public String saveUser(
            @RequestParam(required = false) Long id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) List<Long> roleIds,
            RedirectAttributes redirectAttributes) {

        try {
            User user;
            if (id != null) {

                user = userService.getUserById(id);
                user.setUsername(username);
                user.setPassword(password);
            } else {

                user = new User();
                user.setUsername(username);
                user.setPassword(password);
            }

            if (roleIds != null && !roleIds.isEmpty()) {
                List<Role> selectedRoles = roleService.getRolesByIds(roleIds);
                user.setRoles(new HashSet<>(selectedRoles));

                boolean hasAdminRole = selectedRoles.stream()
                        .anyMatch(role -> "ADMIN".equals(role.getName()));

                if (hasAdminRole) {
                    Role userRole = roleService.findByName("USER")
                            .orElseThrow(() -> new RuntimeException("Роль USER не найдена"));
                    user.getRoles().add(userRole);
                }
            } else {
                user.setRoles(new HashSet<>());
            }

            userService.saveUser(user);

            redirectAttributes.addFlashAttribute("successMessage",
                    id != null ? "Пользователь успешно обновлен." : "Пользователь успешно добавлен.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    id != null ? "Ошибка при обновлении пользователя: " + e.getMessage()
                            : "Ошибка при добавлении пользователя: " + e.getMessage());
        }

        return "redirect:/admin";
    }
}
