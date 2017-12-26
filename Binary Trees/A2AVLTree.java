import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;


/*
 * This implementation uses an (balanced) binary search tree. 
 * it also allows addition of duplicate items.
 * @author KOKO NANAH JI 
 */
public class A2AVLTree <E> implements Tree<E>,Iterable<E>{
    /**
     * This class represents an inner node of the AVL tree.
     */
	private static class Node<E> implements Comparable<E>{	
		private E element;
		private Node<E> left;
		private Node<E> right;
		private int count;
		private int height;
		public Node(E e,Node<E> l,Node<E> r, int c,int h){
			this.element=e;
			this.left=l;
			this.right=r;
			this.count=c;
			this.height=h;
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
	    /**
	     * returns the height of this node which is 
	     * 0 if it has no child and 1 + max height of
	     * the two children
	     * 
	     * @return height
	     */
		public int getHeight(){
			return this.height;
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
	
		/** sets the height of this Node
	     * @param height of this node 
	     */
		public void setHeight(int h){this.height=h;}
		

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
	public A2AVLTree(){
		 root =null;
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
     * @author R. Sedgewick
     */
	private Node<E> Insert(Node<E> root,E value) {//// R. Sedgewick
        if(root==null){
            Node<E> n= new Node<E>(value, null, null, 1, 0);
            root =n;
        }
        else if(root.compareTo(value)>0){
            root.setLeft(Insert(root.getLeft(),value));          
        }
        
        else if(root.compareTo(value)<0){
            root.setRight(Insert(root.getRight(), value));
        }      
        else if(root.compareTo(value)==0){
        	root.setCount(root.getCount()+1);
        }
       	root.setHeight(Math.max( height( root.getLeft() ), height( root.getRight() ) ) + 1);
        return balance(root);
   }
	

    /**
     * Restores the AVL tree property of the subtree.
     * 
     * @param x the subtree
     * @return the subtree with restored AVL property
     * @author R. Sedgewick
     */
    private Node<E> balance(Node<E> root) { // R. Sedgewick
    	 if (balanceFactor(root) < -1) { // left.height<right.height (difference is 2)
             if (balanceFactor(root.right) > 0) {	// root.right.left.height>root.right.right.height
                 root.right = rightRotate(root.right);
             }
             root = leftRotate(root);
         }
         else if (balanceFactor(root) > 1) {
             if (balanceFactor(root.left) < 0) {            	           	 
            	 root.left = leftRotate(root.left);
             }
             root = rightRotate(root);
         }
         return root;
    }
    
    
    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     * 
     * @param x the subtree
     * @return the balance factor of the subtree
     * @author R. Sedgewick
     */
    private int balanceFactor(Node<E> root) { // R. Sedgewick
        return height(root.getLeft()) - height(root.getRight());
    } 
          

    /**
     * Rotates the given subtree to the right.
     * 
     * @param x the subtree
     * @return the right rotated subtree
     * @author R. Sedgewick
     */
	private Node<E> rightRotate(Node<E> x){ // R. Sedgewick
		Node<E> y = x.getLeft();
		x.setLeft(y.getRight());
		y.setRight(x);
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight()))+1);
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight()))+1);
		return y;
	}


    /**
     * Rotates the given subtree to the left.
     * 
     * @param x the subtree
     * @return the left rotated subtree
     * @author R. Sedgewick
     */
    private Node<E> leftRotate(Node<E> x){ // R. Sedgewick
		Node<E> y = x.getRight();
		x.setRight(y.getLeft());
		y.setLeft(x);
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight()))+1);
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight()))+1);
		return y;
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
	   x.setHeight(Math.max( height( x.getLeft() ), height( x.getRight() ) ) + 1);
	   return balance(x); 
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
           root.setHeight(Math.max( height( root.getLeft() ), height( root.getRight() ) ) + 1);
		   return balance(root); 
	}

	
	/** Returns an iterator over the elements in this tree in ascending order
	 * @return the iterator described above
	 */
	@Override
	public Iterator<E> iterator() {
		return (new InorderIterator());
	}

	
	/** Returns the height of the tree. 
	 * For an empty tree should return -1. 
	 * For a tree of one node should return 0
	 * @return height of the tree
	 */	
	@Override
	public int height() {
		return height(root);
	}
	
	
	/** Returns the height of the subtree. 
	 * For an empty subtree should return -1. 
	 * For a subtree of one node should return 0
	 * 
	 * @param root the root of subtree
	 * @return height of the subtree
	 */	
	private int height(Node<E> root) {
		if(root == null){
			return -1;
		}
		return root.getHeight();
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
			return !st.isEmpty();
		}

		@Override
		public E next() {
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
		System.out.println(t.getElement()+" C:"+t.count+" H:"+t.height);
		infixPrint(t.left, levelCount+1);
	}
	
}
