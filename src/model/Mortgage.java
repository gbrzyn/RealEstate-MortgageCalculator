package model;

public class Mortgage {

    private double realEstateValue;
    private double annualPercentageRate;
    private int loanTerm;

    public Mortgage(double value, double rate, int term){
        this.realEstateValue = value;
        this.annualPercentageRate = rate;
        this.loanTerm = term;
    }

    public double getRealEstateValue(){
        return this.realEstateValue;
    }

    public double getAnnualPercentageRate(){
        return this.annualPercentageRate;
    }

    public int getLoanTerm(){
        return this.loanTerm;
    }

    public double getMonthlyPayment(){
        return (this.realEstateValue / (this.loanTerm * 12)) * (1 + (this.annualPercentageRate / 12));
    }

    public double getTotalMortgagePayment(){
        return (this.getMonthlyPayment() * 12) * this.loanTerm;
    }

    public void printMortgageInfo(){
        System.out.print("\n\n");
        System.out.printf("Valor do imóvel: R$%.2f\n", this.getRealEstateValue());
        System.out.printf("Taxa de juros: %.2f%%\n", this.getAnnualPercentageRate());
        System.out.printf("Prazo de financiamento: %d anos\n", this.getLoanTerm());
        System.out.print("\n");
        System.out.printf("Valor total do financiamento: R$%.2f\n", this.getTotalMortgagePayment());
        System.out.printf("Valor da mensalidade: R$%.2f\n", this.getMonthlyPayment());
    }
}
