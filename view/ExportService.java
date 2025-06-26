package view;

import model.Projet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;

public class ExportService {

    public static void exporterPDF(Projet projet, String contenu) {
        if (projet == null || contenu == null || contenu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune donnée à exporter.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Enregistrer en PDF");
        fileChooser.setSelectedFile(new File(projet.getNom() + "_rapport.pdf"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileChooser.getSelectedFile()));
                document.open();
                document.add(new Paragraph("Rapport du projet : " + projet.getNom()));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(contenu));
                document.close();

                JOptionPane.showMessageDialog(null, "PDF exporté avec succès !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur export PDF : " + e.getMessage());
            }
        }
    }

    public static void exporterExcel(Projet projet, String contenu) {
        if (projet == null || contenu == null || contenu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune donnée à exporter.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Enregistrer en Excel");
        fileChooser.setSelectedFile(new File(projet.getNom() + "_rapport.xlsx"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Rapport");

                String[] lignes = contenu.split("\n");
                for (int i = 0; i < lignes.length; i++) {
                    Row row = sheet.createRow(i);
                    row.createCell(0).setCellValue(lignes[i]);
                }

                FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile());
                workbook.write(fos);
                fos.close();

                JOptionPane.showMessageDialog(null, "Excel exporté avec succès !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur export Excel : " + e.getMessage());
            }
        }
    }
}

