package de.dlrg.lifesavingsports.records.ui.views.mytimes;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.dlrg.lifesavingsports.records.ui.data.entity.TimeModel;
import de.dlrg.lifesavingsports.records.ui.data.service.TimeModelService;
import de.dlrg.lifesavingsports.records.ui.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.Optional;
import java.util.UUID;

@PageTitle("My Times")
@Route(value = "my-times/:timeModelID?/:action?(edit)", layout = MainLayout.class)
@RolesAllowed("user")
// @PermitAll
public class MyTimesView extends Div implements BeforeEnterObserver {

    private final String TIMEMODEL_ID = "timeModelID";
    private final String TIMEMODEL_EDIT_ROUTE_TEMPLATE = "my-times/%s/edit";

    private Grid<TimeModel> grid = new Grid<>(TimeModel.class, false);

    private TextField name;
    private TextField club;
    private TextField nation;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<TimeModel> binder;

    private TimeModel timeModel;

    private final TimeModelService timeModelService;

    @Autowired
    public MyTimesView(TimeModelService timeModelService) {
        this.timeModelService = timeModelService;
        addClassNames("my-times-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("club").setAutoWidth(true);
        grid.addColumn("nation").setAutoWidth(true);
        grid.setItems(query -> {
            query.getOffset();
            query.getLimit();
            return timeModelService.findAll();
        });
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(TIMEMODEL_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(MyTimesView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(TimeModel.class);

        // Bind fields. This is where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.timeModel == null) {
                    this.timeModel = new TimeModel();
                }
                binder.writeBean(this.timeModel);

                timeModelService.update(this.timeModel);
                clearForm();
                refreshGrid();
                Notification.show("TimeModel details stored.");
                UI.getCurrent().navigate(MyTimesView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the timeModel details.");
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<UUID> timeModelId = event.getRouteParameters().get(TIMEMODEL_ID).map(UUID::fromString);
        if (timeModelId.isPresent()) {
            Optional<TimeModel> timeModelFromBackend = timeModelService.get(timeModelId.get());
            if (timeModelFromBackend.isPresent()) {
                populateForm(timeModelFromBackend.get());
            } else {
                Notification.show(String.format("The requested timeModel was not found, ID = %s", timeModelId.get()),
                                  3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(MyTimesView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        name = new TextField("Name");
        club = new TextField("Club");
        nation = new TextField("Nation");
        Component[] fields = new Component[]{name, club, nation};

        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(TimeModel value) {
        this.timeModel = value;
        binder.readBean(this.timeModel);

    }
}
