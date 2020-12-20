import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormVendas extends JFrame {

	private ArrayList<ModeloProduto> modeloProduto;
	private ArrayList<ModeloProduto> produtoCliente;
	private ArrayList<ModeloVenda> modeloVenda;
	public ArrayList<ModeloCliente> modeloCliente;

	private double valorTotal = 0; 

	private JLabel lblNomeCliente;
	private JLabel lblValorTotal;
	private JLabel lblValorEntregue;
	private JLabel lblTroco;
	private JTextField txtNomeCliente;
	private JTextField txtValorTotal;
	private JTextField txtValorEntregue;
	private JTextField txtTroco;
	private JPanel painelCentro;
	private JPanel painelBotoes;
	private JPanel painelTabelaProdutos;
	private JPanel painelItens;
	private JPanel painelVenda;
	private JButton btnAdicionar;
	private JButton btnVender;
	private JButton btnValidar;
	private JButton btnCancelar;
	private JTable tabelaProdutos;
	private JTable tabelaProdutosCliente;
	private JComboBox cmbNomeClientes;

	public FormVendas( ArrayList<ModeloProduto> modeloProduto , ArrayList<ModeloVenda> modeloVenda, ArrayList<ModeloCliente> modeloCliente)
	{
		super("Efectuar Venda");
		inicComponents();
		addComponents();
		addEventos();
        //Adicionado as Propriedades do formulario
        setVisible(true);
        setSize(800,400);
        setResizable(false);
        setLocationRelativeTo(null);

        this.modeloProduto = modeloProduto;
        this.modeloVenda = modeloVenda;
        this.modeloCliente = modeloCliente;

        produtoCliente = new ArrayList<ModeloProduto>();

        preencherTabelaProdutos();
       	preencherCmbClientes();
        preencherTabelaCliente();
	}

	void inicComponents(){
		lblNomeCliente = new JLabel(" Nome Completo");
		cmbNomeClientes = new JComboBox();
		lblValorTotal = new JLabel(" Valor Total");
		lblValorEntregue = new JLabel(" Valor Entregue");
		lblTroco = new JLabel(" Troco");
		txtNomeCliente = new JTextField();
		txtValorTotal = new JTextField();
		txtValorTotal.setEditable(false);
		txtValorEntregue = new JTextField();
		txtTroco = new JTextField();
		txtTroco.setEditable(false);
		painelBotoes = new JPanel();
		painelCentro = new JPanel();
		painelCentro.setLayout(new GridLayout(1,2,10,10));
		painelTabelaProdutos = new JPanel();
		painelTabelaProdutos.setLayout(new GridLayout(2,1,10,10));
		painelVenda = new JPanel();
		painelVenda.setLayout(new GridLayout(4,2,10,15));
		painelItens = new JPanel();
		btnAdicionar = new JButton("Adicionar a Lista");
		btnVender = new JButton("Efectuar Venda");
		btnVender.setEnabled(false);
		btnValidar = new JButton("Validar Venda");
		btnCancelar = new JButton("Cancelar");
		tabelaProdutos = new JTable();
		tabelaProdutosCliente = new JTable();
		tabelaProdutosCliente.setModel(new DefaultTableModel(new String[0][0], new String[0]));
		//Sets nas caixas de textos
		txtValorTotal.setText("0.0");
		txtValorEntregue.setText("0.0");
		txtTroco.setText("0.0");
	}
	void addComponents()
	{
		painelVenda.add(lblNomeCliente);
		painelVenda.add(cmbNomeClientes);
		painelVenda.add(lblValorTotal);
		painelVenda.add(txtValorTotal);
		painelVenda.add(lblValorEntregue);
		painelVenda.add(txtValorEntregue);
		painelVenda.add(lblTroco);
		painelVenda.add(txtTroco);
		painelTabelaProdutos.add(tabelaProdutosCliente);
		painelTabelaProdutos.add(painelVenda);
		painelCentro.add(tabelaProdutos);
		painelCentro.add(painelTabelaProdutos);
		painelBotoes.add(btnVender);
		painelBotoes.add(btnValidar);
		painelBotoes.add(btnAdicionar);
		painelBotoes.add(btnCancelar);
		add( painelCentro , BorderLayout.CENTER );
		add( painelBotoes , BorderLayout.SOUTH );
	}

	void addEventos(){
	    btnCancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	        }
	    });

		btnAdicionar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if( tabelaProdutos.getSelectedRow() > -1 )
	        	{
					int quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a Quantidade do Produto"));
					int pos = tabelaProdutos.getSelectedRow();
					int quantidadeTotal = quantidade + getQuantidadeRequerida( modeloProduto.get( pos ).getIdProduto());
		            if( quantidadeTotal > 0 ){
		                if( quantidadeTotal <= modeloProduto.get(pos).getQuantidade() ){
		                    addProdutoCliente( pos , quantidade);
		                }else
		                    JOptionPane.showMessageDialog(null, "Quantidade indisponivel no stock");
		            }else
		                JOptionPane.showMessageDialog(null, "Entre com uma quantidade valida");
				}else
					JOptionPane.showMessageDialog(null, "Seleccione o produto na lista de produtos");
	        }
	    });

		btnVender.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	ModeloVenda venda = new ModeloVenda();
	        	// venda.setNomeCliente(cmbNomeClientes.getSelectedItem());
	        	venda.setValorTotal( Double.parseDouble( txtValorTotal.getText() ) );
	        	venda.setValorEntregue( Double.parseDouble( txtValorEntregue.getText() ) );
	        	venda.setTroco( Double.parseDouble( txtTroco.getText() ) );
	        	venda.setProdutos( produtoCliente );
	        	dispose();
	        	JOptionPane.showMessageDialog(null, "Produtos vendidos com sucesso!");
	        }
	    });

		txtValorEntregue.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	btnVender.setEnabled(false);
	        }
	    });
		
		btnValidar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	double valorTotal = Double.parseDouble( txtValorTotal.getText() );
	        	double valorEntregue = Double.parseDouble( txtValorEntregue.getText() );
	        	double troco = valorEntregue - valorTotal;
	        	if( cmbNomeClientes.getSelectedItem().equals("") == false){
		        	if( troco >= 0 )
		        		btnVender.setEnabled(true);
		        	else
		        		JOptionPane.showMessageDialog(null,"Valor entregue é invalido");
		       	}else
		        	JOptionPane.showMessageDialog(null,"Seleccione o nome do cliente");
	        	txtTroco.setText(""+troco);
	        }
	    });
	}

	void preencherTabelaProdutos(){
	    String colunas[] = {"Descricao","Quantidade","Preco"};
    	String data[][] = new String[modeloProduto.size()][colunas.length];
        for (int i = 0 ; i < modeloProduto.size(); i++ ) {
        	data[i][0] = modeloProduto.get(i).getDescricao();
        	data[i][1] = ""+modeloProduto.get(i).getQuantidade();
        	data[i][2] = ""+modeloProduto.get(i).getPrecoUnitario();
        }
        DefaultTableModel dtm = new DefaultTableModel(data, colunas);
        tabelaProdutos.setModel(dtm);	
    }

	void preencherTabelaCliente(){
	    String colunas[] = {"Descricao","Quantidade","Preco"};
    	String data[][] = new String[produtoCliente.size()][colunas.length];
        for (int i = 0 ; i < produtoCliente.size(); i++ ) {
        	data[i][0] = produtoCliente.get(i).getDescricao();
        	data[i][1] = ""+produtoCliente.get(i).getQuantidade();
        	data[i][2] = ""+produtoCliente.get(i).getPrecoUnitario();
        }
        DefaultTableModel dtm = new DefaultTableModel(data, colunas);
        tabelaProdutosCliente.setModel(dtm);	
    }
    void preencherCmbClientes(){
        for (int i = 0 ; i < modeloCliente.size(); i++ ) {
        	cmbNomeClientes.addItem(modeloCliente.get(i).getNome());
        }
    }

    int getQuantidadeRequerida(int idProduto){
    	int quantidade = 0;
    	for (int i = 0; i < produtoCliente.size(); i++) {
    		if( produtoCliente.get(i).getIdProduto() == idProduto )
    			quantidade += produtoCliente.get(i).getQuantidade();
    	}
    	return quantidade;
    }

	void addProdutoCliente(int pos , int quantidade){
		ModeloProduto produto = new ModeloProduto();
		double valor = modeloProduto.get(pos).getPrecoUnitario() * quantidade;
		produto.setIdProduto( modeloProduto.get(pos).getIdProduto() );
		produto.setDescricao( modeloProduto.get(pos).getDescricao() );
		produto.setCategoria( modeloProduto.get(pos).getCategoria() );
		produto.setQuantidade( quantidade );
		produto.setPrecoUnitario( valor );
		produtoCliente.add( produto );
        setValorTotal( valor );
        txtValorTotal.setText( "" + getValorTotal() );
        preencherTabelaCliente();
	}

	void setValorTotal(double valor){
		valorTotal += valor;
	}

	double getValorTotal(){
		return valorTotal;
	}
}