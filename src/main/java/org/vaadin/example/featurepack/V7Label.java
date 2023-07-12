package org.vaadin.example.featurepack;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.v7.data.Property;

public class V7Label extends Span {

    private Property dataSource;
    private Binder<Property> binder = new Binder<>();

    public V7Label() {
        ReadOnlyHasValue<String> hasValue = new ReadOnlyHasValue<>(this::setText);
        binder.forField(hasValue)
                .bind(
                        (field) -> (String) dataSource.getValue(),
                        (field, value) -> {
                            dataSource.setValue(value);
                        }
                );
    }

    public V7Label(Property dataSource) {
        this();
        setPropertyDataSource(dataSource);
    }

    public void setPropertyDataSource(Property newDataSource) {
        // Sets the new data source
        dataSource = newDataSource;
        binder.setBean(dataSource);
    }




    // ... other methods with Vaadin7 signatures



}
