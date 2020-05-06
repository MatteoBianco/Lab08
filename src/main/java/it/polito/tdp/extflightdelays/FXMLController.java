/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.AirportPair;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	txtResult.clear();
    	String input = distanzaMinima.getText();
    	Integer distanzaMinima = 0;
    	try {
    		distanzaMinima = Integer.parseInt(input);
    	} catch (NumberFormatException e) {
    		txtResult.setText("Errore: inserire il numero di miglia che rappresenti la distanza minima"
    				+ " tra due aeroporti\n ");
    		return;
    	}
    	if(distanzaMinima < 0) {
    		txtResult.setText("Valore errato: impossibile accettare valori in miglia minori di zero\n");
    		return;
    	}
    	Graph<Airport, DefaultWeightedEdge> output = this.model.creaGrafo(distanzaMinima);
    	txtResult.appendText("Il numero di aeroporti presenti nel database (vertici del grafo) è " + output.vertexSet().size() + "\n");
    	txtResult.appendText("Il numero di connessioni disponibili tra aeroporti (archi del grafo) che distano almeno " + distanzaMinima + " miglia è " + output.edgeSet().size() + "\n");
    	for(DefaultWeightedEdge e : output.edgeSet()) {
    		AirportPair ap = new AirportPair(output.getEdgeSource(e), output.getEdgeTarget(e), output.getEdgeWeight(e));
    		txtResult.appendText(ap.toString() + "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
