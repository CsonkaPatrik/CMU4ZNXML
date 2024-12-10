package hu.domparse.CMU4ZN;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteCMU4ZN {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLCMU4ZN.xml");

            // Dokumentum normalizálása
            doc.getDocumentElement().normalize();

            // Gyökérelem és XML fa kiírása a konzolra
            System.out.println("XML tree structure:");
            printElement(doc.getDocumentElement(), 0);

            // XML dokumentum mentése új fájlba
            writeDocumentToFile(doc, "XMLCMU4ZN_Output.xml");

            System.out.println("The XML content has been successfully written to XMLCMU4ZN_Output.xml.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurzív metódus az XML fa bejárására és kiírására
    private static void printElement(Element element, int indent) {
        // Behúzás az aktuális elem szintjének megfelelően
        for (int i = 0; i < indent; i++) System.out.print("  ");

        // Elem neve és attribútumai
        System.out.print("<" + element.getTagName());
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
                String textContent = node.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    for (int j = 0; j <= indent; j++) System.out.print("  ");
                    System.out.println(textContent);
                }
            }
        }

        // Záró elem
        for (int i = 0; i < indent; i++) System.out.print("  ");
        System.out.println("</" + element.getTagName() + ">");
    }

    // Metódus az XML dokumentum fájlba írására
    private static void writeDocumentToFile(Document doc, String filename) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
