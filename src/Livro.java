public class Livro extends ItemAcervo {
    private String autor;
    private String isbn;

    public Livro(String titulo, String codigo, String autor, String isbn) {
        super(titulo, codigo);
        this.autor = autor;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Livro [Título=" + getTitulo() + ", Autor=" + autor + ", Disponível=" + isDisponivel() + "]";
    }
}