package util;

import model.Mortgage;

import java.io.*;
import java.util.ArrayList;

public class FileInterface {

    public static void updateManyMortgage(ArrayList<Mortgage> mortgages, String fileName) throws RuntimeException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));

            for (Mortgage mortgage : mortgages)
                objectOutputStream.writeObject(mortgage);

            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro ao acessar Data Base!\n\n" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar Data Base!\n\n" + e.getMessage());
        }
    }

    public static ArrayList<Mortgage> getManyMortgage(String fileName) throws RuntimeException {
        ArrayList<Mortgage> mortgages = new ArrayList<>();
        Object obj;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));

            while ((obj = objectInputStream.readObject()) != null){
                if (obj instanceof Mortgage)
                    mortgages.add((Mortgage)obj);
            }

        } catch (EOFException _) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro ao acessar Data Base!\n\n" + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao ler Data Base!\n\n" + e.getMessage());
        }

        return mortgages;
    }

    public static void updateReceipt(String str, String fileName) throws RuntimeException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar recibo!\n\n" + e.getMessage());
        }
    }

    public static String getReceipt(String fileName) throws RuntimeException {
        StringBuilder str = new StringBuilder();
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((line = bufferedReader.readLine()) != null)
                str.append(line).append("\n");

            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler recibo!\n\n" + e.getMessage());
        }

        return str.toString();
    }
}