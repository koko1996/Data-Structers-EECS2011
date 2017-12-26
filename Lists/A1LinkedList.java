import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
/* Linked list that follows the list interface specifications
 * @author KOKO NANAH JI
 */
public class A1LinkedList<E> implements List<E> {

	private DoublyLinkedList<E> dlist;
	
	
	/*constructs a new doubly linked list
	 *
	 */
	public A1LinkedList(){
		dlist=new DoublyLinkedList<E>();
	}
	
	/* Appends the specified element to the end of this list
	 *  
	 * @param e - element to be appended to this list  
	 * @return true
	 * @throws ClassCastException - if the class of the specified element prevents it from being added to this list
	 * @throws NullPointerException - if the specified element is null and this list does not permit null elements
	 * @throws IllegalArgumentException - if some property of this element prevents it from being added to this list
	 */
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		this.dlist.addLast(e);
		return true;
	}
	
	
	/* Inserts the specified element at the specified position in this list (optional operation).
	 *  Shifts the element currently at that position (if any) and any subsequent elements to the right 
	 *  (adds one to their indices).
	 *      
	 * @param index - index at which the specified element is to be inserted
	 * @param element - element to be inserted
	 * @throws ClassCastException - if the class of the specified element prevents it from being added to this list
	 * @throws NullPointerException - if the specified element is null and this list does not permit null elements
	 * @throws IllegalArgumentException - if some property of this element prevents it from being added to this list
	 * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()) 
	 */
	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
	try{
		if(index > this.dlist.size() || index<0){
			throw new	IndexOutOfBoundsException();
			}
		if(element == null){
			throw new NullPointerException();
			}
		
		if(index==0){
		this.dlist.addFirst(element);	
		}
		else{
		int i=0;
		DoublyLinkedList<E> tempList=new DoublyLinkedList<E>();

		while(i<index){
				tempList.addLast(this.dlist.removeFirst());
				i++;
			}
		tempList.addLast(element);
	while(this.dlist.last()!=null){
		tempList.addLast(this.dlist.removeFirst());
	}
	
	
	while(0<tempList.size()){
		dlist.addLast(tempList.removeFirst());
	
	}
		}
}
	
catch(ClassCastException e){
	throw new ClassCastException();
}
catch(IllegalArgumentException  e){
	throw new IllegalArgumentException(); 
}

	}


	/*Removes all of the elements from this list (optional operation). 
	 * The list will be empty after this call returns.
	 *  
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
		while(this.dlist.size()>0){
			this.dlist.removeFirst();
		}
		
	}
	

	/* Removes the first occurrence of the specified element from this list, if it is present 
	 * 
	 * @param o - element to be removed from this list, if present
	 * @ return true if the object is removed
	 * @throws ClassCastException - if the class of the specified element prevents it from being added to this list
	 * @throws NullPointerException - if the specified element is null and this list does not permit null elements
	 */
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		try {if(o == null){
			throw new NullPointerException();
			}
		if(this.dlist.size()>0){
		if(this.dlist.first().equals(o)){
			this.dlist.removeFirst();
			return true;
		}
		else if(this.dlist.last().equals(o)){
			this.dlist.removeLast();
			return true;
		}
		
		else{
		
		boolean answer=false, LookingForThefirst=true;
		
		int i=0;
		E t=null;
		while(i<this.dlist.size()){

			t=this.dlist.removeFirst();
			if(LookingForThefirst&& t.equals(o)){
				answer=true;
				LookingForThefirst=false;
			}
			else{
			this.dlist.addLast(t);
			i++;
		}
		}
		
		return answer;
				
		}
		}
		else {
			return false;
		}
		}
	catch(ClassCastException e){throw new ClassCastException();}
	}

	
	/* Removes the element at the specified position in this list (optional operation).
	 *  Shifts any subsequent elements to the left (subtracts one from their indices). 
	 *  Returns the element that was removed from the list. 
	 * 
	 * @param index - the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()) 
	 */
	@Override
	public E remove(int index) {
	// TODO Auto-generated method stub
	if(index > this.dlist.size()-1 || index<0){
			throw new	IndexOutOfBoundsException();
			}

	if(index == 0){
		return this.dlist.removeFirst();
		}
	else if(index == this.dlist.size()-1){
		return this.dlist.removeLast();
		}
	
	else{
	
	
	
	
	
	int i=0;
	E t=null;
	E answer=null;
	while(i<=this.dlist.size()){
		t=this.dlist.removeFirst();
		if(i==index){
		answer=t;	
		}
		else{
		this.dlist.addLast(t);
		}
		i++;
	}
		
	
		return answer;
	}
}
	
	
	/*returns the number of elements in the list
	 * 
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.dlist.size();
	}
	
	
	/*Returns a string representation of this collection.
	 * The string representation consists of a list of the
	 * collection's elements in the order they are returned
	 * by its iterator, enclosed in square brackets ("[]").
	 * Adjacent elements are separated by the characters ", ".
	 *     
	 * @return string representation of this collection. 
	 * 
	 * 
	 */
	public String toString(){
	if(this.dlist.size()>0){
		StringBuilder sB = new StringBuilder();
		sB.append( "[");
		int i=0;
		E t=null;
		while(i<this.dlist.size()){
			
			t=this.dlist.removeFirst();
			this.dlist.addLast(t);
			sB.append( String.valueOf(t));
			sB.append(", ");
			
			i++;
		}
		sB.delete(sB.length()-2,sB.length());
		sB.append( "]");
		
		return sB.toString();
	}
	return "[]";
	}
	
	
	
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();

	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	
	/*Returns true if this list contains no elements.
	 * 
	 * @return true if this list contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.dlist.size()==0);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}



	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
        
}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
      }

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException();
	}


}
