//Alchemy API
import java.util.HashMap;
import java.io.*;
import java.util.Map;

//JSON API
import org.json.JSONArray;
import org.json.JSONObject;

//More Alchemy API
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.CombinedResults;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keywords;
import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;
import com.ibm.watson.developer_cloud.relationship_extraction.v1.RelationshipExtraction;
import com.ibm.watson.developer_cloud.relationship_extraction.v1.model.Dataset;


//Parts of Speech tagger
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class main {
	

	public static void main(String[] args) {
		
		
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("96887abb2b35315e4abe39b894ba91c10c846961");
		Map<String, Object> params = new HashMap<String, Object>();
		String set = "Alex is annoyed constantly by the noise. He dislikes loud interruptions while he works.";
		params.put(AlchemyLanguage.TEXT, set);
		
		//Keyword Analysis
		Keywords yes = service.getKeywords(params);

		//Overall Sentiment (-1 to 1)
		DocumentSentiment sentiment = service.getSentiment(params);

		//FINAL ANALYSIS

		//System.out.print(yes);
		//System.out.println(sentiment);
		
		/*
		PersonalityInsights best = new PersonalityInsights();
		best.setUsernameAndPassword("5a439a6e-558c-4547-ba98-965208fa42f9", "xnWd1m6gIHJe");
		String text = (INSERT TEXT);
		Profile profile = best.getProfile(text);
		System.out.println(profile);
		*/
		
		//Parse keywords to String[]
		JSONObject obj = new JSONObject(yes);
		JSONArray arr = obj.getJSONArray("keywords");
		JSONObject o = null;

		//Keyword relevance array
		double[] a = new double[arr.length()];

		//Keyword array
		String[] s = new String[arr.length()];

		//Keyword + Part of Speech array
		String[] p = new String[arr.length()];
		
		//Parse arrays
		for (int i = 0; i < arr.length(); i++) {
		    o = arr.getJSONObject(i);
			a[i] = o.getDouble("relevance");
			s[i] = o.getString("text");
			String f = s[i];
			MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
			String tagged = tagger.tagString(f);
			p[i] = tagged;
		
		}

		//Print PoS array
		for (int i = 0; i < arr.length(); i++) {
			System.out.println(p[i]);
		}
		

		//Parse sentiment
		JSONObject sent = new JSONObject(sentiment);
		JSONObject senti = sent.getJSONObject("sentiment");
		Double score = senti.getDouble("score");
		
		
		
		//Sample Output
		
		
		System.out.println("Wow, it is clear that you care about " + s[0]);
		if (score < 0) {
			System.out.println("However, you are still being very negative");
		}
		else {
			System.out.println("Thank you for sharing such a positive experience");
		}
		
		
		
		
	}
}
