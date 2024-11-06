package com.hospitalcrud.dao.utilities;

public class SQLQueries {
    public static final String GET_ALL_PATIENTS = "select * from patients";
    public static final String INSERT_PATIENT = "insert into patients (name,date_of_birth,phone) values (?,?,?)";
    public static final String UPDATE_PATIENT = "update patients set name = ?, date_of_birth = ?, phone = ? where patient_id = ?";
    public static final String DELETE_PATIENT = "delete from patients where patient_id = ?";
    public static final String INSERT_CREDENTIAL = "insert into user_login (username,password,patient_id,doctor_id) values (?,?,?,?)";
    public static final String GET_CREDENTIAL = "select * from user_login where username = ?";
    public static final String DELETE_CREDENTIAL = "delete from user_login where patient_id = ?";
    //public static final String GET_ALL_PAYMENTS = "select sum(amount) from patient_payments group by patient_id";
    public static final String GET_ALL_PAYMENTS = "select * from patient_payments";
    public static final String GET_MEDICAL_RECORDS = "select * from medical_records where patient_id = ?";
    public static final String GET_PRESCRIBED_MEDICATIONS = "select * from prescribed_medications where record_id = ?";

    private SQLQueries() {
    }
}
