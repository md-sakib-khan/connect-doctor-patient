package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dao.IssueDao;
import com.project.connectdoctorpatient.model.*;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.connectdoctorpatient.model.Role.DOCTOR;

/**
 * @author sakib.khan
 * @since 3/4/22
 */
@Service
public class IssueService {

    private final PatientService patientService;

    private final IssueDao issueDao;

    public IssueService(PatientService patientService, IssueDao issueDao) {
        this.patientService = patientService;
        this.issueDao = issueDao;
    }

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
        Patient patient = patientService.findById(SessionUtil.getUser().getId());

        switch (action) {
            case SAVE:
                issue.setPatient(patient);
                issue.setStatus(Status.PENDING);
                saveOrUpdate(issue);
                break;

            case UPDATE:
                if (SessionUtil.getUser().getRole() == DOCTOR) {
                    issue.setStatus(Status.RESPONDED);
                }

                saveOrUpdate(issue);
                break;

            case DELETE:
                remove(issue);
                break;
        }
    }
}
