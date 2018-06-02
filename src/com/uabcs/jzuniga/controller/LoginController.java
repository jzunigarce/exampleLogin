package com.uabcs.jzuniga.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.uabcs.jzuniga.model.Log;
import com.uabcs.jzuniga.model.User;
import com.uabcs.jzuniga.view.VLogin;
import com.uabcs.jzuniga.util.Util;

public class LoginController implements ActionListener{
	
	private VLogin login;
	
	public LoginController() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("login")) {
			User user = User.findByEmail(this.login.jtfEmail.getText());
			if(user != null) {
				String password;
				try {
					password = Util.hash256(String.valueOf(this.login.jfpPassword.getPassword()));
					if(user.getPassword().equals(password)) {
						Calendar calendario = Calendar.getInstance();
						Date fecha = new Date(calendario.getTime().getTime());
						Time hora = new Time(calendario.getTime().getTime());
						Log log = new Log();
						log.setFecha(fecha);
						log.setHora(hora);
						log.setIdUser(user.getId());
						log.create();
						JOptionPane.showMessageDialog(this.login, "Bienvenido usuario");
					} else {
						JOptionPane.showMessageDialog(this.login, "Usuario o contraseñe incorrectos");
					}
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this.login, "Usuario o contraseñe incorrectos");
			}
			/*ArrayList<User> usuarios = User.findAll();
			for(User u : usuarios) {
				System.out.println("Email:" + u.getEmail());
			}*/
		}
			
	}
	
	public void init() {
		this.login = new VLogin();
		this.login.init(this);
		this.login.setVisible(true);
		//this.login.btnLogin
	}

}
