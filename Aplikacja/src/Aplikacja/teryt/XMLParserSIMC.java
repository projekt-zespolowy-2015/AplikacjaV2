package Aplikacja.teryt;
import java.util.ArrayList;

import java.util.List;

import javax.xml.parsers.SAXParser;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParserSIMC extends DefaultHandler {
    public static void main(String[] args) {
    	XMLParserSIMC parser = new XMLParserSIMC();
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
                Boolean rodzTag = false;
                Boolean nazwaTag = false;
                Boolean symbolTag = false;
                Boolean symbolPodsTag = false;
                
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
                            if (attributes.getValue(0).equalsIgnoreCase("RODZ"))
                                rodzTag = true;

                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0)
                                    .equalsIgnoreCase("NAZWA"))
                                nazwaTag = true;
                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0)
                                    .equalsIgnoreCase("SYM"))
                            	symbolTag = true;
                        if (attributes.getValue(0) != null)
                            if (attributes.getValue(0)
                                    .equalsIgnoreCase("SYMPOD"))
                            	symbolPodsTag = true;
                    }
                }

                public void characters(char ch[], int start, int length)
                        throws SAXException {

                    StringBuilder temp = new StringBuilder();
                                     
                    if (wojTag.equals(true)) {
                    	temp.append("\n Insert into miejscowosc (id_woj,id_pow,id_gmi,nazwa,symbol,symbol_pods) values (");
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
                    if (rodzTag.equals(true)) {

                        temp.append(new String(ch, start, length)+",");
                        temp.append("  ");
                    }
                   
                    if (nazwaTag.equals(true)) {

                        temp.append("\""+new String(ch, start, length)+
                        		"\",");
                        temp.append("  ");
                    }
                   if (symbolTag.equals(true)) {

                        temp.append(new String(ch, start, length)+",");
                        temp.append("  ");
                    }
                    if (symbolPodsTag.equals(true)) {

                        temp.append(new String(ch, start, length)+");");
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
                        rodzTag = false;
                    }
                    if (qName.equalsIgnoreCase("col")) {
                        nazwaTag = false;
                    }
                    if (qName.equalsIgnoreCase("col")) {
                    	symbolTag = false;
                    }
                    if (qName.equalsIgnoreCase("col")) {
                    	symbolPodsTag = false;
                    }
                }
            };

            saxParser.parse("C:\\Users\\martyna.krajnik\\Downloads\\TERC_01012015\\SIMC.xml",
                    defaultHandler);

            for (StringBuilder s : first) {
                System.out.print(s);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}