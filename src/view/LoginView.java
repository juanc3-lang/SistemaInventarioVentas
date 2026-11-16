package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private LoginController loginController;

    public LoginView() {
        // Pasamos 'this' (la vista actual) al constructor del nuevo controlador
        loginController = new LoginController(this);
        
        setTitle("Login - Sistema de Inventario y Ventas");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 160, 25);
        add(txtUsuario);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 70, 80, 25);
        add(lblPass);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(120, 70, 160, 25);
        add(txtContrasena);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(120, 120, 100, 30);
        add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public/*no-tildes*/ void actionPerformed(ActionEvent e) {
                String user = txtUsuario.getText();
                String pass = new String(txtContrasena.getPassword());

                // Llamamos al nuevo metodo de validacion explicita
                loginController.validarAcceso(user, pass);
            }
        });
    }
}