import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    private static ArrayList<ItemAcervo> listaAcervo = new ArrayList<>();
    private static ArrayList<Emprestimo> listaEmprestimos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = -1;

        do {
            System.out.println("\n===== SISTEMA DE GESTÃO DA BIBLIOTECA =====");
            System.out.println("1. Cadastrar Pessoa (Aluno/Professor)");
            System.out.println("2. Cadastrar Item (Livro/Revista)");
            System.out.println("3. Listar Itens do Acervo");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Realizar Devolução");
            System.out.println("6. Busca de Itens (Por Código, Nome ou Tipo)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                String entrada = scanner.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarPessoa();
                break;
                case 2: cadastrarItem();
                break;
                case 3: listarItens();
                break;
                case 4: realizarEmprestimo();
                break;
                case 5: realizarDevolucao();
                break;
                case 6: menuBusca();
                break;
                case 0: System.out.println("Encerrando sistema...");
                break;
                default: System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

    }

    private static void cadastrarPessoa() {

        System.out.println("1. Aluno\n2. Professor");
        System.out.print("Escolha o tipo: ");

        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("E-mail: "); String email = scanner.nextLine();
        System.out.print("Registro: "); String registro = scanner.nextLine();

        if (tipo == 1) {
            listaPessoas.add(new Alunos(nome, email, registro));
        } else {
            listaPessoas.add(new Professor(nome, email, registro));
        }

        System.out.println("Cadastro realizado com sucesso!");
    }

    private static void cadastrarItem() {

        System.out.println("1. Livro\n2. Revista");
        System.out.print("Escolha o tipo: ");

        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Título: "); String titulo = scanner.nextLine();
        System.out.print("Código: "); String codigo = scanner.nextLine();

        if (tipo == 1) {

            System.out.print("Autor: "); String autor = scanner.nextLine();
            System.out.print("ISBN: "); String isbn = scanner.nextLine();
            listaAcervo.add(new Livro(titulo, codigo, autor, isbn));

        } else {

            System.out.print("Edição: "); String edicao = scanner.nextLine();
            listaAcervo.add(new Revista(titulo, codigo, edicao));

        }

        System.out.println("Item cadastrado!");

    }

    private static void listarItens() {

        if (listaAcervo.isEmpty()) {

            System.out.println("O acervo está vazio.");

        } else {

            for (ItemAcervo item : listaAcervo) {

                System.out.println(item);

            }
        }
    }

    private static void realizarEmprestimo() {

        System.out.print("Informe o código do item: ");

        String codigo = scanner.nextLine();
        ItemAcervo item = buscarItem(codigo);

        if (item == null) {

            System.out.println("Item não encontrado!");
            return;

        }

        System.out.print("Informe o registro da pessoa: ");

        String registro = scanner.nextLine();
        Pessoa pessoa = buscarPessoa(registro);

        if (pessoa == null) {

            System.out.println("Pessoa não encontrada!");
            return;

        }

        try {

            if (!item.isDisponivel()) {

                throw new ItemIndisponivelException("O item " + item.getTitulo() + " já está emprestado!");
            }

            if (pessoa instanceof Alunos) {

                Alunos a = (Alunos) pessoa;

                if (contarEmprestimos(pessoa) >= a.getLimiteEmprestimos()) {

                    throw new LimiteEmprestimosExcedidoException("O aluno já atingiu o limite de empréstimos.");

                }
            }

            item.emprestar();
            listaEmprestimos.add(new Emprestimo(pessoa, item));

            System.out.println("Empréstimo realizado com sucesso!");

        } catch (ItemIndisponivelException | LimiteEmprestimosExcedidoException e) {

            System.out.println("Erro no empréstimo: " + e.getMessage());

        }
    }

    private static void realizarDevolucao() {

        System.out.print("Informe o código do item para devolver: ");

        String codigo = scanner.nextLine();
        ItemAcervo item = buscarItem(codigo);

        if (item != null) {

            item.devolver();
            System.out.println("Item devolvido com sucesso!");

        } else {

            System.out.println("Item não encontrado.");

        }
    }

    private static void menuBusca() {

        System.out.println("1. Buscar por Código\n2. Buscar por Nome\n3. Buscar por Tipo");
        System.out.print("Escolha o tipo de busca: ");

        int tipoBusca = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o termo de busca: ");

        String termo = scanner.nextLine();

        switch (tipoBusca) {

            case 1:
                ItemAcervo i = buscarItem(termo);
                System.out.println(i != null ? i : "Item não encontrado.");
                break;
            case 2:
                for (ItemAcervo item : listaAcervo) {
                    if (item.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                        System.out.println(item);
                    }
                }
                break;
            case 3:
                for (ItemAcervo item : listaAcervo) {
                    if (item instanceof Livro) System.out.println("Livro: " + item);
                    else if (item instanceof Revista) System.out.println("Revista: " + item);
                }

                break;

        }
    }

    private static ItemAcervo buscarItem(String codigo) {
        for (ItemAcervo i : listaAcervo) {
            if (i.getCodigo().equals(codigo)) return i;
        }
        return null;
    }

    private static Pessoa buscarPessoa(String registro) {

        for (Pessoa p : listaPessoas) {

            if (p.getRegistro().equals(registro))
                return p;

        }

        return null;

    }

    private static int contarEmprestimos(Pessoa p) {

        int count = 0;

        for (Emprestimo e : listaEmprestimos) {

            if (e.toString().contains(p.getNome())) count++;

        }

        return count;
    }
}