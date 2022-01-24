package Banco;

import Banco.Segurança.Autentificador;
import Banco.Segurança.SaldoInsuficienteException;
import Banco.Segurança.SenhaInvalidaException;

public abstract class Conta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    protected int agencia;
    protected int numero;
    protected double saldo;
    private Cliente cliente;
    protected Autentificador autentificador;

    //Método Construtor------------------------------------------------------------------
    public Conta() {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        saldo=0;
        cliente = new Cliente();
        autentificador = new Autentificador();

    }
    //Getter and Setter----------------------------------------------------------------------

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() { //Metódo responsavel por passar o saldo
        return saldo;
    }


    //Métodos da classe----------------------------------------------------------------------

    public void depositar(double valor){//Metódo responsavel por fazer depósitos na conta
        saldo +=valor;

    }
    public void sacar(double valor, int senha){//Metódo reponsavel por realizar saques na conta.
        verificarSenha(senha);
        verificarSaldo(valor);
        saldo -= valor;
    }
    public void transferir(double valor, Conta destino, int senha){
        verificarSenha(senha);
        verificarSaldo(valor);
        this.sacar(valor,senha);
        destino.depositar(valor);
    }

    //Segurança----------------------------------------------------------------
    public void verificarSaldo(double valor){
        if (saldo - valor < 0){
            throw new SaldoInsuficienteException("Saldo Insulficiente");
        }
    }

    public void setSenha(int senha){
        this.autentificador.setSenha(senha);
    }
    protected void verificarSenha(int senha){
        if (!this.autentificador.autentificar(senha))
            throw new SenhaInvalidaException("Senha invalida");
    }

    @Override
    public String toString() {
        return "Conta{" +
                "agencia=" + agencia +
                ", numero=" + numero +
                ", saldo=" + saldo +
                '}';
    }
}
