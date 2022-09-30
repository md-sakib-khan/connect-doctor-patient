package com.project.connectdoctorpatient.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medical_history")
@NamedQueries({
        @NamedQuery(
                name = "MedicalHistory.findAll",
                query = "FROM MedicalHistory"
        ),
        @NamedQuery(
                name = "MedicalHistory.findByPatient",
                query = "FROM MedicalHistory history " +
                        "WHERE history.patient=:patient " +
                        "ORDER BY history.category"
        ),
        @NamedQuery(
                name = "MedicalHistory.findByPropertiesAndPatient",
                query = "FROM MedicalHistory history " +
                        "WHERE history.patient=:patient " +
                        "AND history.category=:category " +
                        "AND history.problemStatement=:problemStatement " +
                        "AND history.startDate=:startDate " +
                        "AND history.endDate=:endDate"
        )
})
public class MedicalHistory extends Persistent {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "start_date")
    @NotNull(message = "{error.null}")
    @Past(message = "{error.past}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @NotNull(message = "{error.null}")
    @Past(message = "{error.past}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column
    @NotNull(message = "{error.null}")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "problem_statement")
    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 3000, message = "{error.size}")
    private String problemStatement;

    @Column(name = "doctor_name")
    @Size(min = 3, max = 100, message = "{error.size}")
    private String doctorName;

    @Column(name = "doctor_prescription")
    @Size(min = 3, max = 3000, message = "{error.size}")
    private String doctorPrescription;

    @Column(name = "doctor_comment")
    @Size(min = 3, max = 3000, message = "{error.size}")
    private String doctorComment;
}
