

public class WordList{
    private Node head;
    
    WordList(){
	head = null;
    }

    private class Node{
	String value;
	Node next;
	Node(String s){value = s;}
    }

    public int findWord(String s){
    	Node curr = head;
	int index = 0;
	while(curr != null){
	    if(curr.value.equals(s)){
		return index;}
		curr = curr.next;
		index++;
	}
	return -1;
    }
    
    public void insert(String s){
	Node curr = new Node(s);
	if(head == null){
	    head = curr;}
	else{
	    curr.next = head;
	    head = curr;
	}
    }

    public void moveToFront(String s){
	int index;
	if((index = findWord(s)) == -1){return;}
	if(index == 0){return;}
	
	int curr = 1;
	Node prevNode = head;
	Node currNode = prevNode.next;
	while(curr < index){
	    prevNode = prevNode.next;
	    currNode = currNode.next;
	    curr++;
	}
	prevNode.next = prevNode.next.next;
	currNode.next = head;
	head = currNode;
    }  
    
    public String getWord(int i){
	Node curr = head;
	int index = 0;
	
	while(curr != null){
	    if(index == i){return curr.value;}
	    index++;
	    curr = curr.next;}
	return null;
    }

    public void print(){
	Node a = head;
	while(a != head){
	    System.out.println(a.value);
	    a = a.next;
	}
    }
}