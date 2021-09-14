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
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.addRole(roleRepository.findRoleByName("USER"));
        userRepository.save(user);
    }
    
    @Override
    public void updateUser(User user) {
        user.setPassword(userRepository.getById(user.getId()).getPassword());
        user.setEnabled(true);
        user.addRole(roleRepository.findRoleByName("USER"));
        userRepository.save(user);
    }
    
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
