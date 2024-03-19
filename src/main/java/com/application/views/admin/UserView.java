package com.application.views.admin;

import com.application.description.Employees;
import com.application.description.Product;
import com.database.BackEndUserManager;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.theme.lumo.LumoUtility;


import java.util.ArrayList;
import java.util.List;

@Route("UserView")
public class UserView extends AppLayout{



    //private Crud<> crud;
//private Product product;
    private Grid<Product> grid;
    private Editor<Product> editor;
    BackEndUserManager backEndUserManager = new BackEndUserManager();


    public UserView() {
        VerticalLayout layout = new VerticalLayout();

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Product");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();
        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
        setPrimarySection(Section.DRAWER);

        // Create a GridPro instance


        GridPro<Employees> grid = new GridPro<>();
        grid.setEditOnClick(true);
        grid.addEditColumn(Employees::getFirstName).text(Employees::setFirstName).setHeader("First Name");
        grid.addEditColumn(Employees::getLastName).text(Employees::setLastName).setHeader("Last Name");
        grid.addEditColumn(Employees::getPosition).text(Employees::setPosition).setHeader("Position");
        grid.addEditColumn(Employees::getHours).text((product, price) -> product.setHours(Double.parseDouble(price))).setHeader("Total Hours Worked");
        // Add Save Listener
        grid.getEditor().addSaveListener(event -> {
            Employees huh = event.getItem();
            System.out.println("index");
        });

        // Add a dialog for adding new products
        Dialog addProductDialog = new Dialog();
        TextField employeeFirstNameField = new TextField("First Name");
        TextField employeeLastNameField = new TextField("Last Name");
        TextField employeeSalaryField = new TextField("Salaries");
        TextField employeePositionField = new TextField("Position");
        TextField employeeTrainingField = new TextField("Training");
        int defaultAnnualDayOff = 14;
        int defaultLeftDays = 0;
        double defaultWorkedHours = 0;
        Button saveButton = new Button("Save", event -> {
            if(employeeFirstNameField.getValue().isEmpty() || employeeLastNameField.getValue().isEmpty() || employeePositionField.getValue().isEmpty() || employeeFirstNameField.getValue().isEmpty() || employeeSalaryField.getValue().isEmpty()){
                Notification.show("Please fill in all fields");
            }
            else {
                Employees newEmployee = new Employees(employeeFirstNameField.getValue(),employeeLastNameField.getValue(), Double.parseDouble(employeeSalaryField.getValue()), defaultAnnualDayOff, defaultLeftDays, employeePositionField.getValue(), defaultWorkedHours, Boolean.parseBoolean(employeeTrainingField.getValue()));
                backEndUserManager.Sender(newEmployee);
                grid.setItems(backEndUserManager.GetAllEmployee());
                addProductDialog.close();
            }
        });
        addProductDialog.add(employeeFirstNameField, employeeLastNameField, employeeSalaryField, employeePositionField, employeeTrainingField, saveButton);

//         Add a button for adding new products
        Button addButton = new Button("Add User", event -> addProductDialog.open());


        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        // Add a button for deleting selected products
        Button deleteButton = new Button("Delete Selected", event -> {
            List<Employees> selectedEmployees = new ArrayList<>(grid.getSelectedItems());
            for(int i = 0; i < selectedEmployees.size(); i++){
                System.out.println("hey");
                backEndUserManager.DeleteUser(selectedEmployees.get(i).getFirstName(),selectedEmployees.get(i).getLastName());
            }
            grid.setItems(backEndUserManager.GetAllEmployee());
            System.out.println("updated");
        });


        List<Employees> EmployeeList = backEndUserManager.GetAllEmployee();
        grid.setItems(EmployeeList);
        grid.setAllRowsVisible(true);
        getElement().getStyle().set("height", "100%");


        addToNavbar(addButton, deleteButton);
        layout.add(grid);


        setContent(layout);



    }
    private SideNav getSideNav() {
        SideNav nav = new SideNav();
        nav.addItem(
                new SideNavItem("Products", "/ProductView",
                        VaadinIcon.PACKAGE.create()),
                new SideNavItem("Users","/UserView",
                        VaadinIcon.USERS.create())
        );
        return nav;
    }
}
