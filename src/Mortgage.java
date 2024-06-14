public class Mortgage {

    double realEstateValue;
    double annualPercentageRate;
    int loanTerm;

    Mortgage(double value, double rate, int term){
        this.realEstateValue = value;
        this.annualPercentageRate = rate;
        this.loanTerm = term;
    }

    double getMonthlyPayment(){
        return (realEstateValue / (loanTerm * 12)) * (1 + (annualPercentageRate / 12));
    }

    double getTotalMortgagePayment(){
        double monthlyPayment = this.getMonthlyPayment();
        return (monthlyPayment * 12) * loanTerm;
    }
}
