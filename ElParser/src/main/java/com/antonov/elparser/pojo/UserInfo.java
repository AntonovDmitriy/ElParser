/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonov.elparser.pojo;

/**
 *
 * @author Antonov
 */
public class UserInfo {

    private Double IMPACT_PUBLISH; 
    private Long HIRSH;
    private String FIO;

    public Double getIMPACT_PUBLISH() {
        return IMPACT_PUBLISH;
    }

    public void setIMPACT_PUBLISH(Double IMPACT_PUBLISH) {
        this.IMPACT_PUBLISH = IMPACT_PUBLISH;
    }

    public Long getHIRSH() {
        return HIRSH;
    }

    public void setHIRSH(Long HIRSH) {
        this.HIRSH = HIRSH;
    }

    private Long AMOUNT_LETTERS;

    public Long getAMOUNT_LETTERS() {
        return AMOUNT_LETTERS;
    }

    public void setAMOUNT_LETTERS(Long AMOUNT_LETTERS) {
        this.AMOUNT_LETTERS = AMOUNT_LETTERS;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "IMPACT_PUBLISH=" + IMPACT_PUBLISH + ", HIRSH=" + HIRSH + ", AMOUNT_LETTERS=" + AMOUNT_LETTERS + ", FIO=" + FIO + '}';
    }
}
