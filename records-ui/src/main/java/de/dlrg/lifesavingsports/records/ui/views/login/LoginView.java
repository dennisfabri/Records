package de.dlrg.lifesavingsports.records.ui.views.login;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.dlrg.lifesavingsports.records.ui.data.entity.User;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.PermitAll;
import java.util.Optional;

@PageTitle("Login")
@Route(value = "redirect-login")
public class LoginView extends Div {

    public LoginView() {
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (hasAuthenticatedUser()) {
            getUI().ifPresent(ui -> ui.navigate("/"));
        }
    }

    private boolean hasAuthenticatedUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return false;
        }
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return false;
        }

        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();
        String preferredUsername = keycloakSecurityContext.getIdToken().getPreferredUsername();

        return preferredUsername != null && !preferredUsername.isBlank();
    }

}
