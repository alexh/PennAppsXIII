import com.Hound.HoundJSON.HoundPartialTranscriptJSON;
import com.Hound.HoundRequester.HoundRequester.PartialHandler;

public class PartialHandlerImpl extends PartialHandler {
	
	private String transcript;
	private boolean safeToStop;

	@Override
	public void handle(HoundPartialTranscriptJSON trans) {
		transcript = trans.getPartialTranscript();
		safeToStop = trans.getSafeToStopAudio();
		
	}
	
	public String getTranscript(){
		return transcript;
	}
	
	public boolean getSafeToStop(){
	return safeToStop;
	}

}
