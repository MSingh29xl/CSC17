package hw2;
import java.io.*;
import java.math.*;
import java.util.*;
import java.util.Map.Entry;
import yahoofinance.*;

// to store the stock name and price
class stockInfo {
	private String name;
	private BigDecimal price;
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public stockInfo(String nameIn, BigDecimal priceIn) {
		name = nameIn; price = priceIn;
	}
	public String toString() {return name + " " + price.toString();}
}

public class myStock {
	/* 
	 * TODO: declare the data structures used for the database HERE.
	 * HINT: use two data structures to store two copies of the stock information.
	 * One for O(1) retrieval, the other is used to get top K stocks in O(K) time.
	 * HashMap<String, stockInfo> and TreeSet<Map.Entry<String, stockInfo>> are recommended.
	 * The entries are not sorted in HashMap, but they are sorted in TreeSet.
	 */
	public HashMap<String, stockInfo> one;
	public TreeSet<Map.Entry<String, stockInfo>> tree;
	
	public class priceCompare implements Comparator<Map.Entry<String, stockInfo>>{
		
		@Override
		public int compare(Entry<String, stockInfo> o1, Entry<String, stockInfo> o2) {
			BigDecimal priceOne = o1.getValue().getPrice();
			BigDecimal priceTwo = o2.getValue().getPrice();
			return priceTwo.compareTo(priceOne);
		}
	}
	
	public myStock() {
		/* 
		 * TODO: implement the constructor to initialize the data structures HERE.
		 * HINT: to let the stocks be sorted by the price in a data structure 
		 * the compare method need to be overridden. 
		 */
		one = new HashMap<String, stockInfo>();
		tree = new TreeSet<Map.Entry<String, stockInfo>>(new priceCompare());
	}
	
	
	public void insertOrUpdate(String symbol, stockInfo stock) {
		/* 
		 * TODO: implement this method to insert or update the records
		 * Make sure it can be done within O(log(n)) time.
		 * Make sure multiple copies are inserted or updated.
		 */
		one.put(symbol, stock);
		tree.add(Map.entry(symbol, stock));
	}

	public stockInfo get(String symbol) {
		/* 
		 * TODO: implement this method to retrieve record from database in O(1) time
		 */
		stockInfo stock = one.get(symbol);
		return stock;
	}

	public List<Map.Entry<String, stockInfo>> top(int k) {
		/* 
		 * TODO: implement this method to return the stock records with top k prices in O(k) time
		 * HINT: use iterator to retrieve items in the sorted order from a data structure
		 * If you use TreeSet, the Iterator can be created like:
		 * TreeSet<Map.Entry<String, stockInfo>> set = new TreeSet<Map.Entry<String, stockInfo>>;
		 * Iterator<Map.Entry<String, stockInfo>> setIterator = set.iterator();
		 * see more info from https://www.geeksforgeeks.org/treeset-iterator-method-in-java/
		 */
		
		List<Map.Entry<String, stockInfo>> high = new ArrayList<Map.Entry<String, stockInfo>>();
		Iterator<Map.Entry<String, stockInfo>> treeIterator = tree.iterator();
		while(k > 0) {
			high.add(Map.entry(treeIterator.next().getKey(), treeIterator.next().getValue()));
			k--;
		}
		
		return high;
	}

	public static void main(String[] args) throws IOException {
		// testing code
		myStock techStock = new myStock();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./US-Tech-Symbols.txt"));
			String line = reader.readLine();
			while (line != null) {
				String[] var = line.split(":");
				
				// YahooFinance API is used and make sure the lib files are included in the project build path
				Stock stock = null;
				try {
					stock = YahooFinance.get(var[0]);
				} catch (IOException e) {
					System.out.println("do nothing and skip the invalid stock");
				}
				
				// test the insertOrUpdate method when initializing the database
				if (stock != null && stock.getQuote().getPrice() != null) {
					techStock.insertOrUpdate(var[0], new stockInfo(var[1], stock.getQuote().getPrice()));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i = 1;
		System.out.println("===========Top 10 stocks===========");

		// test the top method
		for (Map.Entry<String, stockInfo> element : techStock.top(10)) {
			System.out.println("[" + i + "]" + element.getKey() + " " + element.getValue());
			i++;
		} 

		// test the get method
		System.out.println("===========Stock info retrieval===========");
		System.out.println("VMW" + " " + techStock.get("VMW"));
		System.out.println("BIDU" + " " + techStock.get("BIDU"));
		
	}
}
	
