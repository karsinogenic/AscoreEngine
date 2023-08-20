package com.example.BankMega.Ascore.Model;

public class Score {
    private Integer slik;
    private Integer nonSlik;
    private Integer rc;

    public Score(){

    }
    public Score(Integer slik,Integer nonSlik,Integer rc){
        this.slik = slik;
        this.nonSlik = nonSlik;
        this.rc = rc;

    }

    public Integer getSlik() {
        return slik;
    }

    public void setSlik(Integer slik) {
        this.slik = slik;
    }

    public Integer getNonSlik() {
        return nonSlik;
    }

    public void setNonSlik(Integer nonSlik) {
        this.nonSlik = nonSlik;
    }

    public Integer getRc() {
        return rc;
    }

    public void setRc(Integer rc) {
        this.rc = rc;
    }
}
