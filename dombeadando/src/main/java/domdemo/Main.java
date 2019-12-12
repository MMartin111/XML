package domdemo;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse("src/main/resources/xml_beadando.xml");

        Aruhaz aruhaz = Aruhaz.create(document);

        
        // �j megrendel�s hozz�ad�sa az XML dokumentumhoz
        Rendelesek rendelesek = new Rendelesek();
        rendelesek.setRid("R22");
        rendelesek.setVegosszeg("123");
        rendelesek.setDatum("2018-01-01");
        rendelesek.setTermek_nev("termek");
        rendelesek.setVevok(new Vevok());
        aruhaz.addRendelesek(rendelesek);
        
        // �j vev� hozz�ad�sa az XML dokumentumhoz
        Vevok vevok = new Vevok();
        vevok.setVid("id");
        vevok.setNev("nev");
        vevok.setVaros("varos");
        vevok.setUtca("utca");
        vevok.setHazszam("hazszam");


        // Tag keres�se id alapj�n
        System.out.print("Melyik megrendel�sr�l szeretne t�bb inform�ci�t megtudni? ");
        Scanner scanner = new Scanner(System.in);
        String rid = scanner.nextLine();
        System.out.println(aruhaz.getRendelesekkById(rid));

        System.out.print("Melyik vev�r�l szeretne t�bb inform�ci�t megtudni? ");
        String id = scanner.nextLine();
        System.out.println(aruhaz.getVevokById(id));
        scanner.close();

        aruhaz.persist("src/main/resources/kimenet.xml");
    }

}
