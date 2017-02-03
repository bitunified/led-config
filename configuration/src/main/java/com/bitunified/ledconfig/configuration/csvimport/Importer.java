package com.bitunified.ledconfig.configuration.csvimport;


import com.bitunified.ledconfig.parts.PartList;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Collection;

public class Importer {


    public Reader fileReader() throws IOException {
        File tempFile = File.createTempFile("tmp", "ddr");
        URL url = new URL("http://localhost:8080/server/dataPartsImport.xml");
        FileUtils.copyURLToFile(url, tempFile);
        return new FileReader(tempFile);
    }

    public Object readXml(Reader dataStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PartList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //We had written this file in marshalling example
        Object data = jaxbUnmarshaller.unmarshal(dataStream);
        return data;
    }

    public Object readXml(FileReader dataString) throws JAXBException {
        return readXml((dataString));

    }

    public Object readXml(String dataString) throws JAXBException {
        return readXml(new StringReader(dataString));

    }

    public String writeXml(Object data) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(PartList.class);
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        StringWriter writer = new StringWriter();
        m.marshal(data, writer);

        // Write to File
        //m.marshal(bookstore, new File(BOOKSTORE_XML));

        return writer.toString();
    }

    public <T> String jaxb(Collection<T> elements, Class<T> elementClass, String plural) {
        try {
            T[] array = (T[]) Array.newInstance(elementClass, elements.size());
            elements.toArray(array);
            JAXBContext jc = JAXBContext.newInstance(array.getClass());
            JAXBElement<T[]> topElement = new JAXBElement(new QName(plural), array.getClass(), array);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(topElement, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Collection<T> unjaxb(String input, Class<T> elementClass, String plural) {
        try {
            //T[] array = (T[]) Array.newInstance(elementClass, elements.size());
            ///elements.toArray(array);
            JAXBContext jc = JAXBContext.newInstance(elementClass.getClass());
            //JAXBElement<T[]> topElement = new JAXBElement(new QName(plural), array.getClass(), array);


            Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();


            //jaxbUnmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            Collection<T> obj = (Collection<T>) jaxbUnmarshaller.unmarshal(new StringReader(input));
            return obj;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
