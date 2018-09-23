/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.export;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class PriceLabel {
	
	private final String code, barcode, description;
	private final Double price;
	private final Instant instant;

	public PriceLabel(String code, String barcode, String description, Double price, Instant instant) {
		this.code = code;
		this.barcode = barcode;
		this.description = description;
		this.price = price;
		this.instant = instant;
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

	public Double getPrice() {
		return price;
	}

	public Instant getInstant() {
		return instant;
	}

	

	
	
	
	
	public static File generatePDF(Collection<PriceLabel> labels) {
		File file = new File("price_labels.pdf");
		
		NumberFormat integerFormatter = NumberFormat.getIntegerInstance();
		integerFormatter.setMaximumFractionDigits(0);
		
		NumberFormat percentFormatter = NumberFormat.getPercentInstance();
		percentFormatter.setMaximumFractionDigits(0);
		
		try{
			Image image = Image.getInstance("logo.png");
			image.scaleToFit(100, 100);
			
			Image backImage = Image.getInstance("backgr.png");
			backImage.scaleToFit(100, 100);
			
			Document document = new Document();	   
			document.setPageSize(PageSize.A4.rotate());
			document.setMargins(20, 20, 20, 20);

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();			
			
			for(PriceLabel label : labels) {
				document.newPage();
				
				PdfContentByte canvas = writer.getDirectContentUnder();
				backImage.scaleAbsolute(PageSize.A4.rotate());
				backImage.setAbsolutePosition(0, 0);
				canvas.addImage(backImage);
				
				
				
				
				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100);
				
				PdfPCell cell;
				Phrase phrase;
				
				
				
				
				
				Paragraph paragraph;
				Font font;
				
				
				//timestamp
				font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12);	
				paragraph = new Paragraph(
					label.instant.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM HH:mm")) + " (" + 
					Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM HH:mm")) + ")", 
					font);
				paragraph.setSpacingAfter(150);
				document.add(paragraph);
				
				//pershkrim
				font = FontFactory.getFont(FontFactory.COURIER_BOLD, 36);	
				paragraph = new Paragraph(label.description.replace(",", "\n"), font);
				paragraph.setAlignment(Element.ALIGN_LEFT);
				document.add(paragraph);
				
				//cmim
				
				
				
				
				
				
			}
			
			document.close();
		} 
		catch (FileNotFoundException | DocumentException ex) {
			Logger.getLogger(PriceLabel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(PriceLabel.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		
		
		return file;
	}
	
	
	private static class ImageBackgroundEvent implements PdfPCellEvent {
 
		protected Image image;

		public ImageBackgroundEvent(Image image) {
		    this.image = image;
		}

		public void cellLayout(PdfPCell cell, Rectangle position,
			   PdfContentByte[] canvases) {
		    try {
			   PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
			   image.scaleAbsolute(position);
			   image.setAbsolutePosition(position.getLeft(), position.getBottom());
			   cb.addImage(image);
		    } catch (DocumentException e) {
			   throw new ExceptionConverter(e);
		    }
		}
	}
	
}
