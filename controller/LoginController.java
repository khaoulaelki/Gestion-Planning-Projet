package controller;

import model.AuthService;
import model.User;
import view.MembreView;
import view.ManagerView;

import javax.swing.*;

public class LoginController {
    public void login(String username, String password) {
        User currentUser = AuthService.authenticate(username, password);

        if (currentUser != null) {
            switch (currentUser.getRole().toLowerCase()) {
                case "chef":
                    new ManagerView();
                    break;
                case "membre":
                    new MembreView(currentUser);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Rôle inconnu : " + currentUser.getRole());
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}