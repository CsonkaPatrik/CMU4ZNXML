package hu.domparse.CMU4ZN;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMQueryCMU4ZN {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLCMU4ZN.xml");

            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Lekérdezés 1: Keresd meg a customer ID "02"-höz tartozó ügyfél adatait
            System.out.println("\nQuery 1: Customer with ID '02'");
            NodeList customerList = doc.getElementsByTagName("customer");
            for (int i = 0; i < customerList.getLength(); i++) {
                Node node = customerList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String customerId = element.getAttribute("customer_id");
                    if (customerId.equals("02")) {
                        System.out.println("Customer ID: " + customerId);
                        System.out.println("Username: " + element.getElementsByTagName("username").item(0).getTextContent());
                        System.out.println("Nickname: " + element.getElementsByTagName("nickname").item(0).getTextContent());
                        System.out.println("City: " + element.getElementsByTagName("city").item(0).getTextContent());
                    }
                }
            }

            // Lekérdezés 2: Minden pizza neve és ára
            System.out.println("\nQuery 2: All pizzas and their prices");
            NodeList pizzaList = doc.getElementsByTagName("pizza");
            for (int i = 0; i < pizzaList.getLength(); i++) {
                Node node = pizzaList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String pizzaName = element.getElementsByTagName("name").item(0).getTextContent();
                    String pizzaPrice = element.getElementsByTagName("price").item(0).getTextContent();
                    System.out.println("Pizza: " + pizzaName + ", Price: " + pizzaPrice);
                }
            }

            // Lekérdezés 3: Az összes rendelés, ahol a megjegyzés tartalmazza a "csípős" szót
            System.out.println("\nQuery 3: Orders with a note containing 'csípős'");
            NodeList orderList = doc.getElementsByTagName("order");
            for (int i = 0; i < orderList.getLength(); i++) {
                Node node = orderList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String note = element.getElementsByTagName("note").item(0).getTextContent();
                    if (note.contains("csípős")) {
                        System.out.println("Order ID: " + element.getAttribute("order_id"));
                        System.out.println("Note: " + note);
                    }
                }
            }

            // Lekérdezés 4: Minden dolgozó neve és tapasztalata, ha az tapasztalat 5 év vagy több
            System.out.println("\nQuery 4: Employees with 5 or more years of experience");
            NodeList employeeList = doc.getElementsByTagName("employee");
            for (int i = 0; i < employeeList.getLength(); i++) {
                Node node = employeeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int experience = Integer.parseInt(element.getElementsByTagName("experience").item(0).getTextContent());
                    if (experience >= 5) {
                        System.out.println("Employee ID: " + element.getAttribute("employee_id"));
                        System.out.println("Username: " + element.getElementsByTagName("username").item(0).getTextContent());
                        System.out.println("Experience: " + experience + " years");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
