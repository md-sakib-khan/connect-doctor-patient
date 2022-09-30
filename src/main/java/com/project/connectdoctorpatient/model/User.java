package com.project.connectdoctorpatient.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(
                name = "User.findByCredentials",
                query = "FROM User user " +
                        "WHERE user.email=:email " +
                        "AND user.password=:password"
        ),
        @NamedQuery(
                name = "User.findAll",
                query = "FROM User"
        )
})
public class User extends Persistent {

    @Column(name = "first_name")
    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 100, message = "{error.size}")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 100, message = "{error.size}")
    private String lastName;

    @Column
    @Email(message = "{error.email}")
    @NotNull(message = "{error.null}")
    private String email;

    @Column
    @NotNull(message = "{error.null}")
    @Size(min = 4, max = 100, message = "{error.size}")
    private String password;

    @Column
    @NotNull(message = "{error.null}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    @Past(message = "{error.past}")
    @NotNull(message = "{error.null}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Column(name = "phone_no")
    @NotNull(message = "{error.null}")
    @Size(min = 7, max = 20, message = "{error.size}")
    private String phoneNo;

    @Column(name = "role", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
