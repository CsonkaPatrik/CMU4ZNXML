package domcmu4zn1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomModifyCMU4ZN {
    public static void main(String[] args) {
        try {
            File inputFile = new File("orarendCMU4ZN.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Eredeti dokumentum:");
            printDocument(doc);

            NodeList oraList = doc.getElementsByTagName("ora");

            for (int i = 0; i < oraList.getLength(); i++) {
                Node ora = oraList.item(i);
                if (ora.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElement = (Element) ora;

                    // Add "oraado" element if missing
                    if (oraElement.getElementsByTagName("oraado").getLength() == 0) {
                        Element oraado = doc.createElement("oraado");
                        oraado.setTextContent("Dr. Példa Elemér");
                        oraElement.appendChild(oraado);
                    }

                    // Change "tipus" attribute from "gyakorlat" to "elmelet"
                    if (oraElement.getAttribute("tipus").equals("gyakorlat")) {
                        oraElement.setAttribute("tipus", "elmelet");
                    }
                }
            }

            System.out.println("\nMódosított dokumentum:");
            printDocument(doc);

            // Write the modified document to a new file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("orarendModifyCMU4ZN.xml"));
            transformer.transform(source, result);

            System.out.println("\nA módosított dokumentum fájlba mentve: orarendModifyCMU4ZN.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printDocument(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
