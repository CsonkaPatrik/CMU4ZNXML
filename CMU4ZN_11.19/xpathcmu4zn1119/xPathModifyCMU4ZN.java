package xpathcmu4zn1119;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;

public class xPathModifyCMU4ZN {
    public static void main(String[] args) {
        try {
            File inputFile = new File("orarendCMU4ZN.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            // Modify 1: Change "szak" name
            Node szakNode = (Node) xPath.compile("/orarend/ora[1]/szak").evaluate(doc, XPathConstants.NODE);
            if (szakNode != null) {
                szakNode.setTextContent("Mesterséges Intelligencia és Programozás");
            }

            // Modify 2: Append monogram to "targy"
            NodeList targyNodes = (NodeList) xPath.compile("/orarend/ora/targy").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < targyNodes.getLength(); i++) {
                Node targyNode = targyNodes.item(i);
                targyNode.setTextContent(targyNode.getTextContent() + " (BL)");
            }

            // Modify 3: Change "helyszin" for id=3
            Node helyszinNode = (Node) xPath.compile("/orarend/ora[@id='3']/helyszin").evaluate(doc, XPathConstants.NODE);
            if (helyszinNode != null) {
                helyszinNode.setTextContent("XXXVII");
            }

            // Save modified XML to a new file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("orarendCMU4ZN1.xml"));
            transformer.transform(source, result);

            System.out.println("Módosított XML fájl mentve: orarendCMU4ZN1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
