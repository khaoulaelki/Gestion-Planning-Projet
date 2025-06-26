package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginView() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        userLabel.setBounds(10, 10, 120, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 10, 120, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Mot de passe:");
        passLabel.setBounds(10, 40, 120, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 40, 120, 25);
        add(passwordField);

        JButton loginButton = new JButton("Connexion");
        loginButton.setBounds(90, 80, 120, 25);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginController().login(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}