package com.br.ufrpe.uag.ia.stanford;

import edu.stanford.nlp.pipeline.CoreDocument;

public interface IStanfordParser {

	CoreDocument getDocument(String text);

}