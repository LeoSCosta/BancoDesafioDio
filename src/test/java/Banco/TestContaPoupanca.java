package Banco;

import Banco.Segurança.SaldoInsuficienteException;
import Banco.Segurança.SenhaInvalidaException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestContaPoupanca {
    ContaPoupanca conta;
    @Before
    public void conta(){
        conta = new ContaPoupanca();
        conta.setSenha(123);
    }
    @Test
    public void depositar(){
        conta.depositar(100);
        assertEquals(100d,conta.getSaldo(),0);
    }
    @Test
    public void sacar(){

        conta.depositar(200);
        conta.sacar(100d,123);
        assertEquals(100, conta.getSaldo(),0);
    }
    @Test
    public void transferir(){
        Conta destino = new ContaCorrente();
        destino.setSenha(123);
        conta.depositar(100);
        conta.transferir(50,destino,123);
        assertEquals(50,conta.getSaldo(),0);
        assertEquals(50,destino.getSaldo(),0);

    }

    @Test(expected = SenhaInvalidaException.class)
    public void autentifica(){
        conta.sacar(100,1561);
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void saldoInsulficiente(){
        conta.sacar(200,123);
    }

    @Test
    public void render(){
        conta.depositar(100);
        conta.render();
        assertEquals(101,conta.getSaldo(),0);
    }



}
