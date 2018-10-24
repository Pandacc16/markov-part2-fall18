import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov
{
	private Map<String, ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	
	public EfficientMarkov() {
		this(3);
	}
	
	@Override 
	public void setTraining(String text) {
		myMap.clear();
		myText = text;
		
		for (int i=0; i < myText.length()- myOrder + 1; i ++) {
			String fakeKey = myText.substring(i, i+myOrder);
			String fakeValue = myText.substring(1+myOrder,i+myOrder + 1);
			if (myMap.containsKey(fakeKey) == false) {
				myMap.put(fakeKey, new ArrayList<String>());
			}
			if (i == myText.length() - myOrder) {
				myMap.get(fakeKey).add(PSEUDO_EOS);
			}
			else {
				myMap.get(fakeKey).add(myText.substring(i + myOrder, i + myOrder+1));
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(String key){
		if (myMap.containsKey(key) == false) {
			throw new NoSuchElementException(key+" not in map");
		}
		
		return myMap.get(key);
			
	}
	
	
	
	
	
	
}
