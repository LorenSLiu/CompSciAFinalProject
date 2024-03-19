# Product Inventory Management System

Course: AP Computer Science A  
Author: Loren Liu  
Class Period: 1  
Academic Year: 2023-2024

## Project Overview

This proposal outlines the development of a robust Product Inventory Management System aimed at enhancing the efficiency of store inventory management. The system will be implemented using Java, utilizing MongoDB as the database and JFrame as the GUI library. Its primary functions will include adding, modifying, and viewing product information, as well as computing the total quantity and value of the inventory.

## Repository Link

The entire source code for this project will be publicly accessible on GitHub: [https://github.com/LorenSLiu/CompSciAFinalProject](https://github.com/LorenSLiu/CompSciAFinalProject)

## Key Features

1. **Item Addition**
    - Enables administrators to add new items to the inventory.
    - Each item will possess attributes such as location, price, unique ID, and quantity on hand.
2. **Item Attribute Modification**
    - Allows administrators to modify various attributes of existing items, such as location, price, or any pertinent details.
3. **Inventory Summation**
    - Incorporates an inventory class to systematically track all products in stock.
    - The system will compute and display the total quantity and value of the entire inventory.
4. **Inventory Information Printing**
    - Facilitates administrators in printing detailed information about the entire inventory, including each product's attributes.

## Input Requirements

- **For Adding Items**
    - Administrators input details such as location, price, unique ID, and initial quantity.
- **For Modifying Items**
    - Administrators input the product ID and the updated attributes.

## Output

- Detailed information about the inventory, encompassing each product's location, price, unique ID, and quantity on hand.
- Total quantity and value of the entire inventory.

## Data Structures

- Arrays or ArrayLists will be employed to store information about individual products.
- The inventory class will leverage these data structures to calculate the total quantity and value.
- Other object class will be created as well

## Project Details

The project aims to create a unified interface for employees to exhibit their behavior in the store. An abstract class will implement this interface, incorporating additional variables and methods to describe employee roles. Four or more distinct classes will be developed, each extending the abstract class:

1. Cashier
2. Seller
3. Driver
4. Admin

An additional item class will be created to elaborate on the items present in the store. A connection class will be created to facilitate data exchange in the production app. The Spring Boot framework will be utilized to create a production-level application.

## Project Milestones and Final Goal

The final objective of this project is to publish a comprehensive management system featuring a user-friendly GUI, a robust database for real data storage, and a mobile application interface. The development will progress through various milestones, including the creation of employee and item classes, integration of the Spring Boot framework, and eventual deployment of the system with the specified features.