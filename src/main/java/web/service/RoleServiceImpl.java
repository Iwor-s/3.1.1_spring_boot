package web.service;

import org.springframework.stereotype.Service;
import web.models.Role;
import web.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
    @Override
    public Role getRoleById(long id) {
        return roleRepository.getById(id);
    }
    
    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
    
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
