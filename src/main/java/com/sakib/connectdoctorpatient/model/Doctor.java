package com.sakib.connectdoctorpatient.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "DOCTOR")
@NamedQueries({
        @NamedQuery(
                name = "Doctor.findAll",
                query = "FROM Doctor"
        ),
        @NamedQuery(
                name = "Doctor.findByEmailAndPhone",
                query = "FROM Doctor doctor " +
                        "WHERE doctor.email=:email " +
                        "AND doctor.phoneNo=:phoneNo"
        )
})
public class Doctor extends User {

    private static final long serialVersionUID = 1L;

    @Column
    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 200, message = "{error.size}")
    private String degree;

    @Column(name = "years_of_experience")
    @NotNull(message = "{error.null}")
    @Min(value = 1, message = "{error.min.experience}")
    private int yearsOfExperience;

    @OneToMany(mappedBy = "doctor")
    private List<Expertise> expertiseList;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private List<Issue> issues;

    public Doctor() {
        expertiseList = new ArrayList<>();
        appointments = new ArrayList<>();
        issues = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Doctor doctor = (Doctor) o;
        return getId() == doctor.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
