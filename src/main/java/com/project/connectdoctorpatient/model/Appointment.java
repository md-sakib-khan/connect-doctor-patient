package com.project.connectdoctorpatient.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointment")
@NamedQueries({
        @NamedQuery(
                name = "Appointment.findAll",
                query = "FROM  Appointment"
        ),
        @NamedQuery(
                name = "Appointment.findByDoctor",
                query = "FROM Appointment apppointment " +
                        "WHERE apppointment.doctor=:doctor"
        ),
        @NamedQuery(
                name = "Appointment.findByPatient",
                query = "FROM Appointment apppointment " +
                        "WHERE apppointment.patient=:patient"
        ),
        @NamedQuery(
                name = "Appointment.findByPropertiesAndDoctor",
                query = "FROM Appointment appointment " +
                        "WHERE appointment.doctor=:doctor " +
                        "AND appointment.date=:date " +
                        "AND LOWER(appointment.details) LIKE LOWER(CONCAT('%', :details, '%')) "
        )
})
public class Appointment extends Persistent {

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column
    @NotNull(message = "{error.null}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 3000, message = "{error.size}")
    private String details;
}
