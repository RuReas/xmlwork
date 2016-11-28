package net.usachev.xmlwork.convert;

import net.usachev.xmlwork.exception.AppException;
import net.usachev.xmlwork.model.KeyPosition;
import net.usachev.xmlwork.model.Position;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.log4j.Logger.getLogger;

public class XmlConverterImpl implements Converter {

    private static final Logger LOG = getLogger(XmlConverterImpl.class);

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder;

    public XmlConverterImpl() {
    }

    @Override
    public Document createXML(List<Position> positions) {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Document Builder error " + e.getMessage());
            return null;
        }

        factory.setNamespaceAware(true);
        Document document = builder.newDocument();
        Element rootElement = document.createElement("positions");

        for (Position item : positions) {
            Element entry = document.createElement("position");
            Element field01 = document.createElement("DepCode");
            field01.appendChild(document.createTextNode(item.getDepCode()));
            Element field02 = document.createElement("DepJob");
            field02.appendChild(document.createTextNode(item.getDepJob()));
            Element field03 = document.createElement("Description");
            field03.appendChild(document.createTextNode(item.getDescription()));
            entry.appendChild(field01);
            entry.appendChild(field02);
            entry.appendChild(field03);
            rootElement.appendChild(entry);
        }

        document.appendChild(rootElement);

        return document;
    }

    @Override
    public Map<KeyPosition, Position> syncXML(File xmlFile) throws IOException, SAXException {
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            if (!document.getDocumentElement().getNodeName().equals("positions")) {
                throw new AppException("XML root incorrect!");
            }

            NodeList nodeList = document.getElementsByTagName("position");

            Map<KeyPosition, Position> mapPositions = new HashMap<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String depCode = element.getElementsByTagName("DepCode").item(0).getTextContent();
                    String depJob = element.getElementsByTagName("DepJob").item(0).getTextContent();
                    String description = element.getElementsByTagName("Description").item(0).getTextContent();

                    if (mapPositions.put(new KeyPosition(depCode, depJob), new Position(depCode, depJob, description)) != null) {
                        throw new AppException("Error in sync file!");
                    }

                    LOG.info("Read elements from XML <" + depCode + "> <" + depJob + ">");
                }
            }
            return mapPositions;
        } catch (Exception e) {

        }
        return null;
    }
}
