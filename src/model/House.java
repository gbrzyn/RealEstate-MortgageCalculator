package model;

public class House extends Mortgage{

    public House(double value, double rate, int term){
        super(value, rate, term);
    }

    //  Insurance:
    //      Monthly Payment += $80
    public double getMonthlyPayment(){
        return super.getMonthlyPayment() + 80;
    }
}