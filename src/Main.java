public class Main {
    public static void main(String[] args) {

        UserInterface user = new UserInterface();
        user.startScanner();

        double value = user.getRealEstateValue();
        double rate = user.getAnnualPercentageRate();
        int term = user.getLoanTerm();
        user.closeScanner();

        Mortgage mortgage_1 = new Mortgage(value, rate, term);

        System.out.print("\n\n");
        System.out.printf("Financiamento: R$%.2f\n", mortgage_1.getTotalMortgagePayment());
        System.out.printf("Mensalidade: R$%.2f\n", mortgage_1.getMonthlyPayment());
    }
}