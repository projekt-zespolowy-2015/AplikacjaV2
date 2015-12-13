package Aplikacja.teryt;
import java.util.ArrayList;

import java.util.List;

import javax.xml.parsers.SAXParser;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParserULIC extends DefaultHandler {
    public static void main(String[] args) {
    	XMLParserULIC parser = new XMLParserULIC();
        parser.getAreaXml();
    }

    public void getAreaXml() {
        final List<StringBuilder> first = new ArrayList<StringBuilder>();
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler defaultHandler = new DefaultHandler() {
                Boolean wojTag = false;
                Boolean powTag = false;
                Boolean gmiTag = false;
                Boolean nazwaTag = false;
                Boolean sym_ul = false;
                Boolean cecha = false;
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {

                    if (qName.equalsIgnoreCase("col")) {
                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0).equalsIgnoreCase("WOJ"))
                                wojTag = true;

                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0).equalsIgnoreCase("POW"))
                                powTag = true;

                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0).equalsIgnoreCase("GMI"))
                                gmiTag = true;

                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0)
                                    .equalsIgnoreCase("CECHA"))
                                cecha = true;

                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0)
                                    .equalsIgnoreCase("NAZWA_1"))
                                nazwaTag = true;

                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0).equalsIgnoreCase(
                                    "SYM_UL"))
                                sym_ul = true;
                    }
                }

                public void characters(char ch[], int start, int length)
                        throws SAXException {

                    StringBuilder temp = new StringBuilder();
                    if (wojTag.equals(true)) {
                    	temp.append("\n Insert into ulica values (");
                        temp.append(new String(ch, start, length)+",");
                        temp.append("  ");
                    }
                    if (powTag.equals(true)) {
                    	
                        temp.append(new String(ch, start, length)+",");
                        temp.append("  ");
                    }
                    if (gmiTag.equals(true)) {
                    	
                        temp.append(new String(ch, start, length)+",");
                        temp.append("  ");
                    }
                                    
                    if (sym_ul.equals(true)) {

                        temp.append(new String(ch, start, length)+",");
                        temp.append("  ");
                    }
                    if (cecha.equals(true)) {

                        temp.append("\""+new String(ch, start, length)+
                        		"\""+",");
                        temp.append("  ");
                    }
                    
                    if (nazwaTag.equals(true)) {

                        temp.append("\""+new String(ch, start, length)+
                        		"\""+");");
                        temp.append("  ");
                  }
                     
                  first.add(temp);
                
      
        }

                public void endElement(String uri, String localName,
                        String qName) throws SAXException {

                    if (qName.equalsIgnoreCase("col")) {
                        wojTag = false;
                    }
                    if (qName.equalsIgnoreCase("col")) {
                        powTag = false;
                    }
                    if (qName.equalsIgnoreCase("col")) {
                        gmiTag = false;
                    }
                   
                    if (qName.equalsIgnoreCase("col")) {
                        cecha = false;
                    }
                    if (qName.equalsIgnoreCase("col")) {
                        nazwaTag = false;
                        
                    }
                   if (qName.equalsIgnoreCase("col")) {
                        sym_ul = false;
                    }
                }
            };

            saxParser.parse("C:\\Users\\martyna.krajnik\\Downloads\\ULIC.xml",
                    defaultHandler);

            for (StringBuilder s : first) {
            	
                System.out.print(s);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}