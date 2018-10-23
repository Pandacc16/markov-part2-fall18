class EfficientMarkov extends BaseMarkov
{
	private Map<String, ArrayList<String>> myMap;
	
	public EfficeintMarkov(int order) {
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
			String fakeKey = text.substring(i, i+3);
			String fakeValue = text.substring(1+3,i+4);
			if (myMap.contains(fakeKey) == false) {
				myMap.put(fakeKey, new ArrayList<String>());
			}
			if (fakeValue == null == false) {
			myMap.get(fakeKey).add(fakeValue);
			}
			else {
				myMap.get(fakeKey).add(PSEUDO_EOS);
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(String key){
		if (myMap.contains(key) == false) {
			throw new NoSuchElementException(key+" not in map");
		}
		
		return myMap.get(key);
			
	}
	
	
	
	
	
	
}
