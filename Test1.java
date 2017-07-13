/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.MakeDensityBasedClusterer;
import weka.core.Instances;

/**
 *
 * @author admin
 */
public class Test1 
{
    public static void main(String ar[])
    {
        try
        {
            
            String filename="somcluster.arff";
            Instances data = new Instances(new BufferedReader(new FileReader(filename)));

		MakeDensityBasedClusterer emc=new MakeDensityBasedClusterer();
		emc.setNumClusters(3) ;
		emc.buildClusterer(data);

		ClusterEvaluation eval = new ClusterEvaluation();
		eval.setClusterer(emc);
		eval.evaluateClusterer(data);
                System.out.println(emc.toString());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
