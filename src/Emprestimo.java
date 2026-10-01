import java.time.LocalDate;

public class Emprestimo {
    private Pessoa pessoa;
    private ItemAcervo item;
    private LocalDate data;

    public Emprestimo(Pessoa pessoa, ItemAcervo item) {
        this.pessoa = pessoa;
        this.item = item;
        this.data = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Empréstimo: " + item.getTitulo() + " para " + pessoa.getNome() + " em " + data;
    }
}