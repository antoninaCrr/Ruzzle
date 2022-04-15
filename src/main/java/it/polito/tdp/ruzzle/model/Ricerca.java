package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	// qui scriverò i miei metodi ricorsivi
	public List<Pos> trovaParola(String parola, Board board){
		
		// la ricorsione parte solo se sulla board è presente la prima lettera
		for(Pos p: board.getPositions()) {
			// la lettera in pos è == alla prima lettera di parola
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				// per passare da una StringProperty ad una Stringa vera e propria devo fare .get()
		        // se la condizione è verificata posso far partire la ricorsione
				List<Pos> parziale = new ArrayList<Pos>();
			    parziale.add(p); // la prima lettera è stata trovata
			    
			    if(cerca(parola, parziale, 1, board))
			    	return parziale; // quando il cerca avrà finito mi restituirà una lista di posizioni
			}
		}
		return null;
	}
		
		public boolean cerca(String parola,List<Pos> percorso, int livello, Board board) {
			
			// caso terminale
			if(livello == parola.length()) {
				return true;
			}
			
			Pos ultima = percorso.get(percorso.size()-1); // tiriamo fuori l'ultima posizione inserita nel percorso
			List<Pos> adiacenti = board.getAdjacencies(ultima); // creiamo una lista di posizioni adiacenti all'ultima inserita
			for(Pos a: adiacenti) {
				if(!percorso.contains(a) && board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {
					// se le condizioni sono verificate posso continuare a costruire il mio percorso
					percorso.add(a);
					// uscita rapida dalla ricorsione
					if(cerca(parola, percorso, livello+1, board))
						return true;
					
					percorso.remove(percorso.size()-1); // ciò che ho appena inserito lo tolto per esplorare eventualmente altri percorsi
				}
			}
			return false;
		}
 }





