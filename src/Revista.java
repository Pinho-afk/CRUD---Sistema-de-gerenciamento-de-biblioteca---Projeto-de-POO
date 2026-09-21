public class Revista extends ItemAcervo {
    private String edicao;

    public Revista(String titulo, String codigo, String edicao) {
        super(titulo, codigo);
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return "Revista [Título=" + titulo + ", Edição=" + edicao + ", Disponível=" + disponivel + "]";
    }
}