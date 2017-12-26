import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class SortTester {

	public static void main(String[] args) {

		
		int N[]={10, 100, 1000, 10000,100000 ,1000000};
		
		long startTime, endTime , elapsed ;
		
		for(int i=0;i<0;i++){
		System.out.printf("N = %d\n",N[i]);
			Number a [] = new Number[N[i]];			
		for (int k = 0; k < a.length; k++) a[k] = k;
	//	System.out.printf("Initial%n%s%n",Arrays.toString(a));
		
		Tree <Number> BSTtree = new A2BSTree<>();
		Tree <Number> AVLtree = new A2AVLTree<>();
	
		
		Collections.shuffle(Arrays.asList(a));
	//	System.out.printf("Shuffled%n%s%n",Arrays.toString(a));
		startTime = System.currentTimeMillis();
		TreeSort.sort(BSTtree, a);
		endTime = System.currentTimeMillis();
		elapsed = endTime - startTime;
		System.out.printf("BST%17d ms\n",elapsed);
		 BSTtree = new A2BSTree<>();		
		
		
		
		Collections.shuffle(Arrays.asList(a));
	//	System.out.printf("Shuffled%n%s%n",Arrays.toString(a));
		startTime = System.currentTimeMillis();
		TreeSort.sort(AVLtree, a);
		 endTime = System.currentTimeMillis();
		 elapsed = endTime - startTime;
		System.out.printf("AVL%17d ms\n",elapsed);
	//	System.out.printf("Sorted%n%s%n",Arrays.toString(a));
		AVLtree = new A2AVLTree<>();
		

		
		Collections.reverse(Arrays.asList(a));
	//	System.out.printf("Reversed%n%s%n",Arrays.toString(a));
		startTime = System.currentTimeMillis();
		TreeSort.sort(BSTtree, a);
		 endTime = System.currentTimeMillis();
		 elapsed = endTime - startTime;
		System.out.printf("BST[rev-sorted]%5d ms\n",elapsed);
	//	System.out.printf("Sorted%n%s%n",Arrays.toString(a));
		
	
		
		Collections.reverse(Arrays.asList(a));
//		System.out.printf("Reversed%n%s%n",Arrays.toString(a));
		startTime = System.currentTimeMillis();
		TreeSort.sort(AVLtree, a);
		 endTime = System.currentTimeMillis();
		 elapsed = endTime - startTime;
		System.out.printf("AVL[rev-sorted]%5d ms\n",elapsed);
//		System.out.printf("Sorted%n%s%n",Arrays.toString(a));
		
		System.out.println();
		
		}
		
		A2BSTree <Number> BSTtree = new A2BSTree<>();
		A2AVLTree <Number> AVLtree = new A2AVLTree<>();
		for(int i=0;i<10;i++){
			BSTtree.add(i);//(int) (Math.random()*100));
			AVLtree.add(i);//(int) (Math.random()*100));
			BSTtree.add(i);//(int) (Math.random()*100));
			AVLtree.add(i);//(int) (Math.random()*100));
		}
		AVLtree.print();
		System.out.println("\n");
		BSTtree.print();
	
		BSTtree.remove(3);//(int) (Math.random()*100));
		AVLtree.remove(3);//(int) (Math.random()*100));
		System.out.println("\n");
		System.out.println("\n");
		AVLtree.print();
		System.out.println("\n");
		BSTtree.print();
	}
}
