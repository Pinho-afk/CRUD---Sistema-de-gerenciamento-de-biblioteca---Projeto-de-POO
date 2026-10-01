import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Listas para armazenar os dados em memória
    private static ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static ArrayList<ItemAcervo> acervo = new ArrayList<>();
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;

        do {
            System.out.println("\n===== SISTEMA DE GESTÃO DA BIBLIOTECA =====");
            System.out.println("1. Cadastrar Pessoa (Aluno ou Professor)");
            System.out.println("2. Cadastrar Item do Acervo (Livro ou Revista)");
            System.out.println("3. Listar Itens do Acervo");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Realizar Devolução");
            System.out.println("6. Buscar Item (Recurso de Pesquisa)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarPessoa(); break;
                case 2: cadastrarItem(); break;
                case 3: listarItens(); break;
                case 4: realizarEmprestimo(); break;
                case 5: realizarDevolucao(); break;
                case 6: menuBusca(); break;
                case 0: System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }