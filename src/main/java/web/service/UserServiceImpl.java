package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.models.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    // private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }
    
    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
    
    @Override
    public void saveUser(User user) {
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setEnabled(true);
        // user.addRole(roleRepository.findRoleByName("USER"));
        userRepository.save(user);
    }
    
    @Override
    public void updateUser(User user) {
        // if (!user.getPassword().startsWith("$2a$")) {
        //     user.setPassword(passwordEncoder.encode(user.getPassword()));
        // }
        userRepository.save(user);
    }
    
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
