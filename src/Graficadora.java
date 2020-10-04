
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juan-Gtsk
 */


public class Graficadora {
    
    public ChartPanel oPanel;
    public void graficar(Producto[] arr, String titulo, String ejeX, String ejeY ) throws IOException
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for ( int i = 0 ; i < arr.length ; i++ )
        {
            dataset.addValue(arr[i].getCantidaProducto(), arr[i].getNombreProducto(), arr[i].getNombreProducto() );
        }
        
        JFreeChart barChart = ChartFactory.createBarChart(
                titulo, //TÍTULO PARA LA GRÁFICA
                ejeX,   //TÍTULO PARA EL EJE X
                ejeY,    //TÍTULO PARA EL EJE Y
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        
        int anchoImagen = 800;
        int altoImagen = 600;
        
        String grafica = "graficas/" + titulo + ".jpeg";
        File BarChart = new File(grafica);
        ChartUtilities.saveChartAsJPEG(BarChart, barChart, anchoImagen, altoImagen);
        
        oPanel = new ChartPanel(barChart);
        
    }
}
