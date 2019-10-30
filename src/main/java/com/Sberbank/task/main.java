package com.Sberbank.task;

import com.Sberbank.task.entities.DocType;
import com.Sberbank.task.service.DocTypeServiceImpl;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class main {
    private static Set<DocType> docTypeSet = new TreeSet<>((DocType o1, DocType o2) -> o1.getName().compareTo(o2.getName()));

    public static void main(String[] args) throws IOException, XMLStreamException {

        String fileName = "sber.xml";
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader r = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));

        try {
            int event = r.getEventType();
            while (true) {
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (r.getLocalName().equals("par") && r.getAttributeCount() > 1) {
                            if (r.getAttributeValue(1).equals("ГРАЖДАНСТВО")) {
                                for (int i = 0, n = r.getAttributeCount(); i < n; ++i)
                                    System.out.println("Attribute: " + r.getAttributeName(i)
                                            + "=" + r.getAttributeValue(i));
                            }
                        } else if (r.getLocalName().equals("par_list")) {
                                DocType docType = new DocType(r.getAttributeValue(0));
                                new DocTypeServiceImpl().createDocType(docType);
                                docTypeSet.add(docType);
                        }
                        break;
                }

                if (!r.hasNext())
                    break;

                event = r.next();
            }
        } finally {
            r.close();
        }
    }
}


