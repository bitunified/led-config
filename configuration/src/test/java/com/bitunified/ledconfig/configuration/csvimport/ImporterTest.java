package com.bitunified.ledconfig.configuration.csvimport;

import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.parts.PartList;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.*;


public class ImporterTest {


    @Test
    public void test3() throws IOException {
        Importer importer=new Importer();
        importer.importerDozer();
    }


    @XmlRootElement
    class ff {
        private Set<Part> parts=new HashSet<Part>();
        ff(){

        }

        public Set<Part> getParts() {
            return parts;
        }

        public void setParts(Set<Part> parts) {
            this.parts = parts;
        }
    }
    @Test
    public void writeXml() throws JAXBException {
        Importer importer = new Importer();
        Parser parser=new Parser();
        parser.init();

        //ff f = new ff();
        //f.parts=parser.getParts();

        String result=importer.writeXml(new PartList(parser.getParts()));

          Object parts=  importer.readXml(result);

        System.out.println(result);
        System.out.println(parts);
    }



}