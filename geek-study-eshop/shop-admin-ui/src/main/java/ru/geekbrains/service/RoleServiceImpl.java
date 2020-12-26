package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.RoleRepr;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.repo.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(RoleRepr roleRepr) {
        Role role = new Role();
        role.setId(roleRepr.getId());
        role.setName(roleRepr.getName());
        role.setUsers(roleRepr.getUsers());
        roleRepository.save(role);
    }

    @Override
    public List<RoleRepr> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleRepr> findById(Long id) {
        return  roleRepository.findById(id).map(RoleRepr::new);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
