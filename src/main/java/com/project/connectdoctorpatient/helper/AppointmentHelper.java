package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.AppointmentController;
import com.project.connectdoctorpatient.model.*;
import com.project.connectdoctorpatient.service.AppointmentService;
import com.project.connectdoctorpatient.service.DoctorService;
import com.project.connectdoctorpatient.service.IssueService;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.project.connectdoctorpatient.model.Role.DOCTOR;
import static com.project.connectdoctorpatient.model.Role.PATIENT;

/**
 * @author sakib.khan
 * @since 3/6/22
 */
@Component
public class AppointmentHelper {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private DoctorService doctorServices;

    @Autowired
    private IssueService issueService;

    @Autowired
    private AppointmentService appointmentService;

    public void setupReferenceData(long issueId, long appointmentId, ModelMap model) {
        if (issueId != 0) {
            setupNewAppointment(issueId, model);
        } else if (appointmentId != 0) {
            setupExistingAppointment(appointmentId, model);
        }
    }

    public void setupSuccessMessage(Action action, RedirectAttributes ra) {
        ra.addFlashAttribute("message", msa.getMessage("success." + action));
    }

    public void setupList(ModelMap model) {
        List<Appointment> appointmentList = new ArrayList<>();

        User user = SessionUtil.getUser();

        if (user.getRole() == DOCTOR) {
            appointmentList = appointmentService.findByDoctor((Doctor) user);
        } else if (user.getRole() == PATIENT) {
            appointmentList = appointmentService.findByPatient((Patient) user);
        }

        model.addAttribute("appointmentList", appointmentList);
    }

    private void setupNewAppointment(long issueId, ModelMap model) {
        Issue issue = issueService.findById(issueId);
        Patient patient = issue.getPatient();
        Doctor doctor = doctorServices.findById(SessionUtil.getUser().getId());

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .build();

        model.addAttribute(AppointmentController.APPOINTMENT_CMD, appointment);
    }

    private void setupExistingAppointment(long appointmentId, ModelMap model) {
        model.addAttribute(AppointmentController.APPOINTMENT_CMD, appointmentService.findById(appointmentId));
    }
}
