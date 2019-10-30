package com.Sberbank.task;

import com.Sberbank.task.entities.DocType;
import com.Sberbank.task.service.DocTypeServiceImpl;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Document;

public class main {
    private static Set<DocType> docTypeSet = new TreeSet<>(Comparator.comparing(DocType::getName));

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        File docFile = new File("sber.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(docFile);

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        createDocTableInDB(doc,xpath);

        printAttributes(doc,xpath);

    }

    private static void createDocTableInDB(Document doc, XPath xPath) throws XPathExpressionException {
        XPathExpression expr = xPath.compile("/order/services/serv/pars/par[@name='ВИД_ДОК']");

        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        NodeList docTypeNodeList = nl.item(0).getChildNodes();

        for (int x = 0; x < docTypeNodeList.getLength(); x++) {
            NamedNodeMap attributes = docTypeNodeList.item(x).getAttributes();
            if (attributes != null) {
                String value = docTypeNodeList.item(x).getAttributes().item(0).getNodeValue();
                DocType docType = new DocType(value);
                new DocTypeServiceImpl().createDocType(docType);
                docTypeSet.add(docType);
            }
        }
    }

    private static void printAttributes(Document doc, XPath xPath) throws XPathExpressionException {
        XPathExpression expr2 = xPath.compile("/order/services/serv/pars/par[@name='ГРАЖДАНСТВО']");

        NodeList n2 = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
        NamedNodeMap attributesList = n2.item(0).getAttributes();

        for (int x = 0; x < attributesList.getLength(); x++) {
            System.out.println("Attribute: " + attributesList.item(x).getNodeName()
                    + "=" + attributesList.item(x).getNodeValue());
        }
    }
}


