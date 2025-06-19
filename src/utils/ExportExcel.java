package utils;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Projet;
import model.Tache;

public class ExportExcel {

    public static void generer(Projet projet, List<Tache> taches) {
        Workbook workbook = new XSSFWorkbook(); 
        Sheet sheet = workbook.createSheet("Tâches Projet");

        // Ligne d’en-tête
        Row header = sheet.createRow(0);
        String[] colonnes = {"Titre", "Description", "Échéance", "Statut", "Responsable"};

        for (int i = 0; i < colonnes.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(colonnes[i]);
        }

        // Lignes des tâches
        int rowNum = 1;
        for (Tache t : taches) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(t.getTitre());
            row.createCell(1).setCellValue(t.getDescription());
            row.createCell(2).setCellValue(t.getDateEcheance().toString());
            row.createCell(3).setCellValue(t.getStatut());
            row.createCell(4).setCellValue(t.getResponsable());
        }

        // Sauvegarde du fichier
        try {
        	String fileName = "C:/Users/hajar/Downloads/rapport_" 
                    + projet.getNom().replaceAll("[^a-zA-Z0-9]", "_") 
                    + ".xlsx";

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("✅ Rapport Excel généré : " + fileName);
        } catch (Exception e) {
            System.out.println("❌ Erreur export Excel : " + e.getMessage());
        }
    }
}
