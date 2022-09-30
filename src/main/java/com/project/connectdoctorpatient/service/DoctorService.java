package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dao.DoctorDao;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Service
public class DoctorService {

    private final DoctorDao doctorDao;

    public DoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

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
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            saveOrUpdate(doctor);
        } else if (action.equals(DELETE)) {
            remove(doctor);
        }
    }
}
