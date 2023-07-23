package com.mouzu.project.interview.designpattern.builder2;

public class KFCProduct {
    private String eggTart;

    private String sprint;

    private String friedChicken;

    private String familyBucket;

    public String getEggTart() {
        return eggTart;
    }

    public void setEggTart(String eggTart) {
        this.eggTart = eggTart;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getFriedChicken() {
        return friedChicken;
    }

    public void setFriedChicken(String friedChicken) {
        this.friedChicken = friedChicken;
    }

    public String getFamilyBucket() {
        return familyBucket;
    }

    public void setFamilyBucket(String familyBucket) {
        this.familyBucket = familyBucket;
    }

    @Override
    public String toString() {
        return "KFCProduct{" +
                "eggTart='" + eggTart + '\'' +
                ", sprint='" + sprint + '\'' +
                ", friedChicken='" + friedChicken + '\'' +
                ", familyBucket='" + familyBucket + '\'' +
                '}';
    }
}
