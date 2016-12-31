package com.bitunified.ledconfig.configuration.csvimport;


import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.parts.PartList;
import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.dozer.CsvDozerBeanReader;
import org.supercsv.io.dozer.ICsvDozerBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class Importer {

    private static final CsvPreference PREFS =
            new CsvPreference.Builder(
                    CsvPreference.STANDARD_PREFERENCE)
                    .surroundingSpacesNeedQuotes(true).build();

    public void import2() throws IOException{
        ICsvBeanReader beanReader = null;
        try {
            Reader reader=fileReader();
            beanReader = new CsvBeanReader(reader, PREFS);

            final String[] header = beanReader.getHeader(true);

            // set up the field mapping and processors dynamically
            final String[] fieldMapping = new String[header.length];
            final PartProcessor[] processors =
                    new PartProcessor[header.length];
            for (int i = 0; i < header.length; i++) {


                    processors[i] =
                            new PartProcessor(header);

            }

            Part part;
            while ((part = beanReader.read(Part.class, fieldMapping,
                    processors)) != null) {
                System.out.println(String.format(
                        "lineNo=%s, rowNo=%s, part=%s",
                        beanReader.getLineNumber(), beanReader.getRowNumber(),
                        part));
            }

        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }

    }
    public Set<Part> importerDozer() throws IOException{
        // simple cell processor that creates an Answer with a value
        final CellProcessor parseAnswer = new CellProcessorAdaptor() {
            public Object execute(Object value, CsvContext context) {
                return value;
            }
        };
        final CellProcessor parseAnswer2 = new CellProcessorAdaptor() {
            public Object execute(Object value, CsvContext context) {
                return next.execute(Integer.parseInt((String)value),context);

            }
        };
        final CellProcessor[] processors = new CellProcessor[] {
                new Optional(),
                new Optional(parseAnswer),
                new Optional(parseAnswer),
                new Optional(parseAnswer),
                new Optional(parseAnswer),
                new ParseInt()
        };

        // no deep mapping (answers[0].answer) required as we're using a cell processor to create the bean
        final String[] fieldMapping = {"id", "description","priceStr","type","code"};

        // the indexed mappings need a hint for Dozer to work
        final Class<?>[] hintTypes = {Part.class,  Part.class, Part.class,Part.class,Part.class};

        ICsvDozerBeanReader beanReader = null;
        try {
            String[] mappingFiles=new String[]{"dozerBeanMapping.xml"};
            DozerBeanMapper dozerMapperBean=new DozerBeanMapper(Arrays.asList(mappingFiles));
            beanReader = new CsvDozerBeanReader(fileReader(), CsvPreference.STANDARD_PREFERENCE,dozerMapperBean);

            beanReader.getHeader(false); // ignore the header
            beanReader.configureBeanMapping(Part.class, fieldMapping);

            Set<Part> parts = new HashSet<Part>();
            Part part;
            while( (part = beanReader.read(Part.class, processors)) != null ) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, part=%s", beanReader.getLineNumber(),
                        beanReader.getRowNumber(), part));
                parts.add(part);
            }

            return parts;
        }
        finally {
            if( beanReader != null ) {
                beanReader.close();
            }
        }
    }

//    public void importFromCSVFile(){
//        Map<String, String> columnMapping = new HashMap<String, String>();
//        columnMapping.put("id", "id");
//        columnMapping.put("description", "description");
//        columnMapping.put("price", "price");
//        columnMapping.put("type", "type");
//        HeaderColumnNameTranslateMappingStrategy<Part> strategy =
//                new HeaderColumnNameTranslateMappingStrategy<Part>();
//        strategy.setType(Part.class);
//        strategy.setColumnMapping(columnMapping);
//
//
//        CsvToBean<Part> csv = new CsvToBean<Part>();
//        try {
//
//
//            List list = csv.parse(strategy, fileReader());
//            System.out.println(list);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    public Reader fileReader() throws IOException {
        File tempFile= File.createTempFile("tmp","ddr");
        URL url=new URL("http://localhost:8080/server/dataParts.csv");
        FileUtils.copyURLToFile(url, tempFile);
        return new FileReader(tempFile);
    }


    public Object readXml(String dataString) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PartList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //We had written this file in marshalling example
        Object data = jaxbUnmarshaller.unmarshal( new StringReader(dataString) );

        return  data;

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

    public <T> String jaxb(Collection<T> elements, Class<T> elementClass, String plural){
        try {
            T[] array = (T[]) Array.newInstance(elementClass, elements.size());
            elements.toArray(array);
            JAXBContext jc = JAXBContext.newInstance(array.getClass());
            JAXBElement<T[]> topElement = new JAXBElement(new QName(plural), array.getClass(), array);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(topElement, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Collection<T>  unjaxb(String input, Class<T> elementClass, String plural){
        try {
            //T[] array = (T[]) Array.newInstance(elementClass, elements.size());
            ///elements.toArray(array);
            JAXBContext jc = JAXBContext.newInstance(elementClass.getClass());
            //JAXBElement<T[]> topElement = new JAXBElement(new QName(plural), array.getClass(), array);


            Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();


            //jaxbUnmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            Collection<T> obj= (Collection<T>) jaxbUnmarshaller.unmarshal(new StringReader(input));
            return obj;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}