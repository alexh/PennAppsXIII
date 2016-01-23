/* file "sample_java_client.java" */

/* Copyright 2014, 2015 SoundHound, Incorporated.  All rights reserved. */

import com.Hound.HoundRequester.*;
import com.Hound.SampleHoundDriver.*;

public class SampleJavaClient {
	public static void main(String[] arguments) {
		if ((arguments.length > 5) || (arguments.length < 3)) {
			System.err.format("Usage: java %s <client-id> <client-key> <user-id> "
					+ "{ <text-URL-base> { <voice-URL-base> }? }?\n", SampleJavaClient.class.getName());
			HoundRequester.show_version_information(System.err);
			System.exit(1);
		}

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
		
		HoundCloudRequester requester = new HoundCloudRequester(user_id, user_id, user_id);

		//HoundCloudRequester requester = new HoundCloudRequester(client_id, client_key, user_id, text_request_url_base,
			//	voice_request_url_base);
		try {
			SampleHoundDriver.sample_hound_driver(requester);
		} catch (Exception e1) {
			System.err.format("Error: %s\n", e1.getMessage());
			e1.printStackTrace();
		}
	}
}
