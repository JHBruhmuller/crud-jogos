package crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class JogoController {
    private JogoService jogoService = new JogoService();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao = 0;
        do {
            try {
                System.out.println("\nEscolha uma operação: ");
                System.out.println("1 - Adicionar Jogo");
                System.out.println("2 - Buscar Jogo");
                System.out.println("3 - Atualizar Jogo");
                System.out.println("4 - Remover Jogo");
                System.out.println("5 - Listar Jogos");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcao) {
                    case 1 -> adicionarJogo();
                    case 2 -> buscarJogo();
                    case 3 -> atualizarJogo();
                    case 4 -> removerJogo();
                    case 5 -> listarJogos();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                scanner.nextLine(); 
            }
        } while (opcao != 0);
    }

    private void adicionarJogo() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Nome: ");
            scanner.nextLine(); 
            String nome = scanner.nextLine();
            System.out.print("Data de Lançamento (yyyy-MM-dd): ");
            LocalDate dataLancamento = LocalDate.parse(scanner.nextLine());
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            System.out.print("Gênero: ");
            scanner.nextLine(); 
            String genero = scanner.nextLine();
            System.out.print("ID da Desenvolvedora: ");
            int idDesenvolvedora = scanner.nextInt();

            Jogo jogo = new Jogo(id, nome, dataLancamento, preco, genero, idDesenvolvedora);
            jogoService.adicionarJogo(jogo);
            System.out.println("Jogo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar jogo: " + e.getMessage());
        }
    }

    private void buscarJogo() {
        try {
            System.out.print("ID do Jogo: ");
            int id = scanner.nextInt();
            Jogo jogo = jogoService.buscarJogo(id);
            if (jogo != null) {
                System.out.println(jogo);
            } else {
                System.out.println("Jogo não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar jogo: " + e.getMessage());
        }
    }

    private void atualizarJogo() {
        try {
            System.out.print("ID do Jogo: ");
            int id = scanner.nextInt();
            System.out.print("Novo Nome: ");
            scanner.nextLine();
            String nome = scanner.nextLine();
            System.out.print("Nova Data de Lançamento (yyyy-MM-dd): ");
            LocalDate dataLancamento = LocalDate.parse(scanner.nextLine());
            System.out.print("Novo Preço: ");
            double preco = scanner.nextDouble();
            System.out.print("Novo Gênero: ");
            scanner.nextLine(); 
            String genero = scanner.nextLine();
            System.out.print("ID da Desenvolvedora: ");
            int idDesenvolvedora = scanner.nextInt();

            Jogo jogo = new Jogo(id, nome, dataLancamento, preco, genero, idDesenvolvedora);
            jogoService.atualizarJogo(jogo);
            System.out.println("Jogo atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar jogo: " + e.getMessage());
        }
    }

    private void removerJogo() {
        try {
            System.out.print("ID do Jogo: ");
            int id = scanner.nextInt();
            jogoService.removerJogo(id);
            System.out.println("Jogo removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover jogo: " + e.getMessage());
        }
    }

    private void listarJogos() {
        try {
            List<Jogo> jogos = jogoService.listarJogos();
            if (jogos.isEmpty()) {
                System.out.println("Nenhum jogo encontrado.");
            } else {
                jogos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar jogos: " + e.getMessage());
        }
    }
}
