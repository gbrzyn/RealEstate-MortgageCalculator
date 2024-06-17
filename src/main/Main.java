package main;

import model.Mortgage;
import util.UserInterface;

public class Main {
    public static void main(String[] args) {

        UserInterface user = new UserInterface();

        double value = user.getUserRealEstateValue();
        double rate = user.getUserAnnualPercentageRate();
        int term = user.getUserLoanTerm();
        user.closeScanner();

        Mortgage mortgage_1 = new Mortgage(value, rate, term);

        mortgage_1.printMortgageInfo();
    }
}