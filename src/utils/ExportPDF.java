package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import model.Projet;
import model.Tache;

import java.io.FileOutputStream;
import java.util.List;
public class ExportPDF {

    public static void generer(Projet projet, List<Tache> taches) {
        Document document = new Document();

        try {
        	String fileName = "C:/Users/hajar/Downloads/rapport_" 
                    + projet.getNom().replaceAll("[^a-zA-Z0-9]", "_") 
                    + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Titre
            Font titreFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titre = new Paragraph("Rapport du projet : " + projet.getNom(), titreFont);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);
            document.add(Chunk.NEWLINE);

            // Tableau
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 4, 3, 2, 3});

            addHeader(table, "Titre", "Description", "Échéance", "Statut", "Responsable");

            for (Tache t : taches) {
                table.addCell(t.getTitre());
                table.addCell(t.getDescription());
                table.addCell(t.getDateEcheance().toString());
                table.addCell(t.getStatut());
                table.addCell(t.getResponsable());
            }

            document.add(table);

        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la génération PDF : " + e.getMessage());
        } finally {
            document.close();
        }
    }

    private static void addHeader(PdfPTable table, String... headers) {
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }
}
