/*Doubly linked list
 * 
 * authors Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser 
 */
public class DoublyLinkedList<E> {

private static class Node<E>{	
	private E element;
	private Node<E> prev;
	private Node<E> next;
	public Node(E e,Node<E> p,Node<E> n){
		this.element=e;
		this.prev=p;
		this.next=n;
	}
	public E getElement(){
		return this.element;
	}
	public Node<E> getPrev(){
		return this.prev;
	}
	public Node<E> getNext(){
		return this.next;
	}
	public void setPrev(Node<E> p){this.prev=p;}
	public void setNext(Node<E> n){this.next=n;}
}

	private Node<E> header;
	private Node<E> trailer;
	private int size =0;

 public DoublyLinkedList(){
	 header = new Node<>(null,null,null);
	 trailer = new Node<>(null,header,null);
	 header.setNext(trailer);
 }

 	public int size(){ 
 		return size;
	}
 	public boolean isEmpty(){
 		return size==0;
 	}

 	public E first(){
 		if(this.isEmpty()){
 			return null;
 		}
 		return header.getNext().getElement();
 	}


 	public E last(){
 		if(this.isEmpty()){
 			return null;
 		}
 		return trailer.getPrev().getElement();
 	}

 	public void addFirst(E e){
 		addBetween(e, header,header.getNext());
 	}
 	public void addLast(E e){
 		addBetween(e, trailer.getPrev(),trailer);
 	}
 	
 	public E removeFirst(){
 		if(this.isEmpty()){
 			return null;
 		}
 		return remove(header.getNext());
 	}
 	public E removeLast(){
 		if(this.isEmpty()){
 			return null;
 		}
 		return remove(trailer.getPrev());
 	}
 private void addBetween (E e, Node<E> predecessor, Node<E> successor ){
	 Node<E> newest =new Node<>(e,predecessor,successor);
	 predecessor.setNext(newest);
	 successor.setPrev(newest);
	 this.size++;
 }
 
 private E remove(Node<E> node){
	 Node <E> pred =node.getPrev();
	 Node <E> succ =node.getNext();
	 pred.setNext(succ);
	 succ.setPrev(pred);
	 this.size--;
	 return node.getElement();
	 
 }



}