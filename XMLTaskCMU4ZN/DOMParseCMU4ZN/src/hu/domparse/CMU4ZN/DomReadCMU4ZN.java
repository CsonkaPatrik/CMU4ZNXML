package hu.domparse.CMU4ZN;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomReadCMU4ZN {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLCMU4ZN.xml");

            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Customer elemek
            NodeList customerList = doc.getElementsByTagName("customer");
            System.out.println("\n--- Customers ---");
            for (int i = 0; i < customerList.getLength(); i++) {
                Node node = customerList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Customer ID: " + element.getAttribute("customer_id"));
                    System.out.println("Username: " + element.getElementsByTagName("username").item(0).getTextContent());
                    System.out.println("Password: " + element.getElementsByTagName("password").item(0).getTextContent());
                    System.out.println("Nickname: " + element.getElementsByTagName("nickname").item(0).getTextContent());
                    Element address = (Element) element.getElementsByTagName("adress").item(0);
                    System.out.println("Address: " + address.getElementsByTagName("city").item(0).getTextContent() + ", "
                            + address.getElementsByTagName("street").item(0).getTextContent() + ", "
                            + address.getElementsByTagName("zip_code").item(0).getTextContent());
                }
            }

            // Order elemek
            NodeList orderList = doc.getElementsByTagName("order");
            System.out.println("\n--- Orders ---");
            for (int i = 0; i < orderList.getLength(); i++) {
                Node node = orderList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Order ID: " + element.getAttribute("order_id"));
                    System.out.println("Order Number: " + element.getElementsByTagName("order_number").item(0).getTextContent());
                    System.out.println("Price: " + element.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Quantity: " + element.getElementsByTagName("quantity").item(0).getTextContent());
                    System.out.println("Note: " + element.getElementsByTagName("note").item(0).getTextContent());
                }
            }

            // Employee elemek
            NodeList employeeList = doc.getElementsByTagName("employee");
            System.out.println("\n--- Employees ---");
            for (int i = 0; i < employeeList.getLength(); i++) {
                Node node = employeeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Employee ID: " + element.getAttribute("employee_id"));
                    System.out.println("Username: " + element.getElementsByTagName("username").item(0).getTextContent());
                    System.out.println("Password: " + element.getElementsByTagName("password").item(0).getTextContent());
                    System.out.println("Experience: " + element.getElementsByTagName("experience").item(0).getTextContent());
                    System.out.println("Nickname: " + element.getElementsByTagName("nickname").item(0).getTextContent());
                }
            }

            // Order Handling elemek
            NodeList handlingList = doc.getElementsByTagName("order_handling");
            System.out.println("\n--- Order Handling ---");
            for (int i = 0; i < handlingList.getLength(); i++) {
                Node node = handlingList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Order ID: " + element.getAttribute("order_id"));
                    System.out.println("Employee ID: " + element.getAttribute("employee_id"));
                    System.out.println("Time: " + element.getElementsByTagName("time").item(0).getTextContent());
                }
            }

            // Pizza elemek
            NodeList pizzaList = doc.getElementsByTagName("pizza");
            System.out.println("\n--- Pizzas ---");
            for (int i = 0; i < pizzaList.getLength(); i++) {
                Node node = pizzaList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Pizza ID: " + element.getAttribute("pizza_id"));
                    System.out.println("Name: " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Price: " + element.getElementsByTagName("price").item(0).getTextContent());
                    NodeList toppings = element.getElementsByTagName("topping");
                    System.out.print("Toppings: ");
                    for (int j = 0; j < toppings.getLength(); j++) {
                        System.out.print(toppings.item(j).getTextContent());
                        if (j < toppings.getLength() - 1) System.out.print(", ");
                    }
                    System.out.println();
                }
            }

            // Ingredients elemek
            NodeList ingredientsList = doc.getElementsByTagName("ingredients");
            System.out.println("\n--- Ingredients ---");
            for (int i = 0; i < ingredientsList.getLength(); i++) {
                Node node = ingredientsList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Ingredient ID: " + element.getAttribute("ingredients_id"));
                    System.out.println("Name: " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Price: " + element.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Origin: " + element.getElementsByTagName("origin").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
