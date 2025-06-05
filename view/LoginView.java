package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;          //permet de gérer les événements comme le clic sur un bouton.
import java.awt.event.ActionListener;

public class LoginView extends JFrame {       //fenêtre graphique Swing
    private JTextField usernameField;
    private JPasswordField passwordField;               //Deux champs d’entrée : un pour le nom d'utilisateur, un autre pour le mot de passe.

    public LoginView() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //ferme l'application si on clique sur la croix.
        setLocationRelativeTo(null);
        setLayout(null);
        
       //Création et positionnement du label Nom d'utilisateur
        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        userLabel.setBounds(10, 10, 120, 25);
        add(userLabel);

        //Zone de saisie du nom d’utilisateur.
        usernameField = new JTextField();
        usernameField.setBounds(140, 10, 120, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Mot de passe:");
        passLabel.setBounds(10, 40, 120, 25);
        add(passLabel);

        passwordField = new JPasswordField();     //JPasswordField masque le mot de passe entré.
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

        setVisible(true);    //Rend la fenêtre visible à l’écran.
    }

    public static void main(String[] args) {
        new LoginView();
    }
}