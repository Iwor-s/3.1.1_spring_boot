package web.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.models.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.addRole(roleRepository.findRoleByName("USER"));  // заглушка
        userRepository.save(user);
    }
    
    @Override
    public void updateUser(User user) {
        if (user.getPassword().isBlank()) {
            user.setPassword(userRepository.getById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setEnabled(true);
        user.addRole(roleRepository.findRoleByName("USER"));  // заглушка
        userRepository.save(user);
    }
    
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
