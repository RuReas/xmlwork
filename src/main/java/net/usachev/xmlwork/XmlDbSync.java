package net.usachev.xmlwork;

import net.usachev.xmlwork.convert.Converter;
import net.usachev.xmlwork.convert.XmlConverterImpl;
import net.usachev.xmlwork.storage.Storage;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;

public class XmlDbSync {
    private static final Logger LOG = getLogger(XmlDbSync.class);

    private Storage storage;

    private Converter converter;

    public XmlDbSync() {
        storage = AppConfig.get().getStorage();
        LOG.info("Get instance of Storage");
        converter = new XmlConverterImpl();
    }

    public void createXml(String filePath) throws TransformerException, IOException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(converter.createXML(storage.getAll())),
                new StreamResult(new FileOutputStream(filePath)));
        LOG.info("Create XML <" + filePath + ">");
    }

    public void syncXml(String filePath) throws IOException, SAXException {
        final File xmlFile = new File(System.getProperty("user.dir") + File.separator + filePath);
        storage.syncDbXml(converter.syncXML(xmlFile));
/*
        if (storage.syncDbXml(converter.syncXML(xmlFile))) {
            LOG.info("Sync completed successful.");
        } else {
            LOG.info("Sync not completed, error in XML file!");
        }
*/
    }
}
