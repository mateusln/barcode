import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//import sandbox.WrapToTest;

@WrapToTest
public class Barcodes {
    public static final String DEST = "results/tables/barcode_table.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Barcodes().createPdf(DEST);

    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        for (int i = 0; i < 12; i++) {
            PdfPCell pdfPCell= createBarcode(writer, String.format("%013d", i));
           //pdfPCell.setPhrase(new Phrase("teste"));
            table.addCell(pdfPCell);

        }


        document.add(table);
        document.close();
    }

    public static PdfPCell createBarcode(PdfWriter writer, String code) throws DocumentException, IOException {
        BarcodeEAN barcode = new BarcodeEAN();
        barcode.setCodeType(Barcode.EAN13);
        barcode.setCode(code);
        barcode.setAltText("TESTE");

        PdfPCell cell = new PdfPCell(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY), true);

        cell.setPadding(10);
        return cell;
    }
}