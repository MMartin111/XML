package domdemo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Vevok {
	
	private String vid;
	private String nev;
	private String varos;
	private String utca;
	private String hazszam;
	
	 public static Vevok create(Node node) {
	        Vevok vevok = new Vevok();

	        Element element = (Element) node;
	        vevok.vid = element.getAttribute("vid");
	        vevok.nev = element.getAttribute("nev");
	        vevok.varos = element.getAttribute("varos");
	        vevok.utca = element.getAttribute("utca");
	        vevok.hazszam = element.getAttribute("hazszam");
	        return vevok;
	    }

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getVaros() {
		return varos;
	}

	public void setVaros(String varos) {
		this.varos = varos;
	}

	public String getUtca() {
		return utca;
	}

	public void setUtca(String utca) {
		this.utca = utca;
	}

	public String getHazszam() {
		return hazszam;
	}

	public void setHazszam(String hazszam) {
		this.hazszam = hazszam;
	}

	@Override
	public String toString() {
		return "Vevok [vid=" + vid + ", nev=" + nev + ", varos=" + varos + ", utca=" + utca + ", hazszam=" + hazszam
				+ "]";
	}

}
