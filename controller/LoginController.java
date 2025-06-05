package controller;

import model.AuthService;
import model.User;
import view.MembreView;
import view.ChefView;

import javax.swing.*;

public class LoginController {
    public void login(String username, String password) {  //fonction appelee lors du clic sur le bouton "se connecter"
        User currentUser = AuthService.authenticate(username, password);    //Vérifie si les identifiants sont valides

        if (currentUser != null) {   //connexion reussie
            switch (currentUser.getRole().toLowerCase()) {  //récupèrer le rôle de l’utilisateur
                case "chef":
                    new ChefView();
                    break;
                case "membre":
                    new MembreView(currentUser);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Rôle inconnu : " + currentUser.getRole());
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);  //Affiche des messages d’erreur
        }
    }
}