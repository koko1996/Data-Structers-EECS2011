import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;


/*
 * This implementation uses an (unbalanced) binary search tree. 
 * it also allows addition of duplicate items.
 * @author KOKO NANAH JI 
 */
public class A2BSTree <E> implements Tree<E>{
    /**
     * This class represents an inner node of the AVL tree.
     */
	private static class Node<E> implements Comparable<E>{	
		private E element;
		private Node<E> left;
		private Node<E> right;
		private int count;
		public Node(E e,Node<E> l,Node<E> r, int c){
			this.element=e;
			this.left=l;
			this.right=r;
			this.count=c;
		}
	    /**
	     * returns the element.
	     * @return the element
	     */
		public E getElement(){ 
			return this.element;
		}
	    /**
	     * returns the left node.
	     * @return the left node
	     */
		public Node<E> getLeft(){
			return this.left;
		}
	    /**
	     * returns the right node.
	     * @return the right node
	     */
		public Node<E> getRight(){
			return this.right;
		}
	    /**
	     * returns the count(number of elements with the same value).
	     * @return count
	     */
		public int getCount(){
			return this.count;
		}
	    /** sets the left child of this Node
	     * @param l the left child
	     */
		public void setLeft(Node<E> l){this.left=l;}
	    /** sets the right child of this Node
	     * @param r the right child
	     */
		public void setRight(Node<E> r){this.right=r;}
	    /** sets the count of this Node
	     * @param count the number of elements with the same value
	     */
		public void setCount(int c){this.count=c;}
		
		
		
		/*
		 * Compares this object with the specified object for order. Returns
		 * a negative integer, zero, or a positive integer as this object is
		 * less than, equal to, or greater than the specified object. 
		 * @return a negative integer, zero, or a positive integer
		 */
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(E e) {
			// TODO Auto-generated method stub

			try{
			return ((Comparable<E>) element).compareTo(e);
			}
			catch(ClassCastException exception){
				throw new ClassCastException("Object can not be compared");
			}	
		}
	
	}

       /**
     * The root node.
     */
	private Node<E> root;
    /**
     * The size of the tree.
     */
	private int size =0;
	
	 /**
     * Initializes an empty AVLTree.
     */
	public A2BSTree(){
		 root = null;
	}
	
	
	/**
	 * Adds the specified element to this tree (duplicates are allowed)
	 * @param e element to add
	 */
	@Override
	public void add(E e) {
			this.size++;
			this.root = this.Insert(root, e);
			
	}
	
	
    /**
     * Inserts the value in the subtree. if the tree already contains the specified value
     * it adds one to the count field of the Node indicating the addition
     *  
     * @param root the subtree
     * @param value the value
     * @return the subtree
     */
	private Node<E> Insert(Node<E> root,E value) {
		if(root==null){
        	root= new Node<E>(value, null, null, 1);
        }
		else {
		Node<E> temp=root;
		boolean left=false;
		boolean right=false;

		while(!(left || right) && !temp.getElement().equals(value) ){
			if(temp.compareTo(value)>0){
				if(temp.getLeft()!=null){
					temp=temp.getLeft();
				}
				else {
					left=true;
				}
			}
        else if(temp.compareTo(value)<0){
				if(temp.getRight()!=null){
					temp=temp.getRight();
				}
				else {
					right=true;
				}	
        	}	
        }
		if(temp.getElement().equals(value)){
        	temp.setCount(temp.getCount()+1);
		}
        else{
        	if(left){
        		temp.setLeft(new Node<E>(value, null, null, 1));
        	}
        	else{
        		temp.setRight(new Node<E>(value, null, null, 1));	
        	}
        }
	}
        return root;
       
    }
	
	/**
	 * Adds all of the elements in the specified collection to this tree.
	 * (duplicates are allowed)
	 * @param c Collection containing the elements
	 */	
	@Override
	public void addAll(Collection<? extends E> c) {
		for(E e:c){
			this.add(e);
		}
	}
		
	

	/**
	 * Removes the specified element from this set if it is present. 
	 * (if more than one were present, removes only the first one)
	 * @param o object to remove
	 * @return true if this tree contained the element 
	 */
	@Override
	public boolean remove(Object o) {
		@SuppressWarnings("unchecked")
		E e =(E) o;
		int sizeBefore=this.size;
		root = this.delete(root, e);
		if(sizeBefore==size){
			return false;
		}
		return true;
	}
	
	
	
