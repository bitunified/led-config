package com.bitunified.ledconfig.configuration.csvimport;


import com.bitunified.ledconfig.parts.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.util.CsvContext;

import java.lang.reflect.InvocationTargetException;

public class PartProcessor extends CellProcessorAdaptor {

    private final String[] header;

    public PartProcessor(final String[] header) {
        this.header = header;
    }

    public Object execute(Object value, CsvContext context) {
        Part part=null;
        if(!(value instanceof Part) ) {
            part = new Part();
        }


        String headerValue=header[context.getColumnNumber() - 1];

        if (headerValue.startsWith("Part.")){

            try {
                BeanUtils.setProperty(part,headerValue.substring("Part.".length()),value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return part;

        // columns start at 1
        //attribute.setKey(header[context.getColumnNumber() - 1]);
        //attribute.setValue((String) value);


    }

}