package DomQueryCMU4ZN;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DomQueryCMU4ZN {
    public static void main(String[] args) {
        try {
            // Query for hallgatoCMU4ZN.xml
            System.out.println("1. Lekérdezés: Hallgatók vezetéknevei");
            File hallgatoFile = new File("hallgatoCMU4ZN.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document hallgatoDoc = dBuilder.parse(hallgatoFile);
            hallgatoDoc.getDocumentElement().normalize();

            NodeList hallgatoList = hallgatoDoc.getElementsByTagName("hallgato");
            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Node node = hallgatoList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                }
            }

            // Query for orarendCMU4ZN.xml
            System.out.println("\n2. Lekérdezés: Kurzusok nevei");
            File orarendFile = new File("orarendCMU4ZN.xml");
            Document orarendDoc = dBuilder.parse(orarendFile);
            orarendDoc.getDocumentElement().normalize();

            NodeList oraList = orarendDoc.getElementsByTagName("ora");
            List<String> kurzusok = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Node node = oraList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    kurzusok.add(element.getElementsByTagName("targy").item(0).getTextContent());
                }
            }
            System.out.println("Kurzusok: " + kurzusok);

            System.out.println("\n3. Lekérdezés: Első óra strukturált formában");
            if (oraList.getLength() > 0) {
                Element firstOra = (Element) oraList.item(0);
                printElement(firstOra);
            }

            System.out.println("\n4. Lekérdezés: Oktatók nevei");
            List<String> oktatok = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Node node = oraList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    oktatok.add(element.getElementsByTagName("oktato").item(0).getTextContent());
                }
            }
            System.out.println("Oktatók: " + oktatok);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printElement(Element element) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(element);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}