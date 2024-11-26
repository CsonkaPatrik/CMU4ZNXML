package domcmu4zn1015;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomWriteCMU4ZN {
    public static void main(String[] args) {
        try {
            File inputFile = new File("orarendCMU4ZN.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Create a new document for output
            Document newDoc = dBuilder.newDocument();
            Element rootElement = newDoc.createElement("orarend");
            newDoc.appendChild(rootElement);

            NodeList nList = doc.getElementsByTagName("ora");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element oldElement = (Element) node;

                    Element newElement = newDoc.createElement("ora");
                    newElement.setAttribute("id", oldElement.getAttribute("id"));
                    newElement.setAttribute("tipus", oldElement.getAttribute("tipus"));

                    // Copy child elements
                    for (int j = 0; j < oldElement.getChildNodes().getLength(); j++) {
                        Node child = oldElement.getChildNodes().item(j);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) child;
                            Element newChild = newDoc.createElement(childElement.getTagName());
                            newChild.setTextContent(childElement.getTextContent().trim());
                            newElement.appendChild(newChild);
                        }
                    }

                    rootElement.appendChild(newElement);
                }
            }

            // Write the new document to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("orarend1CMU4ZN.xml"));
            transformer.transform(source, result);

            System.out.println("Új fájl létrehozva: orarend1CMU4ZN.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
