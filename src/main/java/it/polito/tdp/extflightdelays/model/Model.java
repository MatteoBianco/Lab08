package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph<Airport, DefaultWeightedEdge> grafo;
	Map<Integer, Airport> idMapAirports;
	private Map<Integer, Flight> idMapFlights;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		
		idMapAirports = new HashMap<>();
		idMapFlights = new HashMap<>();
		dao = new ExtFlightDelaysDAO();
		dao.loadAllAirports(idMapAirports);
		dao.loadAllFlights(idMapFlights);
		
	}
	
	public Graph creaGrafo(int distanzaMinima) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, idMapAirports.values());	
		for(AirportPair ap : dao.loadPairs(idMapAirports)) {
			DefaultWeightedEdge e = grafo.getEdge(ap.getOrigin(), ap.getDestination());
			if (e == null) {
				
				Graphs.addEdge(grafo, ap.getOrigin(), 
						ap.getDestination(), ap.getDistance());
			}
			else {
				double avg = this.avg(ap.getDistance(), grafo.getEdgeWeight(e));
				this.grafo.setEdgeWeight(e, avg);
			}
		}
		Set<DefaultWeightedEdge> daRimuovere = new HashSet<>();
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(grafo.getEdgeWeight(e) < distanzaMinima)
				daRimuovere.add(e);
		}
		grafo.removeAllEdges(daRimuovere);
		
		return grafo;
		
	}
	
	private double avg(double i1, double i2) {
		return ((i1+i2)/2);
	}
}

