package com.sakib.connectdoctorpatient.service;

import com.sakib.connectdoctorpatient.dao.AppointmentDao;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Appointment;
import com.sakib.connectdoctorpatient.model.Doctor;
import com.sakib.connectdoctorpatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    public Appointment findById(long id) {
        return appointmentDao.findById(id);
    }

    public Appointment findByPropertiesAndDoctor(Appointment appointment, Doctor doctor) {
        return appointmentDao.findByPropertiesAndDoctor(appointment, doctor).orElse(null);
    }

    public List<Appointment> findByDoctor(Doctor doctor) {
        return appointmentDao.findByDoctor(doctor);
    }

    public List<Appointment> findByPatient(Patient patient) {
        return appointmentDao.findByPatient(patient);
    }

    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    public void saveOrUpdate(Appointment appointment) {
        appointmentDao.saveOrUpdate(appointment);
    }

    public void remove(Appointment appointment) {
        appointmentDao.remove(appointment);
    }

    public void performAction(Action action, Appointment appointment) {
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            saveOrUpdate(appointment);
        } else if (action.equals(Action.DELETE)) {
            remove(appointment);
        }
    }
}
