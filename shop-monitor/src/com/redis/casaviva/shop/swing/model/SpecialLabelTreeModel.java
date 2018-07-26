/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.swing.model;

import com.redis.casaviva.shop.dc.Product;
import com.redis.casaviva.shop.dc.Warehouse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SpecialLabelTreeModel extends DefaultTreeModel{
	
	private Warehouse warehouse;
	
	public SpecialLabelTreeModel() {
		super(new DefaultMutableTreeNode());
	}
	
	public void setWarehouse(Warehouse warehouse){
		this.warehouse = warehouse;
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(warehouse);
		
		TreeSet<LocalDateTime> dateTimes = new TreeSet<>();
		for(Product.SpecialLabel label : warehouse.getLabelList()){
			LocalDateTime dateTime = label.getDateTime();
			DefaultMutableTreeNode dateTimeNode = new DefaultMutableTreeNode(dateTime){
				@Override
				public String toString() {
					return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
				}
			};
			
			if(dateTimes.add(dateTime)){
				rootNode.add(dateTimeNode);
			}
			
		}
		super.setRoot(rootNode);
	}
	
	public List<Product.SpecialLabel> getLabels(DefaultMutableTreeNode node){
		
		if(node == this.getRoot())
			return warehouse.getLabelList();
		else if(node.getUserObject() instanceof LocalDate){
			return warehouse.getLabelList().stream().filter(label -> Objects.equals(label.getDate(), node.getUserObject())).collect(Collectors.toList());
		}
		else if(node.getUserObject() instanceof LocalDateTime){
			return warehouse.getLabelList().stream().filter(label -> Objects.equals(label.getDateTime(), node.getUserObject())).collect(Collectors.toList());
		}
		else{
			return new ArrayList<>();
		}
		
	}	
}
