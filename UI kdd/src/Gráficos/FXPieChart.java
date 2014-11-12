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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

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

        final JFXPanel fxPanel = new JFXPanel();
        JFXPanel PanelVisualizador = Visualizador.panel1;
        PanelVisualizador = fxPanel;
        PanelVisualizador.setVisible(true);
        Visualizador.panelPestanas.addTab("Pie Chart", PanelVisualizador);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel, chartName, tags, values);
            }
        });
    }

    private static void initFX(JFXPanel fxPanel, String chartName, ArrayList<String> tags, ArrayList<Integer> values) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene(chartName, tags, values);
        fxPanel.setScene(scene);
    }

    private static Scene createScene(String chartName, ArrayList<String> tags, ArrayList<Integer> values) {
        Group root = new Group();
        Scene scene = new Scene(root);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < tags.size(); i++) {
            pieChartData.add(new PieChart.Data(tags.get(i), values.get(i)));
        }

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle(chartName);

        ((Group) scene.getRoot()).getChildren().add(chart);

        return (scene);
    }

}
