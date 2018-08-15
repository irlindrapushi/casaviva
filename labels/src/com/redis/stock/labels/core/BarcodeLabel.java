/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.labels.core;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author Redjan Shabani
 */
public class BarcodeLabel {
	private final String code, barcode, description;

	public BarcodeLabel(String code, String barcode, String description) {
		this.code = code;
		this.barcode = barcode;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + Objects.hashCode(this.code);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BarcodeLabel other = (BarcodeLabel) obj;
		return Objects.equals(this.code, other.code);
	}
	
	
	
	
	
	
	
	
	
	
	public static File generatePDF(Collection<BarcodeLabel> labels) {
		File file = new File("labels.pdf");
		
		try{
			Document document = new Document();	   
			document.setPageSize(PageSize.A4);
			document.setMargins(0, 0, 0, 0);

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			PdfPTable table = new PdfPTable(5);//To set number of columns in a row
			table.setWidthPercentage(100);

			
			labels.forEach((label) -> {
				try{
					PdfPCell cell = new PdfPCell();
					cell.setPadding(5);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setFixedHeight(PageSize.A4.getHeight() / 12 - 0.01f);

					Barcode128 barcode = new Barcode128();
					barcode.setBaseline(-1);
					barcode.setCode(label.barcode);
					barcode.setCodeType(Barcode128.CODE128);
					Image barcodeImage = barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY);
					barcodeImage.scaleAbsoluteHeight(cell.getHeight()/1.5f);
					barcodeImage.setAlignment(Image.ALIGN_CENTER);
					cell.addElement(barcodeImage);


					Paragraph paragraph;				
					paragraph = new Paragraph(label.code);
					paragraph.setLeading(7);
					paragraph.setFont(FontFactory.getFont(FontFactory.COURIER_BOLD, 10));
					paragraph.setAlignment(Paragraph.ALIGN_CENTER);
					cell.addElement(paragraph);




					table.addCell(cell);
				}
				catch(Exception ex){
					
				}
			});
			table.completeRow();
			
			document.add(table);
			document.close();
		} catch (FileNotFoundException | DocumentException ex) {
			Logger.getLogger(BarcodeLabel.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return file;
	}
	
	
	
	
	
	
	
	
	
	
	
	public static File generateHTML(Collection<BarcodeLabel> labels) {
		File file = new File("./temp/labels.html");
		
		generateBarcodeImages(labels);
		
		return file;
	}
	
	public static void generateBarcodeImages(Collection<BarcodeLabel> labels) {
		labels.forEach(label -> {
			try {
				Barcode barcode = BarcodeFactory.createCode128(label.getBarcode());
				barcode.setLabel(label.code);
				barcode.setFont(new Font("TimesRoman", Font.PLAIN, 10));
				barcode.setPreferredSize(new Dimension(200, 40));
				BufferedImage barcodeImage = BarcodeImageHandler.getImage(barcode);
				
				
				
				BarcodeImageHandler.savePNG(barcode, new File("./temp/" + label.getCode() + ".png"));
			} 
			catch (BarcodeException | OutputException ex) {
				Logger.getLogger(BarcodeLabel.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}
}
