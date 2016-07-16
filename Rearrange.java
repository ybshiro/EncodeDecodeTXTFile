
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Rearrange{

    public static void main(String [] args){
	if(args.length != 1){
	    System.err.println("Usage:  java Rearrange <filename>");
	    return;
	}

	WordList wordList = new WordList();
	WordList nonWordList = new WordList();
	WordList myList = new WordList();
	String line;
	String [] lines = new String[100];
	int itemRead = 0;
	String [] words;
	String [] nonWords;
	BufferedReader br;
	PrintWriter pw;

	try{
	    br = new BufferedReader(new FileReader(args[0]));
	    pw = new PrintWriter(new FileWriter("Aim.txt"));
	    while((line = br.readLine()) != null){
		words = line.split("[^A-Za-z0-9]+");
		nonWords = line.split("[A-Za-z0-9]+");
		if(nonWords.length > words.length){
		    for(int i = 0; i < words.length; i++){
			pw.println(nonWords[i]);
			int index;
			if((index = wordList.findWord(words[i])) == -1){
			    wordList.insert(words[i]);
			    pw.println("0 " + words[i]);
			}
			else{
			    wordList.moveToFront(words[i]);
			    pw.println((index + 1) + "");
			}
		    }
		    pw.println(nonWords[nonWords.length - 1]);
		    pw.println(" ");
		}
		else{
		    for(int i = 0; i < words.length; i++){
			pw.println(nonWords[i]);
			int index;
			if((index = wordList.findWord(words[i])) == -1){
			    wordList.insert(words[i]);
			    pw.println("0 " + words[i]);
			}
			else{
			    wordList.moveToFront(words[i]);
			    pw.println((index + 1) + "");
			}
		    }
		}
	    }
	    br.close();
	    pw.close();

    	    br = new BufferedReader(new FileReader("Aim.txt"));
    	    while((line = br.readLine()) != null){
		if(itemRead == lines.length)
		    lines = resize(lines, lines.length * 2);
		lines[itemRead++] = line;	    
    	    }
	    br.close();
	    
	    for(int i = 0; i < lines.length; i++){
		if(lines[i] != null){
		    if(i % 2 == 1){
			line = lines[i];
			words = line.split("[^A-Za-z0-9]+");
			for(int j = 0; j < words.length; j++){
			    if(words[j].equals("0")){
				myList.insert(words[j+1]);
				System.out.printf(words[j+1]);
			    }
			    else{
				try{
				    int index = Integer.parseInt(words[j]) - 1;
				    String word = myList.getWord(index);
				    if(word != null){
					System.out.printf(word);
					myList.moveToFront(word);
				    }
				}
				catch(Exception e){}
			    }
			}
		    }
		    else{
			System.out.printf(lines[i]);
		    }
		}
	    }System.out.println("");
    	}
    	catch(Exception err){
    	    System.out.println(err.toString());}
    }

    public static String [] resize(String [] array, int newSize){
	String [] origin = array;
	int numToCopy = Math.min(origin.length, newSize);
	array = new String[newSize];
	for(int i = 0; i < numToCopy; i++)
	    array[i] = origin[i];
	return array;
    }
}