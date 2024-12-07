package hu.domparse.CMU4ZN;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMModifyCMU4ZN {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLCMU4ZN.xml");

            doc.getDocumentElement().normalize();
            System.out.println("Modifying XML data...");

            // Módosítás 1: Egy adott ügyfél felhasználónevének megváltoztatása
            NodeList customerList = doc.getElementsByTagName("customer");
            for (int i = 0; i < customerList.getLength(); i++) {
                Node node = customerList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("customer_id").equals("02")) {
                        element.getElementsByTagName("username").item(0).setTextContent("ujfelhasznalonev");
                        System.out.println("Customer 02's username changed to 'ujfelhasznalonev'.");
                    }
                }
            }

            // Módosítás 2: Egy pizza árának frissítése
            NodeList pizzaList = doc.getElementsByTagName("pizza");
            for (int i = 0; i < pizzaList.getLength(); i++) {
                Node node = pizzaList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("pizza_id").equals("01")) {
                        element.getElementsByTagName("price").item(0).setTextContent("2500");
                        System.out.println("Pizza 01's price updated to 2500.");
                    }
                }
            }

            // Módosítás 3: Egy rendelés megjegyzésének hozzáadása
            NodeList orderList = doc.getElementsByTagName("order");
            for (int i = 0; i < orderList.getLength(); i++) {
                Node node = orderList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("order_id").equals("02")) {
                        element.getElementsByTagName("note").item(0).setTextContent("Frissítve: mégse csípősen kérem!");
                        System.out.println("Order 02's note updated to 'Frissítve: mégse csípősen kérem!'.");
                    }
                }
            }

            // Módosítás 4: Új feltét hozzáadása egy pizzához
            for (int i = 0; i < pizzaList.getLength(); i++) {
                Node node = pizzaList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("pizza_id").equals("02")) {
                        Element newTopping = doc.createElement("topping");
                        newTopping.setTextContent("Extra sajt");
                        element.appendChild(newTopping);
                        System.out.println("Added new topping 'Extra sajt' to Pizza 02.");
                    }
                }
            }

            // Az új XML kiírása fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLCMU4ZN_modified.xml"));
            transformer.transform(source, result);

            System.out.println("\nAll modifications saved to 'XMLCMU4ZN_modified.xml'.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
