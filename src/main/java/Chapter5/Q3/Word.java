package Chapter5.Q3;

//Author: Edward Yang
//Date: 10/9/17
//Word object that stores word, type, def
public class Word {
	String word;
	String type;
	String def;
	public Word (String word, String type, String def) {
		this.word = word;
		this.type = type;
		this.def = def;
	}
	public String toString() { 
	    return "\n" + word.toUpperCase().replaceAll("_", " ") + "	[" + type + "]\n " + def;
	}
}
