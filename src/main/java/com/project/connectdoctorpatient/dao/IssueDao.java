package com.project.connectdoctorpatient.dao;

import com.project.connectdoctorpatient.model.Issue;
import com.project.connectdoctorpatient.model.Status;
import com.project.connectdoctorpatient.util.CriteriaUtil;
import com.project.connectdoctorpatient.model.Category;
import com.project.connectdoctorpatient.model.Patient;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 3/4/22
 */
@Repository
public class IssueDao extends ParentDao<Issue> {

    public Issue findById(long id) {
        return em.find(Issue.class, id);
    }

    public List<Issue> findByPatentAndStatusAndStatement(Patient patient,
                                                         Status status,
                                                         Category category,
                                                         String problemStatement) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Issue> query = CriteriaUtil.createQueryForFindByPatentAndStatusAndStatement(
                criteriaBuilder,
                patient,
                status,
                category,
                problemStatement
        );

        return em.createQuery(query)
                .getResultList();
    }

    public Optional<Issue> findByPropertiesAndPatient(Issue issue, Patient patient) {
        return em.createNamedQuery("Issue.findByPropertiesAndPatient", Issue.class)
                .setParameter("patient", patient)
                .setParameter("category", issue.getCategory())
                .setParameter("problemStatement", issue.getProblemStatement())
                .setParameter("startDate", issue.getStartDate())
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<Issue> findByCategoriesAndStatusAndStatement(List<Category> categories,
                                                             Status status,
                                                             Category category,
                                                             String problemStatement) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Issue> query = CriteriaUtil.createQueryForFindByCategoriesAndStatusAndStatement(
                criteriaBuilder,
                categories,
                status,
                category,
                problemStatement
        );

        return em.createQuery(query)
                .getResultList();
    }

    public List<Issue> findAll() {
        return em.createNamedQuery("Issue.findAll", Issue.class)
                .getResultList();
    }
}
