package Chapter5.Q3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
public class HashTable {
	Word[] HashTable;
	LinkedList<Word>[] SepChainHashTable;
	DataStructure dataStru;
	int numSearched = 0;
	//Create a new Hashtable with probing type and array of Eord objects
	public HashTable(DataStructure dataStru, Word[] dictionary) {
		this.dataStru = dataStru;
		switch (dataStru) {
		case LinProb:
			LinProbCreate(dictionary);
			break;
		case QuadProb:
			QuadProbCreate(dictionary);
			break;
		case SepChain:
			SepChainCreate(dictionary);
			break;
		case DoubleHash:
			DoubleHashCreate(dictionary);
			break;
		}
	}
	
	public enum DataStructure{
		LinProb, QuadProb, SepChain, DoubleHash
	}
	//Create a new Hashtable with linear probing
	private void LinProbCreate(Word[] dictionary){
		HashTable = new Word[319133];
		for (Word word : dictionary) {
			String vocab = word.word;
			int place = 0;
			//the hash function
			place = HashFunction(vocab);
			boolean notplaced = true;
			while (notplaced) {
				if (HashTable[place % HashTable.length] == null) {
					HashTable[place % HashTable.length] = word;
					notplaced = false;
				}
				else {
					//linear probing
					place++;
				}
			}
		}
	}
	//Search in a Hashtable with linear probing
	private Word LinProbSearch(String vocab) {
		int place = 0;
		place = HashFunction(vocab);

		boolean notfound = true;
		Word word;
		while (notfound) {
			numSearched++;
			if (HashTable[place % HashTable.length] != null) {
				if (vocab.equals(HashTable[place % HashTable.length].word)) {
					word = HashTable[place % HashTable.length];
					return word;
				}
				else {
					place++;
				}
			}
			else {
				return null;
			}
		}
		return null;
	}
	//Create a new Hashtable with Quadratic Probing
	private void QuadProbCreate(Word[] dictionary) {
		HashTable = new Word[319133];
		for (Word word : dictionary) {
			int failInsertCount = 0;
			String vocab = word.word;
			int place = 0;
			////the hash function
			place = HashFunction(vocab);
			boolean notplaced = true;
			while (notplaced) {
				if (HashTable[place % HashTable.length] == null) {
					HashTable[place % HashTable.length] = word;
					notplaced = false;
				}
				else {
					//Quadtratic Probing 
					failInsertCount++;
					place+=Math.pow(failInsertCount, 2);
				}
			}
		}
	}
	//Search in a Hashtable with Quadratic Probing
	private Word QuadProbSearch(String vocab) {
		int place = 0;
		int failFindCount = 0;
		place = HashFunction(vocab);

		boolean notfound = true;
		Word word;
		while (notfound) {
			numSearched++;
			if (HashTable[place % HashTable.length] != null) {
				if (vocab.equals(HashTable[place % HashTable.length].word)) {
					word = HashTable[place % HashTable.length];
					return word;
				}
				else {
					failFindCount++;
					place+=Math.pow(failFindCount, 2);
				}
			}
			else {
				return null;
			}
		}
		return null;
	}
	//Create a new Hashtable with Separate Chaining
	private void SepChainCreate(Word[] dictionary) {
		SepChainHashTable = new LinkedList[155285];
		for (Word word : dictionary) {
			String vocab = word.word;
			int place = 0;
			//Hash Function
			place = HashFunction(vocab);
			
			if (SepChainHashTable[place % SepChainHashTable.length] == null) {
				SepChainHashTable[place % SepChainHashTable.length] = new LinkedList<Word>();
				SepChainHashTable[place % SepChainHashTable.length].add(word);
			}
			else {
				//Separate Chaining
				SepChainHashTable[place % SepChainHashTable.length].add(word);
			}
		}
	}
	//Search in a Hashtable with Separate Chaining
	private Word SepChainSearch(String vocab) {
		int place = 0;
		int failFindCount = 0;
		place = HashFunction(vocab);

		boolean notfound = true;
		Word word;
		while (notfound) {
			numSearched++;
			if (SepChainHashTable[place % SepChainHashTable.length] != null) {
				if (vocab.equals(SepChainHashTable[place % SepChainHashTable.length].get(failFindCount).word)) {
					word = SepChainHashTable[place % SepChainHashTable.length].get(failFindCount);
					return word;
				}
				else {
					failFindCount++;
					if (SepChainHashTable[place % SepChainHashTable.length].size() <= failFindCount) {
						return null;
					}
				}
			}
			else {
				return null;
			}
		}
		return null;
	}
	//Create a new Hashtable with  Double Hashing
	private void DoubleHashCreate(Word[] dictionary) {
		HashTable = new Word[319133];
		for (Word word : dictionary) {
			String vocab = word.word;
			int place = 0;
			//hash function
			place = HashFunction(vocab);
			boolean notplaced = true;
			while (notplaced) {
				if (HashTable[place % HashTable.length] == null) {
					HashTable[place % HashTable.length] = word;
					notplaced = false;
				}
				else {
					//Double Hashing (diffrent than the 1st hash function)
					for (int x = 0; x<vocab.length(); x++) {
						place += vocab.charAt(x)/(x+1);
					}
				}
			}
		}
	}
	//Search in a Hashtable with  Double Hashing
	private Word DoubleHashSearch(String vocab) {
		int place = 0;
		place = HashFunction(vocab);

		boolean notfound = true;
		Word word;
		while (notfound) {
			numSearched++;
			if (HashTable[place % HashTable.length] != null) {
				if (vocab.equals(HashTable[place % HashTable.length].word)) {
					word = HashTable[place % HashTable.length];
					return word;
				}
				else {
					for (int x = 0; x<vocab.length(); x++) {
						place += vocab.charAt(x)/(x+1);
					}
				}
			}
			else {
				return null;
			}
		}
		return null;
	}
	//Search in Hashtable with appropriate search type and return info about search
	public String Search(String vocab) {
		numSearched = 0;
		Word result;
		String success = " 	Yes		";
		switch (dataStru) {
		case LinProb:
			result = LinProbSearch(vocab);
			String format = "\n\nData Structure		Table Size	Lambda		Success		Item investigated";
			if (result == null) {
				success = "	No		";
			}
			return format + "\nLinear Probing		" + HashTable.length + "		" + (float)155285/HashTable.length
					+ success + numSearched;
		case QuadProb:
			result = QuadProbSearch(vocab);
			if (result == null) {
				success = "	No		";
			}
			return "Quadratic Probing	" + HashTable.length + "		" + (float)155285/HashTable.length
					+ success + numSearched;
		case SepChain:
			result = SepChainSearch(vocab);
			if (result == null) {
				success = "	No		";
			}
			return "Separate Chaining	" + SepChainHashTable.length + "		" + (float)155285/SepChainHashTable.length
					+ "	" + success + numSearched;
		case DoubleHash:
			result = DoubleHashSearch(vocab);
			if (result == null) {
				success = "	No		";
			}
			return "Double Hashing		" + HashTable.length + "		" + (float)155285/HashTable.length
					+ success + numSearched;
			
		}
		return "";
	}
	//Reter def of a word
	public Word ReturnWord(String vocab) {
		switch (dataStru) {
		case LinProb:
			return LinProbSearch(vocab);
		case QuadProb:
			return QuadProbSearch(vocab);
		case SepChain:
			return SepChainSearch(vocab);
		case DoubleHash:
			return DoubleHashSearch(vocab);
			
		}
		return null;
	}
	// the hash function
	private int HashFunction(String vocab) {
		int place = 0;
		for (int x = 0; x<vocab.length(); x++) {
			place += vocab.charAt(x)*x*50+(x+1)*(x);
		}
		return place;
	}
	
