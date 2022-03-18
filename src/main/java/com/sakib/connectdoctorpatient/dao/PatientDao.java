package com.sakib.connectdoctorpatient.dao;

import com.sakib.connectdoctorpatient.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
@Repository
public class PatientDao extends ParentDao<Patient> {

    public Patient findById(long id) {
        return em.find(Patient.class, id);
    }

    public Optional<Patient> findByEmailAndPhoneNo(Patient patient) {
        return em.createNamedQuery("Patient.findByEmailAndPhoneNo", Patient.class)
                .setParameter("email", patient.getEmail())
                .setParameter("phoneNo", patient.getPhoneNo())
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<Patient> findAll() {
        return em.createNamedQuery("Patient.findAll", Patient.class)
                .getResultList();
    }
}
