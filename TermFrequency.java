/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class TermFrequency 
{
     ArrayList feat;
    String fpath;
    //int term_Wg[][]=new int[100][6000];
    double term_Wg[][];//=new double[100][6000];
    double term_1[][];//=new double[100][6000];
    double mat[][];
    int dc_Count=0;
    int tm_Count=0;

    TermFrequency(ArrayList al,String s1,int dc)
    {
        feat=al;
        fpath=s1;
        term_Wg=new double[dc][al.size()];
        term_1=new double[dc][al.size()];
    }

    public void find_tf()
    {
        try
        {
            File f=new File(fpath);
            File files[]=f.listFiles();

            System.out.println("feat size "+feat.size()+" : "+files[0].getAbsolutePath());
            for(int i=0;i<files.length;i++)
            {
		FileInputStream fis=new FileInputStream(files[i]);
		byte data[]=new byte[fis.available()];
		fis.read(data);
		fis.close();

                String str[]=new String(data).toLowerCase().trim().split(" ");

		tm_Count=0;

		for(int k=0;k<feat.size();k++)
		{
                    String s1=feat.get(k).toString().trim();
                    int cn=0;
                    for(int j=0;j<str.length;j++)
                    {
			String s2=str[j];
			if(s1.equals(s2))
			{
				cn++;
			}
                    }

                        int idf=0;
                    for(int d=0;d<files.length;d++)
                    {
                        FileInputStream fis1=new FileInputStream(files[d]);
                        byte data1[]=new byte[fis1.available()];
                        fis1.read(data1);
                        fis1.close();

                        String str1=new String(data1).toLowerCase();
                        if(str1.contains(s1))
                        {
                            idf++;
                        }


                    }
                    //term_Wg[dc_Count][tm_Count]=String.valueOf(cn);
                        double ds1=(double)cn/feat.size();
                       // double ds2=1;
                        double ds2=1+Math.log(files.length/idf);
                       // System.out.println(cn+" : "+feat.size()+" : "+files.length+" : "+idf);
                       // System.out.println(ds1+" : "+ds2+" :  "+(ds1*ds2));
                      //  System.out.println("-----> "+(ds1*ds2));
                        double de=ds1*ds2;
                        DecimalFormat twoDForm = new DecimalFormat("#.####");
                       double da= Double.valueOf(twoDForm.format(de));
                    term_Wg[dc_Count][tm_Count]=da;
                    term_1[dc_Count][tm_Count]=idf;
                    tm_Count++;
		}
                dc_Count++;

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public double[][] getMatrix1()
    {
        try
        {
            System.out.println("term -------->");
            for(int i=0;i<dc_Count;i++)
            {

                for(int j=0;j<tm_Count;j++)
                {
                    System.out.print(term_Wg[i][j]+" : ");
                }

                System.out.println("---> ");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        mat=new double[dc_Count][tm_Count];
        mat=term_Wg;
       
        return mat;
        //return term_Wg;
    }

    public double[][] getMatrix2()
    {
        try
        {
            System.out.println("term -------->");
            for(int i=0;i<dc_Count;i++)
            {

                for(int j=0;j<tm_Count;j++)
                {
                    System.out.print(term_Wg[i][j]+" : ");
                }

                System.out.println("---> ");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        mat=new double[dc_Count][tm_Count];
        mat=term_1;

        return mat;
        //return term_Wg;
    }
}
