package it.polito.tdp.extflightdelays.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Graph<Airport, DefaultWeightedEdge> grafo = model.creaGrafo(900);
		System.out.println(grafo.vertexSet() + "\n");
		System.out.println(grafo.vertexSet().size() + "\n");
		System.out.println(grafo.edgeSet() + "\n");
		System.out.println(grafo.edgeSet().size() + "\n");
		System.out.println(grafo.getEdge(model.idMapAirports.get(149), model.idMapAirports.get(228)));
		System.out.println(grafo.getEdge(model.idMapAirports.get(228), model.idMapAirports.get(149)));
		System.out.println(grafo.getEdgeWeight(grafo.getEdge(model.idMapAirports.get(149), model.idMapAirports.get(228))));
		System.out.println(grafo.getEdgeWeight(grafo.getEdge(model.idMapAirports.get(228), model.idMapAirports.get(149))));
		
	}
		
}
