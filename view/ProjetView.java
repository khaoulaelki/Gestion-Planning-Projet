package view;

import model.ProjetService;
import model.Projet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProjetView extends JFrame {

		private JTable table;
	    private DefaultTableModel model;

	    public ProjetView() {
	        setTitle("Gestion des Projets");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(800, 400);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout());
	        
	     // Colonnes
	        model = new DefaultTableModel(new String[]{"ID", "Nom","Description","Début", "Fin", "Statut"}, 0);
	        table = new JTable(model);
	        add(new JScrollPane(table), BorderLayout.CENTER);

	        // Boutons
	        JPanel panelBoutons = new JPanel();
	        JButton btnAjouter = new JButton("Ajouter");
	        JButton btnModifier = new JButton("Modifier");
	        JButton btnSupprimer = new JButton("Supprimer");
	        
	        panelBoutons.add(btnAjouter);
	        panelBoutons.add(btnModifier);
	        panelBoutons.add(btnSupprimer);

	        add(panelBoutons, BorderLayout.SOUTH);

	        // Chargement des projets
	        chargerProjets();

	        setVisible(true);
	    }
	    
	    
	    private void chargerProjets() {
	        model.setRowCount(0); // vider
	        List<Projet> projets = ProjetService.getTousLesProjets();
	        for (Projet p : projets) {
	            model.addRow(new Object[]{
	                p.getId(),
	                p.getNom(),
	                p.getDescription(),
	                p.getDateDebut(),
	                p.getDateFin(),
	                p.getStatut()
	            });
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(ProjetView::new);
	    }
		
		
	}