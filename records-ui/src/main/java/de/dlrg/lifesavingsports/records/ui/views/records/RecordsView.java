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

        grid.setItems(query -> this.recordsService.fetchTimes("open", Gender.Female, query.getOffset(), query.getLimit()));
    }

    private HorizontalLayout createCard(Record record) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        Image image = new Image();
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(record.getName());
        name.addClassName("name");
        Span date = new Span(record.getClub());
        date.addClassName("club");
        header.add(name, date);

        Span post = new Span(record.getNation());
        post.addClassName("nation");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        description.add(header, post, actions);
        card.add(image, description);
        return card;
    }
}
