package de.dlrg.lifesavingsports.records.ui.views.records;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class RecordView extends HorizontalLayout {
    public RecordView(Record record) {
        addClassName("card");
        setSpacing(false);
        getThemeList().add("spacing-s");

        Span discipline = new Span(record.getDiscipline());
        discipline.addClassName("discipline");

        Span name = new Span(record.getName());
        discipline.addClassName("name");

        Span nation = new Span(record.getNation());
        discipline.addClassName("nation");

        Span gender = new Span(record.getGender());
        discipline.addClassName("gender");

        Span club = new Span(record.getClub());
        discipline.addClassName("club");

        Span venue = new Span(record.getVenue());
        discipline.addClassName("venue");

        Span date = new Span(record.getDate());
        discipline.addClassName("date");

        Span time = new Span(record.getDate());
        discipline.addClassName("time");

        VerticalLayout venueAndDate = new VerticalLayout();
        venueAndDate.addClassName("venueAndDate");
        venueAndDate.setSpacing(false);
        venueAndDate.setPadding(false);
        venueAndDate.add(venue, date);

        add(discipline, name, nation, gender, club, venueAndDate, time);
    }
}
