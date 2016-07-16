

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MTFdecode{

    public static void main(String [] args){
	if(args.length != 1){
	    System.err.println("Usage:  java MTFdecode <filename>");
	    return;
	}

	WordList myList = new WordList();
	WordList myList2 = new WordList();
	String line;
	String [] tokens;
	BufferedReader br = null;
	PrintWriter pw = null;

	try{
	    br = new BufferedReader(new FileReader(args[0]));
	    pw = new PrintWriter(new FileWriter("MTFencode.txt"));
	    while((line = br.readLine()) != null){
		tokens = line.split("[^A-Za-z0-9]+");
		for(int i = 0; i < tokens.length; i++){
		    int index;
		    if((index = myList.findWord(tokens[i])) == -1){
			myList.insert(tokens[i]);
			pw.println("0 " + tokens[i]);
		    }
		    else{
			myList.moveToFront(tokens[i]);
			pw.println((index + 1) + "");
		    }
		}		
	    }
	    br.close();
	    pw.close();

    	    br = new BufferedReader(new FileReader("MTFencode.txt"));
    	    while((line = br.readLine()) != null){
    	    	tokens = line.split("[^A-Za-z0-9]+");
    	    	for(int i = 0; i < tokens.length; i++){
    	    	    if(tokens[i].equals("0")){
    	    	    	myList2.insert(tokens[i+1]);
			System.out.println(tokens[i+1]);
	    	    }
    	    	    else{
	    		try{
			    int index = Integer.parseInt(tokens[i]) - 1;
			    String word = myList2.getWord(index);
			    if(word != null){
				System.out.println(word);
				myList2.moveToFront(word);
			    }
			}
			catch(Exception e){}
    	    	    }
    	    	}	 
    	    }
	    br.close();
    	}
    	catch(Exception err){
    	    System.out.println(err.toString());}
    }
}