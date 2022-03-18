package com.sakib.connectdoctorpatient.service;

import com.sakib.connectdoctorpatient.dao.ExpertiseDao;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Doctor;
import com.sakib.connectdoctorpatient.model.Expertise;
import com.sakib.connectdoctorpatient.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Service
public class ExpertiseService {

    @Autowired
    private ExpertiseDao expertiseDao;

    @Autowired
    private DoctorService doctorService;

    public Expertise findById(long id) {
        return expertiseDao.findById(id);
    }

    public Expertise findByPropertiesAndDoctor(Expertise expertise, Doctor doctor) {
        return expertiseDao.findByPropertiesAndDoctor(expertise, doctor).orElse(null);
    }

    public List<Expertise> findByDoctor(Doctor doctor) {
        return expertiseDao.findByDoctor(doctor);
    }

    public List<Expertise> findAll() {
        return expertiseDao.findAll();
    }

    public void saveOrUpdate(Expertise expertise) {
        expertiseDao.saveOrUpdate(expertise);
    }

    public void remove(Expertise expertise) {
        expertiseDao.remove(expertise);
    }

    public void performAction(Action action, Expertise expertise) {
        Doctor doctor = doctorService.findById(SessionUtil.getUser().getId());

        switch (action) {
            case SAVE:
                expertise.setDoctor(doctor);
                saveOrUpdate(expertise);
                break;

            case UPDATE:
                saveOrUpdate(expertise);
                break;

            case DELETE:
                remove(expertise);
                break;
        }
    }
}
