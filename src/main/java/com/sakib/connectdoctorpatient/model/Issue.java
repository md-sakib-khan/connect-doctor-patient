package com.sakib.connectdoctorpatient.model;

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
@Table(name = "issue")
@NamedQueries({
        @NamedQuery(
                name = "Issue.findByPropertiesAndPatient",
                query = "FROM Issue issue " +
                        "WHERE issue.patient=:patient " +
                        "AND issue.category=:category " +
                        "AND issue.problemStatement=:problemStatement " +
                        "AND issue.startDate=:startDate"
        ),
        @NamedQuery(
                name = "Issue.findAll",
                query = "FROM Issue"
        )
})
public class Issue extends Persistent {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column
    @NotNull(message = "{error.null}")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "problem_statement")
    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 3000, message = "{error.size}")
    private String problemStatement;

    @Column(name = "start_date")
    @NotNull(message = "{error.null}")
    @Past(message = "{error.past}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "doctor_response")
    @Size(min = 3, max = 3000, message = "{error.size}")
    private String doctorResponse;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
