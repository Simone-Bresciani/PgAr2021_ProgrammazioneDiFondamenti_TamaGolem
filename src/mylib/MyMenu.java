package mylib;
/*
Questa classe rappresenta un menu testuale generico a piu' voci
Si suppone che la voce per uscire sia sempre associata alla scelta 0 
e sia presentata in fondo al menu

*/
public class MyMenu
{
  final private static String CORNICE_SUP = "┌----------------┐";
  final private static String CORNICE_INF = "└----------------┘";
  final private static String VOCE_USCITA = "0\tEsci";
  final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

  private String titolo;
  private String [] voci;

	
  public MyMenu (String titolo, String [] voci)
  {
	this.titolo = titolo;
	this.voci = voci;
  }

  public int scegliConUscita ()
  {
	stampaMenuConUscita();
	return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);	 
  }

    public int scegliSenzaUscita ()
    {
        stampaMenuSenzaUscita();
        return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
    }
		
  public void stampaMenuConUscita ()
  {
	System.out.println(CORNICE_SUP);
	System.out.println(titolo);
	System.out.println(CORNICE_INF);
    for (int i=0; i<voci.length; i++)
	 {
	  System.out.println( (i+1) +"⟼\t"+ voci[i]);
	 }
    System.out.println();
	System.out.println(VOCE_USCITA);
    System.out.println();
  }

    public void stampaMenuSenzaUscita ()
    {
        System.out.println(CORNICE_SUP);
        System.out.println("|"+"\t"+titolo+"\t"+" |");
        System.out.println(CORNICE_INF);
        for (int i=0; i<voci.length; i++)
        {
            System.out.println( (i+1) +"⟼\t" + voci[i]);
        }
        System.out.println();
    }
		
}

