
package model.arquivo;
import java.awt.List;
// VAI TER UM CHAVE ["FIM"]
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeitorArquivosSaveGame {
    public ArrayList<Integer> lerNumerosPokemon() {

        ArrayList<Integer> guardarNumeros = new ArrayList<>();

        try {
            File arquivo = new File("saves/kel.txt");
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                if (leitor.nextLine().equals("[TIME_POKEMON]")) {
                    break;
                }
            }

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                if (linha.equals("[PC_BOX]")) {
                    break;
                }
                String[] pedacos = linha.split(",");

                int id = Integer.parseInt(pedacos[0]);
                guardarNumeros.add(id);
            }

            leitor.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        return guardarNumeros;
    }
}
