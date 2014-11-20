/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gráficos;

import GUI.Visualizador;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Juan Olaya O
 */
public class FXPieChart {

    final String chartName;
    final ArrayList<String> tags;
    final ArrayList<Integer> values;

    public FXPieChart(final String chartName, final ArrayList<String> tags, final ArrayList<Integer> values) {

        this.tags = tags;
        this.values = values;
        this.chartName = chartName;

        //final JFXPanel fxPanel = new JFXPanel();
        //JFXPanel PanelVisualizador = fxPanel;
        JFXPanel PanelVisualizador = initFX(/*fxPanel, */chartName, tags, values);
        PanelVisualizador.setVisible(true);
        Visualizador.panelPestanas.removeAll();
        Visualizador.panelPestanas.addTab("Pie Chart", PanelVisualizador);
        System.out.println("Se prepara PieChart");
        /*Platform.runLater(new Runnable() {
            @Override
            public void run() {
                
            }
        });*/
    }

    private JFXPanel initFX(/*JFXPanel fxPanel, */String chartName, ArrayList<String> tags, ArrayList<Integer> values) {
        // This method is invoked on the JavaFX thread
        JFXPanel fxPanel = new JFXPanel();
        System.out.println("Se inicia PieChart");
        Scene scene = createScene(chartName, tags, values);
        fxPanel.setScene(scene);
        return fxPanel;
    }

    private Scene createScene(String chartName, ArrayList<String> tags, ArrayList<Integer> values) {
        System.out.println("Se dibuja PieChart");
        Group root = new Group();
        Scene scene = new Scene(root);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < tags.size(); i++) {
            pieChartData.add(new PieChart.Data(tags.get(i), values.get(i)));
        }

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle(chartName);

        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()));
                        }
                    });
        }

        ((Group) scene.getRoot()).getChildren().add(chart);
        ((Group) scene.getRoot()).getChildren().add(caption);

        return (scene);
    }

}
