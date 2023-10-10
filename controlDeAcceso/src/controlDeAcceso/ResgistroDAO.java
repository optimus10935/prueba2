package controlDeAcceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ResgistroDAO {
private Connection con;
private PreparedStatement ps;
private ResultSet rs;

private Connection conectar() {
	String url="jdbc:mysql://localhost:3306/control_de_Acceso";
	String usuario="root";
	String contrasena="";
	try {
		con =DriverManager.getConnection(url,usuario,contrasena);
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR"+e);
		e.printStackTrace();
	}
	return con;
}
public boolean guardarRegistro(Registro r) {
	boolean guarda=false;
	String sql ="insert into registros values(0,?,?,?,?)";
	conectar();
	try {
		ps=con.prepareStatement(sql);
	    ps.setInt(1, r.getCodAcc());
	    ps.setString(2, r.getNombre());
	    ps.setString(3, r.getFecha());
	    ps.setString(4, r.getHora());
	    int g=ps.executeUpdate();
	    if(g>0) {
	    	JOptionPane.showMessageDialog(null, "GUARDADO");
	    	guarda=true;
	    }else {
	    	JOptionPane.showMessageDialog(null, "NO GUARDADO");
	    }
	    ps.close();
		con.close();
	} catch (SQLException e) {
	JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR"+e);
		e.printStackTrace();
	}
	
	
	return guarda;
	
}
public Registro consultarReg(int cod) {
	Registro r =new Registro();
	String query="Select * from registros where cosAcc=?";
	conectar();
	try {
		ps=con.prepareStatement(query);
		ps.setInt(1, cod);
		rs =ps.executeQuery();
		if(rs.next()) {
			r.setId(rs.getInt(1));
			r.setCodAcc(rs.getInt(2));
			r.setNombre(rs.getString(3));
			r.setFecha(rs.getString(4));
			r.setHora(rs.getString(5));
		}else {
			JOptionPane.showMessageDialog(null, "NO EXISTE EL REGISTRO");
		}
		ps.close();
		rs.close();
		con.close();
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null,"ERROR AL CONSULTAR"+e);
		e.printStackTrace();
	}
	return r;
}
public boolean actualizarRegistro(Registro r) {
	boolean actual=false;
	String query="update registros set cosAcc=?,nombrev=?,fecha=?,hora=? where id=?";
	conectar();
	try {
		ps= con.prepareStatement(query);
		ps.setInt(1, r.getCodAcc());
		ps.setString(2, r.getNombre());
		ps.setString(3, r.getFecha());
		ps.setString(4, r.getHora());
		ps.setInt(5, r.getId());
		int act=ps.executeUpdate();
		if(act>0) {
			actual=true;
			JOptionPane.showMessageDialog(null, "EXELENTE SE HA ACTUALIZADO ESTAMOS AGRADECIDOS");
			
		}else {
			JOptionPane.showMessageDialog(null, "VALIO NO LOGRASTES ACTUALIZAR");
		}
		ps.close();                                             
		con.close();
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null,"ERROR AL CONECTAR"+e);
		
	}
	
    return actual;
	
}
public boolean eliminarRegistros(Registro r) {
	boolean elim=false;
	String sql="delete from registros where cosAcc=?";
	conectar();
	try {
		ps=con.prepareStatement(sql);
		ps.setInt(1, r.getCodAcc());
		int elimi=ps.executeUpdate();		
		if(elimi>0) {
			elim=true;
			JOptionPane.showMessageDialog(null, "ELIMINADO :)");
			
		}else {
			JOptionPane.showMessageDialog(null, "NO ELIMINADO :(");
		}
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR >:("+e);
	}
	return false;
	
}
}
