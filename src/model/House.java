package model;

public class House extends Mortgage{

    private double builtArea;
    private double landArea;

    public House(double value, double rate, int term, double builtArea, double landArea){
        super(value, rate, term);
        this.builtArea = builtArea;
        this.landArea = landArea;
    }

    public double getBuiltArea(){
        return this.builtArea;
    }

    public double getLandArea(){
        return this.landArea;
    }

    //  Insurance:
    //      Monthly Payment += $80
    public double getMonthlyPayment(){
        return super.getMonthlyPayment() + 80;
    }
}