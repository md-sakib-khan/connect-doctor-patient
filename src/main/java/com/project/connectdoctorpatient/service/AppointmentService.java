package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dao.AppointmentDao;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Appointment;
import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Service
public class AppointmentService {

    private final AppointmentDao appointmentDao;

    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

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
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            saveOrUpdate(appointment);
        } else if (action.equals(DELETE)) {
            remove(appointment);
        }
    }
}
