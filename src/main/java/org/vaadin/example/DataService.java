package org.vaadin.example;

import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.data.util.ObjectProperty;
import com.vaadin.v7.data.util.PropertysetItem;

public class DataService {

    /**
     * This is how I guess the method for "returning data for grids" might look like
     */
    public IndexedContainer getAllPeople() {
        // query from database, map result set directly to IndexedContainer
        IndexedContainer result = new IndexedContainer();

        result.addContainerProperty("name", String.class, "");
        result.addContainerProperty("email", String.class, "");

        Item newItem;

        // imagine iterating over result set
        newItem = result.getItem(result.addItem());
        newItem.getItemProperty("name").setValue("Bretislav");
        newItem.getItemProperty("email").setValue("b@vaadin.com");

        newItem = result.getItem(result.addItem());
        newItem.getItemProperty("name").setValue("Olli");
        newItem.getItemProperty("email").setValue("o@vaadin.com");

        return result;
    }

    /**
     * This is how I guess the method for "getting data for forms" might look like
     */
    public PropertysetItem getPerson(int id) {
        // query from database, map result set directly to PropertysetItem
        PropertysetItem result = new PropertysetItem();
        result.addItemProperty("name", new ObjectProperty<>("Bretislav"));
        result.addItemProperty("email", new ObjectProperty<>("b@vaadin.com"));
        result.addItemProperty("phone", new ObjectProperty<>("+420733682172"));
        return result;
    }

    public void savePerson(PropertysetItem person) {

        // imagine storing these values to database
        person.getItemProperty("name").getValue();
        person.getItemProperty("email").getValue();
        person.getItemProperty("phone").getValue();
    }

}
