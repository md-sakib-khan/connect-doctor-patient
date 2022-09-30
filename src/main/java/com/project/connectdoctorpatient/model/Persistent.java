package com.project.connectdoctorpatient.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
@Getter
@Setter
@MappedSuperclass
public class Persistent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Version
    private long version;

    public boolean isNew() {
        return getId() == 0;
    }
}
