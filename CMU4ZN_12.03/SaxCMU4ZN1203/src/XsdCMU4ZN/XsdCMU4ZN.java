package XsdCMU4ZN;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XsdCMU4ZN {
    public static void main(String[] args) {
        // Az XML és XSD fájlok elérési útjai
        String xmlFile = "CMU4ZN_kurzusfelvetel.xml";
        String xsdFile = "CMU4ZN_kurzusfelvetel.xsd";

        // Validáció
        boolean isValid = validateXMLSchema(xsdFile, xmlFile);

        if (isValid) {
            System.out.println("A dokumentum érvényes az XSD séma szerint!");
        } else {
            System.out.println("A dokumentum érvénytelen az XSD séma szerint!");
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            // XSD séma betöltése
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));

            // Validator létrehozása
            Validator validator = schema.newValidator();

            // XML validálása
            validator.validate(new javax.xml.transform.stream.StreamSource(new File(xmlPath)));
            return true;

        } catch (SAXException e) {
            System.out.println("SAXException: " + e.getMessage());
            return false;

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}