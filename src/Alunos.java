public class Alunos extends Pessoa {
    private int limiteEmprestimos;

    public Alunos(String nome, String email, String registro) {
        super(nome, email, registro);
        this.limiteEmprestimos = 3;
    }

    public Alunos(String nome, String email, String registro, int limite) {
        super(nome, email, registro);
        this.limiteEmprestimos = limite;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    @Override
    public String toString() {
        return "Aluno [Nome=" + getNome() + ", Registro=" + getRegistro() + ", Limite=" + limiteEmprestimos + "]";
    }
}