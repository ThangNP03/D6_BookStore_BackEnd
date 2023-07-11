package ra.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.user.RoleName;
import ra.model.user.Roles;
import ra.repository.IRoleRepository;
import ra.service.IRoleService;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Roles roles) {
         roleRepository.save(roles);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Roles findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Roles findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
