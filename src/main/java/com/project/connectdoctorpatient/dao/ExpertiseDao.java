package com.project.connectdoctorpatient.dao;

import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.model.Expertise;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
@Repository
public class ExpertiseDao extends ParentDao<Expertise> {

    public Expertise findById(long id) {
        return em.find(Expertise.class, id);
    }

    public Optional<Expertise> findByPropertiesAndDoctor(Expertise expertise, Doctor doctor) {
        return em.createNamedQuery("Expertise.findByPropertiesAndDoctor", Expertise.class)
                .setParameter("doctor", doctor)
                .setParameter("category", expertise.getCategory())
                .setParameter("details", expertise.getDetails())
                .setParameter("yearsOfExpertise", expertise.getYearsOfExpertise())
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<Expertise> findByDoctor(Doctor doctor) {
        return em.createNamedQuery("Expertise.findByDoctor", Expertise.class)
                .setParameter("doctor", doctor)
                .getResultList();
    }

    public List<Expertise> findAll() {
        return em.createNamedQuery("Expertise.findAll", Expertise.class)
                .getResultList();
    }
}
