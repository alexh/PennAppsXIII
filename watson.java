import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.CombinedResults;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

import java.io.*;

public class main {
	public static void main(String[] args) {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("96887abb2b35315e4abe39b894ba91c10c846961");

		Map<String, Object> params = new HashMap<String, Object>();
		
		

		CombinedResults yes = service.getCombinedResults(params);
		DocumentSentiment sentiment = service.getSentiment(params);
		System.out.print(yes);

		System.out.println(sentiment);
	}

}

