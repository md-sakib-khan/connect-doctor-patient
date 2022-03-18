package com.sakib.connectdoctorpatient.service;

import com.sakib.connectdoctorpatient.dao.PatientDao;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            saveOrUpdate(patient);
        } else if (action.equals(Action.DELETE)) {
            remove(patient);
        }
    }
}
