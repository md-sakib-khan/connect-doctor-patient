package com.project.connectdoctorpatient.dao;

import com.project.connectdoctorpatient.model.MedicalHistory;
import com.project.connectdoctorpatient.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 3/3/22
 */
@Repository
public class MedicalHistoryDao extends ParentDao<MedicalHistory> {

    public MedicalHistory findById(long id) {
        return em.find(MedicalHistory.class, id);
    }

    public Optional<MedicalHistory> findByPropertiesAndPatient(MedicalHistory medicalHistory, Patient patient) {
        return em.createNamedQuery("MedicalHistory.findByPropertiesAndPatient", MedicalHistory.class)
                .setParameter("patient", patient)
                .setParameter("category", medicalHistory.getCategory())
                .setParameter("problemStatement", medicalHistory.getProblemStatement())
                .setParameter("startDate", medicalHistory.getStartDate())
                .setParameter("endDate", medicalHistory.getEndDate())
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<MedicalHistory> findByPatient(Patient patient) {
        return em.createNamedQuery("MedicalHistory.findByPatient", MedicalHistory.class)
                .setParameter("patient", patient)
                .getResultList();
    }

    public List<MedicalHistory> findAll() {
        return em.createNamedQuery("MedicalHistory.findAll", MedicalHistory.class)
                .getResultList();
    }
}
