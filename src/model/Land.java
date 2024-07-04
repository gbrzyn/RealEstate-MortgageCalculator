package model;

public class Land extends Mortgage{

    public Land(double value, double rate, int term){
        super(value, rate, term);
    }

    //  Insurance:
    //      Monthly Payment += 2%
    public double getMonthlyPayment(){
        return super.getMonthlyPayment() * 1.02;
    }
}
