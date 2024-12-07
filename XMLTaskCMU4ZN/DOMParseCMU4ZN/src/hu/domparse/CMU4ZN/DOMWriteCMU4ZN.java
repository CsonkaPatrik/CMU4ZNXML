package hu.domparse.CMU4ZN;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMWriteCMU4ZN {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLCMU4ZN.xml");

            doc.getDocumentElement().normalize();

            // Gyökérelem kiírása
            System.out.println("XML tree structure:");
            printElement(doc.getDocumentElement(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurzív metódus az XML fa bejárására és kiírására
    private static void printElement(Element element, int indent) {
        // Behúzás az aktuális elem szintjének megfelelően
        for (int i = 0; i < indent; i++) System.out.print("  ");
        
        // Elem neve
        System.out.print("<" + element.getTagName());
        
        // Attribútumok kiírása
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
        }
        System.out.println(">");

        // Gyerek elemek feldolgozása
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                printElement((Element) node, indent + 1);
            } else if (node.getNodeType() == Node.TEXT_NODE) {
                // Szöveges tartalom kiírása (ha nem üres)
                String textContent = node.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    for (int j = 0; j <= indent; j++) System.out.print("  ");
                    System.out.println(textContent);
                }
            }
        }

        // Záró elem kiírása
        for (int i = 0; i < indent; i++) System.out.print("  ");
        System.out.println("</" + element.getTagName() + ">");
    }
}
