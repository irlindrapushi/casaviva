/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.export;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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
public class Label {
	
	private final String code, barcode, sector, category, description;
	private final Double price, discount;
	private final Instant instant;

	public Label(String code, String barcode, String sector, String category, String description, Double price, Double discount, Instant instant) {
		this.code = code;
		this.barcode = barcode;
		this.sector = sector;
		this.category = category;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.instant = instant;
	}

	public String getCode() {
		return code;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getSector() {
		return sector;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public Double getDiscount() {
		return discount;
	}

	public Instant getInstant() {
		return instant;
	}

	

	
	
	
	
	public static File generatePDF(Collection<Label> labels) {
		File file = new File("labels.pdf");
		
		NumberFormat integerFormatter = NumberFormat.getIntegerInstance();
		integerFormatter.setMaximumFractionDigits(0);
		
		NumberFormat percentFormatter = NumberFormat.getPercentInstance();
		percentFormatter.setMaximumFractionDigits(0);
		
		try{
			Image image = Image.getInstance("logo.png");
			image.scaleToFit(100, 100);
			
			ImageBackgroundEvent imageBackgroundEvent = new ImageBackgroundEvent(Image.getInstance("backgr_small.png"));
			
			Document document = new Document();	   
			document.setPageSize(PageSize.A4);
			document.setMargins(0, 0, 0, 0);

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			PdfPTable mainTable = new PdfPTable(3);//To set number of columns in a row
			mainTable.setWidthPercentage(100);

			for(Label label : labels) {
				PdfPCell labelCell = new PdfPCell();
				labelCell.setPadding(5);
				labelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				labelCell.setVerticalAlignment(Element.ALIGN_TOP);
				labelCell.setFixedHeight(PageSize.A4.getHeight() / 7 - 0.01f);
				labelCell.setBorder(Rectangle.NO_BORDER);
				
				System.out.println(PageSize.A4.getHeight());
				
				
				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100);
				
				PdfPCell cell;
				Phrase phrase;
				Font font;
				
				//logo
				font = FontFactory.getFont(FontFactory.COURIER_BOLD, 5);				
				phrase = new Phrase(
					label.instant.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM HH:mm")) + " (" + 
					Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM HH:mm")) + ")", 
					font);
				cell = new PdfPCell(phrase);
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(24);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				table.addCell(cell);
				
				//price
				font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12);
				font.setColor(BaseColor.RED);
				font.setStyle(Font.STRIKETHRU);
				
				phrase = new Phrase(integerFormatter.format(label.price) + "L", font);
				
				cell = new PdfPCell(phrase);
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
				
				table.addCell(cell);
				
				//sector				
				phrase = new Phrase(label.sector, FontFactory.getFont(FontFactory.COURIER, 10));				
				cell = new PdfPCell(phrase);	
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(24);			
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				//discount price			
				font = FontFactory.getFont(FontFactory.COURIER_BOLD, 18);
				font.setColor(BaseColor.GREEN.darker());
				
				phrase = new Phrase(integerFormatter.format((1-label.discount) * label.price) + "L", font);
				
				cell = new PdfPCell(phrase);	
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				
				table.addCell(cell);
				
				
				//description
				phrase = new Phrase(label.description, FontFactory.getFont(FontFactory.COURIER_BOLD, 12));
				cell = new PdfPCell(phrase);	
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(45);	
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				//discount percent
				font = FontFactory.getFont(FontFactory.COURIER_BOLD, 28);
				font.setColor(BaseColor.ORANGE);
				
				phrase = new Phrase(percentFormatter.format(label.discount), font);
				cell = new PdfPCell(phrase);	
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				
				
				table.addCell(cell);
				
				
				//barcode
				Barcode128 barcode = new Barcode128();
				barcode.setBaseline(-1);
				barcode.setFont(null);
				barcode.setCode(label.barcode);
				barcode.setCodeType(Barcode128.CODE128);
				barcode.setSize(8);
				barcode.setBarHeight(10.0f);
				Image barcodeImage = barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY);				
				cell = new PdfPCell(barcodeImage);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.TOP);
				cell.setFixedHeight(20);
				cell.setColspan(2);
				table.addCell(cell);
				
				//code
				phrase = new Phrase(label.code, FontFactory.getFont(FontFactory.COURIER_BOLD, 10));
				cell = new PdfPCell(phrase);	
				cell.setBorder(Rectangle.TOP);
				cell.setFixedHeight(20);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);
				
				
				
				
				
				
				
				labelCell.addElement(table);
				
				labelCell.setCellEvent(imageBackgroundEvent);
				
				mainTable.addCell(labelCell);
			}
			mainTable.completeRow();
			
			document.add(mainTable);
			document.close();
		} 
		catch (FileNotFoundException | DocumentException ex) {
			Logger.getLogger(Label.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Label.class.getName()).log(Level.SEVERE, null, ex);
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
