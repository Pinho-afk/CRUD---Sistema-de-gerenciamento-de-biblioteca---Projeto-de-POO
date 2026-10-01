public abstract class ItemAcervo implements Emprestavel {
    private String titulo;
    private String codigo;
    private boolean disponivel;

    public ItemAcervo(String titulo, String codigo) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public void emprestar() {
        this.disponivel = false;
    }

    @Override
    public void devolver() {
        this.disponivel = true;
    }
}