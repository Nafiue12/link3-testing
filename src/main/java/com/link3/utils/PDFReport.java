package com.link3.utils;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReport {

    Document pdf;

    public PDFReport(String fileName) {

        try {

            pdf = new Document();

            PdfWriter.getInstance(
                    pdf,
                    new FileOutputStream(fileName)
            );

            pdf.open();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void addText(String text) {

        try {

            pdf.add(
                    new Paragraph(text)
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void closeReport() {

        pdf.close();
    }
}