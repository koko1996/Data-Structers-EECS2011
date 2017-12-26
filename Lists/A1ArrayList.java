import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
/* Array list that follows the list interface specifications
 * @author KOKO NANAH JI
 */
public class A1ArrayList <E> implements List<E> {
	private E[] arr;
	private int NumberOfElements=0;
	private int size=10;
	 
	/*constructs a new array of size 10
	 *
	 */
	public A1ArrayList(){
		this.arr= (E[]) new Object[size];
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
		this.add(NumberOfElements, e);
		return true;
	}
	
	/* doubles the size of the array of the object
	 * 
	 */
	private void doubleTheSize(){
		E[] temp=(E[]) new Object[size];
		for(int i=0;i<size;i++){
			temp[i]=arr[i];
		}
		this.size=this.size<<1;
		arr=(E[]) new Object[size];
		for(int i=0;i<size/2;i++){
			arr[i]=temp[i];
		}
		
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
	if(index > this.NumberOfElements || index<0){
		throw new	IndexOutOfBoundsException();
		}
	if(element == null){
		throw new NullPointerException();
		}
		
	if(NumberOfElements+1>size ){
		this.doubleTheSize();	
		}
//////////////////////////////////////////	
		if(arr[index]!=null){
			for(int i=size-1;i>index;i--){
				arr[i]=arr[i-1];
		}
	}
		arr[index]=element;
		this.NumberOfElements++;	 
		
///////////////////////////////////////////////
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
		int i=0;
		while(i<this.NumberOfElements){
			this.remove(0);
		}
		
	}
	/* if the size is less that 10 
	 * reduces the size of the array of this object
	 * to the half 
	 *
	 * 
	 */
	private void halfTheSize(){
		if(size>10){
		this.size=size>>>1;
		E[] temp=(E[]) new Object[size];
		for(int i=0;i<size;i++){
			temp[i]=arr[i];
		}
		arr=(E[]) new Object[size];
		for(int i=0;i<size/2;i++){
			arr[i]=temp[i];
		}
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
		int i;
		for(i=0;i<this.NumberOfElements && !arr[i].equals(o);i++){
		}
		
		if(i>=0 && i<this.NumberOfElements &&  arr[i]!=null  && arr[i].equals(o) ){
		this.remove(i);
		
		return true;
				
		}
				return false;

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
	if(index > this.NumberOfElements-1 || index<0){
			throw new	IndexOutOfBoundsException();
			}
		
		E temp=arr[index];
		for(int i=index;i<this.NumberOfElements-1;i++){
			arr[i]=arr[i+1];
		}
		arr[this.NumberOfElements-1]=null;
		this.NumberOfElements--;
		if(this.NumberOfElements<= 0.25*(this.size)){
			this.halfTheSize();
		}
		return temp;

}
	/*returns the number of elements in the list
	 * 
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub

		return this.NumberOfElements;
	}
	
	/*Returns a string representation of this collection.
	 *  The string representation consists of a list of the
	 *   collection's elements in the order they are returned
	 *    by its iterator, enclosed in square brackets ("[]").
	 *     Adjacent elements are separated by the characters ", ".
	 *     
	 *  @return string representation of this collection. 
	 * 
	 * 
	 */
	public String toString(){
		StringBuilder sB = new StringBuilder();
		if(this.NumberOfElements>0){
			sB.append( "[");
		for(E e :arr){
			if(e!=null){
			sB.append( String.valueOf(e));
			sB.append(", ");
			}
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
	 *@return true if this list contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.NumberOfElements==0);
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