	public static void main(String[] args) {
		Scanner sf;
		Word[] dictionary = new Word[155285];
		boolean dontExit = true;
		//read file
		try {
			sf = new Scanner(new File(Thread.currentThread().getContextClassLoader().getResource("dictionary.txt").getFile()));
			for (int x = 0; x < dictionary.length; x++) {
				String s = sf.nextLine();
				String[] word = s.split("\\|");
				dictionary[x] = new Word(word[0], word[1], word[2]);
			} 
			sf.close( );
		} catch (FileNotFoundException e) {
			System.out.println("Cant find file.");
		} catch (NullPointerException e) {
			System.out.println("Cant find file.");
		}
		//Linear Probing
		HashTable linHash = new HashTable(DataStructure.LinProb, dictionary);
		//Quadratic Probing
		HashTable quadHash = new HashTable(DataStructure.QuadProb, dictionary);
		//Separate Chaining
		HashTable sepChainHash = new HashTable(DataStructure.SepChain, dictionary);
		//Double Hashing
		HashTable doubleHash = new HashTable(DataStructure.DoubleHash, dictionary);
		
		//search
		Scanner input = new Scanner(System.in);
		// get input
		while (dontExit) {
			System.out.print("Enter \"xyz\" to exit, Word to search for: ");
			String vocab = input.nextLine().toLowerCase();
			vocab = vocab.replaceAll("\\s+", "_");
			if (vocab.equals("xyz")) {
				dontExit = false;
				System.out.print("Exit string \"xyz\" detected.");
			}
			else {
				//make sure we get the Word object in case one hash table fails to find it
				Word temp = linHash.ReturnWord(vocab);
				if (temp == null) {
					temp = quadHash.ReturnWord(vocab);
					if (temp == null) {
						temp = sepChainHash.ReturnWord(vocab);
						if (temp == null) {
							temp = doubleHash.ReturnWord(vocab);
							if (temp == null) {
								System.out.print("Word not found!!!");
							}
						}
					}
				}
				if (temp != null) {
					System.out.print(temp.toString());
				}
				System.out.println(linHash.Search(vocab));
				System.out.println(quadHash.Search(vocab));
				System.out.println(sepChainHash.Search(vocab));
				System.out.println(doubleHash.Search(vocab));
			}
		}
		input.close();

	}

}
