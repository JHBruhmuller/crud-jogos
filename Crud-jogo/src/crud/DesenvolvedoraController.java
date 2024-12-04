package crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DesenvolvedoraController {
    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraService();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao = 0;
        
        do {
            try {
                System.out.println("\nEscolha uma operação: ");
                System.out.println("1 - Adicionar Desenvolvedora");
                System.out.println("2 - Buscar Desenvolvedora");
                System.out.println("3 - Atualizar Desenvolvedora");
                System.out.println("4 - Remover Desenvolvedora");
                System.out.println("5 - Listar Desenvolvedoras");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1 -> adicionarDesenvolvedora();
                    case 2 -> buscarDesenvolvedora();
                    case 3 -> atualizarDesenvolvedora();
                    case 4 -> removerDesenvolvedora();
                    case 5 -> listarDesenvolvedoras();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                scanner.nextLine(); 
            }
        } while (opcao != 0);
    }

    private void adicionarDesenvolvedora() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Nome: ");
            scanner.nextLine(); 
            String nome = scanner.nextLine();
            System.out.print("Data de Fundação (yyyy-MM-dd): ");
            LocalDate dataFundacao = LocalDate.parse(scanner.nextLine());

            Desenvolvedora desenvolvedora = new Desenvolvedora(id, nome, dataFundacao);
            desenvolvedoraService.adicionarDesenvolvedora(desenvolvedora);
            System.out.println("Desenvolvedora adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar desenvolvedora: " + e.getMessage());
        }
    }

    private void buscarDesenvolvedora() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            Desenvolvedora desenvolvedora = desenvolvedoraService.buscarDesenvolvedora(id);
            if (desenvolvedora != null) {
                System.out.println(desenvolvedora);
            } else {
                System.out.println("Desenvolvedora não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar desenvolvedora: " + e.getMessage());
        }
    }

    private void atualizarDesenvolvedora() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Novo Nome: ");
            scanner.nextLine();
            String nome = scanner.nextLine();
            System.out.print("Nova Data de Fundação (yyyy-MM-dd): ");
            LocalDate dataFundacao = LocalDate.parse(scanner.nextLine());

            Desenvolvedora desenvolvedora = new Desenvolvedora(id, nome, dataFundacao);
            desenvolvedoraService.atualizarDesenvolvedora(desenvolvedora);
            System.out.println("Desenvolvedora atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar desenvolvedora: " + e.getMessage());
        }
    }

    private void removerDesenvolvedora() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            desenvolvedoraService.removerDesenvolvedora(id);
            System.out.println("Desenvolvedora removida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover desenvolvedora: " + e.getMessage());
        }
    }

    private void listarDesenvolvedoras() {
        try {
            List<Desenvolvedora> desenvolvedoras = desenvolvedoraService.listarDesenvolvedoras();
            if (desenvolvedoras.isEmpty()) {
                System.out.println("Nenhuma desenvolvedora encontrada.");
            } else {
                desenvolvedoras.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar desenvolvedoras: " + e.getMessage());
        }
    }
}
