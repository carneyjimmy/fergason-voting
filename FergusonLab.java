package ferguson;

import java.util.ArrayList;
import java.util.Hashtable;

public class FergusonLab {
	static int totWhite = 0;
	static int totBlack = 0;
	
	static int numCandidates = 14; //	modifiable
	static int numSeats = 14;		//modifiable
	static int numLimVotes = 2; //number of votes for limited voting, modifiable




	public static void main(String[] args) {


		FergReader fr = new FergReader("ferguson.csv", 40, 36);
		for (int r=0; r<40; ++r) {
			for (int c=0; c<36; ++c) {
				totBlack += fr.getBlack(r, c);
				totWhite += fr.getWhite(r, c);
			}
		}
		System.out.println(totBlack);
		System.out.println(totWhite);
		allTypes(numCandidates);
	}
	public static  void  allTypes (int numCandidates){
		for (int i = 0; i <= numCandidates; ++i){
			System.out.println();
			System.out.println("num of black candidates: " + i);
			System.out.println("num of white candidates: " + (numCandidates-i));
			System.out.println();
			System.out.println("for Bloc Voting: ");
			bloc(i, numCandidates - i);
			System.out.println();
			System.out.println("for limited voting: ");
			limited(i, numCandidates -i);
			System.out.println();
			System.out.println("for cumultative voting: ");
			cumulative(i, numCandidates - i);
		}
	}

	public static void bloc(int blackCandidates, int whiteCandidates){
		ArrayList<Integer> votes = new ArrayList<Integer>();
		for (int i = 0; i < numCandidates; ++i){
			votes.add(0);
		}
		int blackSeats = 0;
		int whiteSeats = 0;
		for (int j = 0; j < totBlack; ++j){
			ArrayList <Integer> votedFor = new ArrayList<Integer>();
			for (int i = 0; i < numCandidates; i++){//populate arraylist, so that the objects are equal to the indicies of the votes list
				votedFor.add(i);
			}
			int blackC = blackCandidates; //this is the blackCandidates that is gonna decrement so if theres no more black candidtes, they stop
			int numV = numSeats; //if theres more black candidates than seats, this is going to what stops candidates from voting too much. 
			while (blackC > 0 && numV > 0){
				int i = (int)( Math.random() * blackC);		
				votes.set(votedFor.get(i), (votes.get(votedFor.get(i)) +1));	//this should add 1 to the index of votes that matches the object of votedFor
				votedFor.remove(i);	
				blackC--;
				numV--;				
			}
		}

		for (int j = 0; j < totWhite; ++j){
			ArrayList <Integer> votedFor = new ArrayList<Integer>();
			for (int i = 0; i < numCandidates; i++){ //populate arrayList
				votedFor.add(i);
			}
			int whiteC = whiteCandidates; //this is the white Candidates that is gonna decrement so if there's no more white candidates, they stop voting
			int numV = numSeats; //if theres more white candidates than seats, this is going to what stops candidates from voting too much. 
			while (whiteC > 0 && numV > 0){
				int i = (int)(blackCandidates + (Math.random() * whiteC));
				votes.set(votedFor.get(i), (votes.get(votedFor.get(i)) +1));	
				votedFor.remove(i);
				whiteC--;
				numV--;				
			}

		}

		ArrayList<Integer> most = new ArrayList<Integer>();
		for (int i = 0; i < numSeats; ++i){
			int maxValue = 0;
			int maxIndex = 0;
			for (int j = 0; j < numCandidates; ++j){
				if (votes.get(j) > maxValue){				
					maxValue = votes.get(j);
					maxIndex = j;										
				}
			}
			votes.set(maxIndex,  0)
			;
			most.add(maxValue);
			if (maxIndex < blackCandidates){
				blackSeats++;
			}
			else 
				whiteSeats++;
		}
		System.out.println("black seats:" + blackSeats);
		System.out.println("white seats:" + whiteSeats);
	}
	public static void limited(int blackCandidates, int whiteCandidates){
		ArrayList<Integer> votes = new ArrayList<Integer>();
		for (int i = 0; i < numCandidates; ++i){
			votes.add(0);
		}
		int blackSeats = 0;
		int whiteSeats = 0;
		for (int j = 0; j < totBlack; ++j){
			ArrayList <Integer> votedFor = new ArrayList<Integer>();
			for (int i = 0; i < numCandidates; i++){//populate arraylist, so that the objects are equal to the indicies of the votes list
				votedFor.add(i);
			}
			int blackC = blackCandidates; //this is the blackCandidates that is gonna decrement so if theres no more black candidtes, they stop
			int numV = numLimVotes; //if theres more black candidates than seats, this is going to what stops candidates from voting too much. 
			while (blackC > 0 && numV > 0){
				int i = (int)( Math.random() * blackC);		
				votes.set(votedFor.get(i), (votes.get(votedFor.get(i)) +1));	//this should add 1 to the index of votes that matches the object of votedFor
				votedFor.remove(i);	
				blackC--;
				numV--;				
			}
		}

		for (int j = 0; j < totWhite; ++j){
			ArrayList <Integer> votedFor = new ArrayList<Integer>();
			for (int i = 0; i < numCandidates; i++){ //populate arrayList
				votedFor.add(i);
			}
			int whiteC = whiteCandidates; //this is the white Candidates that is gonna decrement so if there's no more white candidates, they stop voting
			int numV = numLimVotes; //if theres more white candidates than seats, this is going to what stops candidates from voting too much. 
			while (whiteC > 0 && numV > 0){
				int i = (int)(blackCandidates + (Math.random() * whiteC));
				votes.set(votedFor.get(i), (votes.get(votedFor.get(i)) +1));	
				votedFor.remove(i);
				whiteC--;
				numV--;				
			}

		}

		ArrayList<Integer> most = new ArrayList<Integer>();
		for (int i = 0; i < numSeats; ++i){
			int maxValue = 0;
			int maxIndex = 0;
			for (int j = 0; j < numCandidates; ++j){
				if (votes.get(j) > maxValue){				
					maxValue = votes.get(j);
					maxIndex = j;										
				}
			}
			votes.set(maxIndex,  0)
			;
			most.add(maxValue);
			if (maxIndex < blackCandidates){
				blackSeats++;
			}
			else 
				whiteSeats++;
		}
		System.out.println("black seats:" + blackSeats);
		System.out.println("white seats:" + whiteSeats);
	}



