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
import java.util.ArrayList;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.MakeDensityBasedClusterer;
import weka.core.Instances;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class SOMCluster 
{
    double de[][];
    int clsize=0;

    Vector v1[];
    SOMCluster(double dd[][],int dc,int tc,int cl)
    {
        
        de=new double[dc][tc];
        de=dd;
        clsize=cl;
        v1=new Vector[clsize];
    }

    //public double[] getcluster()
    public Vector[] getcluster()
    {
        double as[]=new double[de.length];
        try
        {
            String arf="@relation cluster\n";
            arf=arf+"@attribute doc numeric\n";
            for(int i=0;i<de[0].length;i++)
            {
                arf=arf+"@attribute feat"+i+" numeric\n";
            }
            arf=arf+"@data\n";

            for(int i=0;i<de.length;i++)
            {
                for(int j=0;j<de[0].length;j++)
                {
                    arf=arf+(i+1)+" , "+de[i][j];
                }
                arf=arf+"\n";
            }
            System.out.println("----------");
            System.out.println(arf);

            File f=new File("somcluster.arff");
            FileOutputStream fos=new FileOutputStream(f);
            fos.write(arf.getBytes());
            fos.close();
            String filename="somcluster.arff";
            Instances data = new Instances(new BufferedReader(new FileReader(filename)));

		MakeDensityBasedClusterer emc=new MakeDensityBasedClusterer();
		emc.setNumClusters(clsize) ;
		emc.buildClusterer(data);

		ClusterEvaluation eval = new ClusterEvaluation();
		eval.setClusterer(emc);
		eval.evaluateClusterer(data);


		//System.out.println(eval.clusterResultsToString());
		//System.out.println("cluster "+eval.getNumClusters());
		//System.out.println("global info "+emc.globalInfo());
		//System.out.println(" cl "+emc.toString());
		double cs[]=eval.getClusterAssignments();
                ArrayList al=new ArrayList();

		for(int i=0;i<cs.length;i++)
                {
                    if(!(al.contains(cs[i])))
                    {
                        al.add(cs[i]);
                    }
			//System.out.println(cs[i]);
                }

                as=cs;
               /*File fe=new File(mf.cnt_Path);

                 SOMFrame emf=new SOMFrame(fe,cs);
                 emf.setTitle("SOM Clustering");
                 emf.setVisible(true);*/

                File fe=new File("content");
                File files[]=fe.listFiles();



                for(int i=0;i<al.size();i++)
                {
                    double sg=Double.parseDouble(al.get(i).toString());

                    System.out.println("cluster "+i);
                    Vector v=new Vector();
                    for(int j=0;j<cs.length;j++)
                    {
                        
                       // System.out.println(cs[j]);
                        if(sg==cs[j])
                        {
                            System.out.println(files[j].getName());
                            v.add(files[j].getName());
                        }
                    }
                    v1[i]=v;
                    System.out.println("---------");
                }



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //return as;
        return v1;
    }


}
