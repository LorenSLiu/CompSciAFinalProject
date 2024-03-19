package com.application.views.admin;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;

@PageTitle("Admin Portal")
@Route("AdminView")
public class AdminView extends AppLayout implements BeforeEnterObserver {
    public AdminView() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Welcome to Admin Portal");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();
        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
        setPrimarySection(Section.DRAWER);

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
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Object isLoggedIn = VaadinSession.getCurrent().getAttribute("isLoggedIn");
        Object AccessLevel = VaadinSession.getCurrent().getAttribute("AccessLevel");
        System.out.println(isLoggedIn);
        System.out.println(AccessLevel);
        if(!(isLoggedIn.equals(true) && AccessLevel.equals("Admin"))){
//            event.rerouteTo(LoginView.class);
        }
    }
}
