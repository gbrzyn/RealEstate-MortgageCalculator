package util;

import model.Mortgage;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileInterface {
    private final File dataBaseFile;

    public FileInterface(String dataBaseFileName) {
        dataBaseFile = new File(dataBaseFileName);
    }

    public void openDataBase() {
        try {
            if (!getDataBaseFile().createNewFile())
                Paths.get(dataBaseFile.toURI());

        } catch (IOException e) {
            System.out.println("Erro ao acessar Data Base! -> " + e.getMessage() + "\n");
        }
    }

    public File getDataBaseFile() { return dataBaseFile; }

    public void updateManyMortgage(ArrayList<Mortgage> mortgages) throws RuntimeException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(getDataBaseFile()));

            for (Mortgage mortgage : mortgages)
                objectOutputStream.writeObject(mortgage);

            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro ao acessar Data Base! -> " + e.getMessage() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar Data Base! -> " + e.getMessage() + "\n");
        }
    }

    public ArrayList<Mortgage> getManyMortgage() throws RuntimeException {
        ArrayList<Mortgage> mortgages = new ArrayList<>();
        Object obj;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(getDataBaseFile()));

            while ((obj = objectInputStream.readObject()) != null){
                if (obj instanceof Mortgage)
                    mortgages.add((Mortgage)obj);
            }
            objectInputStream.close();

        } catch (EOFException _) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro ao acessar Data Base! -> " + e.getMessage() + "\n");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao ler Data Base! -> " + e.getMessage() + "\n");
        }

        return mortgages;
    }

    public void updateReceipt(String fileName, String str) throws RuntimeException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar recibo! -> " + e.getMessage() + "\n");
        }
    }

    public String getReceipt(String fileName) throws RuntimeException {
        StringBuilder str = new StringBuilder();
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((line = bufferedReader.readLine()) != null)
                str.append(line).append("\n");

            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler recibo! -> " + e.getMessage() + "\n");
        }

        return str.toString();
    }
}