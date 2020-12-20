import java.util.ArrayList;

public class Main{
    	
	public ArrayList<ModeloCliente> modeloCliente;
	public ArrayList<ModeloProduto> modeloProduto;
	public ArrayList<ModeloVenda> modeloVenda;

    public Main(){

    	modeloCliente = new ArrayList<ModeloCliente>();
		modeloProduto = new ArrayList<ModeloProduto>();
		modeloVenda = new ArrayList<ModeloVenda>();

		new FormPrincipal( modeloCliente , modeloProduto , modeloVenda);

    }

    public static void main(String[] args) {
    	new Main();
    }
}