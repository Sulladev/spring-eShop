package ru.geekbrains.service;

import ru.geekbrains.controller.repr.BrandRepr;
import ru.geekbrains.controller.repr.RoleRepr;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    void save(BrandRepr brandRepr);

    List<BrandRepr> findAll();

    Optional<BrandRepr> findById(Long id);

    void delete(Long id);
}
