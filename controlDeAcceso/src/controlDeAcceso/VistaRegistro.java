
package controlDeAcceso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class VistaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField txtcodigodeacceso;
	private JTextField txtnombre;
	private JTextField txtfecha;
	private JTextField txthora;
	JLabel lblid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistro frame = new VistaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtcodigodeacceso = new JTextField();
		txtcodigodeacceso.setBounds(157, 77, 86, 20);
		contentPane.add(txtcodigodeacceso);
		txtcodigodeacceso.setColumns(10);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(157, 109, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtfecha = new JTextField();
		txtfecha.setBounds(157, 140, 86, 20);
		contentPane.add(txtfecha);
		txtfecha.setColumns(10);
		
		txthora = new JTextField();
		txthora.setBounds(157, 171, 86, 20);
		contentPane.add(txthora);
		txthora.setColumns(10);
		
		 lblid = new JLabel("");
		lblid.setBounds(162, 11, 59, 14);
		contentPane.add(lblid);
		
		JButton btnGUARDAR = new JButton("GUARDAR");
		btnGUARDAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarR();
			}
		});
		btnGUARDAR.setBounds(10, 199, 89, 23);
		contentPane.add(btnGUARDAR);
		
		JButton btnCONSULTAR = new JButton("CONSULTAR");
		btnCONSULTAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarR();
			}
		});
		btnCONSULTAR.setBounds(109, 199, 89, 23);
		contentPane.add(btnCONSULTAR);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarR();
			}
		});
		btnActualizar.setBounds(208, 199, 95, 23);
		contentPane.add(btnActualizar);
		
		JLabel lblCONTROL = new JLabel("CONTROL");
		lblCONTROL.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblCONTROL.setBounds(10, 80, 75, 14);
		contentPane.add(lblCONTROL);
		
		JLabel lblNOMBRE = new JLabel("NOMBRE");
		lblNOMBRE.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNOMBRE.setBounds(10, 112, 75, 14);
		contentPane.add(lblNOMBRE);
		
		JLabel lblFECHA = new JLabel("FECHA");
		lblFECHA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblFECHA.setBounds(10, 143, 46, 14);
		contentPane.add(lblFECHA);
		
		JLabel lblHORA = new JLabel("HORA");
		lblHORA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblHORA.setBounds(10, 174, 46, 14);
		contentPane.add(lblHORA);
		
		JButton btnELIMINAR = new JButton("ELIMINAR");
		btnELIMINAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarR();
			}
		});
		btnELIMINAR.setBounds(313, 199, 89, 23);
		contentPane.add(btnELIMINAR);
	}
	private void guardarR() {
		Registro reg=new Registro();
		reg.setCodAcc(Integer.parseInt(txtcodigodeacceso.getText()));
		reg.setNombre(txtnombre.getText());
		reg.setFecha(txtfecha.getText());
		reg.setHora(txthora.getText());
		ResgistroDAO rd=new ResgistroDAO();
		rd.guardarRegistro(reg);
	}
	private void consultarR() {
		ResgistroDAO rd =new ResgistroDAO();
		Registro r= rd.consultarReg(Integer.parseInt(txtcodigodeacceso.getText()));
		lblid.setText(String.valueOf(r.getId()));
		txtnombre.setText(r.getNombre());
		txtfecha.setText(r.getFecha());
		txthora.setText(r.getHora());
	}
	private void actualizarR() {
		Registro r=new Registro();
		r.setId(Integer.parseInt(lblid.getText()));
		r.setCodAcc(Integer.parseInt(txtcodigodeacceso.getText()));
		r.setNombre(txtnombre.getText());
		r.setFecha(txtfecha.getText());
		r.setHora(txthora.getText());
		ResgistroDAO rd=new ResgistroDAO();
		rd.actualizarRegistro(r);
	}
	private void eliminarR(){
		Registro r=new Registro();
		r.setCodAcc(Integer.parseInt(txtcodigodeacceso.getText()));
		ResgistroDAO rd=new ResgistroDAO();
		rd.eliminarRegistros(r);
	}
}
