package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuiviReportingMenuView extends JFrame {

    public SuiviReportingMenuView() {
        setTitle("Menu Principal - Suivi & Reporting");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du bouton
        JButton btnSuivi = new JButton("Suivi et Reporting");

        // Action lorsqu'on clique sur le bouton
        btnSuivi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionProjetView(); 
            }
        });

        // Ajout du bouton à la fenêtre
        add(btnSuivi);

        // Affichage
        setVisible(true);
    }

    // Méthode main pour tester directement cette vue
    public static void main(String[] args) {
        new SuiviReportingMenuView();
    }
}
