package org.vaadin.example.featurepack;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;

import java.util.Collection;
import java.util.Iterator;

public class V7Grid extends Grid<Item> {

    public V7Grid(IndexedContainer data) {
        super();
        setContainerDataSource(data);
        setItems(new CallbackDataProvider<>(
                query -> {
                    return data.getItemIds(query.getOffset(), query.getLimit())
                            .stream().map(data::getItem);
                },
                query -> {
                    return data.getItemIds(query.getOffset(), query.getLimit()).size();
                }));
    }

    private void setContainerDataSource(IndexedContainer container) {

        if (container instanceof Container.Sortable) {

            // If the container is sortable, go through the current sort order
            // and match each item to the sortable properties of the new
            // container. If the new container does not support an item in the
            // current sort order, that item is removed from the current sort
            // order list.
            Collection<?> sortableProps = ((Container.Sortable) container)
                    .getSortableContainerPropertyIds();

            final Iterator<GridSortOrder<Item>> i = this.getSortOrder().iterator();
            while (i.hasNext()) {
                if (!sortableProps.contains(i.next().getSorted().getKey())) {
                    i.remove();
                }
            }

            this.sort(this.getSortOrder());
        } else {
            // Clear sorting order. Don't sort.
            this.getSortOrder().clear();
        }

        if (this.getColumns().isEmpty()) {
            // Add columns
            for (Object propertyId : container.getContainerPropertyIds()) {
                Column<?> column = appendColumn(propertyId, container);

                // Initial sorting is defined by container
                if (container instanceof Container.Sortable) {
                    column.setSortable(((Container.Sortable) container)
                            .getSortableContainerPropertyIds()
                            .contains(propertyId));
                } else {
                    column.setSortable(false);
                }
            }
        } else {
            // cover the case where the container has changed and some columns already exist in the grid
        }

    }

    private Column<?> appendColumn(Object datasourcePropertyId, IndexedContainer container) {
        if (datasourcePropertyId == null) {
            throw new IllegalArgumentException("Property id cannot be null");
        }
        assert container.getContainerPropertyIds().contains(
                datasourcePropertyId) : "Datasource should contain the property id";

        final Column<Item> column = addColumn((item) -> item.getItemProperty(datasourcePropertyId).getValue());

        String humanFriendlyPropertyId = SharedUtil.propertyIdToHumanFriendly(
                String.valueOf(datasourcePropertyId));
        column.setHeader(humanFriendlyPropertyId);

        if (container instanceof Container.Sortable
                && ((Container.Sortable) container).getSortableContainerPropertyIds()
                .contains(datasourcePropertyId)) {
            column.setSortable(true);
        }

        return column;
    }



    // ... other methods with Vaadin7 signatures



}
