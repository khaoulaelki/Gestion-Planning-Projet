package view;

import javax.swing.*;

public class ManagerView extends JFrame {
    public ManagerView() {
        setTitle("Espace Chef de Projet");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton gestionMembres = new JButton("Gestion des membres");
        gestionMembres.setBounds(100, 20, 200, 30);
        add(gestionMembres);

        JButton gestionProjets = new JButton("Gestion des projets");
        gestionProjets.setBounds(100, 60, 200, 30);
        add(gestionProjets);

        JButton reporting = new JButton("Suivi et reporting");
        reporting.setBounds(100, 100, 200, 30);
        add(reporting);

        setVisible(true);
    }
}