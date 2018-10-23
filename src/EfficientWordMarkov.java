import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	
	public EfficientWordMarkov() {
		this(2);
	}
	
	
	@Override
	public void setTraining(String text) {
		myMap.clear();
		myWords = text.split("\\s+");
		
		for (int i=0; i < myWords.length- myOrder + 1; i ++) {
			WordGram fakeKey = new WordGram(myWords, i, myOrder);
			if (! myMap.containsKey(fakeKey)) {
				myMap.put(fakeKey, new ArrayList<String>());
			}
			if (i == myWords.length - myOrder) {
				myMap.get(fakeKey).add(PSEUDO_EOS);
			}
			else {
				myMap.get(fakeKey).add(myWords[i+myOrder]);
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key){
		if (myMap.containsKey(key) == false) {
			throw new NoSuchElementException(key+" not in map");
		}
		
		return myMap.get(key);
			
	}
}
