package co.edu.uptc.printer.model;

public class Printer {
    private int sheetsCapacity;
    private double cyanAmount;
    private double MgAmount;
    private double BlkAmount;
    private double yellowAmount;
    private int letterSheets;
    private int legalSheets;

    public int getSheetsCapacity() {
        return sheetsCapacity;
    }

    public void setSheetsCapacity(int sheetsCapacity) {
        this.sheetsCapacity = sheetsCapacity;
    }

    public double getCyanAmount() {
        return cyanAmount;
    }

    public void setCyanAmount(double cyanAmount) {
        this.cyanAmount = cyanAmount;
    }

    public double getMgAmount() {
        return MgAmount;
    }

    public void setMgAmount(double mgAmount) {
        MgAmount = mgAmount;
    }

    public double getBlkAmount() {
        return BlkAmount;
    }

    public void setBlkAmount(double blkAmount) {
        BlkAmount = blkAmount;
    }

    public double getYellowAmount() {
        return yellowAmount;
    }

    public void setYellowAmount(double yellowAmount) {
        this.yellowAmount = yellowAmount;
    }

    public int getLetterSheets() {
        return letterSheets;
    }

    public void setLetterSheets(int letterSheets) {
        this.letterSheets = letterSheets;
    }

    public int getLegalSheets() {
        return legalSheets;
    }

    public void setLegalSheets(int legalSheets) {
        this.legalSheets = legalSheets;
    }

}
