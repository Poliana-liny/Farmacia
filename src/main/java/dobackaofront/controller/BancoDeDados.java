package dobackaofront.controller;

import dobackaofront.model.Item;

import java.io.*;
import java.util.ArrayList;

public class BancoDeDados {
    private Item item;

    public BancoDeDados() {

    }

    public void cadastrar(Item item, boolean opcao) {
        try {
            OutputStream os = new FileOutputStream("medicamentos.txt", opcao);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String linha = item.getNome() + "," + item.getQuantidade() + "," + item.getTipo();

            bw.write(linha);
            bw.newLine();

            bw.close();
            osw.close();
            os.close();
            System.out.println("O Medicamento " + item.getNome() + " foi cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Não conseguiu cadastrar o medicamento");
            System.out.println(e);
        }
    }

    public void editar(int codigo, ArrayList<Item> itens) {
        try {
            // Verifica se o índice é válido
            if (codigo < 0 || codigo >= itens.size()) {
                throw new IndexOutOfBoundsException("Código inválido: índice fora do intervalo da lista.");
            }

            // Obtém o item no índice especificado
            Item item = itens.get(codigo);

            // Atualiza os atributos do item
            item.setNome("Tilenol 200ml xsptk72");
            item.setQuantidade(300);
            item.setTipo("Frasco de 200ml");

            // Substitui o item editado na mesma posição
            itens.set(codigo, item);

            // Mensagem de sucesso
            System.out.println("Medicamento editado com sucesso!");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: Índice inválido ao tentar editar o medicamento.");
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("Erro inesperado ao editar o medicamento.");
            System.out.println(e.getMessage());

            // Reprocessa todos os itens para garantir que sejam persistidos
            for (int i = 0; i < itens.size(); i++) {
                if (i == 0) {
                    cadastrar(itens.get(i), false); // Primeiro item
                } else {
                    cadastrar(itens.get(i), true); // Itens subsequentes
                }
            }
        }
    }


    public Item pesquisar(int codigo, ArrayList<Item> itens) {
        try {
            return itens.get(codigo);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar o medicamento.");
            return null;
        }
    }

    public void excluir(int codigo, ArrayList<Item> itens) {
        try {
            itens.remove(codigo);
            salvarTodos(itens);
            System.out.println("Medicamento excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir o medicamento.");
            System.out.println(e);
        }
    }

    private void salvarTodos(ArrayList<Item> itens) {
        try {
            for (int i = 0; i < itens.size(); i++) {
                if (i == 0) {
                    cadastrar(itens.get(i), false);
                } else {
                    cadastrar(itens.get(i), true);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar os itens no arquivo.");
            System.out.println(e);
        }
    }

    public ArrayList<Item> ler() {
        try {
            InputStream is = new FileInputStream("medicamentos.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linha = br.readLine();
            ArrayList<String> linhas = new ArrayList<>();

            while (linha != null) {
                linhas.add(linha);
                linha = br.readLine();
            }

            br.close();
            isr.close();
            is.close();

            ArrayList<Item> itens = new ArrayList<>();
            for (String l : linhas) {
                String[] elementos = l.split(",");
                int quantidade = Integer.parseInt(elementos[1]);
                Item item = new Item(elementos[0], quantidade, elementos[2]);
                itens.add(item);
            }

            System.out.println("Arquivo lido e itens carregados com sucesso.");
            return itens;
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo de texto.");
            System.out.println(e);
            return null;
        }
    }
}
