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



public class FormCadCliente extends JFrame{
        
	public ArrayList<ModeloCliente> modeloCliente;
    private int posCliente;
	private JLabel lblId;
	private JLabel lblNome;
	private JTextField txtId;
	private JTextField txtNome;
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JPanel painelCentro;
	private JPanel painelBotoes;
        
	public FormCadCliente(ArrayList<ModeloCliente> modeloCliente)
	{
		super("Cadastrar Cliente");
		inicComponents();
		addComponents();
		addEventos();
        //Adicionado as Propriedades do formulario
        setVisible(true);
        setSize(400,170);
        setResizable(false);
        setLocationRelativeTo(null);
        
        this.modeloCliente = modeloCliente;
        
        txtId.setText(""+getNovoId());
        
	}

	public FormCadCliente( ArrayList<ModeloCliente> modeloCliente , int posCliente )
	{
		super("Cadastrar Cliente");
		inicComponents();
		addComponents();
		addEventos();
        //Adicionado as Propriedades do formulario
        setVisible(true);
        setSize(400,170);
        setResizable(false);
        setLocationRelativeTo(null);

       	this.modeloCliente = modeloCliente;

       	txtId.setText(""+getNovoId());
       	btnSalvar.setVisible(false);
       	btnAlterar.setVisible(true);
       	this.posCliente = posCliente;
       	preencherComponents( posCliente );
	}

	void inicComponents(){
		lblId = new JLabel(" Id do Cliente");
		lblNome = new JLabel(" Nome do Cliente");
		txtId = new JTextField();
        txtId.setEditable(false);
		txtNome = new JTextField(25);
		btnSalvar = new JButton("Salvar");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setVisible(false);
		btnCancelar = new JButton("Cancelar");
		painelCentro = new JPanel();
		painelCentro.setLayout(new GridLayout(2, 2, 0, 10));
		painelBotoes = new JPanel();
	}

	void addComponents(){
		painelCentro.add(lblId);
		painelCentro.add(txtId);
		painelCentro.add(lblNome);
		painelCentro.add(txtNome);
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnCancelar);
		add(painelCentro , BorderLayout.CENTER);
		add(painelBotoes , BorderLayout.SOUTH);
	}

	boolean verificarComponentes(){
            if( txtNome.getText().equals("") == true )
                return false;
            return true;
	}
        
    int getNovoId(){
        if( modeloCliente.size() == 0 )
            return 1;
        return modeloCliente.get( modeloCliente.size() - 1 ).getIdCliente() + 1;
    }
    
    void addEventos(){

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if( verificarComponentes() == true ){
                    ModeloCliente modelo = new ModeloCliente();
                    modelo.setIdCliente( getNovoId() );
                    modelo.setNome( txtNome.getText() );
                    modeloCliente.add(modelo);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso ");
                    txtNome.setText("");
                    txtId.setText(""+getNovoId());
                }else
                    JOptionPane.showMessageDialog(null, "Entre com o nome do Cliente");
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if( verificarComponentes() == true ){
	        		ModeloCliente modelo = new ModeloCliente();
	        		modelo.setIdCliente(Integer.parseInt(txtId.getText()));
	        		modelo.setNome(txtNome.getText());
	        		modeloCliente.set(posCliente , modelo);
	        		JOptionPane.showMessageDialog(null, "Cliente Alterado com sucesso");
	        		dispose();
	        		new FormAlterarCliente( modeloCliente );
	        	}else 
                    	JOptionPane.showMessageDialog(null, "Preencha correctamente todos os campos!");
	        }
	    });
	}

   void preencherComponents(int posCliente){
		txtId.setText("" + modeloCliente.get(posCliente).getIdCliente() );
		txtNome.setText( modeloCliente.get(posCliente).getNome() );
	}

}