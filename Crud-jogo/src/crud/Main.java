package crud;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DesenvolvedoraController desenvolvedoraController = new DesenvolvedoraController();
        JogoController jogoController = new JogoController();

        int opcao;
        do {
            System.out.println("\nEscolha o módulo para gerenciar:");
            System.out.println("1 - Desenvolvedoras");
            System.out.println("2 - Jogos");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o restante da linha.

            switch (opcao) {
                case 1 -> desenvolvedoraController.iniciar();
                case 2 -> jogoController.iniciar();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
