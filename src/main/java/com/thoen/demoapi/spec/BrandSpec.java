package com.thoen.demoapi.spec;

import com.thoen.demoapi.models.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrandSpec implements Specification<Brand> {
    private final BrandFilter filter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Brand> brandRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (filter.getName() != null) {

            predicates.add(cb.like(brandRoot.get("name"), "%" + filter.getName().
                    toUpperCase().replace(" ", "%20") + "%"));

        }
        if (filter.getId() != null) {
            predicates.add(cb.equal(brandRoot.get("id"), filter.getId()));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
