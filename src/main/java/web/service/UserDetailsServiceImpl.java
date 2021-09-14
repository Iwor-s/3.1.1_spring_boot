package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    UserServiceImpl userService;
    
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userService.findUserByLogin(login);
    }
}
