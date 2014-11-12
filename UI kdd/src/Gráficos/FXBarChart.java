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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Juan Olaya O
 */
public class FXBarChart {
    final String chartName, valuesName, tagName;
    final ArrayList<String> tags;
    final ArrayList<Integer> values;

    public FXBarChart(final String chartName, final String tagName, final ArrayList<String> tags, final String valuesName, final ArrayList<Integer> values) {

        this.tags = tags;
        this.values = values;
        this.chartName = chartName;
        this.valuesName = valuesName;
        this.tagName = tagName;

        final JFXPanel fxPanel = new JFXPanel();
        JFXPanel PanelVisualizador = Visualizador.panel1;
        PanelVisualizador = fxPanel;
        PanelVisualizador.setVisible(true);
        Visualizador.panelPestanas.addTab("Bar Chart", PanelVisualizador);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel, chartName, tags, tagName, values, valuesName);
            }
        });
    }

    private static void initFX(JFXPanel fxPanel, String chartName, ArrayList<String> tags, String tagName, ArrayList<Integer> values, String valuesName) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene(chartName, tags, tagName, values, valuesName);
        fxPanel.setScene(scene);
    }

    private static Scene createScene(String chartName, ArrayList<String> tags, String tagName, ArrayList<Integer> values, String valuesName) {
        Group root = new Group();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc
                = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle(chartName);
        xAxis.setLabel(tagName);
        yAxis.setLabel(valuesName);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Abandonos");
        for (int i = 0; i < tags.size(); i++) {
            series1.getData().add(new XYChart.Data(tags.get(i), values.get(i)));
        }
        Scene scene = new Scene(bc);
        bc.getData().addAll(series1);

        return (scene);
    }
}
