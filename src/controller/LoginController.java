package controller;

import view.LoginView;
import view.MenuView; // O el nombre real de tu ventana
import javax.swing.JOptionPane;

public class LoginController {
    private LoginView vista;

    public LoginController(LoginView vista) {
        this.vista = vista;
    }

    public void validarAcceso(String usuario, String password) {
        if (usuario.equals("admin") && password.equals("1234")) {
            JOptionPane.showMessageDialog(vista, "Acceso concedido");
            MenuView mainView = new MenuView(); // Usa el nombre correcto aquí
            mainView.setVisible(true);
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas (admin / 1234)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}