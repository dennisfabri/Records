package de.dlrg.lifesavingsports.records.ui.views.logout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@PageTitle("logout")
@Route(value = "logout")
public class LogoutView extends Div implements AfterNavigationObserver {

    public LogoutView() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        getUI().ifPresent(ui -> ui.navigate("/"));
    }
}
