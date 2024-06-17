package util;

import java.util.Scanner;

/*TODO  - Adicionar localização do scanner
        - Otimizar código para tentar instanciar scanner uma vez só
*/

public class UserInterface {

    private Scanner scan;

    public UserInterface(){
        this.scan = new Scanner(System.in);
    }

    public void closeScanner(){
        if(scan != null)
            this.scan.close();
    }

    public double getUserRealEstateValue(){
        System.out.print("Digite o valor do imóvel: R$");
        return this.scan.nextDouble();
    }

    public double getUserAnnualPercentageRate(){
        System.out.print("Digite o valor da taxa de juros (anual):");
        return this.scan.nextDouble();
    }

    public int getUserLoanTerm(){
        System.out.print("Digite o prazo de financiamento (anos):");
        return this.scan.nextInt();
    }
}
