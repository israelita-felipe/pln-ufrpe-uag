package com.br.ufrpe.uag.ia.stanford;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

//@Service
public class StanfordParser implements IStanfordParser {
	private Properties props;
	private StanfordCoreNLP pipeline;

	@PostConstruct
	private void init() {
		// set up pipeline properties
		props = new Properties();
		// set the list of annotators to run
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
		props.setProperty("tokenize.language", "en");
		// set a property for an annotator, in this case the coref annotator is being
		// set to use the neural algorithm
		props.setProperty("coref.algorithm", "neural");
		// build pipeline
		pipeline = new StanfordCoreNLP(props);
	}
	
	/* (non-Javadoc)
	 * @see com.br.ufrpe.uag.ia.stanford.IStanfordParser#getDocument(java.lang.String)
	 */
	@Override
	public CoreDocument getDocument(final String text) {
		// create a document object
	    CoreDocument document = new CoreDocument(text);
	    // annnotate the document
	    pipeline.annotate(document);
	    return document;
	}
}
