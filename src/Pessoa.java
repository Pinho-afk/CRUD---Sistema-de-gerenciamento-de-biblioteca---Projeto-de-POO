public abstract class Pessoa {
    private String nome;
    private String email;
    private String registro;

    public Pessoa(String nome, String email, String registro) {
        this.nome = nome;
        this.email = email;
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}
}