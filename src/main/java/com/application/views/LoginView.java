package com.application.views;

import com.database.BackEndLogin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.ResponseEntity;

@PageTitle("Main")
@Route(value = "")
public class LoginView extends VerticalLayout {
    private BackEndLogin dbConnection = new BackEndLogin();

    private Button Submit;
    private String usernameEntry;
    private String passwordEntry;


    public LoginView() {
        H1 title = new H1("Welcome to INVENTORY MANAGEMENT SYSTEM!");
        VaadinSession.getCurrent().setAttribute("isLoggedIn", false);
        TextField username = new TextField();
        username.setLabel("Username");
        username.setClearButtonVisible(true);
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");
        Submit = new Button("Submit");
        Submit.addClickListener(e -> {
            usernameEntry = username.getValue();
            passwordEntry = passwordField.getValue();
            ResponseEntity<String> responseData = dbConnection.Login(usernameEntry,passwordEntry);
            boolean loginVerification = dbConnection.checkResponseData(usernameEntry,passwordEntry,responseData);
            VaadinSession.getCurrent().setAttribute("isLoggedIn", loginVerification);
            VaadinSession.getCurrent().setAttribute("AccessLevel",dbConnection.getAccessLevel(responseData));
            if(loginVerification && dbConnection.getAccessLevel(responseData).equals("Admin")) {
                UI.getCurrent().navigate("AdminView");
            }
//            else if (loginVerification && dbConnection.getAccessLevel(responseData).equals("driver")) {
//                UI.getCurrent().navigate("DriverView");
//            }
            else {
                Notification.show("NOPE! Try again!");
            }
        });
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(title,username, passwordField, Submit);
    }

}
