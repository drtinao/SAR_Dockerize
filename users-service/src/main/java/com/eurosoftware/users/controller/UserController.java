package com.eurosoftware.users.controller;

import com.eurosoftware.users.DTOs.NonSenseDto;
import com.eurosoftware.users.DTOs.UserOverviewDto;
import com.eurosoftware.users.entity.User;
import com.eurosoftware.users.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        userService.save(new User(1L, "Uživatel 1", "Bydliště 1", "123456789/0123", "123456/789", "uzivatel1@sar.fav", "123456789"));
        userService.save(new User(2L, "Uživatel 2", "Bydliště 2", "123456789/0123", "123456/789", "uzivatel2@sar.fav", "123456789"));
        userService.save(new User(3L, "Uživatel 3", "Bydliště 3", "123456789/0123", "123456/789", "uzivatel3@sar.fav", "123456789"));
        userService.save(new User(4L, "Uživatel 4", "Bydliště 4", "123456789/0123", "123456/789", "uzivatel4@sar.fav", "123456789"));
        userService.save(new User(5L, "Uživatel 5", "Bydliště 5", "123456789/0123", "123456/789", "uzivatel5@sar.fav", "123456789"));
        userService.save(new User(6L, "Uživatel 6", "Bydliště 6", "123456789/0123", "123456/789", "uzivatel6@sar.fav", "123456789"));
        userService.save(new User(7L, "Uživatel 7", "Bydliště 7", "123456789/0123", "123456/789", "uzivatel7@sar.fav", "123456789"));
        userService.save(new User(8L, "Uživatel 8", "Bydliště 8", "123456789/0123", "123456/789", "uzivatel8@sar.fav", "123456789"));
    }

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser endpoint");
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public UserOverviewDto userProperties(@PathVariable("id") Long id) {
        return userService.getProperties(id);
    }

    @GetMapping("/customers/{id}")
    public List<String> assignedCustomers(@PathVariable("id") Long id) {
        return userService.getAssignedCustomers(id);
    }

    @GetMapping("/non-sense-data")
    public NonSenseDto getNonsenseData() {
        return userService.getNonsenseData();
    }
}
