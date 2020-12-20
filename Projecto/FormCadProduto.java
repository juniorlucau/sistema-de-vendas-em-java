import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCadProduto extends JFrame{

	public ArrayList<ModeloProduto> modeloProduto;
	private int posProduto;
	private JLabel lblId;
	private JLabel lblDescricao;
	private JLabel lblCategoria;
	private JLabel lblPrecoUnitario;
	private JLabel lblQuantidade;
	private JTextField txtId;
	private JTextField txtDescricao;
	private JTextField txtPrecoUnitario;
	private JTextField txtQuantidade;
	private JComboBox cmbCategoria;
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JPanel painelCentro;
	private JPanel painelBotoes;
	private String categorias[] = {"Refrigerante","Biscoistos","Sumo","Limpeza","Material Escolar"};

	public FormCadProduto( ArrayList<ModeloProduto> modeloProduto )
	{
		super("Cadastrar Produto");
		inicComponents();
		addComponents();
		addEventos();
        //Adicionado as Propriedades do formulario
        setVisible(true);
        setSize(400,350);
        setResizable(false);
        setLocationRelativeTo(null);

       	this.modeloProduto = modeloProduto;

       	txtId.setText(""+getNovoId());
	}

	public FormCadProduto( ArrayList<ModeloProduto> modeloProduto , int posProduto )
	{
		super("Cadastrar Produto");
		inicComponents();
		addComponents();
		addEventos();
        //Adicionado as Propriedades do formulario
        setVisible(true);
        setSize(400,350);
        setResizable(false);
        setLocationRelativeTo(null);

       	this.modeloProduto = modeloProduto;

       	txtId.setText(""+getNovoId());
       	btnSalvar.setVisible(false);
       	btnAlterar.setVisible(true);
       	this.posProduto = posProduto;
       	preencherComponents( posProduto );
	}

	void inicComponents(){

		lblId = new JLabel(" Id do Produto");
		lblDescricao = new JLabel(" Descricao do Produto");
		lblCategoria = new JLabel(" Categoria");
		lblPrecoUnitario = new JLabel(" Preco Unitario");
		lblQuantidade = new JLabel(" Quantidade em Stock");
		txtId = new JTextField();
		txtId.setEditable(false);
		txtDescricao = new JTextField(25);
		txtPrecoUnitario = new JTextField(25);
		txtQuantidade = new JTextField();
		cmbCategoria = new JComboBox(categorias);
		btnSalvar = new JButton("Salvar");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setVisible(false);
		btnCancelar = new JButton("Cancelar");
		painelCentro = new JPanel();
		painelCentro.setLayout(new GridLayout(5, 2, 0, 10));
		painelBotoes = new JPanel();

	}

	void addComponents(){
		painelCentro.add(lblId);
		painelCentro.add(txtId);
		painelCentro.add(lblDescricao);
		painelCentro.add(txtDescricao);
		painelCentro.add(lblCategoria);
		painelCentro.add(cmbCategoria);
		painelCentro.add(lblPrecoUnitario);
		painelCentro.add(txtPrecoUnitario);
		painelCentro.add(lblQuantidade);
		painelCentro.add(txtQuantidade);
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnCancelar);
		add(painelCentro , BorderLayout.CENTER);
		add(painelBotoes , BorderLayout.SOUTH);
	}

	boolean verificarComponentes(){
		if(txtDescricao.getText().equals("") == true)
			return false;
		if(txtQuantidade.getText().equals("") == true)
			return false;
		if(Integer.parseInt(txtQuantidade.getText()) < 0)
			return false;
		if(txtPrecoUnitario.getText().equals("") == true)
			return false;
		if(Integer.parseInt(txtPrecoUnitario.getText()) < 0)
			return false;
		return true;
	}

	int getNovoId(){
        if( modeloProduto.size() == 0 )
            return 1;
        return modeloProduto.get( modeloProduto.size() - 1 ).getIdProduto() + 1;
    }

	void addEventos()
	{
	    btnCancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	        }
	    });

	    btnSalvar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if( verificarComponentes() == true ){
	        		ModeloProduto modelo = new ModeloProduto();
	        		modelo.setIdProduto(getNovoId());
	        		modelo.setDescricao(txtDescricao.getText());
	        		modelo.setCategoria(cmbCategoria.getSelectedItem().toString());
	        		modelo.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
	        		modelo.setPrecoUnitario(Double.parseDouble(txtPrecoUnitario.getText()));
	        		modeloProduto.add(modelo);
	        		JOptionPane.showMessageDialog(null, "Produto Cadastrado com sucesso");
	        		limparComponentes();
	        		txtId.setText(""+getNovoId());
	        	}else
                    JOptionPane.showMessageDialog(null, "Preencha correctamente todos os campos!");
	        }
	    });

		btnAlterar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if( verificarComponentes() == true ){
	        		ModeloProduto modelo = new ModeloProduto();
	        		modelo.setIdProduto(Integer.parseInt(txtId.getText()));
	        		modelo.setDescricao(txtDescricao.getText());
	        		modelo.setCategoria(cmbCategoria.getSelectedItem().toString());
	        		modelo.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
	        		modelo.setPrecoUnitario(Double.parseDouble(txtPrecoUnitario.getText()));
	        		modeloProduto.set(posProduto , modelo);
	        		JOptionPane.showMessageDialog(null, "Produto Alterado com sucesso");
	        		dispose();
	        		new FormAlterarProduto( modeloProduto );
	        	}else 
                    	JOptionPane.showMessageDialog(null, "Preencha correctamente todos os campos!");
	        }
	    });
	}

	void limparComponentes(){
		txtQuantidade.setText("");
		txtDescricao.setText("");
		txtPrecoUnitario.setText("");
	}

	void preencherComponents(int posProduto){
		txtId.setText("" + modeloProduto.get(posProduto).getIdProduto() );
		txtDescricao.setText( modeloProduto.get(posProduto).getDescricao() );
		cmbCategoria.setSelectedItem( modeloProduto.get(posProduto).getCategoria() );
		txtQuantidade.setText(""+modeloProduto.get(posProduto).getQuantidade());
		txtPrecoUnitario.setText(""+modeloProduto.get(posProduto).getPrecoUnitario());
	}
}