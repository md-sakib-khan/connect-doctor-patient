package com.project.connectdoctorpatient.dao;

import com.project.connectdoctorpatient.model.Appointment;
import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 3/6/22
 */
@Repository
public class AppointmentDao extends ParentDao<Appointment> {

    public Appointment findById(long id) {
        return em.find(Appointment.class, id);
    }

    public Optional<Appointment> findByPropertiesAndDoctor(Appointment appointment, Doctor doctor) {
        return em.createNamedQuery("Appointment.findByPropertiesAndDoctor", Appointment.class)
                .setParameter("doctor", doctor)
                .setParameter("date", appointment.getDate())
                .setParameter("details", appointment.getDetails())
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<Appointment> findByDoctor(Doctor doctor) {
        return em.createNamedQuery("Appointment.findByDoctor", Appointment.class)
                .setParameter("doctor", doctor)
                .getResultList();
    }

    public List<Appointment> findByPatient(Patient patient) {
        return em.createNamedQuery("Appointment.findByPatient", Appointment.class)
                .setParameter("patient", patient)
                .getResultList();
    }

    public List<Appointment> findAll() {
        return em.createNamedQuery("Appointment.findAll", Appointment.class)
                .getResultList();
    }
}
