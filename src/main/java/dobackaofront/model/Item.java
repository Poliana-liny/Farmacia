package dobackaofront.model;

public class Item {
    private int codigo;
    private String nome;
    private int quantidade;
    private String tipo;

    public Item(String nome, int quantidade, String tipo){
        this.setNome(nome);
        this.setQuantidade(quantidade);
        this.setTipo(tipo);

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