    /**
     * Returns the node with the smallest value in the subtree.
     * 
     * @param x the subtree
     * @return the node with the smallest value in the subtree
     * @author R. Sedgewick
     */
	private Node<E> min(Node<E> x) // R. Sedgewick
	{
	   if (x.getLeft() == null) return x;
	   return min(x.getLeft()); 
	}
	
    /**
     * Removes the smallest value from the given subtree.
     * 
     * @param x the subtree
     * @return the updated subtree
     * @author R. Sedgewick
     */
	private Node<E> deleteMin(Node<E> x) // R. Sedgewick
	{	 
	   if (x.getLeft() == null) {	
		return x.getRight();
		}
	   x.setLeft(deleteMin(x.getLeft()));
	   return x; 
	}
	
	
    /**
     * Removes the specified value from the given subtree.
     * 
     * @param root the subtree
     * @param value the value 
     * @return the updated subtree
     * @author R. Sedgewick
     */
	private Node<E> delete(Node<E> root, E value){ // R. Sedgewick
		   if ( root == null ) return null;
		   	  int cmp = root.compareTo(value);
		   if (cmp > 0) {
			  root.setLeft(delete(root.getLeft(),  value));             
		   }
		   else if (cmp < 0){
		      root.setRight(delete(root.getRight(), value));	   
		   }
		   else 
		   {
			   if(root.getCount()>1) { root.setCount(root.count-1);size--;return root; }
			   else if (root.getRight()== null){ size--;return root.getLeft();}
			   else if (root.getLeft() == null){ size--;return root.getRight();}
		      Node<E> t = root;
		      root = min(t.getRight()); 
		      root.setRight(deleteMin(t.getRight()));      
		      root.setLeft(t.getLeft());
		      size--;
		   }
		   return root; 
	}
	
	/** Returns an iterator over the elements in this tree in ascending order
	 * @return the iterator described above
	 */
	@Override
	public Iterator<E> iterator() {
		return (new InorderIterator());
	}
	
	
	/** Returns the depth of the Node. 
	 * For a null Node should return 0. 
	 * For a Node should return maximum depth(left Node, right Node) + 1  
	 * @return depth of the Node
	 */
	private int depth(Node<E> root){
		if(root == null){
			return 0;
		}
		else {
			return Math.max(depth(root.getLeft()), depth(root.getRight()))+1;
		}
	}
	
	/** Returns the height of the tree. 
	 * For an empty tree should return -1. 
	 * For a tree of one node should return 0
	 * @return height of the tree
	 */
	@Override
	public int height() {
		return depth(root)-1;
	}

	/** Returns the number of elements in the tree. 
	 * @return number of elements
	 */
	@Override
	public int size() {
		return this.size;
	}

    // an iterator, doesn't implement remove().
	private class InorderIterator implements Iterator<E> {
		private Stack<Node<E>> st= new Stack<>();

		public InorderIterator(){
			if(root!=null){
			  Node<E> current = root;
			    while(current!=null){
				  st.push(current);
					current=current.getLeft();
			    }
			}
	    }

		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !st.isEmpty();
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
		  E answer = null;
		  if(!st.isEmpty()){
			  Node<E> current = st.pop();	  
			  answer = (E) current.getElement();
			  if(current!=null && current.getRight()!=null){
			  current=current.getRight();
			  }
			  if(current!=null && current.getElement()!= answer){
			  st.push(current);
			  current = st.pop();
			  
			  while(current!=null){
					st.push(current);
					current=current.getLeft();
			  	}
			  }
		  }
		 
		return answer;
		}
	}
	
	public void print() {
		infixPrint(root, 0);
	}

	/*
	 * this method implements print
	 */
	protected void infixPrint(Node  t, int levelCount) {
		if(t == null)
			return;

		infixPrint(t.right, levelCount+1);
		for(int i=0; i < levelCount; i++)
			System.out.print("    ");
		System.out.println(t.getElement()+" C:"+t.count);
		infixPrint(t.left, levelCount+1);
	}
	
}
