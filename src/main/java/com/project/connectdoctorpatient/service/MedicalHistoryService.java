package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dao.MedicalHistoryDao;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.MedicalHistory;
import com.project.connectdoctorpatient.model.Patient;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sakib.khan
 * @since 3/3/22
 */
@Service
public class MedicalHistoryService {

    private final PatientService patientService;

    private final MedicalHistoryDao medicalHistoryDao;

    public MedicalHistoryService(PatientService patientService, MedicalHistoryDao medicalHistoryDao) {
        this.patientService = patientService;
        this.medicalHistoryDao = medicalHistoryDao;
    }

    public MedicalHistory findById(long id) {
        return medicalHistoryDao.findById(id);
    }

    public MedicalHistory findByPropertiesAndPatient(MedicalHistory medicalHistory, Patient patient) {
        return medicalHistoryDao.findByPropertiesAndPatient(medicalHistory, patient).orElse(null);
    }

    public List<MedicalHistory> findByPatient(Patient patient) {
        return medicalHistoryDao.findByPatient(patient);
    }

    public List<MedicalHistory> findAll() {
        return medicalHistoryDao.findAll();
    }

    public void saveOrUpdate(MedicalHistory medicalHistory) {
        medicalHistoryDao.saveOrUpdate(medicalHistory);
    }

    public void remove(MedicalHistory medicalHistory) {
        medicalHistoryDao.remove(medicalHistory);
    }

    public void performAction(Action action, MedicalHistory medicalHistory) {
        Patient patient = patientService.findById(SessionUtil.getUser().getId());

        switch (action) {
            case SAVE:
                medicalHistory.setPatient(patient);
                saveOrUpdate(medicalHistory);
                break;

            case UPDATE:
                saveOrUpdate(medicalHistory);
                break;

            case DELETE:
                remove(medicalHistory);
                break;
        }
    }
}
