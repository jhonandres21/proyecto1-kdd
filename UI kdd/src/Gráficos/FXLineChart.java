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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 *
 * @author Juan Olaya O
 */
public class FXLineChart {

    final String chartName, valuesName, tagName, legend;
    final ArrayList<String> tags;
    final ArrayList<Integer> values;
    static ObservableList<XYChart.Data> lineChartData;

    public FXLineChart(final String chartName, final String tagName, final ArrayList<String> tags, final String valuesName, final ArrayList<Integer> values, final String legend) {

        this.tags = tags;
        this.values = values;
        this.chartName = chartName;
        this.valuesName = valuesName;
        this.tagName = tagName;
        this.legend = legend;

        final JFXPanel fxPanel = new JFXPanel();
        Visualizador.panelPestanas.addTab("Line Chart", fxPanel);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel, chartName, tags, tagName, values, valuesName, legend);
            }
        });
    }

    private static void initFX(JFXPanel fxPanel, String chartName, ArrayList<String> tags, String tagName, ArrayList<Integer> values, String valuesName, String legend) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene(chartName, tags, tagName, values, valuesName, legend);
        fxPanel.setScene(scene);
    }

    private static Scene createScene(String chartName, ArrayList<String> tags, String tagName, ArrayList<Integer> values, String valuesName, String legend) {
        Group root = new Group();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lc = new LineChart(xAxis, yAxis);
        lc.setTitle(chartName);
        lc.setAnimated(true);
        xAxis.setLabel(tagName);
        yAxis.setLabel(valuesName);

        lineChartData = FXCollections.observableArrayList();
        for (int i = 0; i < tags.size(); i++) {
            lineChartData.add(new XYChart.Data(tags.get(i), values.get(i)));
        }

        XYChart.Series lineChartSeries = new XYChart.Series(lineChartData);
        lineChartSeries.setName(legend);
        Scene scene = new Scene(lc);
        lc.getData().addAll(lineChartSeries);

        return (scene);
    }

    //adds new Data to the list
    public void naiveAddData(String name, int value) {
        lineChartData.add(new XYChart.Data(name, value));
    }

    //updates existing Data-Object if name matches
    public void addData(ArrayList<String> addTags, final ArrayList<Integer> addValues) {
        for (int i = 0; i < addTags.size(); i++) {
            for (Data d : lineChartData) {
                if (d.getXValue().equals(addTags.get(i))) {
                    d.setYValue(addValues.get(i));
                    return;
                }
            }
            naiveAddData(addTags.get(i), addValues.get(i));
        }
    }
}
