package Banco.Segurança;

public class Autentificador {
    private int senha;

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public boolean autentificar(int senha){
        return this.senha == senha;
    }
}
