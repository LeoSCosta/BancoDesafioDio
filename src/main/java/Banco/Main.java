package Banco;

public class Main {
    public static void main(String[] args) {
        Conta conta1 = new ContaCorrente();
        Conta conta2 = new ContaCorrente();
        Conta conta3 = new ContaPoupanca();

        System.out.println(conta1);
        System.out.println(conta2);
        conta1.setSenha(10);
        conta1.depositar(100);
        conta1.sacar(10,10);
        conta1.transferir(50,conta2,10);

        System.out.println(conta1);
        System.out.println(conta2);

        conta3.depositar(100);
        conta3.setSenha(15);
        conta3.transferir(50,conta1,15);
        System.out.println(conta3);
        System.out.println(conta1);

        conta1.getCliente().setNome("Leo");
        System.out.println(conta1.getCliente());






    }
}
