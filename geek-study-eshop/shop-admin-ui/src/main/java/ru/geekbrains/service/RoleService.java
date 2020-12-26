package ru.geekbrains.service;

import ru.geekbrains.controller.repr.RoleRepr;
import ru.geekbrains.controller.repr.UserRepr;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void save(RoleRepr roleRepr);

    List<RoleRepr> findAll();

    Optional<RoleRepr> findById(Long id);

    void delete(Long id);
}
