package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dao.ExpertiseDao;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.model.Expertise;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Service
public class ExpertiseService {

    private final ExpertiseDao expertiseDao;

    private final DoctorService doctorService;

    public ExpertiseService(ExpertiseDao expertiseDao, DoctorService doctorService) {
        this.expertiseDao = expertiseDao;
        this.doctorService = doctorService;
    }

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
