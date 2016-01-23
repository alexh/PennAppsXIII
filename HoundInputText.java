
	import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import com.Hound.HoundJSON.CommandResultJSON;
import com.Hound.HoundJSON.ConversationStateJSON;
import com.Hound.HoundJSON.HoundServerJSON;
import com.Hound.HoundJSON.RequestInfoJSON;
import com.Hound.HoundRequester.*;
import com.Hound.HoundRequester.HoundRequester.PartialHandler;
import com.Hound.HoundRequester.HoundRequester.VoiceRequest;
import com.Hound.SampleHoundDriver.*;
	
public class HoundInputText {


		public static String doInputQuestion(){
			

			String client_id = "qWlZIExLdNX8ZZ3fNnETRA==";
			String client_key = "ejLuAGykuBqu0WxWpYjVeycfmALfdB3IiBV14x9leF9BSBBvt_X0bC6uSu6oEu28XVZrWI2YJc2ml8HqCC979Q==";
			String user_id = "alex";
			/*
			 * String text_request_url_base = ((arguments.length >= 4) ?
			 * arguments[3] : HoundCloudRequester.default_text_request_url_base());
			 * String voice_request_url_base = ((arguments.length >= 5) ?
			 * arguments[4] : HoundCloudRequester.default_voice_request_url_base());
			 */
			if (client_key.length() != 88) {
				System.err.format("Error: The client key must be 88 characters long, but it" + " is %d characters long.\n",
						client_key.length());
				System.exit(1);
			}
			
			HoundCloudRequester requester = new HoundCloudRequester(client_id, client_key, user_id);
			ConversationStateJSON state = new ConversationStateJSON();
			RequestInfoJSON reqInfo = new RequestInfoJSON();
			PartialHandlerImpl partial_handler = new PartialHandlerImpl();
			VoiceRequest request;
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			BufferedInputStream in;
			String transcription = null;
			try {
				request = requester.start_voice_request(state, reqInfo, partial_handler);
				File file = new File("input.wav");
				InputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[(int)file.length()];
				fis.read(buffer, 0, buffer.length);
				fis.close();
				request.add_audio(buffer.length, buffer);
				HoundServerJSON done = request.finish();
				Vector<CommandResultJSON> results = done.getAllResults();
				transcription = results.get(0).getWrittenResponseLong();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return transcription;

		}
}

