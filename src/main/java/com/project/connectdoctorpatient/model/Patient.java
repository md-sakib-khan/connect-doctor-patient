package com.project.connectdoctorpatient.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "PATIENT")
@NamedQueries({
        @NamedQuery(
                name = "Patient.findAll",
                query = "FROM Patient"
        ),
        @NamedQuery(
                name = "Patient.findByEmailAndPhoneNo",
                query = "FROM Patient patient " +
                        "WHERE patient.email=:email " +
                        "AND patient.phoneNo=:phoneNo"
        )
})
public class Patient extends User {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private List<MedicalHistory> medicalHistories;

    @OneToMany(mappedBy = "patient")
    private List<Issue> issues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Patient patient = (Patient) o;
        return getId() == patient.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
