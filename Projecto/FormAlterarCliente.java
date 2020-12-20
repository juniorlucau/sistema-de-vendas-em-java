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

public class FormAlterarCliente extends JFrame
{
	public ArrayList<ModeloCliente> modeloCliente;
	private JButton btnAlterar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JTable tabela;
	private JPanel painelCentro;
	private JPanel painelBotoes;

	public FormAlterarCliente( ArrayList<ModeloCliente> modeloCliente )
	{
		super("Clientes");
		inicComponents();
		addComponents();
		addEventos();
        //Adicionado as Propriedades do formulario
        setVisible(true);
        setSize(400,350);
        setResizable(false);
        setLocationRelativeTo(null);

       	this.modeloCliente = modeloCliente;
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
		titulo.add(new JLabel("Clientes"));
		add(titulo, BorderLayout.NORTH);
		add(painelCentro , BorderLayout.CENTER);
		add(painelBotoes , BorderLayout.SOUTH);
	}
	
	void preencherTabela(){
	    String colunas[] = {"IdCliente","Nome"};
    	String data[][] = new String[modeloCliente.size()][colunas.length];
        for (int i = 0 ; i < modeloCliente.size(); i++ ) {
        	data[i][0] = ""+modeloCliente.get(i).getIdCliente();
        	data[i][1] = modeloCliente.get(i).getNome();
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
	        	new FormCadCliente( modeloCliente , tabela.getSelectedRow());
	        }
	    });

		btnEliminar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if( tabela.getSelectedRow() > -1 ){
	        		modeloCliente.remove( tabela.getSelectedRow() );
	        		JOptionPane.showMessageDialog(null,"Cliente removido com sucesso");
	        		preencherTabela();
	        	}else
	        		JOptionPane.showMessageDialog(null,"Seleccione um cliente na tabela");
	        }
	    });
	}
}