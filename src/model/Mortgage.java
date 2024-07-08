package model;

import java.io.Serializable;

public abstract class Mortgage implements Serializable {

    private final double realEstateValue;
    private final double annualPercentageRate;
    private final int loanTerm;

    private static double totalRealEstateValues;
    private static double totalMortgagePayments;

    public Mortgage(double value, double rate, int term){
        this.realEstateValue = value;
        this.annualPercentageRate = rate;
        this.loanTerm = term;
    }

    public static double getTotalRealEstateValues(){ return totalRealEstateValues; }

    public static double getTotalMortgagePayments(){ return totalMortgagePayments; }

    public int getLoanTerm(){ return this.loanTerm; }

    public double getRealEstateValue(){ return this.realEstateValue; }

    public double getAnnualPercentageRate(){ return this.annualPercentageRate; }

    private double getMortgagePayment(){
        return (this.getMonthlyPayment() * 12) * this.loanTerm;
    }

    protected double getMonthlyPayment(){
        return (getRealEstateValue() / (getLoanTerm() * 12)) * (1 + (getAnnualPercentageRate() / 12));
    }

    public void updateTotalMortgageValues(){
        totalRealEstateValues += this.getRealEstateValue();
        totalMortgagePayments += this.getMortgagePayment();
    }

    public abstract String getMortgageInfo();

    protected String getPaymentInfo(){

        return "\nDados de Financiamento\n" +
                "------------------------------\n" +
                String.format("Valor Financiado: R$%.2f\n", this.getRealEstateValue()) +
                String.format("Juros Aplicado: %.2f%% a.a.\n", this.getAnnualPercentageRate() * 100) + // To percentage
                String.format("Prazo Estimado: %d anos\n", this.getLoanTerm()) +
                "\nDados do Pagamento\n" +
                "------------------------------\n" +
                String.format("Mensalidade: R$%.2f\n", this.getMonthlyPayment()) +
                String.format("Total Financiado: R$%.2f\n", this.getMortgagePayment());
    }

    public static String getTotalMortgageValues(int size) {
        return "\n\nTotal Financiamentos:\n" +
                "==============================\n" +
                String.format("Financiamentos simulados: %d\n", size) +
                String.format("Valor total Imóveis: R$%.2f\n", getTotalRealEstateValues()) +
                String.format("Valor total Financiado: R$%.2f\n", getTotalMortgagePayments());
    }
}