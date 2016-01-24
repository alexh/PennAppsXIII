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
		
		setTraits(analysis);
		setTraits_score(anscore);
		
		setNeeds(need);
		setNeeds_score(nescore);
		
		
		
		
	}
	public static void main(String[] args) {
		
		//FinalAnalysis test = new FinalAnalysis("readit.txt");
		//System.out.println(test.traits[0]);
		
		
	}

}