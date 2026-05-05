package kz.edu.unicrm.domain;
public class MarkData {
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    public MarkData(double firstAttestation, double secondAttestation, double finalExam) {
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
    }
    public double getFirstAttestation() {
        return firstAttestation;
    }
    public void setFirstAttestation(double firstAttestation) {
        this.firstAttestation = firstAttestation;
    }
    public double getSecondAttestation() {
        return secondAttestation;
    }
    public void setSecondAttestation(double secondAttestation) {
        this.secondAttestation = secondAttestation;
    }
    public double getFinalExam() {
        return finalExam;
    }
    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }
}
