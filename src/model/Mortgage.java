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

    public double getTotalPayment(){
        return (this.getMonthlyPayment() * 12) * this.loanTerm;
    }

    public void printInfo(){
        System.out.println("\n");
        System.out.println("Dados de Cadastro");
        System.out.println("------------------------------");
        System.out.printf("Imóvel: R$%.2f\n", this.getRealEstateValue());
        System.out.printf("Juros: %.2f%% a.a.\n", this.getAnnualPercentageRate());
        System.out.printf("Prazo: %d anos\n\n", this.getLoanTerm());
        System.out.println("Dados de Financiamento");
        System.out.println("------------------------------");
        System.out.printf("Mensalidade: R$%.2f\n", this.getMonthlyPayment());
        System.out.printf("Total Financiado: R$%.2f\n", this.getTotalPayment());
    }
}
