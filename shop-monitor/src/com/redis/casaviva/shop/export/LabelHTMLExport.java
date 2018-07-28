/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.export;

import com.redis.casaviva.shop.dc.Product;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class LabelHTMLExport {
	
	
	public static File exportHtml(String path, List<Product.SpecialLabel> labels){
		
		File file = new File(path);
		
		String html = getHtml(labels);
		
		try {
			Files.write(file.toPath(), html.getBytes());
		} catch (IOException ex) {
			Logger.getLogger(LabelHTMLExport.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return file;
	}
	
	
	private static String getHtml(List<Product.SpecialLabel> labels){		
		String html = "<html>";
		
		html += getHtmlHead();
		
		html += "<div class='page' size='A4'>";
		
		int cellIdx = 1;
		for(Product.SpecialLabel label : labels){
			if(!label.isSelected())
				continue;
			
			html += getHtmlDiv(label);
			
			
			if(cellIdx % 3 == 0) {//new line
				
			}
			
			if(cellIdx % 6 == 0) {//new page
				html += "</div>";
				html += "<div class='page' size='A4'>";
			}
		}
		html += "</div>";
		html += "</html>";
		
		return html;
	}
	
	private static String getHtmlHead(){
		String head = "<head>";
		
		head += CSS_STYLE;
		
		head += "</head>";
		return head;
	}
	
	
	/**
	 * Etikete oferte normale
	 * @param label
	 * @return 
	 */
	private static String getHtmlDiv(Product.SpecialLabel label){
		String htmlDiv;
		
		if(label.getDateTime().getHour() == 0 && label.getDateTime().getMinute() == 0){
			htmlDiv = HTML_LABEL_2;
		}
		else{
			htmlDiv = label.getDiffPriceRatio() < 0.13 ? HTML_LABEL1 : HTML_LABEL;
		}
		
		
		
		
		NumberFormat numberFormatA = NumberFormat.getIntegerInstance(); 
		NumberFormat numberFormatB = NumberFormat.getIntegerInstance(); numberFormatB.setMinimumIntegerDigits(3);
		
		String ofertaA = numberFormatA.format( Math.floor(label.getNewPrice()/1000) );
		String ofertaB =  numberFormatB.format(label.getNewPrice() - 1000 * Math.floor(label.getNewPrice()/1000));
		
		String cmimiA = numberFormatA.format(Math.floor(label.getOldPrice()/1000));
		String cmimiB = numberFormatB.format(label.getOldPrice() - 1000 * Math.floor( label.getOldPrice() / 1000) );
		
		htmlDiv = htmlDiv.replace("_CODE_", label.getCode());
		htmlDiv = htmlDiv.replace("_LABEL_INSTANT_", label.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		htmlDiv = htmlDiv.replace("_PRINT_INSTANT_", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		
		htmlDiv = htmlDiv.replace("_PERC_ULJE_", "" + Math.round( 100 * label.getDiffPriceRatio()));
		htmlDiv = htmlDiv.replace("_OFERTE_A_","" + ofertaA);
		htmlDiv = htmlDiv.replace("_OFERTE_B_","" + ofertaB);
		htmlDiv = htmlDiv.replace("_CMIM_A_","" + cmimiA);
		htmlDiv = htmlDiv.replace("_CMIM_B_","" + cmimiB);
		htmlDiv = htmlDiv.replace("_TIPI_", label.getCateogory() == null ? "" : label.getCateogory());
		
		return htmlDiv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private static final String CSS_STYLE = 
		"<style>\n" +
"			page[size=\"A4\"] {\n" +
"				background: white;\n" +
"				width: 21cm;\n" +
"				height: 29.7cm;\n" +
"				display: block;\n" +
"				margin: 0 auto;\n" +
"				margin-bottom: 0.5cm;\n" +
"				box-shadow: 0 0 0.5cm rgba(0,0,0,0.5);\n" +
"			}\n" +
"			@media print {\n" +
"				body, page[size=\"A4\"] {\n" +
"					margin: 0;\n" +
"					box-shadow: 0;\n" +
"				}\n" +
"			}\n" +
"			.outline {\n" +
"				color:rgb(206,023,030); \n" +
"				text-shadow:rgb(0,0,0) 2px 2px 2px; \n" +
"				-webkit-font-smoothing:antialiased;\n" +
"			}\n" +
"			.label-container { background-image:url(backgr.png); background-repeat:no-repeat; background-size:100% 100%; width:65mm; height:141mm; float:left; margin-top: 2mm; margin-left: 2mm;}\n" +
"			.label-container1 { background-image:url(backgr1.png); background-repeat:no-repeat; background-size:100% 100%; width:65mm; height:141mm; float:left; margin-top: 2mm; margin-left: 2mm;}\n" +
"			.label-container-2 { background-image:url(backgr2.png); background-repeat:no-repeat; background-size:100% 100%; width:65mm; height:141mm; float:left; margin-top: 2mm; margin-left: 2mm;}\n" +
"			.label-container-3 { background-image:url(backgr3.png); background-repeat:no-repeat; background-size:100% 100%; width:65mm; height:141mm; float:left; margin-top: 2mm; margin-left: 2mm;}\n" +
"			.percent{\n" +
"				position:relative; \n" +
"				top:55mm; width:100%; \n" +
"				font-family:FranklinGothicDemi; \n" +
"				font-weight:bold;\n" +
"				font-size:75;\n" +
"				text-align:center;\n" +
"			}\n" +
"			.oferta {\n" +
"				position:relative; \n" +
"				top:65mm; \n" +
"				width:100%; \n" +
"				color:rgb(206,023,030); \n" +
"				font-family:DinBlack; \n" +
"				font-weight:bold; \n" +
"				font-size:60; \n" +
"				text-align:center;\n" +
"			}\n" +
"			\n" +
"			.cmimi {\n" +
"				position:relative; \n" +
"				top:60mm; \n" +
"				left: 30mm; \n" +
"				width: 30mm;\n" +
"				color:rgb(000,125,197); \n" +
"				font-family:DinBlack; \n" +
"				font-weight:bold; \n" +
"				font-size:33; \n" +
"				text-align:right; \n" +
"			}\n" +
"			\n" +
"			.artikull{\n" +
"				position:relative; \n" +
"				top:70mm;right:0px; \n" +
"				width:100%; \n" +
"				font-family:DinBlack; \n" +
"				font-weight:bold; \n" +
"				text-align:center;\n" +
"			}\n" +
"			\n" +
"			.strikethrough {\n" +
"				position: relative;\n" +
"			}\n" +
"			\n" +
"			.strikethrough:before {\n" +
"				position: absolute;\n" +
"				content: \"\";\n" +
"				left: 0;\n" +
"				top: 50%;\n" +
"				right: 0;\n" +
"				border-top: 3px solid;\n" +
"				border-color: rgb(206,023,030);\n" +
"			\n" +
"				-webkit-transform:rotate(-10deg);\n" +
"				-moz-transform:rotate(-10deg);\n" +
"				-ms-transform:rotate(-10deg);\n" +
"				-o-transform:rotate(-10deg);\n" +
"				transform:rotate(-10deg);\n" +
"			}\n" +
"			\n" +
"			.code-instant{position:relative; top:72mm; right:0px; width:100%; font-size: 10px; }\n" +
"			.code-instant1{position:relative; top:72mm; right:0px; width:100%; font-size: 10px; }\n" +
"		</style>";
	
	
	
	
	private static final String HTML_LABEL = 
		"<div class='label-container'>	\n" +
"				<div class = 'percent'>_PERC_ULJE_<small><small>%</small></small></div>	\n" +
"				<div class = 'oferta outline'>_OFERTE_A_<small><sup>._OFERTE_B_ L</sup></small></div>	\n" +
"				<div class = 'cmimi strikethrough'>_CMIM_A_<small><sup>._CMIM_B_ L</sup></small></div>	\n" +
"				<div class = 'artikull'>		\n" +
"					<div class = 'tipi'>_TIPI_</div>		\n" +
"					<div class = 'marka'>_MARKA_</div>		\n" +
"					<div class = 'modeli'>_MODELI_</div>	\n" +
"				</div>\n" +
"				<div class='code-instant'>_CODE_ : _LABEL_INSTANT_ : _PRINT_INSTANT_</div>\n" +
"			</div>";
	private static final String HTML_LABEL1 = 
		"<div class='label-container1'>	\n" +
"				<div class = 'percent'><font color='white'>_PERC_ULJE_<small><small>%</small></small></font></div>	\n" +
"				<div class = 'oferta outline'>_OFERTE_A_<small><sup>._OFERTE_B_ L</sup></small></div>	\n" +
"				<div class = 'cmimi strikethrough'>_CMIM_A_<small><sup>._CMIM_B_ L</sup></small></div>	\n" +
"				<div class = 'artikull'>		\n" +
"					<div class = 'tipi'>_TIPI_</div>		\n" +
"					<div class = 'marka'>_MARKA_</div>		\n" +
"					<div class = 'modeli'>_MODELI_</div>	\n" +
"				</div>\n" +
"				<div class='code-instant'>_CODE_ : _LABEL_INSTANT_ : _PRINT_INSTANT_</div>\n" +
"			</div>";
	
	private static final String HTML_LABEL_2 = 
		"<div class='label-container-2'>	\n" +
"				<div class = 'percent'>_PERC_ULJE_<small><small>%</small></small></div>	\n" +
"				<div class = 'oferta outline'>_OFERTE_A_<small><sup>._OFERTE_B_ L</sup></small></div>	\n" +
"				<div class = 'cmimi strikethrough'>_CMIM_A_<small><sup>._CMIM_B_ L</sup></small></div>	\n" +
"				<div class = 'artikull'>		\n" +
"					<div class = 'tipi'>_TIPI_</div>		\n" +
"					<div class = 'marka'>_MARKA_</div>		\n" +
"					<div class = 'modeli'>_MODELI_</div>	\n" +
"				</div>\n" +
"				<div class='code-instant'>_CODE_ : _LABEL_INSTANT_ : _PRINT_INSTANT_</div>\n" +
"			</div>";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static final String CSS_PRICE_LABEL_3X2 = 
		"<style>\n" +
		"	.page{width:210mm; height:297mm;}	\n" +
		"	@media print {body, .page {margin:0;box-shadow: 0;}}\n" +
		"	\n" +
		"	.label{border:1px dotted black; width:63mm; height:140mm; float:left; position:relative; margin: 3mm}\n" +
		"	\n" +
		"	img{height:100%; width:100%;}\n" +
		"	\n" +
		"	.logo {height: 15%; width:100%; text-align:center; }	\n" +
		"	\n" +
		"	\n" +
		"	.price{width:100%; text-align:center; font-size:40px; font-weight:bold; }	\n" +
		"	.descr{height: 15%;width:100%; text-align:center; font-size:20px; font-weight:bold;}		\n" +
		"\n" +
		"	\n" +
		"	.featr{height:45%;width:100%; text-align:center; margin-top: 2mm; border-bottom:1px dotted black;  border-top:1px dotted black;}	\n" +
		"	.featr-key {font-size:13px; font-weight: plain;}\n" +
		"	.featr-val {font-size:13px; font-weight: bold;}	\n" +
		"	\n" +
		"	.bcode {height: 10%; width: 95%; text-align:center; position: absolute; bottom: 5mm; left: 1mm;}\n" +
		"	\n" +
		"	.instn{width: 100%; text-align: center; position: absolute; bottom: 0mm; font-size: 10;}\n" +
		"</style>";
	
	private static final String HTML_PRICE_LABEL_3X2 = ""
		+ "<div class='label'>"
		+ "	<div class='logo'><img src='../aza.jpg'/></div>"
		+ "	<div class='price'>_PRICE_</div>"
		+ "	<div class='descr'>_DESCRIPTION_</div>"
		+ "	<div class='featr'>_FEATURES_</div>"
		+ "	<div class='bcode'><img src='_CODE_.png' width='100%'/></div>"
		+ "	<div class='instn'>_CODE_ : _LABEL_INSTANT_ : _PRINT_INSTANT_</div>"
		+ "</div>";
	
	public static File exportHtmlFromPriceLables3x2(String path, List<Product.PriceLabel> labels){
		
		
		
		File file = new File(path);
		
		String html = htmlFromPriceLables3x2(labels);
		
		try {
			Files.write(file.toPath(), html.getBytes());
		} catch (IOException ex) {
			Logger.getLogger(LabelHTMLExport.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return file;
	}
	
	public static String htmlFromPriceLables3x2(List<Product.PriceLabel> labels) {
		
		String html = "<html>";
		
		html += "<head>" + CSS_PRICE_LABEL_3X2 + "</head><body>";
		
		html += "<div class='page' size='A4'>";
		
		int cellIdx = 1;
		for(Product.PriceLabel label : labels){
						
			html += htmlDivFromPriceLabel3x2(label);
			
			
			if(cellIdx % 3 == 0) {//new line
				html += "<br/>";
			}
			
			if(cellIdx % 6 == 0) {//new page
				html += "</div>";
				html += "<div class='page' size='A4'>";
			}
			
			cellIdx++;
		}
		html += "</div>";
		html += "</body></html>";
		
		return html;
	}
	
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0");
	private static String htmlDivFromPriceLabel3x2(Product.PriceLabel label) {
		String htmlDiv = HTML_PRICE_LABEL_3X2;
		
		htmlDiv = htmlDiv.replace("_DESCRIPTION_", label.getDescription());
		
		
		htmlDiv = htmlDiv.replace("_CODE_", label.getCode());
		htmlDiv = htmlDiv.replace("_LABEL_INSTANT_", label.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		htmlDiv = htmlDiv.replace("_PRINT_INSTANT_", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		htmlDiv = htmlDiv.replace("_PRICE_", DECIMAL_FORMAT.format(label.getPrice()));
		
		String features = "<table>";
		for(Map.Entry entry : label.entrySet()) {
			features = features.concat("<tr><td class='featr-key'>" + entry.getKey() + "</td><td class='featr-val'>" + entry.getValue() + "</td></tr>");
		}
		features = features.concat("</table>");
		
		htmlDiv = htmlDiv.replace("_FEATURES_", features);
		
		
		
		try {
			Barcode barcode = BarcodeFactory.createCode128(label.getBarcode());
			barcode.setFont(null);
//			barcode.setFont(new Font("TimesRoman", Font.PLAIN, 10));
			barcode.setPreferredSize(new Dimension(200, 40));
			BarcodeImageHandler.savePNG(barcode, new File("./temp/" + label.getCode() + ".png"));
		} 
		catch (BarcodeException | OutputException ex) {
			Logger.getLogger(LabelHTMLExport.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return htmlDiv;
	}
	
	
	
	
	
	private static final String CSS_PRICE_LABEL_2 = 
		"<style>\n" +
		"	.page{width:210mm; height:297mm;}	\n" +
		"	@media print {body, .page {margin:0;box-shadow: 0;}}\n" +
		"	\n" +
		"	.label{border:1px dotted black; width:70mm; height:38mm; float:left; position:relative; margin:5mm;}\n" +
		"	\n" +
		"	.logo img{width:100%;height:100%;}\n" +
		"	\n" +
		"	.logo{\n" +
		"		height: 12mm;\n" +
		"		width:50%;\n" +
		"		position:absolute;\n" +
		"		top:0mm; \n" +
		"		left:0mm;\n" +
		"		border-bottom: 1px dotted black;\n" +
		"	}\n" +
		"	\n" +
		"	.price {\n" +
		"		width:50%;\n" +
		"		height: 12mm;\n" +
		"		position:absolute;\n" +
		"		top:0mm; \n" +
		"		right:0mm;  \n" +
		"		text-align:right; \n" +
		"		font-size:10mm; \n" +
		"		font-weight:bold;\n" +
		"		border-bottom: 1px dotted black;\n" +
		"		\n" +
		"	}	\n" +
		"	\n" +
		"	.descr{\n" +
		"		width:100%;\n" +
		"		position:absolute;\n" +
		"		top:15mm; \n" +
		"		text-align:center; \n" +
		"		font-size:4mm; \n" +
		"		font-weight:bold;\n" +
		"	}	\n" +
		"\n" +
		"	\n" +
		"	.bcode {height: 10mm; width: 95%; text-align:center; position: absolute; bottom: 0mm; left: 1mm;}\n" +
		"	\n" +
		"	.instn{width: 100%; text-align: center; position: absolute; bottom: 0mm; font-size: 10;}" +
		"</style>";
	
	private static final String HTML_PRICE_LABEL_2 = ""
		+ "<div class='label'>"
		+ "	<div class='logo'><img src='../aza.jpg'/></div>"
		+ "	<div class='price'>_PRICE_</div>"
		+ "	<div class='descr'>_DESCRIPTION_</div>"
		+ "	<div class='bcode'><img src='_CODE_.png' width='100%'/></div>"
		+ "	<div class='instn'>_CODE_ : _LABEL_INSTANT_ : _PRINT_INSTANT_</div>"
		+ "</div>";
	
	public static File exportHtmlFromPriceLables2(String path, List<Product.PriceLabel> labels){
		
		
		
		File file = new File(path);
		
		String html = htmlFromPriceLables2(labels);
		
		try {
			Files.write(file.toPath(), html.getBytes());
		} catch (IOException ex) {
			Logger.getLogger(LabelHTMLExport.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return file;
	}
	
	public static String htmlFromPriceLables2(List<Product.PriceLabel> labels) {
		
		String html = "<html>";
		
		html += "<head>" + CSS_PRICE_LABEL_2 + "</head><body>";
		
		html += "<div class='page' size='A4'>";
		
		int cellIdx = 1;
		for(Product.PriceLabel label : labels){
						
			html += htmlDivFromPriceLabel2(label);
			
			
			if(cellIdx % 2 == 0) {//new line
				html += "<br/>";
			}
			
			if(cellIdx % 12 == 0) {//new page
				html += "</div>";
				html += "<div class='page' size='A4'>";
			}
			
			cellIdx++;
		}
		html += "</div>";
		html += "</body></html>";
		
		return html;
	}
	
	private static String htmlDivFromPriceLabel2(Product.PriceLabel label) {
		String htmlDiv = HTML_PRICE_LABEL_2;
		
		htmlDiv = htmlDiv.replace("_DESCRIPTION_", label.getDescription());
		
		
		htmlDiv = htmlDiv.replace("_CODE_", label.getCode());
		htmlDiv = htmlDiv.replace("_LABEL_INSTANT_", label.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		htmlDiv = htmlDiv.replace("_PRINT_INSTANT_", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		htmlDiv = htmlDiv.replace("_PRICE_", DECIMAL_FORMAT.format(label.getPrice()));
		
				
		try {
			Barcode barcode = BarcodeFactory.createCode128(label.getBarcode());
			barcode.setFont(null);
//			barcode.setFont(new Font("TimesRoman", Font.PLAIN, 10));
			barcode.setPreferredSize(new Dimension(200, 40));
			BarcodeImageHandler.savePNG(barcode, new File("./temp/" + label.getCode() + ".png"));
		} 
		catch (BarcodeException | OutputException ex) {
			Logger.getLogger(LabelHTMLExport.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return htmlDiv;
	}
	
	
	
	private static final String CSS_PRICE_LABEL_3 = 
		"<style>\n" +
"	.page{width:210mm; height:297mm;}	\n" +
"	@media print {body, .page {margin:0;box-shadow: 0;}}\n" +
"	\n" +
"	.label{border:1px dotted black; width:70mm; height:75mm; float:left; position:relative; margin:5mm;}\n" +
"	\n" +
"	.logo img{width:100%;height:100%;}\n" +
"	\n" +
"	.logo{\n" +
"		height: 12mm;\n" +
"		width:50%;\n" +
"		position:absolute;\n" +
"		top:0mm; \n" +
"		left:0mm;\n" +
"		border-bottom: 1px dotted black;\n" +
"	}\n" +
"	\n" +
"	.price {\n" +
"		width:50%;\n" +
"		height: 12mm;\n" +
"		position:absolute;\n" +
"		top:0mm; \n" +
"		right:0mm;  \n" +
"		text-align:right; \n" +
"		font-size:10mm; \n" +
"		font-weight:bold;\n" +
"		border-bottom: 1px dotted black;\n" +
"		\n" +
"	}	\n" +
"	\n" +
"	.descr{\n" +
"		width:100%;\n" +
"		position:absolute;\n" +
"		top:15mm; \n" +
"		text-align:center; \n" +
"		font-size:4mm; \n" +
"		font-weight:bold;border-bottom: 1px dotted black;\n" +
"	}	\n" +
"	\n" +
"	.featr{width:100%; text-align:center;position:absolute; top: 30mm; }	\n" +
"	.featr-key {font-size:10px; font-weight: plain;}\n" +
"	.featr-val {font-size:10px; font-weight: bold;}	\n" +
"	\n" +
"	.bcode {height: 10mm; width: 100%; text-align:center; position: absolute; bottom: 0mm; border-top: 1px dotted black;}\n" +
"	\n" +
"	.instn{width: 100%; text-align: center; position: absolute; bottom: 0mm; font-size: 10;}</style>";
	
	private static final String HTML_PRICE_LABEL_3 = ""
		+ "<div class='label'>"
		+ "	<div class='logo'><img src='../aza.jpg'/></div>"
		+ "	<div class='price'>_PRICE_</div>"
		+ "	<div class='descr'>_DESCRIPTION_</div>"
		+ "	<div class='featr'>_FEATURES_</div>"
		+ "	<div class='bcode'><img src='_CODE_.png' width='100%'/></div>"
		+ "	<div class='instn'>_CODE_ : _LABEL_INSTANT_ : _PRINT_INSTANT_</div>"
		+ "</div>";
	
	public static File exportHtmlFromPriceLables3(String path, List<Product.PriceLabel> labels){
		
		
		
		File file = new File(path);
		
		String html = htmlFromPriceLables3(labels);
		
		try {
			Files.write(file.toPath(), html.getBytes());
		} catch (IOException ex) {
			Logger.getLogger(LabelHTMLExport.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return file;
	}
	
	public static String htmlFromPriceLables3(List<Product.PriceLabel> labels) {
		
		String html = "<html>";
		
		html += "<head>" + CSS_PRICE_LABEL_3 + "</head><body>";
		
		html += "<div class='page' size='A4'>";
		
		int cellIdx = 1;
		for(Product.PriceLabel label : labels){
						
			html += htmlDivFromPriceLabel3(label);
			
			
			if(cellIdx % 2 == 0) {//new line
				html += "<br/>";
			}
			
			if(cellIdx % 6 == 0) {//new page
				html += "</div>";
				html += "<div class='page' size='A4'>";
			}
			
			cellIdx++;
		}
		html += "</div>";
		html += "</body></html>";
		
		return html;
	}
	
	private static String htmlDivFromPriceLabel3(Product.PriceLabel label) {
		String htmlDiv = HTML_PRICE_LABEL_3;
		
		htmlDiv = htmlDiv.replace("_DESCRIPTION_", label.getDescription());
		
		
		htmlDiv = htmlDiv.replace("_CODE_", label.getCode());
		htmlDiv = htmlDiv.replace("_LABEL_INSTANT_", label.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		htmlDiv = htmlDiv.replace("_PRINT_INSTANT_", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")));
		htmlDiv = htmlDiv.replace("_PRICE_", DECIMAL_FORMAT.format(label.getPrice()));
		
		String features = "<table>";
		int count = 0;
		for(Map.Entry entry : label.entrySet()) {
			if(count == 7)
				break;
			
			features = features.concat("<tr><td class='featr-key'>" + entry.getKey() + "</td><td class='featr-val'>" + entry.getValue() + "</td></tr>");
			count++;
		}
		features = features.concat("</table>");
		htmlDiv = htmlDiv.replace("_FEATURES_", features);
				
		try {
			Barcode barcode = BarcodeFactory.createCode128(label.getBarcode());
			barcode.setFont(null);
//			barcode.setFont(new Font("TimesRoman", Font.PLAIN, 10));
			barcode.setPreferredSize(new Dimension(200, 40));
			BarcodeImageHandler.savePNG(barcode, new File("./temp/" + label.getCode() + ".png"));
		} 
		catch (BarcodeException | OutputException ex) {
			Logger.getLogger(LabelHTMLExport.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return htmlDiv;
	}
}
