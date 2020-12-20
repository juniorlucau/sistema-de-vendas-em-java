import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormAlterarProduto extends JFrame
{
	public ArrayList<ModeloProduto> modeloProduto;
	private JButton btnAlterar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JTable tabela;
	private JPanel painelCentro;
	private JPanel painelBotoes;

	public FormAlterarProduto( ArrayList<ModeloProduto> modeloProduto )
	{
		super("Produtos");
		inicComponents();
		addComponents();
		addEventos();
        //Adicionado as Propriedades do formulario
        setVisible(true);
        setSize(500,400);
        setResizable(false);
        setLocationRelativeTo(null);

       	this.modeloProduto = modeloProduto;
       	preencherTabela();
	}

	void inicComponents(){
		btnAlterar = new JButton("Alterar");
		btnEliminar = new JButton("Eliminar");
		btnCancelar = new JButton("Cancelar");
		painelCentro = new JPanel();
		painelCentro.setLayout(new GridLayout(1,1,10,10));
		painelBotoes = new JPanel();
		tabela = new JTable();
	}

	void addComponents(){
		painelCentro.add(tabela);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnEliminar);
		painelBotoes.add(btnCancelar);
		JPanel titulo = new JPanel();
		titulo.add(new JLabel("Produtos"));
		add(titulo, BorderLayout.NORTH);
		add(painelCentro , BorderLayout.CENTER);
		add(painelBotoes , BorderLayout.SOUTH);
	}
	
	void preencherTabela(){
	    String colunas[] = {"IdProduto","Descricao","Categoria","Quantidade","Preco"};
    	String data[][] = new String[modeloProduto.size()][colunas.length];
        for (int i = 0 ; i < modeloProduto.size(); i++ ) {
        	data[i][0] = ""+modeloProduto.get(i).getIdProduto();
        	data[i][1] = modeloProduto.get(i).getDescricao();
        	data[i][2] = modeloProduto.get(i).getCategoria();
        	data[i][3] = ""+modeloProduto.get(i).getQuantidade();
        	data[i][4] = ""+modeloProduto.get(i).getPrecoUnitario();
        }
        DefaultTableModel dtm = new DefaultTableModel(data, colunas);
        tabela.setModel(dtm);	
    }
	
	void addEventos(){
	    btnCancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	        }
	    });

		btnAlterar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
	        	new FormCadProduto( modeloProduto , tabela.getSelectedRow());
	        }
	    });

		btnEliminar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if( tabela.getSelectedRow() > -1 ){
	        		modeloProduto.remove( tabela.getSelectedRow() );
	        		JOptionPane.showMessageDialog(null,"Produto removido com sucesso");
	        		preencherTabela();
	        	}else
	        		JOptionPane.showMessageDialog(null,"Seleccione um produto na tabela");
	        }
	    });
	}
}