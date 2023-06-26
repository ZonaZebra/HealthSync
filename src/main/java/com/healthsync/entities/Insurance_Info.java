package com.healthsync.entities;

public class Insurance_Info {
    private final int policy_id; // primary key
    private String insurance_company;
    private String insurance_policy_number;
    private String insurance_group_number;

    public Insurance_Info(int policy_id, String insurance_company, String insurance_policy_number, String insurance_group_number) {
        this.policy_id = policy_id;
        this.insurance_company = insurance_company;
        this.insurance_policy_number = insurance_policy_number;
        this.insurance_group_number = insurance_group_number;
    }

    public int getPolicy_id() {
        return policy_id;
    }

    public String getInsurance_company() {
        return insurance_company;
    }

    public void setInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
    }

    public String getInsurance_policy_number() {
        return insurance_policy_number;
    }

    public void setInsurance_policy_number(String insurance_policy_number) {
        this.insurance_policy_number = insurance_policy_number;
    }

    public String getInsurance_group_number() {
        return insurance_group_number;
    }

    public void setInsurance_group_number(String insurance_group_number) {
        this.insurance_group_number = insurance_group_number;
    }

    @Override
    public String toString() {
        return "Insurance_Info{" +
                "policy_id='" + policy_id + '\'' +
                ", insurance_company='" + insurance_company + '\'' +
                ", insurance_policy_number='" + insurance_policy_number + '\'' +
                ", insurance_group_number='" + insurance_group_number + '\'' +
                '}';
    }

}
