package dobackaofront;

import dobackaofront.controller.BancoDeDados;
import dobackaofront.model.Item;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        BancoDeDados banco = new BancoDeDados();
        ArrayList<Item> itens = banco.ler();

        banco.editar(3,itens);
    }

}
