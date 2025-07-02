package view;


import view.ProjetDashboard;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ReportingDashboard;


public class ChefView extends JFrame {
    public ChefView() {
        setTitle("Espace Chef de Projet");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Bouton pour gérer les membres
        JButton gestionMembres = new JButton("Gestion des membres");
        gestionMembres.setBounds(100, 20, 200, 30);
        add(gestionMembres);

        // Bouton pour gérer les projets
        JButton gestionProjets = new JButton("Gestion des projets");
        gestionProjets.setBounds(100, 60, 200, 30);
        add(gestionProjets);

        // Bouton pour suivi et reporting
        JButton reporting = new JButton("Suivi et reporting");
        reporting.setBounds(100, 100, 200, 30);
        add(reporting);

        // Action : ouvre l'interface de gestion de projet
        gestionProjets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProjetDashboard(); // Ouvre le tableau de bord des projets
                dispose(); 
            }
        });

        
        gestionMembres.addActionListener(e -> {
            new MembreDashboard();
        });

        reporting.addActionListener(e -> {
        	    new ReportingDashboard();

        });

        setVisible(true);
    }
}
