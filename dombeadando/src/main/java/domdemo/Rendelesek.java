package domdemo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Rendelesek {
	
	private String rid;
	private String vevoId;
	private String vegosszeg;
	private String datum;
	private String termek_nev;
    private Vevok vevok;
	
	public static Rendelesek create(Node node) {
        Rendelesek rendelesek = new Rendelesek();

        Element element = (Element) node;
        rendelesek.rid = element.getAttribute("rid");
        rendelesek.vevoId = element.getAttribute("vevo");
        rendelesek.vegosszeg = element.getAttribute("vegosszeg");
        rendelesek.datum = element.getAttribute("datum");
        rendelesek.termek_nev = element.getAttribute("termek_nev");

        return rendelesek;
    }

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getVevoId() {
		return vevoId;
	}

	public void setVevoId(String vevoId) {
		this.vevoId = vevoId;
	}

	public Vevok getVevok() {
		return vevok;
	}

	public void setVevok(Vevok vevok) {
		this.vevok = vevok;
	}

	public String getVegosszeg() {
		return vegosszeg;
	}

	public void setVegosszeg(String vegosszeg) {
		this.vegosszeg = vegosszeg;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getTermek_nev() {
		return termek_nev;
	}

	public void setTermek_nev(String termek_nev) {
		this.termek_nev = termek_nev;
	}

	@Override
	public String toString() {
		return "Rendelesek [rid=" + rid + ", vevoId=" + vevoId + ", vegosszeg=" + vegosszeg + ", datum=" + datum
				+ ", termek_nev=" + termek_nev +"]";
	}

}
