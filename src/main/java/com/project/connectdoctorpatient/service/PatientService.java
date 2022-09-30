package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dao.PatientDao;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Service
public class PatientService {

    @Autowired
    private PatientDao patientDao;

    public Patient findById(long id) {
        return patientDao.findById(id);
    }

    public Patient findByEmailAndPhoneNo(Patient patient) {
        return patientDao.findByEmailAndPhoneNo(patient).orElse(null);
    }

    public List<Patient> findAll() {
        return patientDao.findAll();
    }

    public void saveOrUpdate(Patient patient) {
        patientDao.saveOrUpdate(patient);
    }

    public void remove(Patient patient) {
        patientDao.remove(patient);
    }

    public void performAction(Action action, Patient patient) {
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            saveOrUpdate(patient);
        } else if (action.equals(DELETE)) {
            remove(patient);
        }
    }
}
