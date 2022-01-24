package Banco;

public class ContaPoupanca extends Conta {
    private final double rendimento;

    public ContaPoupanca() {
        this.rendimento = 0.01;
    }

    public void render(){//O metódo adiciona um rendimento ao saldo.
        saldo = saldo + (saldo*rendimento);
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "agencia=" + agencia +
                ", numero=" + numero +
                ", saldo=" + saldo +
                ", rendimento=" + rendimento +
                '}';
    }
}
