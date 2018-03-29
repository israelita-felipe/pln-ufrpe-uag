package com.br.ufrpe.uag.ia;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.ufrpe.uag.ia.stanford.IStanfordParser;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreQuote;
import edu.stanford.nlp.pipeline.CoreSentence;

/**
 * 
 * @author Israel Araújo 
 * @see Stanford CoreNLP API {@link https://stanfordnlp.github.io/CoreNLP/api.html}
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlnUfrpeUagApplicationTests {

	private @Autowired IStanfordParser stanfordParser;

	private static final String line = "____________________________________";

	// @formatter:off
	private static String text = "Joe Smith was born in California. "
			+ "In 2017, he went to Paris, France in the summer. " + "His flight left at 3:00pm on July 10th, 2017. "
			+ "After eating some escargot for the first time, Joe said, \"That was delicious!\" "
			+ "He sent a postcard to his sister Jane Smith. "
			+ "After hearing about Joe's trip, Jane decided she might go to France one day.";
	// @formatter:on

	@Test
	public void posTagger() {
		System.out.println("POS: ");
		for (CoreSentence cs : stanfordParser.getDocument(text).sentences()) {
			System.out.println(cs.text());
			System.out.println(cs.posTags());
			System.out.println(line);
		}
	}

	@Test
	public void nerTagger() {
		System.out.println("NER: ");
		for (CoreSentence cs : stanfordParser.getDocument(text).sentences()) {
			System.out.println(cs.text());
			System.out.println(cs.nerTags());
			System.out.println(line);
		}
	}

	@Test
	public void constituencyParser() {
		System.out.println("TREE: ");
		for (CoreSentence cs : stanfordParser.getDocument(text).sentences()) {
			System.out.println(cs.text());
			System.out.println(cs.constituencyParse().pennString());
			System.out.println(line);
		}
	}

	@Test
	public void dependencyParser() {
		System.out.println("DEPENDENCY: ");
		for (CoreSentence cs : stanfordParser.getDocument(text).sentences()) {
			System.out.println(cs.text());
			System.out.println(cs.dependencyParse());
			System.out.println(line);
		}
	}

	@Test
	public void relations() {
		System.out.println("RELATIONS: ");
		for (CoreSentence cs : stanfordParser.getDocument(text).sentences()) {
			System.out.println(cs.text());
			for (RelationTriple rt : cs.relations()) {
				System.out.println(rt);
			}
			System.out.println(line);
		}
	}

	@Test
	public void mentions() {
		System.out.println("MENTIONS: ");
		for (CoreSentence cs : stanfordParser.getDocument(text).sentences()) {
			System.out.println(cs.text());
			for (CoreEntityMention cem : cs.entityMentions()) {
				System.out.println(cem);
			}
			System.out.println(line);
		}
	}

	@Test
	public void coref() {
		System.out.println("COREF: ");
		Map<Integer, CorefChain> corefChains = stanfordParser.getDocument(text).corefChains();
		for (Integer key : corefChains.keySet()) {
			System.out.println(corefChains.get(key));
			System.out.println(line);
		}
	}

	@Test
	public void quote() {
		System.out.println("QUOTE: ");
		for (CoreQuote cq : stanfordParser.getDocument(text).quotes()) {
			System.out.println(cq);
			System.out.println("speaker: " + cq.speaker().get());
			System.out.println("canonical speaker: " + cq.canonicalSpeaker().get());
			System.out.println(line);
		}
	}

}
