package com.iist.assignment1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

@RestController
public class LoadAndQueryDBLPHdt {
	static Model model;
	
	static List<String> authorsList;
	
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
		
		authorsList = new ArrayList();
		
	}

	@PreDestroy
	public void close()
	{
		if(model != null && !model.isClosed())
			model.close();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/authors/get")
	public String getAuthors()
	{
		
		String response = "";
		List<String> authorsList = new ArrayList();
		Query query = QueryFactory.create("prefix foaf: <http://xmlns.com/foaf/0.1/> prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT DISTINCT ?author ?name " + 
				"WHERE { ?author a foaf:Agent. " + 
				"?author rdfs:label ?name} " + 
				"ORDER BY ?author");
		
		    
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		
		response = ResultSetFormatter.asXMLString(qe.execSelect());
		qe.close();
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/load")
	public int queryData(@RequestParam("fauth") String firstAuthor, @RequestParam("sauth") String secondAuthor)
	{
		if(firstAuthor != null)
			System.out.println(firstAuthor);
		if(secondAuthor != null)
			System.out.println(secondAuthor);
		int counter = 0;		
		if(model != null)
		{
			String query1 = "prefix foaf: <http://xmlns.com/foaf/0.1/> prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
					+ "prefix dc: <http://purl.org/dc/elements/1.1/> SELECT DISTINCT ?s WHERE {" + 
					"?author1 rdfs:label \"" + firstAuthor + "\"." +  
					"?author2 rdfs:label \"" + secondAuthor + "\"." + 
					"?s dc:creator|foaf:maker ?author1." + 
					"?s dc:creator|foaf:maker ?author2" + 
					"}";
			System.out.println("query "+ query1);
			Query query = QueryFactory.create(query1);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			
			ResultSet rs= qe.execSelect();
			while(rs.hasNext())
			{
				rs.next();
				counter+=1;
			}
			
			qe.close();
		}
		return counter;
	}
}
