package Aplikacja;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class PieChartExample {
	public void zrob_wykres() {
		int a, b,c,d;
		// Create a simple Bar chart
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//System.out.println("Podaj wartosci dla kandydat�w");
		//Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od u�ytkownika
	  //  a = odczyt.nextInt(); 
		dataset.setValue(23, "G�osy", "Jane");
		//b = odczyt.nextInt(); 
		dataset.setValue(32, "G�osy", "Tom");
		//a = odczyt.nextInt(); 
		dataset.setValue(3, "G�osy", "Jill");
		//c = odczyt.nextInt(); 
		dataset.setValue(4, "G�osy", "John");
		//c = odczyt.nextInt();
		dataset.setValue(5, "G�osy", "Fred");
		JFreeChart chart = ChartFactory.createBarChart3D("G�osowanie na kandydat�w",
		"Kandydaci", "G�osy", dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.gray); // Set the background colour of the chart
		chart.getTitle().setPaint(Color.black); // Adjust the colour of the title
		CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
		p.setBackgroundPaint(Color.pink); // Modify the plot background
		p.setRangeGridlinePaint(Color.black); // Modify the colour of the plot gridlines
		try {
		ChartUtilities.saveChartAsJPEG(new File("C:\\Users\\martyna.krajnik\\wykres.jpg"), chart, 500, 300);
		} catch (IOException e) {
		System.err.println("Problem occurred creating chart.");
		}
		}
}
