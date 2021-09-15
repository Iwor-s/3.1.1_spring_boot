package web.config;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Component
public class DBinit {
    private final RoleService roleService;
    private final UserService userService;
    
    @PostConstruct
    public void createData() {
        roleService.saveRole(new Role("ADMIN"));
        roleService.saveRole(new Role("USER"));
        
        User user1 = new User();
        user1.setLogin("том");
        user1.setPassword("том");
        user1.setName("Tom");
        user1.setSurname("Jones");
        user1.setEmail("tom@gmail.com");
        
        User user2 = new User();
        user2.setLogin("анна");
        user2.setPassword("анна");
        user2.setName("Ann");
        user2.setSurname("Smith");
        user2.setEmail("ann@hotmail.com");
        
        User user3 = new User();
        user3.setLogin("sam");
        user3.setPassword("sam");
        user3.setName("Sam");
        user3.setSurname("Black");
        user3.setEmail("sam@yahoo.com");
    
        user1.addRole(roleService.getRoleByName("ADMIN"));
        user3.addRole(roleService.getRoleByName("ADMIN"));
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}
