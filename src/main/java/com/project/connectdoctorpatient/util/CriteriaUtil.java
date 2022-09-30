package com.project.connectdoctorpatient.util;

import com.project.connectdoctorpatient.model.Issue;
import com.project.connectdoctorpatient.model.Status;
import com.project.connectdoctorpatient.model.Category;
import com.project.connectdoctorpatient.model.Patient;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * @author sakib.khan
 * @since 3/10/22
 */
public final class CriteriaUtil {

    private CriteriaUtil() {
        throw new AssertionError("Utility Class Constructor Called.");
    }

    public static CriteriaQuery<Issue> createQueryForFindByPatentAndStatusAndStatement(CriteriaBuilder criteriaBuilder,
                                                                                       Patient patient,
                                                                                       Status status,
                                                                                       Category category,
                                                                                       String problemStatement) {

        CriteriaQuery<Issue> query = criteriaBuilder.createQuery(Issue.class);
        Root<Issue> issue = query.from(Issue.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(issue.get("patient"), patient));

        return getIssueCriteriaQuery(criteriaBuilder, status, category, problemStatement, query, issue, predicates);
    }

    public static CriteriaQuery<Issue> createQueryForFindByCategoriesAndStatusAndStatement(CriteriaBuilder criteriaBuilder,
                                                                                           List<Category> categories,
                                                                                           Status status,
                                                                                           Category category,
                                                                                           String problemStatement) {

        CriteriaQuery<Issue> query = criteriaBuilder.createQuery(Issue.class);
        Root<Issue> issue = query.from(Issue.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.in(issue.get("category")).value(categories));

        return getIssueCriteriaQuery(criteriaBuilder, status, category, problemStatement, query, issue, predicates);
    }

    private static CriteriaQuery<Issue> getIssueCriteriaQuery(CriteriaBuilder criteriaBuilder,
                                                              Status status,
                                                              Category category,
                                                              String problemStatement,
                                                              CriteriaQuery<Issue> query,
                                                              Root<Issue> issue,
                                                              List<Predicate> predicates) {

        if (nonNull(status)) {
            predicates.add(criteriaBuilder.equal(issue.get("status"), status));
        }

        if (nonNull(category)) {
            predicates.add(criteriaBuilder.equal(issue.get("category"), category));
        }

        if (nonNull(problemStatement)) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(issue.get("problemStatement")),
                    "%" + problemStatement.toLowerCase() + "%"
            ));
        }

        query.select(issue).where(predicates.toArray(new Predicate[]{}));

        return query;
    }
}
