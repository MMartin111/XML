package domdemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Aruhaz {

    private static final String ARUHAZ_TAG = "aruhaz";
    private static final String RENDELESEK_TAG = "rendelesek";
    private static final String VEVOK_TAG = "vevok";

    private Document root;
    private List<Rendelesek> rendelesekList;
    private List<Vevok> vevokList;

    private Aruhaz(Document root,
    		List<Rendelesek> rendelesek,
    		List<Vevok> vevok) {
        this.root = root;
        this.rendelesekList = rendelesek;
        this.vevokList = vevok;
    }

    public static Aruhaz create(Document document) {
        Element root = document.getDocumentElement();
        if (!root.getNodeName().equals(ARUHAZ_TAG)) {
            throw new IllegalArgumentException("nem áruház a gyökérelem");
        }

        NodeList nodeList = root.getElementsByTagName(RENDELESEK_TAG);
        List<Rendelesek> rendelesek = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            rendelesek.add(Rendelesek.create(node));
        }

        nodeList = root.getElementsByTagName(VEVOK_TAG);
        List<Vevok> vevok = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            vevok.add(Vevok.create(node));
        }

        Aruhaz aruhaz = new Aruhaz(document, rendelesek, vevok);

        System.out.println("Vevõk listája: ");
        for (int i = 0; i < vevok.size(); i++) {
            System.out.println(vevok.get(i).getVid());
        }

        System.out.println("Rendelések listája: ");
        for (int i = 0; i < rendelesek.size(); i++) {
            System.out.println(rendelesek.get(i).getRid());
        }
        
        return aruhaz;
    }
    

    public Vevok getVevokById(String id) {
        return vevokList.stream().filter(vevok -> vevok.getVid().equals(id)).findFirst().orElseGet(Vevok::new);
    }
    
    public Rendelesek getRendelesekkById(String id) {
        return rendelesekList.stream().filter(rendelesek -> rendelesek.getRid().equals(id)).findFirst().orElseGet(Rendelesek::new);
    }
 

    public void addRendelesek(Rendelesek rendelesek) {
        Element element = root.createElement(RENDELESEK_TAG);
        element.setAttribute("rid", rendelesek.getRid());
        element.setAttribute("vevo", rendelesek.getVevok().getVid());
        element.setAttribute("vegosszeg", rendelesek.getVegosszeg());
        element.setAttribute("datum", rendelesek.getDatum());
        element.setAttribute("termek_nev", rendelesek.getTermek_nev());

        root.getDocumentElement().appendChild(element);
        rendelesekList.add(rendelesek);
    }
    
    public void addVevok(Vevok vevok) {
        Element element = root.createElement(VEVOK_TAG);
        element.setAttribute("vid", vevok.getVid());
        element.setAttribute("nev", vevok.getNev());
        element.setAttribute("varos", vevok.getVaros());
        element.setAttribute("utca", vevok.getUtca());
        element.setAttribute("hazszam", vevok.getHazszam());
        

        root.getDocumentElement().appendChild(element);
        vevokList.add(vevok);
    }

    public void persist(String pathname) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource source = new DOMSource(root);
        StreamResult result = new StreamResult(new File(pathname));
        transformer.transform(source, result);
    }
    
    

}
