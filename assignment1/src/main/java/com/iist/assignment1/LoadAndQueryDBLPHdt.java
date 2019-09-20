package com.iist.assignment1;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.rdfhdt.hdt.hdt.HDT;
import org.rdfhdt.hdt.hdt.HDTManager;
import org.rdfhdt.hdtjena.HDTGraph;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

@RestController
public class LoadAndQueryDBLPHdt {
	static Model model;
	
	static
	{
		HDT hdt;
		try {
			hdt = HDTManager.mapIndexedHDT("/Users/santhoshiyama/Downloads/dblp-20170124.hdt", null);
			// Create Jena Model on top of HDT.
			HDTGraph graph = new HDTGraph(hdt, true);
			model = ModelFactory.createModelForGraph(graph);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void close()
	{
		if(model != null && !model.isClosed())
			model.close();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/load")
	public String queryData(@RequestParam("fauth") String firstAuthor, @RequestParam("sauth") String secondAuthor)
	{
		if(firstAuthor != null)
			System.out.println(firstAuthor);
		if(secondAuthor != null)
			System.out.println(secondAuthor);
		String response = "";
		if(model != null)
		{
			Query query = QueryFactory.create("SELECT * WHERE {?s ?p ?o} LIMIT 5");
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			
			response = ResultSetFormatter.asXMLString(qe.execSelect());
			qe.close();
		}
		return response;
	}
}
