package com.strider.posterr.application.infra.database.specification;

import com.strider.posterr.application.infra.database.entity.PostEntity;
import com.strider.posterr.application.infra.database.entity.PostEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostEntitySpecification implements Specification<PostEntity> {

    private final UUID userId;

    private final LocalDate startDate;

    private final LocalDate endDate;

    public PostEntitySpecification(UUID userId, LocalDate startDate, LocalDate endDate) {

        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    @Override
    public Predicate toPredicate(Root<PostEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (userId != null) {
            predicates.add(criteriaBuilder.equal(root.get(PostEntity_.userId), userId));
        }

        if (startDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PostEntity_.timestamp), startDate.atTime(LocalTime.MIN)));
        }

        if (endDate != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PostEntity_.timestamp), endDate.atTime(LocalTime.MAX)));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));

    }

}
