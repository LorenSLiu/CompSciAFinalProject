package com.application.views.admin;

import com.application.description.Product;
import com.database.BackEndProductsProvider;
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

@Route("ProductView")
public class ProductView extends AppLayout{



    //private Crud<> crud;
//private Product product;
    private Grid<Product> grid;
    private Editor<Product> editor;
    BackEndProductsProvider BackEndProductsProvider = new BackEndProductsProvider();


    public ProductView() {
        VerticalLayout layout = new VerticalLayout();

//        DrawerToggle toggle = new DrawerToggle();
//
//        H1 title = new H1("Product");
//        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
//                .set("margin", "0");
//
//        SideNav nav = getSideNav();
//        Scroller scroller = new Scroller(nav);
//        scroller.setClassName(LumoUtility.Padding.SMALL);
//
//        addToDrawer(scroller);
//        addToNavbar(toggle, title);
//        setPrimarySection(Section.DRAWER);
//        Grid<Product> grid = new Grid<>(Product.class, false);
//        grid.addColumn(Product::getName).setHeader("Product name");
//        grid.addColumn(Product::getPrice).setHeader("Product price");
//        grid.addColumn(Product::getLocation).setHeader("Product location");
//        grid.addColumn(Product::getNumberInStore).setHeader("Product inventory");
//        List<Product> Product = BackEndProductsProvider.GetAllProducts();
//        grid.setItems(Product);
//        grid.setAllRowsVisible(true);
//        getElement().getStyle().set("height", "100%");
//        //addToDrawer(grid);
//        setContent(grid);
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
        GridPro<Product> grid = new GridPro<>();
        grid.setEditOnClick(true);
        grid.addEditColumn(Product::getName).text(Product::setName).setHeader("Product name");

        grid.addEditColumn(Product::getPrice).text((product, price) -> product.setPrice(Double.parseDouble(price))).setHeader("Product price");
        grid.addEditColumn(Product::getLocation).text(Product::setLocation).setHeader("Product location");
        grid.addEditColumn(Product::getNumberInStore).text(((product, NumberInStore) -> product.setNumberInStore(Integer.parseInt(NumberInStore)))).setHeader("Product inventory");


        // Add Save Listener
        grid.getEditor().addSaveListener(event -> {
            Product product = event.getItem();
            System.out.println("index");
        });

        // Add a dialog for adding new products
        Dialog addProductDialog = new Dialog();
        TextField productNameField = new TextField("Product name");
        TextField productPriceField = new TextField("Product price");
        TextField productLocationField = new TextField("Product location");
        TextField productInventoryField = new TextField("Product inventory");
        Button saveButton = new Button("Save", event -> {
            if(productInventoryField.getValue().isEmpty() || productLocationField.getValue().isEmpty() || productPriceField.getValue().isEmpty() || productNameField.getValue().isEmpty()){
                Notification.show("Please fill in all fields");
            }
            if(!productInventoryField.getValue().matches("[0-9]+")){
                Notification.show("Please enter valid numbers for price and inventory count");
            }
            else {
                Product newProduct = new Product(productNameField.getValue(), Double.parseDouble(productPriceField.getValue()), productLocationField.getValue(), Integer.parseInt(productInventoryField.getValue()));
                BackEndProductsProvider.Sender(newProduct);
                grid.setItems(BackEndProductsProvider.GetAllProducts());
                addProductDialog.close();
            }
        });
        addProductDialog.add(productNameField, productPriceField, productLocationField, productInventoryField, saveButton);

        // Add a button for adding new products
        Button addButton = new Button("Add Product", event -> addProductDialog.open());


        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        // Add a button for deleting selected products
        Button deleteButton = new Button("Delete Selected", event -> {
            List<Product> selectedItems = new ArrayList<>(grid.getSelectedItems());
            for(int i = 0; i < selectedItems.size(); i++){
                System.out.println("hey");
                BackEndProductsProvider.DeleteProduct(selectedItems.get(i).getName());
            }
            grid.setItems(BackEndProductsProvider.GetAllProducts());
            System.out.println("updated");
        });

        List<Product> ProductList = BackEndProductsProvider.GetAllProducts();
        grid.setItems(ProductList);
        grid.setAllRowsVisible(true);
        getElement().getStyle().set("height", "100%");


        IntegerField inventoryCount = new IntegerField();
        inventoryCount.setLabel("Total Inventory Count");
        inventoryCount.setValue(BackEndProductsProvider.getTotalInventory());

        //below are graph
//        Chart chart = new Chart(ChartType.LINE);
//
//        Configuration configuration = chart.getConfiguration();
//
//        configuration.setTitle("");
//        configuration.setSubTitle("Source: thesolarfoundation.com");
//
//        YAxis yAxis = configuration.getyAxis();
//        yAxis.setTitle("Number of Employees");
//
//        Legend legend = configuration.getLegend();
//        legend.setLayout(LayoutDirection.VERTICAL);
//        legend.setVerticalAlign(VerticalAlign.MIDDLE);
//        legend.setAlign(HorizontalAlign.RIGHT);
//
//        PlotOptionsSeries plotOptionsSeries = new PlotOptionsSeries();
//        plotOptionsSeries.setPointStart(2010);
//        configuration.setPlotOptions(plotOptionsSeries);
//
//        configuration.addSeries(new ListSeries("Installation", 43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175));
//        configuration.addSeries(new ListSeries("Manufacturing", 24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434));
//        configuration.addSeries(new ListSeries("Sales & Distribution", 11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387));
//        configuration.addSeries(new ListSeries("Project Development", null, null, 7988, 12169, 15112, 22452, 34400, 34227));
//        configuration.addSeries(new ListSeries("Other", 12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111));
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration configuration = chart.getConfiguration();
        configuration.setTitle("Total Inventory Count");
        configuration.setSubTitle("Source: LOREN LIU'S dAtABasE");

        List<Double> inventoryCountList = new ArrayList<>();
        for(Product product : ProductList){
            inventoryCountList.add((double) product.getNumberInStore());
        }
        Double[] inventoryCountArray = inventoryCountList.toArray(new Double[0]);
        configuration.addSeries(new ListSeries("Inventory Count", inventoryCountArray));
        XAxis x = new XAxis();
        List<String> productNames = new ArrayList<>();
        x.setCrosshair(new Crosshair());
        for(Product product : ProductList){
            productNames.add(product.getName());
        }
        String[] productNamesArray = productNames.toArray(new String[0]);
        x.setCategories(productNamesArray);

        configuration.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setTitle("Rainfall (mm)");
        configuration.addyAxis(y);

        Tooltip tooltip = new Tooltip();
        tooltip.setShared(true);
        configuration.setTooltip(tooltip);
        //end of graph



        // Add the grid, add button, and delete button to the layout
        addToNavbar(addButton, deleteButton);
        layout.add(grid);
        layout.add(inventoryCount);
        layout.add(chart);
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
