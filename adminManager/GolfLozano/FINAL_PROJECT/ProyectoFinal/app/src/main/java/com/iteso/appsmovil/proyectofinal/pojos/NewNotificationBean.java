package com.iteso.appsmovil.proyectofinal.pojos;

import com.owlike.genson.annotation.JsonProperty;

/**
 * Created by dideleo on 11/05/2015.
 */
public final class NewNotificationBean {

    private final String companyName;
    private final String hospitalName;
    private final String patientName;
    private final String treatmentName;
    private final String userName;
    private final int id;

    public NewNotificationBean(@JsonProperty("company") String companyName, @JsonProperty("hospital") String hospitalName, @JsonProperty("patient") String patientName, @JsonProperty("treatment") String treatmentName,@JsonProperty("userName") String userName , @JsonProperty("id") int id) {
        this.companyName = companyName;
        this.hospitalName = hospitalName;
        this.patientName = patientName;
        this.treatmentName = treatmentName;
        this.userName = userName;
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getId() {
        return id;
    }

    public String getHospitalName() {
        return hospitalName;
    }


    public String getPatientName() {
        return patientName;
    }


    public String getTreatmentName() {
        return treatmentName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString(){

        return this.getPatientName() + " " + this.getTreatmentName();
    }

}
