package model;

public class Apartment extends Mortgage{

    private final int parkingSpace;
    private final int floor;

    public Apartment(double value, double rate, int term, int parkingSpaces, int floor){
        super(value, rate, term);
        this.parkingSpace = parkingSpaces;
        this.floor = floor;
    }

    public int getParkingSpaces(){ return this.parkingSpace; }

    public int getFloor(){ return this.floor; }

    @Override
    //  PRICE Amortization Method:
    //      PMT = Periodic Monthly Payment
    //      PV = Present Value
    //      i = Interest Rate (monthly)
    //      n = Period (months)
    //
    //  Formula:
    //      PMT = PV * (((1 + i) ^ n) * i) / (((1 + i) ^ n) - 1 )
    public double getMonthlyPayment(){
        var rate = getAnnualPercentageRate() / 12;
        var period = getLoanTerm() * 12;

        return getRealEstateValue() * (Math.pow((1 + rate), period) * rate) / (Math.pow((1 + rate), period) - 1);
    }

    public String getMortgageInfo(){

        return "Tipo: APARTAMENTO\n" +
                String.format("Garagem: %d vagas\n", this.getParkingSpaces()) +
                String.format("Andar: %d°\n", this.getFloor()) +
                super.getPaymentInfo();
    }
}