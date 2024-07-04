package model;

public class Land extends Mortgage{

    private String zoning;

    public Land(double value, double rate, int term, String zoning){
        super(value, rate, term);
        this.zoning = zoning;
    }

    @Override
    //  Insurance:
    //      Monthly Payment += 2%
    public double getMonthlyPayment(){
        return super.getMonthlyPayment() * 1.02;
    }

    public String getZoning(){
        return this.zoning;
    }

    public void printMortgageInfo(){
        System.out.println("Tipo: TERRENO");
        System.out.printf("Zona: %s \n", this.getZoning());

        super.printPaymentInfo();
    }
}
