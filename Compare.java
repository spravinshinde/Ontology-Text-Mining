package otmm;
import org.jfree.chart.*;  
import org.jfree.data.category.*;  
import org.jfree.chart.plot.*;  
import java.awt.*;  
  
public class Compare{
    
public Compare(String j, String c){  

    
    DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
    dataset.setValue(Double.parseDouble(j), "Accuracy", "Kmean");  
    dataset.setValue(Double.parseDouble(c), "Accuracy", "SOM");  
  JFreeChart chart = ChartFactory.createBarChart("BarChart","Method", "Accuracy", dataset, PlotOrientation.VERTICAL, false,true, false);  
  chart.setBackgroundPaint(Color.cyan);  
  chart.getTitle().setPaint(Color.blue);   
  CategoryPlot p = chart.getCategoryPlot();   
  p.setRangeGridlinePaint(Color.red);   
  ChartFrame frame1=new ChartFrame("Bar Chart",chart);  
  frame1.setVisible(true);  
  frame1.setSize(500,450);  
  }  
}   
