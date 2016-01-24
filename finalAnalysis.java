//finalAnalysis.java
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

//
public class FinalAnalysis {
	String[] traits;
	double[] traits_score;
	String[] needs;
	double[] needs_score;
	
	
	public String[] getTraits() {
		return traits;
	}
	public void setTraits(String[] traits) {
		this.traits = traits;
	}
	public double[] getTraits_score() {
		return traits_score;
	}
	public void setTraits_score(double[] traits_score) {
		this.traits_score = traits_score;
	}
	public String[] getNeeds() {
		return needs;
	}
	public void setNeeds(String[] needs) {
		this.needs = needs;
	}
	public double[] getNeeds_score() {
		return needs_score;
	}
	public void setNeeds_score(double[] needs_score) {
		this.needs_score = needs_score;
	}
	public FinalAnalysis(String arg) {
		
		// The name of the file to open.
        String fileName = arg;
        String line = null;
        String fixed = "";
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	fixed += line;
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        PersonalityInsights best = new PersonalityInsights();
		best.setUsernameAndPassword("5a439a6e-558c-4547-ba98-965208fa42f9", "xnWd1m6gIHJe");
		String text = (fixed);
		if (text != "" && text != null){
		Profile profile = best.getProfile(text);
		
		JSONObject full = new JSONObject(profile);
		JSONObject tree = full.getJSONObject("tree");
		
		JSONArray childa = tree.getJSONArray("children");
		
		JSONObject childaa = childa.getJSONObject(0);
		JSONArray childb = childaa.getJSONArray("children");
		
		JSONObject childbb = childb.getJSONObject(0);
		JSONArray childc = childbb.getJSONArray("children");
		
		//System.out.println(childb);

		String[] analysis = new String[childc.length()];
		double[] anscore = new double[childc.length()];
		for (int i = 0; i < childc.length(); i++) {
			JSONObject name = childc.getJSONObject(i);
			JSONObject percent = childc.getJSONObject(i);
			analysis[i] = name.getString("name");
			anscore[i] = percent.getDouble("percentage");
		}
		
		JSONObject childnn = childa.getJSONObject(1);
		JSONArray childn = childnn.getJSONArray("children");
		
		JSONObject childmm = childn.getJSONObject(0);
		JSONArray childm = childmm.getJSONArray("children");
		
		String[] need = new String[childm.length()];
		double[] nescore = new double[childm.length()];
		for (int i = 0; i < childm.length(); i++) {
			JSONObject name = childm.getJSONObject(i);
			JSONObject percent = childm.getJSONObject(i);
			need[i] = name.getString("name");
			nescore[i] = percent.getDouble("percentage");
		}
		
		String[] topTraits = new String[3];
		double[] topScore = new double[3];
		int place1 = 0;
		int place2 = 0;
		int place3 = 0;
		for (int i = 0; i < anscore.length; i++) {
			if (anscore[i] > topScore[0]) {
				topScore[2] = topScore[1];
				place3 = place2;
				topScore[1] = topScore[0];
				place2 = place1;
				topScore[0] = anscore[i];
				place1 = i;
				
			}
			else if (anscore[i] > topScore[1]) {
				topScore[2] = topScore[1];
				place3 = place2;
				topScore[1] = anscore[i];
				place2 = i;
			}
			else if (anscore[i] > topScore[2]) {
				topScore[2] = anscore[i];
				place3 = i;
			}
		}
		topTraits[0] = analysis[place1];
		topTraits[1] = analysis[place2];
		topTraits[2] = analysis[place3];
		
		String[] topNeeds = new String[3];
		double[] highScore = new double[3];
		for (int i = 0; i < nescore.length; i++) {
			if (nescore[i] > highScore[0]) {
				highScore[2] = highScore[1];
				place3 = place2;
				highScore[1] = highScore[0];
				place2 = place1;
				highScore[0] = nescore[i];
				place1 = i;
			}
			else if (nescore[i] > highScore[1]) {
				highScore[2] = highScore[1];
				place3 = place2;
				highScore[1] = nescore[i];
				place2 = i;
			}
			else if (nescore[i] > highScore[2]) {
				highScore[2] = nescore[i];
				place3 = i;
			}
		}
		
		topNeeds[0] = need[place1];
		topNeeds[1] = need[place2];
		topNeeds[2] = need[place3];
		
		setTraits(topNeeds);
		setTraits_score(topScore);
		
		setNeeds(topNeeds);
		setNeeds_score(highScore);
		
		}
		
		
	}

}