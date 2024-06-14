import java.util.Scanner;

/*TODO  - Adicionar localização do scanner
        - Otimizar código para tentar instanciar scanner uma vez só
*/

public class UserInterface {

    Scanner scan;

    void startScanner(){
        scan = new Scanner(System.in);
    }

    void closeScanner(){
        if(scan != null)
            scan.close();
    }

    double getRealEstateValue(){
        System.out.print("Digite o valor do imóvel: R$");
        return scan.nextDouble();
    }

    double getAnnualPercentageRate(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Digite o valor da taxa de juros (anual):");
        return scan.nextDouble();
    }

    int getLoanTerm(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Digite o prazo de financiamento (anos):");
        return scan.nextInt();
    }
}
