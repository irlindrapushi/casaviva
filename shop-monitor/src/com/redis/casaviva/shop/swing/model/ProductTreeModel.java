/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.swing.model;

import com.redis.casaviva.shop.dc.Product;
import com.redis.casaviva.shop.dc.Warehouse;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class ProductTreeModel extends DefaultTreeModel{
	
	private Warehouse warehouse;
	
	public ProductTreeModel() {
		super(new DefaultMutableTreeNode());
		
	}
	
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
		
		if(warehouse == null){
			this.setRoot(new DefaultMutableTreeNode());
			return;
		}
		
		
		
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(warehouse);
		
		for(int idx = 0; idx < warehouse.getProducts().size(); idx++){
			Product p = warehouse.getProducts().get(idx);
			
			DefaultMutableTreeNode parent = rootNode;
			
			//category
			DefaultMutableTreeNode child = null;
			for(int i = 0; i<parent.getChildCount(); i++){
				if(((DefaultMutableTreeNode) parent.getChildAt(i)).getUserObject().equals(p.getCategory())){
					child = (DefaultMutableTreeNode) parent.getChildAt(i);
					break;
				}
			}
			if(child == null){
				child = new DefaultMutableTreeNode(p.getCategory());
				parent.add(child);
			}
			parent = child; child = null;
			
			//type
			for(int i = 0; i<parent.getChildCount(); i++){
				if(((DefaultMutableTreeNode) parent.getChildAt(i)).getUserObject().equals(p.getType())){
					child = (DefaultMutableTreeNode) parent.getChildAt(i);
					break;
				}
			}
			if(child == null){
				child = new DefaultMutableTreeNode(p.getType());
				parent.add(child);
			}
			
		}
		
		super.setRoot(rootNode);
	}
	
	
}