	public static void cumulative(int blackCandidates, int whiteCandidates){
		ArrayList<Integer> votes = new ArrayList<Integer>();
		for (int i = 0; i < numCandidates; ++i){
			votes.add(0);
		}
		int blackSeats = 0;
		int whiteSeats = 0;
		for (int j = 0; j < totBlack; ++j){
			ArrayList <Integer> votedFor = new ArrayList<Integer>();
			for (int i = 0; i < numCandidates; i++){//populate arraylist, so that the objects are equal to the indicies of the votes list
				votedFor.add(i);
			}
			int blackC = blackCandidates; //this is the blackCandidates that is gonna decrement so if theres no more black candidtes, they stop
			int numV = numSeats; //if theres more black candidates than seats, this is going to what stops candidates from voting too much. 
			while (blackC > 0 && numV > 0){
				int i = (int)( Math.random() * blackC);		
				votes.set(i, (votes.get(i) +1));	//this should add 1 to the index of votes that matches the object of votedFor								
				numV--;				
			}
		}

		for (int j = 0; j < totWhite; ++j){
			ArrayList <Integer> votedFor = new ArrayList<Integer>();
			for (int i = 0; i < numCandidates; i++){ //populate arrayList
				votedFor.add(i);
			}
			int whiteC = whiteCandidates; //this is the white Candidates that is gonna decrement so if there's no more white candidates, they stop voting
			int numV = numSeats; //if theres more white candidates than seats, this is going to what stops candidates from voting too much. 
			while (whiteC > 0 && numV > 0){
				int i = (int)(blackCandidates + (Math.random() * whiteC));
				votes.set(i, (votes.get(i) +1));							
				numV--;				
			}

		}

		ArrayList<Integer> most = new ArrayList<Integer>();
		for (int i = 0; i < numSeats; ++i){
			int maxValue = 0;
			int maxIndex = 0;
			for (int j = 0; j < numCandidates; ++j){
				if (votes.get(j) > maxValue){				
					maxValue = votes.get(j);
					maxIndex = j;										
				}
			}
			votes.set(maxIndex,  0)
			;
			most.add(maxValue);
			if (maxIndex < blackCandidates){
				blackSeats++;
			}
			else 
				whiteSeats++;
		}
		System.out.println("black seats:" + blackSeats);
		System.out.println("white seats:" + whiteSeats);
	}
}