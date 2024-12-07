package DomReadCMU4ZN;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DomReadCMU4ZN {
    public static void main(String[] args) {
        try {
            File inputFile = new File("CMU4ZN_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("ora");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println("\nCurrent Element: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("ID: " + element.getAttribute("id"));
                    System.out.println("Típus: " + element.getAttribute("tipus"));
                    System.out.println("Tantárgy: " + element.getElementsByTagName("targy").item(0).getTextContent());
                    System.out.println("Nap: " + element.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("Kezdés: " + element.getElementsByTagName("tol").item(0).getTextContent());
                    System.out.println("Vége: " + element.getElementsByTagName("ig").item(0).getTextContent());
                    System.out.println("Helyszín: " + element.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktató: " + element.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak: " + element.getElementsByTagName("szak").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}