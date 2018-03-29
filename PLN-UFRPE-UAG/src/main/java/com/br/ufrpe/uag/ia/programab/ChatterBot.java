package com.br.ufrpe.uag.ia.programab;

import javax.annotation.PostConstruct;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;
import org.springframework.stereotype.Service;

@Service
public class ChatterBot implements IChatterBot {

	private Chat chat;

	@PostConstruct
	private void init() {
		this.chat = new Chat(new Bot("NLP", System.getProperty("user.dir")));
		MagicBooleans.trace_mode = true;
	}

	/* (non-Javadoc)
	 * @see com.br.ufrpe.uag.ia.programab.IChatterBot#respond(java.lang.String)
	 */
	@Override
	public String respond(final String sentence) {
		return this.chat.multisentenceRespond(sentence);
	}
}
