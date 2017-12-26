import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListTester {
	
	enum Position {beginning, end, random, byValue};

	public static void main(String[] args) {
		List [] lists = {new ArrayList<Number>(), new A1ArrayList<Number>(), new LinkedList<Number>(), new A1LinkedList<Number>()};
		for (int N = 10_000; N <= 10_000; N *= 10){
			 System.out.printf("N = %8d: ms to Ins. start, end, rnd; Rmv. start, end, rnd; Rmv. by value%n", N);
			 for (List <Number> currentList : lists){
				 System.out.printf("%-22s", currentList.getClass().getSimpleName());
				 System.out.printf("%6d", listAdd(N, currentList, Position.beginning));
				 System.out.printf("%6d", listAdd(N, currentList, Position.end));
				 System.out.printf("%6d", listAdd(N, currentList, Position.random));
				 System.out.printf("%10d", listRemove(N, currentList, Position.beginning));
				 System.out.printf("%6d", listRemove(N, currentList, Position.end));
				 System.out.printf("%6d", listRemove(N, currentList, Position.random));
				 if (N <100_000)
					 System.out.printf("%12d", listRemove(N, currentList, Position.byValue));
				 System.out.println();
			 }

			 long benchmark = listAdd(N, lists[0], Position.end)+1;
			 final int TOLERANCE = 5;
			 System.out.println("benchmark value: " + benchmark + " ms");
			 System.out.println();

			 for (List <Number> currentList : lists){
				 //System.out.printf("%s: Testing add()%n", currentList.getClass().getSimpleName());
				 if (listAdd(N, currentList, Position.end) > benchmark * TOLERANCE)
					 System.out.printf("%s: add() too slow%n", currentList.getClass().getSimpleName());
				 if (currentList.size() != N) 
					 System.out.printf("%s: add() FAIL%n", currentList.getClass().getSimpleName());
				 currentList.clear(); currentList.add(524679692); currentList.add(524239692);
				 if ((Integer)currentList.remove(1) != 524239692) 
					 System.out.printf("%s: add() or remove by index FAIL%n", currentList.getClass().getSimpleName());

				 //System.out.printf("%s: Testing add(index)%n", currentList.getClass().getSimpleName());
				 if (listAdd(N, currentList, Position.beginning) > benchmark * TOLERANCE &&
						 currentList.getClass().getSimpleName().equals("A1LinkedList"))
					 System.out.printf("%s: add(beginning) too slow%n", currentList.getClass().getSimpleName());
				 if (currentList.size() != N) 
					 System.out.printf("%s: add() FAIL%n", currentList.getClass().getSimpleName());
				 
				 //System.out.printf("%s: Testing remove(index.end)%n", currentList.getClass().getSimpleName());
				 if (listRemove(N, currentList, Position.end) > benchmark * TOLERANCE )
					 System.out.printf("%s: remove(end) too slow%n", currentList.getClass().getSimpleName());
				 if (currentList.size() != 0) 
					 System.out.printf("%s: remove(index) FAIL%n", currentList.getClass().getSimpleName());
				 
				 //System.out.printf("%s: Testing remove(index.beginning)%n", currentList.getClass().getSimpleName());
				 if (listRemove(N, currentList, Position.beginning) > benchmark * TOLERANCE &&
						 currentList.getClass().getSimpleName().equals("A1LinkedList"))
					 System.out.printf("%s: remove(beginning) too slow%n", currentList.getClass().getSimpleName());
				 if (currentList.size() != 0) 
					 System.out.printf("%s: remove(index) FAIL%n", currentList.getClass().getSimpleName());
				 
				 //System.out.printf("%s: Testing remove(randomValue)%n", currentList.getClass().getSimpleName());
				 listRemove(N, currentList, Position.byValue);
				 if (currentList.size() < N * 85 / 100 || currentList.size() > N * 95 / 100) 
					 System.out.printf("%s: remove(randomValue) FAIL: list size = %d (Expected %d..%d)%n", 
							 currentList.getClass().getSimpleName(), currentList.size(), N * 85 / 100, N * 95 / 100);
				 
				 currentList.clear();
				 for (int i = 1; i <=10; i++) currentList.add(i);
				 System.out.printf("%-12s.toString(): %s%n", currentList.getClass().getSimpleName(), currentList);
			 }
			 System.out.printf("Tests Done%n");
		}
	}
	
	public static <E> long listAdd (int N, List<E> list, Position pos)
	{
		Random rnd = new Random();
		int insertIndex = 0;
		list.clear();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < N; i++)
		{	
			if (pos == Position.end) list.add((E) new Integer (rnd.nextInt(10*N)));
			else{
				if (pos == Position.random) 
					insertIndex = list.size() > 0 ? rnd.nextInt(list.size() + 1) : 0;
					//default is 0 (beginning)
				list.add(insertIndex, (E) new Integer (rnd.nextInt(10*N)));
			}
		}
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;
		
		//for Debugging student's submissions
		//System.out.println(list + "("+list.size()+")");
		return elapsed;
	}

	public static <E> long listRemove (int N, List<E> list, Position position)
	{
		Random rnd = new Random();
		int removeIndex = 0;
		list.clear();
		listAdd(N, list, Position.end); //create a list of N items, end is the fastest

		long startTime = System.currentTimeMillis();
		for(int i = 0; i < N; i++)
		{	
			if (position != Position.byValue){
				if (position == Position.end) removeIndex = list.size() - 1;
				if (position == Position.random) removeIndex = rnd.nextInt(list.size());
				list.remove(removeIndex);
			}else
				list.remove((E) new Integer (rnd.nextInt(10*N)));
		}
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;
		
		//for Debugging student's submissions
		//System.out.printf("(%4d)", l.size());
		return elapsed;
	}

}
