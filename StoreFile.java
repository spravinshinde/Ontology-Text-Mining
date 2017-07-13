/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;

import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
/**
 *
 * @author admin
 */
public class StoreFile 
{
    public static String dm = "";
    public static String ns = "";
    public static String ip = "";
    public static String mc = "";
    String path;
    StoreFile(String pp)
    {
        path=pp;
    }
    
    public void assignPDF(String t,String f){
        
        String text = t.toLowerCase();
        String fname = f.toLowerCase();
        if(text.contains("data mining")){
            dm += fname + ".pdf" + ",";
        }else if(text.contains("network security")){
            ns += fname + ".pdf" + ",";
        }else if(text.contains("image processing") || text.contains("face")){
            ip += fname + ".pdf" + ",";
        }else if(text.contains("mobile computing") || text.contains("cloud computing")){
            mc += fname + ".pdf" + ",";
        }
    }
    public String read()
    {
        String sp="";
        try
        {
            File pr1=new File("content");       // store pdf file in text format
            pr1.mkdir();
            
            String pt1=pr1.getAbsolutePath();
            sp=pt1;
             
            File ds1=new File(pt1);
            String sd1[]=ds1.list();
            for(int i=0;i<sd1.length;i++)
            {
                File fi=new File(pt1+"\\"+sd1[i]);
                boolean b1=fi.delete();
                System.out.println(sd1[i]+"delete "+b1);
            }
            
            
            File spm=new File(path);
            
            File list1[]=spm.listFiles();
            String fileNames = "";
            for (int i = 0; i < list1.length; i++) {
                
                fileNames += fileNames + "\n";
            }
            System.out.println("All file names :\n" + fileNames);     
            for(int i=0;i<list1.length;i++)
            {            
                String fname=list1[i].getName();
                System.out.println("file name : " + fname);
                String fp=list1[i].getPath();                
                PdfReader pr=new PdfReader(fp);
		String txt= PdfTextExtractor.getTextFromPage(pr,1);
		fname=fname.substring(0, fname.lastIndexOf("."));
                String ps=pt1+"\\"+fname+".txt";
                File fe=new File(ps);
                FileOutputStream fos=new FileOutputStream(fe);
                fos.write(txt.getBytes());
                fos.close();
                assignPDF(txt,fname);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sp;
    }
}
