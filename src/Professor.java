public class Professor extends Pessoa {

    public Professor(String nome, String email, String registro) {
        super(nome, email, registro);
    }

    @Override
    public String toString() {
        return "Professor [Nome=" + nome + ", Registro=" + registro + "]";
    }
}