package xpathcmu4zn1119;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;

public class xPathCMU4ZN {
    public static void main(String[] args) {
        try {
            File inputFile = new File("studentCMU4ZN.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            // Query 1: List all student names
            System.out.println("Minden hallgató neve:");
            NodeList nodeList = (NodeList) xPath.compile("/class/student/keresztnev").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getTextContent());
            }

            // Query 2: Get student with id=2
            System.out.println("\nHallgató id=2:");
            Node node = (Node) xPath.compile("/class/student[@id='2']").evaluate(doc, XPathConstants.NODE);
            if (node != null) {
                System.out.println("Vezetéknév: " + xPath.compile("vezeteknev").evaluate(node));
                System.out.println("Keresztnév: " + xPath.compile("keresztnev").evaluate(node));
                System.out.println("Becenév: " + xPath.compile("becenev").evaluate(node));
                System.out.println("Kor: " + xPath.compile("kor").evaluate(node));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
