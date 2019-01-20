package weekopdrachten;

import java.util.Random;
import java.util.Scanner;

public class SpelGanzenbord {
	
	public static void main(String[] args) {	
		new SpelGanzenbord().run();
	}

	void run() {
	int spelen = 0;
		
		Bord bord = new Bord();
		bord.intro();
		
		do {
			bord.verplaatsSpeler();
			Scanner scSpelen = new Scanner(System.in);
			System.out.println("Wil je een nieuwe ronde spelen? Kies (1) voor ja en (0) voor nee: ");
			spelen = scSpelen.nextInt();
		} while (spelen == 1);
		System.out.println("Bedankt voor het spelen van dit spel en tot de volgende keer!");	
	}
}

class Bord {
	private int huidigePlaats= 0;
	Speler speler = new Speler(); // aantal spelers vragen en dan aantal speler creeeren?
	Dobbelsteen dobbelsteen = new Dobbelsteen();
	
	int getHuidigePlaats() {return huidigePlaats;}
	void setHuidigePlaats(int huidigePlaats) {this.huidigePlaats = huidigePlaats;}
	
	void intro() {
		speler.setSpelerNaam();
		System.out.println("Welkom " + speler.getSpelerNaam() + "! Je begint nu vanaf het startpunt op plaats 0. "
				+ "Het doel is om te eindigen op een plaats tussen 63-68. Pas op voor plaats 23, dan kom je namelijk in de gevangenis terecht en eindigt het spel."
				+ "Om te beginnen met stappen te zetten in het spel dien je de dobbelsteen te gooien. ");
	}
	
	void verplaatsSpeler(){
		int loop = 1; 
		
		do {	
		Scanner scGooiDobbelsteen = new Scanner(System.in);
		System.out.println("Gooien? Druk op (g): ");
		String gooiDobbelsteen = scGooiDobbelsteen.nextLine();
			
		if ( gooiDobbelsteen.equals("g") ) {	
			int gooi = dobbelsteen.randomGooien();	
			setHuidigePlaats(getHuidigePlaats() + gooi);
			System.out.println("Je hebt " + gooi + " gegooid, je staat op plaats " + getHuidigePlaats() + " .");
			
			//De regels van het spel Ganzenbord
			if ( (getHuidigePlaats() == 25) || (getHuidigePlaats() == 45) ) {
				setHuidigePlaats(0);
				System.out.println("Helaas je moet terug naar start.... je staat weer op plaats " + getHuidigePlaats() + ".");
				
			} else if ( (getHuidigePlaats() == 10) || ( getHuidigePlaats() == 20 ) || ( getHuidigePlaats() == 30 ) || ( getHuidigePlaats() == 40 ) || ( getHuidigePlaats() == 50 ) || ( getHuidigePlaats() == 60 ) ) { 
				System.out.println("Je mag je geworpen stappen nogmaals zetten!");
				setHuidigePlaats(getHuidigePlaats() + gooi);
				getHuidigePlaats();
				System.out.println("Dus je staat nu op plaats " + getHuidigePlaats() + ".");
				
	  		}
			else if ( (getHuidigePlaats() >= 63) && (getHuidigePlaats() <= 67) ) { 
				System.out.println("je bent beland op plaats " + getHuidigePlaats() +" je hebt gewonen!"); 
				loop = 0;
			}
			
			else if (getHuidigePlaats() == 23) {
				System.out.println("Ai, je bent op " + getHuidigePlaats() +" beland en zit in de gevangenis. Het spel is per direct over!");
				loop = 0;
				
			} else if (( getHuidigePlaats() > 0) && (getHuidigePlaats() < 63 ) ){
				System.out.println("Niks aan de hand. Gooi je dobbelsteen (g). ");
			} else if(getHuidigePlaats() >= 68 ) {
				System.out.println("Helaas het spel eindigt wegens een te hoog bereikte plaats.");
				loop = 0;
			}
			
			
		} else if ( gooiDobbelsteen.equals("s") ) {
			System.out.println("De opgegeven keuze was (s), daarom sluit het programma zich nu af.");
			loop = 0;
		}
		} while( loop == 1 );
	}
}
				
class Speler {
	private String spelerNaam;
	
	void setSpelerNaam(){
	Scanner scSpelerNaam = new Scanner(System.in);
	System.out.println("Welkom bij het bordspel Ganzenbord! Wat is je naam? ");
	this.spelerNaam = scSpelerNaam.nextLine(); 
	}
	
	String getSpelerNaam() {return spelerNaam;}
}
	
class Dobbelsteen {
	
	int randomGooien() {
		Random random = new Random();
		int randomGegooid = random.nextInt(6) +1;
		return randomGegooid;
	}
}	