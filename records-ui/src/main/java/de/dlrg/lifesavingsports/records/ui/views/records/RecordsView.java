package de.dlrg.lifesavingsports.records.ui.views.records;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.ui.views.MainLayout;

@PageTitle("Records")
@Route(value = "records", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class RecordsView extends Div {

    private final RecordsService recordsService;

    Grid<Record> grid = new Grid<>();

    public RecordsView(RecordsService recordsService) {
        this.recordsService = recordsService;

        addClassName("records-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(record -> createCard(record));
        add(grid);

        grid.setItems(query -> this.recordsService.fetchTimes("AK 12", Gender.Female, query.getOffset(), query.getLimit()));
    }

    private HorizontalLayout createCard(Record record) {
        return new RecordView(record);
    }
}
