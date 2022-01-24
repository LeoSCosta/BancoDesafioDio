package Banco;

import Banco.Segurança.SaldoInsuficienteException;

public class ContaCorrente extends Conta{
    private double limite;
    private double taxa;

    public ContaCorrente() {
        limite = 100;
        taxa = 0.05;
    }

    @Override
    public void sacar(double valor, int senha) {
        //O saldo da conta corrente conta com um limite que é acesso quando o saldo chega a zero
        super.verificarSenha(senha);
        if(valor <= limite + saldo){
            if (saldo < valor) {
                limite += saldo - valor;
                saldo = 0;
            } else {
                super.sacar(valor, senha);
            }
        }else{
            throw new SaldoInsuficienteException("Saldo insulficiente");
        }
    }

    @Override
    public void depositar(double valor) {
        if (limite == 100)//Caso o limite esteja sendo ultilizado, o metodo faz o depósito normalmente
            super.depositar(valor);
        else{//caso não esteja o valor do limite deve ser retornado
            limite -= 100;
            valor = valor + (limite*taxa);//é cobrado uma taxa por ultilização do limite
            // (a taxa é igual a 5% do valor gasto do limite, se foi gasto $100 a taxa sera igual a $5)
            saldo = valor+limite;
            limite = 100;
            if (saldo< 0 ){
                limite += saldo;
                saldo =0;
            }
        }
    }

    @Override
    public void transferir(double valor, Conta destino, int senha) {
        super.verificarSenha(senha);
        if(valor < saldo)
            super.transferir(valor, destino, senha);
        else{
            this.sacar(valor,senha);
            destino.depositar(valor);
        }
    }

    public double getLimite() {
        return limite;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "agencia=" + agencia +
                ", numero=" + numero +
                ", saldo=" + saldo +
                ", limite=" + limite +
                '}';
    }
}
