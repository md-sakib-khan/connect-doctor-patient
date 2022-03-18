package com.sakib.connectdoctorpatient.service;

import com.sakib.connectdoctorpatient.dao.IssueDao;
import com.sakib.connectdoctorpatient.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sakib.connectdoctorpatient.model.Role.DOCTOR;
import static com.sakib.connectdoctorpatient.model.Status.PENDING;
import static com.sakib.connectdoctorpatient.model.Status.RESPONDED;
import static com.sakib.connectdoctorpatient.util.SessionUtil.getUser;

/**
 * @author sakib.khan
 * @since 3/4/22
 */
@Service
public class IssueService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private IssueDao issueDao;

    public Issue findById(long id) {
        return issueDao.findById(id);
    }

    public Issue findByPropertiesAndPatient(Issue issue, Patient patient) {
        return issueDao.findByPropertiesAndPatient(issue, patient).orElse(null);
    }

    public List<Issue> findByCategoriesAndStatusAndStatement(List<Category> categories,
                                                                   Status status,
                                                                   Category category,
                                                                   String problemStatement) {

        return issueDao.findByCategoriesAndStatusAndStatement(categories, status, category, problemStatement);
    }

    public List<Issue> findByPatentAndStatusAndStatement(Patient patient,
                                                               Status status,
                                                               Category category,
                                                               String problemStatement) {

        return issueDao.findByPatentAndStatusAndStatement(patient, status, category, problemStatement);
    }

    public List<Issue> findAll() {
        return issueDao.findAll();
    }

    public void saveOrUpdate(Issue issue) {
        issueDao.saveOrUpdate(issue);
    }

    public void remove(Issue issue) {
        issueDao.remove(issue);
    }

    public void performAction(Action action, Issue issue) {
        Patient patient = patientService.findById(getUser().getId());

        switch (action) {
            case SAVE:
                issue.setPatient(patient);
                issue.setStatus(PENDING);
                saveOrUpdate(issue);
                break;

            case UPDATE:
                if (getUser().getRole() == DOCTOR) {
                    issue.setStatus(RESPONDED);
                }

                saveOrUpdate(issue);
                break;

            case DELETE:
                remove(issue);
                break;
        }
    }
}
