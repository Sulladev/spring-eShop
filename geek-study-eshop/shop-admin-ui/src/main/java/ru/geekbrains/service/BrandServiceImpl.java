package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.BrandRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.repo.BrandRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void save(BrandRepr brandRepr) {
        Brand brand = new Brand();
        brand.setId(brandRepr.getId());
        brand.setName(brandRepr.getName());
        brand.setProducts(brandRepr.getProducts());
        brandRepository.save(brand);
    }

    @Override
    public List<BrandRepr> findAll() {
        return brandRepository.findAll().stream()
                .map(BrandRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandRepr> findById(Long id) {
        return brandRepository.findById(id).map(BrandRepr::new);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
