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
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.swing.JPanel;

/**
 *
 * @author Juan Olaya O
 */
public class FXPieChart extends JPanel{

    final String chartName;
    final ArrayList<String> tags;
    final ArrayList<Integer> values;
    static ObservableList<PieChart.Data> pieChartData;
    static PieChart chart;
    static JFXPanel fxPanel;

    public FXPieChart(final String chartName, final ArrayList<String> tags, final ArrayList<Integer> values) {

        this.tags = tags;
        this.values = values;
        this.chartName = chartName;

        fxPanel = new JFXPanel();
        add(fxPanel);
        //Visualizador.jTabbedPane1.removeAll();
        //Visualizador.jTabbedPane1.addTab("Pie Chart", fxPanel);
        System.out.println("Se prepara PieChart");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(chartName, tags, values);

            }
        });
    }

    private static void initFX(String chartName, ArrayList<String> tags, ArrayList<Integer> values) {
        // This method is invoked on the JavaFX thread
        System.out.println("Se inicia PieChart");
        Scene scene = createScene(chartName, tags, values);
        fxPanel.setScene(scene);
    }

    private static Scene createScene(String chartName, ArrayList<String> tags, ArrayList<Integer> values) {
        System.out.println("Se dibuja PieChart");
        Group root = new Group();
        Scene scene = new Scene(root);

        pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < tags.size(); i++) {
            pieChartData.add(new PieChart.Data(tags.get(i), values.get(i)));
        }
        String name = chartName;
        chart = new PieChart(pieChartData);
        chart.setTitle(name);
        chart.setLegendVisible(false);

        Label caption = new Label("");
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

    //adds new Data to the list
    public void naiveAddData(String name, int value) {
        pieChartData.add(new PieChart.Data(name, value));
    }

    //updates existing Data-Object if name matches
    public void addData(ArrayList<String> addTags, final ArrayList<Integer> addValues) {

        for (int i = 0; i < addTags.size(); i++) {
            for (Data d : pieChartData) {
                if (d.getName().equals(addTags.get(i))) {
                    d.setPieValue(addValues.get(i));
                    return;
                }
            }
            naiveAddData(addTags.get(i), addValues.get(i));
        }

    }

}
