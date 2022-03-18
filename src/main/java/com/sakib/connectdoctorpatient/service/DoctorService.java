package com.sakib.connectdoctorpatient.service;

import com.sakib.connectdoctorpatient.dao.DoctorDao;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    public Doctor findById(long id) {
        return doctorDao.findById(id);
    }

    public Doctor findByEmailAndPhone(Doctor doctor) {
        return doctorDao.findByEmailAndPhone(doctor).orElse(null);
    }

    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    public void saveOrUpdate(Doctor doctor) {
        doctorDao.saveOrUpdate(doctor);
    }

    public void remove(Doctor doctor) {
        doctorDao.remove(doctor);
    }

    public void performAction(Action action, Doctor doctor) {
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            saveOrUpdate(doctor);
        } else if (action.equals(Action.DELETE)) {
            remove(doctor);
        }
    }
}
