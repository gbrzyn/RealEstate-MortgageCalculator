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

    public void printMortgageInfo(){
        System.out.println("Tipo: CASA");
        System.out.printf("Área Construída: %.2f%% m²\n", this.getBuiltArea());
        System.out.printf("Área do Terreno: %.2f%% m²\n", this.getLandArea());

        super.printPaymentInfo();
    }

    @Override
    //  Insurance:
    //      Monthly Payment += $80
    public double getMonthlyPayment(){
        return super.getMonthlyPayment() + 80;
    }
}