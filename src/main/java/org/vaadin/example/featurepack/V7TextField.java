package org.vaadin.example.featurepack;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.v7.data.Property;

public class V7TextField extends TextField {

    private Binder<Property> binder = new Binder<>();

    public V7TextField() {
        binder.forField(this)
                .bind(
                        (property) -> (String) property.getValue(),
                        (property, value) -> {
                            property.setValue(value);
                        }
                );
    }

    public V7TextField(Property dataSource) {
        this();
        setPropertyDataSource(dataSource);
    }

    public V7TextField(String caption, Property dataSource) {
        this(dataSource);
        setLabel(caption);
    }

    public void setPropertyDataSource(Property datasource) {
        // Sets the new data source
        setReadOnly(datasource == null ? false
                : datasource.isReadOnly());
        binder.setBean(datasource);
    }





    // ... other methods with Vaadin7 signatures




}
