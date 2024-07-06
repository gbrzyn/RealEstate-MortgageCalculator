package model;

import util.DiscountRateException;

public class House extends Mortgage{

    private final double discountRate;
    private final double builtArea;
    private final double landArea;

    public House(double value, double rate, double discountRate, int term, double builtArea, double landArea) throws DiscountRateException {
        super(value, rate, term);

        this.discountRate = discountRate;
        this.builtArea = builtArea;
        this.landArea = landArea;
    }

    public double getDiscountRate(){ return this.discountRate; }

    public double getBuiltArea(){ return this.builtArea; }

    public double getLandArea(){ return this.landArea; }

    @Override
    //  Insurance:
    //      Monthly Payment += $80
    public double getMonthlyPayment(){
        return super.getMonthlyPayment() + 80;
    }

    public void printMortgageInfo(){
        System.out.println("Tipo: CASA");
        System.out.printf("Área Construída: %.2f m²\n", this.getBuiltArea());
        System.out.printf("Área do Terreno: %.2f m²\n", this.getLandArea());
        System.out.printf("Desconto: %.2f%% a.a.\n", this.getDiscountRate() * 100);

        super.printPaymentInfo();
    }
}