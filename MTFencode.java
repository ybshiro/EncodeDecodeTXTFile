

import java.io.FileReader;
import java.io.BufferedReader;

public class MTFencode{

    public static void main(String [] args){
	if(args.length != 1){
	    System.err.println("Usage:  java MTFencode <filename>");
	    return;
	}

	WordList myList = new WordList();
	String line;
	String [] tokens;

	try{
	    BufferedReader br = new BufferedReader(new FileReader(args[0]));
	    while((line = br.readLine()) != null){
		tokens = line.split("[^A-Za-z0-9]+");
		for(int i = 0; i < tokens.length; i++){
		    int index;
		    if((index = myList.findWord(tokens[i])) == -1){
			myList.insert(tokens[i]);
			System.out.println("0 " + tokens[i]);}
		    else{
			myList.moveToFront(tokens[i]);
			System.out.println((index + 1) + "");
		    }
		}		
	    }
	    br.close();
	}
	catch(Exception err){
	    System.out.println(err.toString());}
    }
}