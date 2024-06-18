package util;

import java.util.Scanner;

public class UserInterface {

    private Scanner scan;

    public UserInterface(){
        this.scan = new Scanner(System.in);
    }

    public void closeScanner(){
        if(this.scan != null)
            this.scan.close();
    }

    public double getUserRealEstateValue(){
        boolean valid;
        double value;

        do{
            valid = true;

            System.out.print("\nDigite o valor do imóvel: R$");
            value = this.scan.nextDouble();

            if (value < 0){
                System.out.print("Valor inválido! Digite um valor positivo.\n");
                valid = false;
            }

        } while (!valid);

        return value;
    }

    public double getUserAnnualPercentageRate(){
        boolean valid;
        double rate;

        do{
            valid = true;

            System.out.print("\nDigite a taxa de juros (anual):");
            rate = this.scan.nextDouble();

            if (rate < 0){
                System.out.print("Taxa inválida! Digite uma taxa positiva.\n");
                valid = false;
            }
            else if (rate > 100){
                System.out.print("Não é permitido taxas maiores que 100% a.a.!\n");
                valid = false;
            }

        } while (!valid);

        return rate;
    }

    public int getUserLoanTerm(){
        boolean valid;
        int term;

        do{
            valid = true;

            System.out.print("\nDigite o prazo de financiamento (anos):");
            term = this.scan.nextInt();

            if (term < 0){
                System.out.print("Prazo inválido! Digite um prazo positivo.\n");
                valid = false;
            }
            else if (term > 50){
                System.out.print("Não é permitido prazos maiores que 50 anos!\n");
                valid = false;
            }

        } while (!valid);

        return term;
    }
}
