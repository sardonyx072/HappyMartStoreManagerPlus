package com.happymart;

import java.util.*;

public class AppServer implements Launchable {
	private Map<UUID,Employee> employees;
	private Map<UUID,ItemQuantityManaged> inventory;
	private Map<UUID,Session> sessions;
	
	public AppServer () {
		this.employees = new HashMap<UUID,Employee>();
		this.inventory = new HashMap<UUID,ItemQuantityManaged>();
		this.sessions = new HashMap<UUID,Session>();

		Employee e0 = new Employee(UUID.randomUUID(),new Name("admin","","admin"),"admin");
		Employee e1 = new Employee(UUID.randomUUID(),new Name("Mitchell","J","Hoffmann"),"hoff3539");
		Employee e2 = new Employee(UUID.randomUUID(),new Name("Sohana","","Badhon"),"badh3511");
		Employee e3 = new Employee(UUID.randomUUID(),new Name("Chetan","","Vasudevan"),"vasu1146");
		Employee e4 = new Employee(UUID.randomUUID(),new Name("Jiashu","","Li"),"li354491");
		this.employees.put(e0.getID(), e0);
		this.employees.put(e1.getID(), e1);
		this.employees.put(e2.getID(), e2);
		this.employees.put(e3.getID(), e3);
		this.employees.put(e4.getID(), e4);
		ItemQuantityManaged i0 = new ItemQuantityManaged(new PerishableItemType(UUID.randomUUID(),"banana","bunch",599,new Date(2016,12,24),""),30,15);
		ItemQuantityManaged i1 = new ItemQuantityManaged(new ItemType(UUID.randomUUID(),"book","book",995,""),50,20);
		ItemQuantityManaged i2 = new ItemQuantityManaged(new PerishableItemType(UUID.randomUUID(),"milk","gallon",799,new Date(2016,12,24),""),15,6);
		ItemQuantityManaged i3 = new ItemQuantityManaged(new PerishableItemType(UUID.randomUUID(),"eggs","carton",899,new Date(2016,12,24),""),15,6);
		ItemQuantityManaged i4 = new ItemQuantityManaged(new ItemType(UUID.randomUUID(),"chips","bag",499,""),30,15);
		this.inventory.put(i0.getItemType().getID(), i0);
		this.inventory.put(i1.getItemType().getID(), i1);
		this.inventory.put(i2.getItemType().getID(), i2);
		this.inventory.put(i3.getItemType().getID(), i3);
		this.inventory.put(i4.getItemType().getID(), i4);
	}
	
	@Override
	public Launchable launch(Launchable app) {
		
		return null;
	}

}
