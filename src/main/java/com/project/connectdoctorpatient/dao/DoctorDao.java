package com.project.connectdoctorpatient.dao;

import com.project.connectdoctorpatient.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Repository
public class DoctorDao extends ParentDao<Doctor> {

    public Doctor findById(long id) {
        return em.find(Doctor.class, id);
    }

    public Optional<Doctor> findByEmailAndPhone(Doctor doctor) {
        return em.createNamedQuery("Doctor.findByEmailAndPhone", Doctor.class)
                .setParameter("email", doctor.getEmail())
                .setParameter("phoneNo", doctor.getPhoneNo())
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<Doctor> findAll() {
        return em.createNamedQuery("Doctor.findAll", Doctor.class)
                .getResultList();
    }
}
