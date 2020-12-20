
import java.util.ArrayList;

public class ModeloVenda 
{   
    private String nomeCliente;
    private double valorTotal;
    private double valorEntregue;
    private double troco;
    private ArrayList<ModeloProduto> produtos;
    
    public ModeloVenda(){
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorEntregue() {
        return valorEntregue;
    }

    public void setValorEntregue(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public ArrayList<ModeloProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ModeloProduto> produtos) {
        this.produtos = produtos;
    }	
}