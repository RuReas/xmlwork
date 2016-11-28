package net.usachev.xmlwork;

import net.usachev.xmlwork.exception.AppException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XmlWork {
    public static void main(String[] args) throws TransformerException, IOException {
        if (args.length != 2) {
            System.out.println("Use two parameters in command line!");
            System.out.println("first :");
            System.out.println(" save - save database in XML");
            System.out.println(" sync - load from XML to database");
            System.out.println("second :");
            System.out.println(" 1.xml - name of XML file (may be with path to file)");
        } else {
            try {
                XmlDbSync xmlDbSync = new XmlDbSync();
                switch (args[0]) {
                    case "save":
                        xmlDbSync.createXml(args[1]);
                        break;
                    case "sync":
                        xmlDbSync.syncXml(args[1]);
                        break;
                    default:
                        throw new AppException("First parameter incorrect!");
                }
            } catch (Exception e) {
                System.out.printf("Parameters incorrect! " + e);
            }
        }
    }
}
