package code2;


public class Test {

	public void test() {
		MyConnection c = new MyConnection("jdbc:mysql://localhost:3306/javaproject", "", "root");
		//System.out.println(c.pronadjiVrstu());
		Apartman a = new Apartman(2, 3, 4, "apartman", c.getConnection());
		//System.out.println(a.findVrstaID());
		//System.out.println(a.findMaxID());
		//a.insertApartman();
		//System.out.println(c.pronadjiStatus());
		Rezervacija r = new Rezervacija(7, 3, "2016-04-23", "2016-05-20", "Otkazana", c.getConnection());
		//System.out.println(r.findStatusID());
		//System.out.println(r.findGostID("5432167899999"));
		//System.out.println(r.checkApartmanID());
		//System.out.println(r.rezervisi());
		Gost g = new Gost("0000000000000", "Marko", "Markovic", "2000-01-01", "z", c.getConnection());
		//System.out.println(g.findMaxID());
		//System.out.println(g.insertGost());
		//System.out.println(c.pronadjiPodatke(1));
		//System.out.println(g.updateGost(5));
		Gost g2 = new Gost(5, c.getConnection());
		//System.out.println(g2.pronadjiPodatkeOGostu());
		//System.out.println(g2.izbrisiGosta());
	}
}
