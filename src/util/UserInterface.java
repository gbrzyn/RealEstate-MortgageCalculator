package util;

import java.util.Scanner;

//TODO - Adicionar localização do scanner
//TODO - Definir regras de negócios

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

            System.out.print("Digite o valor do imóvel: R$");
            value = this.scan.nextDouble();

            if (value < 0){
                valid = false;
                System.out.print("Valor inválido!!!\n\n");
            }

        } while (!valid);

        return value;
    }

    public double getUserAnnualPercentageRate(){
        boolean valid;
        double rate;

        do{
            valid = true;

            System.out.print("Digite o valor da taxa de juros (anual):");
            rate = this.scan.nextDouble();

            //TODO - Tratar cada regra individualmente
            if (rate < 0 || rate > 1000){
                valid = false;
                System.out.print("Valor inválido!!!\n\n");
            }

        } while (!valid);

        return rate;
    }

    public int getUserLoanTerm(){
        boolean valid;
        int term;

        do{
            valid = true;

            System.out.print("Digite o prazo de financiamento (anos):");
            term = this.scan.nextInt();

            if (term < 0){
                valid = false;
                System.out.print("Valor inválido!!!\n\n");
            }

        } while (!valid);

        return term;
    }
}
