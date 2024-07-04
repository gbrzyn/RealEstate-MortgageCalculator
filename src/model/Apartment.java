package model;

public class Apartment extends Mortgage{

    public Apartment(double value, double rate, int term){
        super(value, rate, term);
    }

    //  PRICE Amortization Method:
    //      PMT = Periodic Monthly Payment
    //      PV = Present Value
    //      i = Interest Rate (monthly)
    //      n = Period (months)
    //
    //  Formula:
    //      PMT = PV * (((1 + i) ^ n) * i) / (((1 + i) ^ n) - 1 )
    public double getMonthlyPayment(){
        var rate = getDecimalAPR() / 12;
        var period = getLoanTerm() * 12;

        return getRealEstateValue() * (Math.pow((1 + rate), period) * rate) / (Math.pow((1 + rate), period) - 1);
    }
}