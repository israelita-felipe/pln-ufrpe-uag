package com.br.ufrpe.uag.ia;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.ufrpe.uag.ia.programab.IChatterBot;

/**
 * 
 * @author Israel Araújo
 * @see Documentação AIML {@link https://pandorabots.com/docs/} 
 * 		Program AB Original {@link https://code.google.com/archive/p/program-ab/}
 *      LumenRobot {@link https://github.com/lumenrobot/program-ab}
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlnUfrpeUagChatterBotTests {

	private @Autowired IChatterBot bot;

	private static final String line = "____________________________________";

	@Test
	public void dialogTest() {
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.print("Você: ");
			String input = s.nextLine();
			if (input.equalsIgnoreCase("sair")) {
				s.close();
				return;
			}
			System.out.println("BOT: " + bot.respond(input) + "\n" + line);
		}
	}

}
