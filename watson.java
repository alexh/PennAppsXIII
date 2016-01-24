import java.util.HashMap;


import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keywords;


import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class main {
	String[] keyword;
	double[] importance;
	double value;
	
	
	public String[] getKeyword() {
		return keyword;
	}




	public void setKeyword(String[] keyword) {
		this.keyword = keyword;
	}




	public double[] getImportance() {
		return importance;
	}




	public void setImportance(double[] importance) {
		this.importance = importance;
	}




	public double getValue() {
		return value;
	}




	public void setValue(double value) {
		this.value = value;
	}




	public main(String input) {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("96887abb2b35315e4abe39b894ba91c10c846961");
		Map<String, Object> params = new HashMap<String, Object>();
		String set = input;
		params.put(AlchemyLanguage.TEXT, set);
		
		Keywords yes = service.getKeywords(params);
		DocumentSentiment sentiment = service.getSentiment(params);
		
		JSONObject obj = new JSONObject(yes);
		JSONArray arr = obj.getJSONArray("keywords");
		JSONObject o = null;
		
		JSONObject sent = new JSONObject(sentiment);
		JSONObject senti = sent.getJSONObject("sentiment");
		Double score = senti.getDouble("score");
		
		double[] a = new double[arr.length()];
		String[] s = new String[arr.length()];
		String[] p = new String[arr.length()];
		
		for (int i = 0; i < arr.length(); i++) {
		    o = arr.getJSONObject(i);
			a[i] = o.getDouble("relevance");
			s[i] = o.getString("text");
			String f = s[i];
			MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
			String tagged = tagger.tagString(f);
			p[i] = tagged;
		
		}
		setKeyword(p);
		setImportance(a);
		setValue(score);
		
		
	}
	public static void main(String[] args) {
		main sick = new main("Fuck the police");
		
	}
}
