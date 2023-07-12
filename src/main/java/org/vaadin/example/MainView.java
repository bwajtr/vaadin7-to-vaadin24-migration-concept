package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.data.util.PropertysetItem;
import org.vaadin.example.featurepack.V7Grid;
import org.vaadin.example.featurepack.V7Label;
import org.vaadin.example.featurepack.V7TextField;

@Route
public class MainView extends VerticalLayout {

    private DataService dataService = new DataService();

    public MainView() {
        buildForm();
        buildGrid();
    }

    private void buildGrid() {
        final IndexedContainer allPeople = dataService.getAllPeople();
        V7Grid grid = new V7Grid(allPeople);
        add(grid);
    }

    private void buildForm() {
        final PropertysetItem person = dataService.getPerson(1);

        V7Label label = new V7Label(person.getItemProperty("name"));
        add(label);

        V7TextField textField = new V7TextField("My text", person.getItemProperty("name"));
        add(textField);

        V7TextField emailField = new V7TextField("My text", person.getItemProperty("email"));
        add(emailField);

        V7TextField phoneField = new V7TextField("My text", person.getItemProperty("phone"));
        add(phoneField);

        Button save = new Button("Save");
        save.addClickListener(event -> {
            System.out.println("Value: " + person.toString());
        });
        add(save);
    }

}
