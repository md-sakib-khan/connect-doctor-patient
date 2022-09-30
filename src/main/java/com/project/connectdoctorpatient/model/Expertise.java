package com.project.connectdoctorpatient.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name = "expertise")
@NamedQueries({
        @NamedQuery(
                name = "Expertise.findAll",
                query = "FROM Expertise"
        ),
        @NamedQuery(
                name = "Expertise.findByDoctor",
                query = "FROM Expertise expertise " +
                        "WHERE expertise.doctor=:doctor"
        ),
        @NamedQuery(
                name = "Expertise.findByPropertiesAndDoctor",
                query = "FROM Expertise expertise " +
                        "WHERE expertise.doctor=:doctor " +
                        "AND expertise.category=:category " +
                        "AND expertise.details=:details " +
                        "AND expertise.yearsOfExpertise=:yearsOfExpertise"
        )
})
public class Expertise extends Persistent {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column
    @NotNull(message = "{error.null}")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 3000, message = "{error.size}")
    private String details;

    @Column(name = "years_of_expertise")
    @NotNull(message = "{error.null}")
    private int yearsOfExpertise;
}
