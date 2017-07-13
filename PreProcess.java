/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class PreProcess 
{
    String path;
    
    PreProcess()
    {
        
    }
    
    public static ArrayList<String> match=new ArrayList<String>();

        public String getContent(String pp,String f)
        {
            String cc="";
            String cc1="";
             String cf2="";
            path=pp;
            String cf="";
            File fl=new File(f);
            String fname=fl.getName();
            try
            {
                ArrayList<String> st_wrd=new ArrayList<String>();
                st_wrd=read_stopwd();         // Read Stopword

                ArrayList<String> words=new ArrayList<String>();
                String whole_txt="";

                Scanner   sr = new Scanner(new BufferedReader(new FileReader(f)));
                String temp="";
                while (sr.hasNext())
                {
                    char src_word[];
                    String d_word="";
                    temp=sr.next();

                    src_word=temp.toCharArray();

                    for(int k=0; k<src_word.length; k++)
                    {
                        if( java.lang.Character.isLetter(src_word[k]) || src_word[k]=='.' )
                        {
                            if(src_word[k]=='.' || src_word[k]=='-' || src_word[k]=='_')
				d_word=d_word+"";
                            else
				d_word=d_word+src_word[k];
                         }
                    }
                    words.add(d_word);

		whole_txt=whole_txt+" "+d_word;
                    
                    
                    
            }



            match=remove_stopwd(st_wrd,words);   // Remove Stopword

          //  System.out.println("match "+match);
            edu.stanford.nlp.tagger.maxent.MaxentTagger ob=new edu.stanford.nlp.tagger.maxent.MaxentTagger(".\\models\\left3words-wsj-0-18.tagger");
          for(int i=0;i<match.size();i++)
          {
              String qs=match.get(i).toString().trim();
              
              if(!(qs.equals("")))
              {
                  String ret=ob.tagString(qs).trim();
                  System.out.println(ret);
                  if(ret.endsWith("/NN") || ret.endsWith("/NNS"))
                {
                    String g=ret.substring(0, ret.indexOf("/"));
                    if(g.length()>3)
                        cf=cf+g+" ";
                    //cf=cf+g+"\n";
                  //cf=cf+match.get(i).toString()+"\n";
                }
              }

          }
           /* File fn=new File(path+"\\"+fname);
            FileOutputStream fos=new FileOutputStream(fn);
            fos.write(cf.getBytes());
            fos.close();*/


           
          /*  for(int i=0;i<match.size();i++)
            {
                String qs1=match.get(i).toString();
                if(!(qs1.equals("")))
                {
                    Stemmer app = new Stemmer();
                    //String word = "passionate";
                    String word = qs1;
                    char wordArray[] = word.toCharArray();
   	
                    app.add( wordArray, wordArray.length );
                    app.stem();
   	
                   
                    cf2=cf2+app.toString()+" ";
                }    

            }*/            

         
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //return cc1;
         return cf;
    }

    static ArrayList<String> read_stopwd()throws Exception
    {
        ArrayList<String> st=new ArrayList<String>();
        Scanner scan1 = null;

        scan1 = new Scanner(new BufferedReader(new FileReader("stopwords.txt")));
        while (scan1.hasNext())
            st.add(scan1.next());

       return(st);
    }

    static ArrayList<String> remove_stopwd(ArrayList stp,ArrayList org) throws Exception
    {
        ArrayList al=new ArrayList();

        int max=org.size();
        int stop_max=stp.size();

        int flag=0;

        for(int i=0; i<max; i++)
        {
            flag=0;

            for(int k=0; k<stop_max; k++)
            {
                // if( org.get(i).equalsIgnoreCase( stp.get(k)) )
                if(org.get(i).toString().equalsIgnoreCase(stp.get(k).toString()))
                {
        		flag=1;
                	break;
		}

             }//k

            if(flag==1){}
            else
		al.add(org.get(i));

	}//max

        return al;
    }

    
}

