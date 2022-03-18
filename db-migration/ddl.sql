CREATE TABLE user
(
    role                VARCHAR(31)  NOT NULL,
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    created             DATETIME(6)  NULL,
    updated             DATETIME(6)  NULL,
    version             BIGINT       NOT NULL,
    date_of_birth       DATETIME(6)  NOT NULL,
    email               VARCHAR(255) NOT NULL,
    first_name          VARCHAR(100) NOT NULL,
    gender              VARCHAR(255) NOT NULL,
    last_name           VARCHAR(100) NOT NULL,
    password            VARCHAR(255) NOT NULL,
    phone_no            VARCHAR(255) NOT NULL,
    degree              VARCHAR(200) NULL,
    years_of_experience INT          NULL
);

CREATE TABLE appointment
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    created    DATETIME(6)  NULL,
    updated    DATETIME(6)  NULL,
    version    BIGINT       NOT NULL,
    date       DATE         NULL,
    details    VARCHAR(255) NULL,
    doctor_id  BIGINT       NULL,
    patient_id BIGINT       NULL,
    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id) REFERENCES user (id),
    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id) REFERENCES user (id)
);

CREATE TABLE expertise
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    created            DATETIME(6)  NULL,
    updated            DATETIME(6)  NULL,
    version            BIGINT       NOT NULL,
    category           VARCHAR(255) NOT NULL,
    details            VARCHAR(255) NOT NULL,
    years_of_expertise INT          NOT NULL,
    doctor_id          BIGINT       NULL,
    CONSTRAINT fk_expertise_doctor
        FOREIGN KEY (doctor_id) REFERENCES user (id)
);

CREATE TABLE issue
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    created           DATETIME(6)  NULL,
    updated           DATETIME(6)  NULL,
    version           BIGINT       NOT NULL,
    category          VARCHAR(255) NOT NULL,
    doctor_response   VARCHAR(255) NULL,
    problem_statement VARCHAR(255) NOT NULL,
    start_date        DATETIME(6)  NOT NULL,
    status            VARCHAR(255) NULL,
    doctor_id         BIGINT       NULL,
    patient_id        BIGINT       NULL,
    CONSTRAINT fk_issue_patient
        FOREIGN KEY (patient_id) REFERENCES user (id),
    CONSTRAINT fk_issue_doctor
        FOREIGN KEY (doctor_id) REFERENCES user (id)
);

CREATE TABLE medical_history
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    created             DATETIME(6)  NULL,
    updated             DATETIME(6)  NULL,
    version             BIGINT       NOT NULL,
    category            VARCHAR(255) NOT NULL,
    doctor_comment      VARCHAR(255) NULL,
    doctor_name         VARCHAR(255) NULL,
    doctor_prescription VARCHAR(255) NULL,
    end_date            DATETIME(6)  NOT NULL,
    problem_statement   VARCHAR(255) NOT NULL,
    start_date          DATETIME(6)  NOT NULL,
    patient_id          BIGINT       NULL,
    CONSTRAINT fk_medical_history_patient
        FOREIGN KEY (patient_id) REFERENCES user (id)
);
