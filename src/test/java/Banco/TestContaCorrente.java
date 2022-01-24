package Banco;


import Banco.Segurança.SaldoInsuficienteException;
import Banco.Segurança.SenhaInvalidaException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestContaCorrente {
    ContaCorrente conta;

    @Before
    public void conta(){
        conta = new ContaCorrente();
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

        conta.transferir(100,destino,123);
        assertEquals(0,conta.getSaldo(),0);
        assertEquals(50,conta.getLimite(),0);
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
    public void sacarlimite(){
        conta.sacar(50,123);
        assertEquals(50,conta.getLimite(),0);
        assertEquals(0,conta.getSaldo(),0);
    }
    @Test
    public void depositolimite(){
        //Teste para quando depositar acima do limite-------------------------
        conta.sacar(50,123);
        conta.depositar(200);
        assertEquals(100,conta.getLimite(),0);
        assertEquals(147.5,conta.getSaldo(),0);

        //teste quando pagar exatamente o limite---------------------------------
        conta.sacar(247.5,123);
        conta.depositar(100);
        assertEquals(95,conta.getLimite(),0);
        assertEquals(0,conta.getSaldo(),0);

        //Teste para quando não conseguir pagar o limite----------------------
        conta.sacar(85,123);
        conta.depositar(80);
        assertEquals(85.5,conta.getLimite(),1);
        assertEquals(0,conta.getSaldo(),0);
    }
}
