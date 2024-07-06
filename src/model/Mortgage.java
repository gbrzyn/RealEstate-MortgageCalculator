package model;

//TODO - Formatar texto das mensagens

public abstract class Mortgage {

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

    public abstract void printMortgageInfo();

    protected void printPaymentInfo(){
        System.out.print("\n");
        System.out.println("Dados de Financiamento");
        System.out.println("------------------------------");
        System.out.printf("Valor Financiado: R$%.2f\n", this.getRealEstateValue());
        System.out.printf("Juros Aplicado: %.2f%% a.a.\n", this.getAnnualPercentageRate() * 100); // To percentage
        System.out.printf("Prazo Estimado: %d anos\n", this.getLoanTerm());
        System.out.print("\n");
        System.out.println("Dados do Pagamento");
        System.out.println("------------------------------");
        System.out.printf("Mensalidade: R$%.2f\n", this.getMonthlyPayment());
        System.out.printf("Total Financiado: R$%.2f\n", this.getMortgagePayment());
    }

    public static void printTotalMortgageValues(int size) {
        System.out.print("\n\n");
        System.out.println("Total Financiamentos:");
        System.out.print("==============================");
        System.out.print("\n");
        System.out.printf("Financiamentos simulados: %d\n", size);
        System.out.printf("Valor total Imóveis: R$%.2f\n", getTotalRealEstateValues());
        System.out.printf("Valor total Financiado: R$%.2f\n", getTotalMortgagePayments());
    }
}