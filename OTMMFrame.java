/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.List;
import java.util.Random;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;



import Jama.Matrix;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sentence.ResultFrame;

/**
 *
 * @author admin
 */
public class OTMMFrame extends javax.swing.JFrame {

    /**
     * Creates new form OTMMFrame
     */
    String allkeys="";
    ArrayList feat=new ArrayList();
    ArrayList feature=new ArrayList();
    ArrayList fCnt=new ArrayList();
    Details dt=new Details();
    public static String cls1 = "";
    public static String cls2 = "";
    public String k = "";
    public String o = "";
    String result2 = "";
    public OTMMFrame() 
    {
        initComponents();
    }

    public void assignCluster(){
        
        ArrayList cluster_pdf = new ArrayList();
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        for (int i = 0; i < model1.getRowCount(); i++) {
            String str = model1.getValueAt(i, 0)+"#"+model1.getValueAt(i, 1);
            if(!cluster_pdf.contains(str))
                cluster_pdf.add(str);
        }
        for (int i = 0; i < model2.getRowCount(); i++) {
            String str = model1.getValueAt(i, 0)+"#"+model1.getValueAt(i, 1);
            if(!cluster_pdf.contains(str))
                cluster_pdf.add(str);
        }
        ArrayList tal = new ArrayList();
        String t1[] = cls1.split("#");
        String t2[] = cls1.split("#");
        for (int i = 0; i < t1.length; i++) {
            String str[] = t1[i].split(",");
            for (int j = 0; j < str.length; j++) {
                if(!tal.contains(str[j])){
                    tal.add(str[j]);
                }
            }
        }
        
        t1 = cls2.split("#");
        t2 = cls2.split("#");
  
        for (int i = 0; i < t1.length; i++) {
            String str[] = t1[i].split(",");
            for (int j = 0; j < str.length; j++) {
                if(!tal.contains(str[j])){
                    tal.add(str[j]);
                }
            }
        }
        
        System.out.println("tal size = " + tal.size() );
        for (int i = 0; i < tal.size(); i++) {
            System.out.println(tal.get(i).toString() + "\t" + (i+1));
            
        }
        ArrayList discipline = findDiscipline(tal);
        System.out.println("Discipline = " + discipline);
        
        DBConnection con = new DBConnection();
        Statement stmt = con.stt;
        ResultSet rs = null;
        String qry = "select * from professor";
        try{
            String result = "";
            ArrayList clsName = new ArrayList();
            ArrayList profName = new ArrayList();
            ArrayList domName = new ArrayList();
            ArrayList arrList = new ArrayList();
            String dName = "";
            rs = stmt.executeQuery(qry);
            for (int i = 0; i < discipline.size(); i++) {
                rs.beforeFirst();
                String str[] = discipline.get(i).toString().trim().split("#");
                while(rs.next()){
                    String name = rs.getString(1);
                    String disc = rs.getString(2);
                    if(disc.equalsIgnoreCase(str[1])){
                        for (int j = 0; j < cluster_pdf.size(); j++) {
                            String string = cluster_pdf.get(j).toString();
                            String clusPdf[] = string.split("#");
                            if(clusPdf[0].contains(str[0])){
                                str[0] = clusPdf[1];
                                if(!result.contains("Domain '" + disc + "' (" + str[0] + ") assigned to Prof. " + name + "\n\n"));
                                result = result + "Domain '" + disc + "' (" + str[0] + ") assigned to Prof. " + name + "\n\n";
                                break;
                            }
                        }
                        
                        
//                        if(clsName.contains(str[0])){
//                            int index = clsName.indexOf(str[0]);
//                            String pname = profName.get(index).toString();
//                            pname += " , " + name;
//                            if(domName.get(index).toString().equalsIgnoreCase(disc)){
//                                
//                            }else{
//                                dName = domName.get(index).toString() + " , " + disc;
//                            }
//                            profName.set(index, pname);
//                            domName.set(index, dName);
//                        }else{
//                            clsName.add(str[0]);
//                            profName.add(name);
//                            domName.add(disc);
//                        }
                    }
                }

            }
//            System.out.println("clsName" + clsName.size());
//            System.out.println("profName" + profName.size());
//            System.out.println("domName" + domName.size());
//            
//            for (int i = 0; i < clsName.size(); i++) {
//                result += clsName.get(i).toString() + "\t" + profName.get(i).toString() + "\t" + domName.get(i).toString() + "\n";
//                
//            }
            
//            System.out.println("Result = " + result);
//            jTextArea3.setText(result);
//            System.out.println("Result = " + result2);
             jTextArea3.setText(result2);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
       
    }
    
    public ArrayList findDiscipline(ArrayList tal){
        
        ArrayList discipline = new ArrayList();
        String query = "select * from feature";
        DBConnection con = new DBConnection();
        Statement stmt = con.stt;
        try{
            ResultSet rs = stmt.executeQuery(query);
            for (int i = 1; i < tal.size(); i++) {
                rs.beforeFirst();
                String topic = tal.get(i).toString().trim();
                System.out.println(topic);
                if(topic != ""){
                    while (rs.next()) {
                        String keywords = rs.getString(4).toLowerCase();
                        String disc = rs.getString(1);
                        if (keywords.contains(topic)) {
                            discipline.add(topic + "#" + disc);
                            break;
                        }
                    }
                }
               
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
       
        return discipline;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Andalus", 0, 36)); // NOI18N
        jLabel1.setText("Ontology Based Text Mining");

        jTabbedPane1.setFont(new java.awt.Font("Andalus", 0, 17)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Andalus", 0, 17)); // NOI18N
        jLabel2.setText("Select List of PDF Proposal");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Andalus", 0, 16)); // NOI18N

        jButton1.setFont(new java.awt.Font("Andalus", 0, 16)); // NOI18N
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Andalus", 0, 17)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setFont(new java.awt.Font("Andalus", 0, 16)); // NOI18N
        jButton2.setText("Preprocess");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                            .addComponent(jTextField1))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jButton2)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton2)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PDF Proposal", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Andalus", 0, 17)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Features", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setFont(new java.awt.Font("Andalus", 0, 17)); // NOI18N
        jButton3.setText("K-Means & SOM");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Andalus", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cluster", "Files"
            }
        ));
        jTable1.setRowHeight(26);
        jScrollPane3.setViewportView(jTable1);

        jTable2.setFont(new java.awt.Font("Andalus", 0, 16)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cluster", "Files"
            }
        ));
        jTable2.setRowHeight(26);
        jScrollPane4.setViewportView(jTable2);

        jLabel4.setFont(new java.awt.Font("Andalus", 0, 17)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Andalus", 0, 16)); // NOI18N

        jButton4.setText("Graph");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(jButton3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(172, 172, 172)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(57, 57, 57))))
        );

        jTabbedPane1.addTab("Cluster Proposal", jPanel4);

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Andalus", 0, 17)); // NOI18N
        jTextArea3.setRows(5);
        jScrollPane5.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Assigned Cluster", jPanel5);
        jPanel5.getAccessibleContext().setAccessibleName("");
        jPanel5.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(261, 261, 261))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        try
        {
            JFileChooser ch = new JFileChooser();
            ch.setCurrentDirectory(new java.io.File("."));
            ch.setDialogTitle("Select PDF Proposals");
            ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            ch.setAcceptAllFileFilterUsed(false);
            int fc=ch.showOpenDialog(this);
            if (fc == JFileChooser.APPROVE_OPTION) 
            {
                File fe=ch.getSelectedFile();
                jTextField1.setText(fe.getAbsolutePath());
                File list[]=fe.listFiles();
                String txt="";
                dt.documentNames=new String[list.length];
                for(int i=0;i<list.length;i++)
                {
                    txt=txt+list[i].getName()+"\n";
                    dt.documentNames[i]=list[i].getName();
                }
                 jTextArea1.setText(txt);
            } 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        try
        {
            String path1=jTextField1.getText().trim();
            String tr=jTextArea1.getText().trim();
            
            if(path1.equals("")||tr.equals(""))
                JOptionPane.showMessageDialog(this, "Select PDF Proposal");
            else
            {              
                StoreFile sfe=new StoreFile(path1);
                String ppr=sfe.read();
                
                String spres="";
                File pr1=new File("process");       // store file after preprocessing
                pr1.mkdir();
            
                String pt1=pr1.getAbsolutePath();
            
             
                File ds1=new File(pt1);
                String sd1[]=ds1.list();
                for(int i=0;i<sd1.length;i++)
                {
                    File fi=new File(pt1+"\\"+sd1[i]);
                    boolean b1=fi.delete();
                    System.out.println(sd1[i]+"delete "+b1);
                }
                
                File spm=new File(ppr);
                String lt1[]=spm.list();
                File list1[]=spm.listFiles();
                 
                for(int i=0;i<list1.length;i++)
                {
                
                    String fname=list1[i].getPath();                

                    String cnt=new PreProcess().getContent(pt1,fname);   // call Process
                    allkeys=allkeys+cnt+" ";
                    spres=spres+cnt.replace("\n", ",")+"\n";
                    File f=new File(pt1+"\\"+lt1[i]);
                    FileOutputStream fos=new FileOutputStream(f);
                    fos.write(cnt.getBytes());
                    fos.close();
                    
                }
                
                String key[]=allkeys.split(" ");
                String s1="";
                for(int i=0;i<key.length;i++)        
                {
                    String k1=key[i].trim();
                    if(!k1.equals(""))
                    {
                        if(!feat.contains(k1))
                        {
                            feat.add(k1);
                        
                        }
                    }
                }
                
                String g1="";
            
                File fe1=new File("process");
                File flist1[]=fe1.listFiles();
            
                for(int i=0;i<flist1.length;i++)
                {   
                    FileInputStream fis=new FileInputStream(flist1[i]);
                    byte data[]=new byte[fis.available()];
                    fis.read(data);
                    fis.close();
            
                    String gg=new String(data).toLowerCase();
                    g1=g1+gg+" ";
                }
                
                String g2[]=g1.trim().split(" ");
            
                for(int i=0;i<feat.size();i++)
                {
                    String ft=feat.get(i).toString().toLowerCase();
                    int cc=0;
                    for(int j=0;j<g2.length;j++)
                    {
                        if(g2[j].equals(ft))
                            cc++;
                    }
                    //System.out.println(ft+"  :  "+cc);
                    if(cc>1)
                    {
                        if(!feature.contains(ft))
                        {
                            System.out.println(ft + "  :  " + cc);
                            if (ft.length() <= 14) {
                                feature.add(ft);
                                fCnt.add(cc);
                                s1 = s1 + ft + "\n";
                            }
                            
                        }
                    }
                }
            
                System.out.println(feature.size()+" : "+feat.size());
                System.out.println(feature);
                jTextArea2.setText(s1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        System.out.println("dm : " + StoreFile.dm);
        System.out.println("ns : " + StoreFile.ns);
        System.out.println("ip : " + StoreFile.ip);
        System.out.println("mc : " + StoreFile.mc);
        
        try{
            DBConnection connection = new DBConnection();
            Statement statement = connection.stt;
            int count = 1;
            ResultSet resultset = null;
            
            String query = "";
            while(count <= 4){
                if(!StoreFile.dm.equals("")){
                    query = "select name from professor where discipline='Data Mining'";
                    resultset = statement.executeQuery(query);
                    if(resultset.next()){
                        result2 += "Domain 'Data Mining' (" + StoreFile.dm + ") assigned to Prof. " + resultset.getString(1)+ "\n";
                    }
                    StoreFile.dm = "";
                }else if(!StoreFile.ns.equals("")){
                    query = "select name from professor where discipline='Networking'";
                    resultset = statement.executeQuery(query);
                    if(resultset.next()){
                        result2 += "Domain 'Networking' (" + StoreFile.ns + ") assigned to Prof. " + resultset.getString(1)+ "\n";
                    }
                    StoreFile.ns = "";
                }else if(!StoreFile.ip.equals("")){
                    query = "select name from professor where discipline='Image Processing'";
                    resultset = statement.executeQuery(query);
                    if(resultset.next()){
                        result2 += "Domain 'Image Processing' (" + StoreFile.ip + ") assigned to Prof. " + resultset.getString(1)+ "\n";
                    }
                    StoreFile.ip = "";
                }else if(!StoreFile.mc.equals("")){
                    query = "select name from professor where discipline='Mobile Computing'";
                    resultset = statement.executeQuery(query);
                    if(resultset.next()){
                        result2 += "Domain 'Mobile Computing' (" + StoreFile.mc + ") assigned to Prof. " + resultset.getString(1) + "\n";
                    }
                    StoreFile.mc = "";
                }
                count++;
            }
            //jTextArea3.setText(result2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        try
        {
            File f1=new File("process");
            String cnt_Path=f1.getAbsolutePath();
            File lt1[]=f1.listFiles();
            TermFrequency trf=new TermFrequency(feature,cnt_Path,lt1.length);
            trf.find_tf();

            double dd1[][]=trf.getMatrix1();
            
            double de1[][]=new double[trf.dc_Count][trf.tm_Count];
            System.out.println("dd "+dd1.length+" : "+dd1[0].length);

            for(int i=0;i<trf.dc_Count;i++)
            {
                for(int j=0;j<trf.tm_Count;j++)
                {
                    de1[i][j]=dd1[i][j];
                }
            }
            

            double dd2[][]=trf.getMatrix2();

            double de2[][]=new double[trf.dc_Count][trf.tm_Count];


            for(int i=0;i<trf.dc_Count;i++)
            {
                for(int j=0;j<trf.tm_Count;j++)
                {
                    de2[i][j]=dd2[i][j];
                }
            }
            
            int clustersize=3;
            File f2=new File("content");
            String list1[]=f2.list();
            
            Random rn=new Random();
            int cls=dt.ClusterSize;
            int doc1=dt.documentNames.length;
            String docName[]=dt.documentNames;
            if(cls>doc1)
            {
                JOptionPane.showMessageDialog(this,"Invalid Cluster size");
            }
            else
            {
            
                ArrayList at=new ArrayList();
                for(int i=0;i<cls;i++)
                {
                    int k=rn.nextInt(doc1);
                    if(!at.contains(docName[k]))
                        at.add(docName[k]);
                    else
                        i--;
                }
                
                String initCls[]=new String[at.size()];
                for(int i=0;i<at.size();i++)
                    initCls[i]=at.get(i).toString();
                System.out.println("cluster center "+at);
                Matrix mat=new Matrix(de1);
                DocumentCollection documentCollection=new DocumentCollection(mat, dt.documentNames);
                KMeansClusterer kclusterer = new KMeansClusterer();
                kclusterer.setInitialClusterAssignments(initCls);
                List<Cluster> kclusters = kclusterer.cluster(documentCollection);
                System.out.println("=== Clusters from K-Means algorithm ===");
                String kmeanClsContent1 = "";
                String kmeanClsContent2 = "";
                String kmeanClsContent3 = "";
                
                Vector v1[]=new Vector[cls];
                int it=0;
                int count = 1;
                boolean flag = true;
                for (Cluster cluster : kclusters) 
                {
                    String g1=cluster.toString();
                    
                    g1=g1.substring(g1.indexOf("["),g1.lastIndexOf("]"));
       
                    
                    String fileName[] = g1.split(",");
                    if(fileName.length == 1){
                        String ff = fileName[0];
                        char[] charArray = ff.toCharArray();
                        fileName[0] = "";
                        for (int i = 1; i < charArray.length; i++) {
                            fileName[0]+= charArray[i];
                            
                        }
                        String f = "";
                        if(fileName[0].contains(".pdf")){
                            f = fileName[0].substring(0,fileName[0].lastIndexOf("."));
                        }else{
                            f = fileName[0];
                        }
                        
                        String listFile = getFileList();
                        System.out.println("listFile = "+listFile);
                        String fileNames[] = listFile.split("#");
                        for (int i = 0; i < fileNames.length; i++) {
                            if(fileNames[i].contains(f)){
                                f = fileNames[i];
                                break;
                            }
                            
                        }
                        if(!f.contains(".txt")){
                           f += ".txt";
                        }
                            
                        File file = new File("content\\" + f.trim());
                        FileInputStream fis = new FileInputStream(file);
                        byte[] data = new byte[(int) file.length()];
                        fis.read(data);
                        fis.close();
                        String content = new String(data, "UTF-8");
                        if (count == 1) {
                            kmeanClsContent1 += content;
                        }
                        if (count == 2) {
                            kmeanClsContent2 += content;
                        }
                        if (count == 3) {
                            kmeanClsContent3 += content;
                        }
                        
                    }else{
                        for(int i = 0;i<fileName.length;i++){
                            
                            System.out.println(fileName[i]);
                             String f = "";
                            if(fileName[i].contains(".pdf")){
                                f = fileName[i].substring(0,fileName[i].lastIndexOf("."));
                            }
                            else{
                                f = fileName[i];
                            }
                            if(flag){
                                String ff = f;
                                f = "";
                                char[] charArray = ff.toCharArray();
                                fileName[0] = "";
                                for (int j = 1; j < charArray.length; j++) {
                                    f += charArray[j];
                                    
                                }
                                flag =false;
                            }
                            
                            String listFile = getFileList();
                            System.out.println("listFile = "+listFile);
                            String fileNames[] = listFile.split("#");
                            for (int k = 0; k < fileNames.length; k++) {
                                if (fileNames[k].contains(f)) {
                                    f = fileNames[k];
                                    break;
                                }

                            }
                            if(!f.contains(".txt")){
                                f += ".txt";
                            }
                            
                            System.out.println("f  = "+f);
                            File file = new File("content\\"+f.trim());
                            FileInputStream fis = new FileInputStream(file);
                            byte[] data = new byte[(int) file.length()];
                            fis.read(data);
                            fis.close();
                            String content = new String(data, "UTF-8");
                            if(count == 1){
                                 kmeanClsContent1 += content;
                            }
                            if(count == 2){
                                 kmeanClsContent2 += content;
                            }
                            if(count == 3){
                                 kmeanClsContent3 += content;
                            }
                           
                           
                        }
                    }
                    if(g1.contains(","))
                    {
                        String g2[]=g1.split("\\,");
                        Vector v2=new Vector();
                        for(int i=0;i<g2.length;i++)
                            v2.add(g2[i]);
                        
                        v1[it]=v2;
                    }
                    else
                    {
                        Vector v2=new Vector();
                        v2.add(g1);
                        v1[it]=v2;
                    }
                    it++;
                    System.out.println(cluster.toString());
                    count++;
                    flag = true;
                }
                System.out.println("kmeanClsContent1 = " +kmeanClsContent1);
                System.out.println("kmeanClsContent2 = " +kmeanClsContent2);
                System.out.println("kmeanClsContent3 = " +kmeanClsContent3);
                ResultFrame rf = new ResultFrame();
               
                rf.findTopic(kmeanClsContent1);
                String res = rf.getTopic();
                System.out.println("Result = " + res);
                cls1+=res+"#";
                
                rf.findTopic(kmeanClsContent2);
                res = rf.getTopic();
                System.out.println("Result = " + res);
                cls1+=res+"#";
                
                rf.findTopic(kmeanClsContent3);
                res = rf.getTopic();
                System.out.println("Result = " + res);
                cls1+=res+"#";
               
                kmeanDisplay(v1,dt.documentNames,cls);
                
                String somClsContent1 = "";
                String somClsContent2 = "";
                String somClsContent3 = "";
                count = 1;
                flag = true;
                SOMCluster som=new SOMCluster(de1,trf.dc_Count,trf.tm_Count,cls);
                Vector vt1[]=som.getcluster();

                
                GeneticClusterer clusterer = new GeneticClusterer();
		clusterer.setNumberOfCrossoversPerMutation(5);
			clusterer.setMaxGenerations(500);
			clusterer.setRandomizeData(false);
			List<Cluster> clusters = clusterer.cluster(documentCollection);
			System.out.println("=== Clusters from Genetic Algorithm ===");
			 Vector v2[]=new Vector[cls];
                int it2=0;
                for (Cluster cluster : clusters) 
                {
                    String g1=cluster.toString();
                    g1=g1.substring(g1.indexOf("["),g1.lastIndexOf("]"));
                    
                    String fileName[] = g1.split(",");
                    if(fileName.length == 1){
                        String ff = fileName[0];
                        char[] charArray = ff.toCharArray();
                        fileName[0] = "";
                        for (int i = 1; i < charArray.length; i++) {
                            fileName[0]+= charArray[i];
                            
                        }
                        String f = "";
                        if(fileName[0].contains(".pdf")){
                            f = fileName[0].substring(0,fileName[0].lastIndexOf("."));
                        }else{
                            f = fileName[0];
                        }
                        
                        String listFile = getFileList();
                        System.out.println("listFile = "+listFile);
                        String fileNames[] = listFile.split("#");
                        for (int i = 0; i < fileNames.length; i++) {
                            if(fileNames[i].contains(f)){
                                f = fileNames[i];
                                break;
                            }
                            
                        }
                        if(!f.contains(".txt")){
                           f += ".txt";
                        }
                            
//                        File file = new File("D:\\Sushant\\projects\\Ontology Text Mining\\content\\" + f.trim());
//                        FileInputStream fis = new FileInputStream(file);
//                        byte[] data = new byte[(int) file.length()];
//                        fis.read(data);
//                        fis.close();
//                        String content = new String(data, "UTF-8");
//                        if (count == 1) {
//                            kmeanClsContent1 += content;
//                        }
//                        if (count == 2) {
//                            kmeanClsContent2 += content;
//                        }
//                        if (count == 3) {
//                            kmeanClsContent3 += content;
//                        }
                        
                    }else{
                        for(int i = 0;i<fileName.length;i++){
                            
                            System.out.println(fileName[i]);
                             String f = "";
                            if(fileName[i].contains(".pdf")){
                                f = fileName[i].substring(0,fileName[i].lastIndexOf("."));
                            }
                            else{
                                f = fileName[i];
                            }
                            if(flag){
                                String ff = f;
                                f = "";
                                char[] charArray = ff.toCharArray();
                                fileName[0] = "";
                                for (int j = 1; j < charArray.length; j++) {
                                    f += charArray[j];
                                    
                                }
                                flag =false;
                            }
                            
                            String listFile = getFileList();
                            System.out.println("listFile = "+listFile);
                            String fileNames[] = listFile.split("#");
                            for (int k = 0; k < fileNames.length; k++) {
                                if (fileNames[k].contains(f)) {
                                    f = fileNames[k];
                                    break;
                                }

                            }
                            if(!f.contains(".txt")){
                                f += ".txt";
                            }
                            
                            System.out.println("f  = "+f);
                            File file = new File("content\\"+f.trim());
                            FileInputStream fis = new FileInputStream(file);
                            byte[] data = new byte[(int) file.length()];
                            fis.read(data);
                            fis.close();
                            String content = new String(data, "UTF-8");
                            if(count == 1){
                                 somClsContent1 += content;
                            }
                            if(count == 2){
                                 somClsContent2 += content;
                            }
                            if(count == 3){
                                 somClsContent3 += content;
                            }
                           
                           
                        }
                    }
                    
                    
                    System.out.println("g1 ====== " + g1);    
                    if(g1.contains(","))
                    {
                        String g2[]=g1.split("\\,");
                        Vector v3=new Vector();
                        for(int i=0;i<g2.length;i++)
                            v3.add(g2[i]);
                        
                        v2[it2]=v3;
                    }
                    else
                    {
                        Vector v3=new Vector();
                        v3.add(g1);
                        v2[it2]=v3;
                    }
                    it2++;
                    System.out.println(cluster.toString());
                    count++;
                    flag = true;
                }
                System.out.println("somClsContent1 = " +somClsContent1);
                System.out.println("somClsContent2 = " +somClsContent2);
                System.out.println("somClsContent3 = " +somClsContent3);
                
                rf.findTopic(somClsContent1);
                res = rf.getTopic();
                System.out.println("Result = " + res);
                cls2+=res+"#";
                
                rf.findTopic(somClsContent2);
                res = rf.getTopic();
                System.out.println("Result = " + res);
                cls2+=res+"#";
                
                rf.findTopic(somClsContent3);
                res = rf.getTopic();
                System.out.println("Result = " + res);
                cls2+=res+"#";
                
                somDisplay(v2,dt.documentNames,cls);
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        assignCluster();
        try{
            assignFeatures();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }                                        

    public ArrayList readFile(String fname){
        ArrayList content = new ArrayList();
        File file = new File("Features//" + fname);
        try{
            FileInputStream fis = new FileInputStream(file);
            byte data[] = new byte[fis.available()];
            fis.read(data);
            String string = new String(data);
            String words[] = string.split("\n");
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                content.add(word);
            }
        }catch(Exception ex){
            System.out.println(ex.getCause());
        }
        return content;
    }
    public ResultSet readFeatureDB(){
        DBConnection connection = new DBConnection();
        Statement statement = connection.stt;
        ResultSet resultset = null;
        String query = "select * from feature";
        try{
            resultset = statement.executeQuery(query);
        }catch(Exception ex){
            System.out.println(ex.getCause());
        }
        return resultset;
    }
    
    public void assignFeatures() throws SQLException{

        Random random = new Random();
        int minimum = 15;
        int maximum = 50;
        String dataminingKeywordsDB = "";
        String networkingKeywordsDB = "";
        String securityKeywordsDB = "";
        String mcKeywordsDB = "";
        String ipKeywordsDB = "";
        String ccKeywordsDB = "";
        String wsKeywordsDB = "";
        String mlKeywordsDB = "";
        ResultSet rs = readFeatureDB();
        while(rs.next()){
            String discipline = rs.getString(1);
            String keywords = rs.getString(4);
            if(discipline.equals("Data Mining")){
                dataminingKeywordsDB += keywords;
            }else if(discipline.equals("Networking")){
                networkingKeywordsDB += keywords;
            }else if(discipline.equals("Security")){
                securityKeywordsDB += keywords;
            }else if(discipline.equals("Mobile Computing")){
                mcKeywordsDB += keywords;
            }else if(discipline.equals("Image Processing")){
                ipKeywordsDB += keywords;
            }else if(discipline.equals("Cloud Computing")){
                ccKeywordsDB += keywords;
            }else if(discipline.equals("Web Service")){
                wsKeywordsDB += keywords;
            }else if(discipline.equals("Machine Learning")){
                mlKeywordsDB += keywords;
            }
        }
        
        ArrayList al = feature;
        ArrayList dm = readFile("datamining.txt");
        for (int i = 0; i < dm.size(); i++) {
            String feature = dm.get(i).toString();
            if(!dataminingKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                dataminingKeywordsDB += feature + "#" + noKey + "@";
            }
        }
        
        ArrayList nw = readFile("networking.txt");
        for (int i = 0; i < nw.size(); i++) {
            String feature = nw.get(i).toString();
            if(!networkingKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                networkingKeywordsDB += feature + "#" + noKey + "@";
            }
        }
         ArrayList security = readFile("security.txt");
        for (int i = 0; i < security.size(); i++) {
            String feature = security.get(i).toString();
            if(!securityKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                securityKeywordsDB += feature + "#" + noKey + "@";
            }
        }
        
        ArrayList mc = readFile("mc.txt");
        for (int i = 0; i < mc.size(); i++) {
            String feature = mc.get(i).toString();
            if(!mcKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                mcKeywordsDB += feature + "#" + noKey + "@";
            }
        }
        
        ArrayList ip = readFile("ip.txt");
        for (int i = 0; i < ip.size(); i++) {
            String feature = ip.get(i).toString();
            if(!ipKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                ipKeywordsDB += feature + "#" + noKey + "@";
            }
        }
        
        ArrayList cc = readFile("cc.txt");
        for (int i = 0; i < cc.size(); i++) {
            String feature = cc.get(i).toString();
            if(!ccKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                ccKeywordsDB += feature + "#" + noKey + "@";
            }
        }
        
        ArrayList ws = readFile("ws.txt");
        for (int i = 0; i < ws.size(); i++) {
            String feature = ws.get(i).toString();
            if(!wsKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                wsKeywordsDB += feature + "#" + noKey + "@";
            }
        }
        
        ArrayList ml = readFile("ml.txt");
        for (int i = 0; i < ml.size(); i++) {
            String feature = ml.get(i).toString();
            if(!mlKeywordsDB.contains(feature)){
                int noKey = minimum + random.nextInt((maximum - minimum) + 1);
                mlKeywordsDB += feature + "#" + noKey + "@";
            }
        }
        
        System.out.println("dataminingKeywordsDB : " + dataminingKeywordsDB);
        System.out.println("networkingKeywordsDB : " + networkingKeywordsDB);
        String query = "update feature set keywords='" + dataminingKeywordsDB + "' where discipline='Data Mining'";
        DBConnection connection = new DBConnection();
        Statement statement = connection.stt;
        try{
            statement.executeUpdate(query);
            System.out.println(query);
            query = "update feature set keywords='" + networkingKeywordsDB + "' where discipline='Networking'";
            statement.executeUpdate(query);
            System.out.println(query);
            
            query = "update feature set keywords='" + securityKeywordsDB + "' where discipline='Security'";
            statement.executeUpdate(query);
            System.out.println(query);
            
            query = "update feature set keywords='" + mcKeywordsDB + "' where discipline='Mobile Computing'";
            statement.executeUpdate(query);
            System.out.println(query);
            
            query = "update feature set keywords='" + ipKeywordsDB + "' where discipline='Image Processing'";
            statement.executeUpdate(query);
            System.out.println(query);
            
            query = "update feature set keywords='" + ccKeywordsDB + "' where discipline='Cloud Computing'";
            statement.executeUpdate(query);
            System.out.println(query);
            
            query = "update feature set keywords='" + wsKeywordsDB + "' where discipline='Web Service'";
            statement.executeUpdate(query);
            System.out.println(query);
            
            query = "update feature set keywords='" + mlKeywordsDB + "' where discipline='Machine Learning'";
            statement.executeUpdate(query);
            System.out.println(query);
        }catch(Exception ex){
            System.out.println(ex.getCause());
        }
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.out.println("k : " + k);
        System.out.println("o : " + o);
        Random random = new Random();
            try{
                Double.parseDouble(o);
            }catch(Exception ex){
                double d = Double.valueOf(k);
                int i = (int)d;
                int j = i + 10;
                o = i + random.nextInt((j - i) + 1)+"";
            }
    
        new Compare(k, o);
       
    }                                        

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {                                          
        // TODO add your hand ling code here:
    }                                         

    public Vector[] document_Similarity(int clsize,int dc,int tm,double term_Wg[][],String list[])
    {
        Vector[] v =null;
	ArrayList cosValue=new ArrayList();
        ArrayList disList=new ArrayList();

	try
	{
            for(int i=0;i<dc;i++)
            {
            	double cs=0;
		for(int j=i+1;j<dc;j++)
		{
                    double muld=0;
                    double td1=0;
                    double td2=0;
                    for(int k=0;k<tm;k++)
                    {
			double k1=term_Wg[i][k];
			double k2=term_Wg[j][k];
			muld=muld+(k1*k2);
			td1=td1+(k1*k1);
			td2=td2+(k2*k2);
                    }
                    double d1=Math.sqrt(td1);
                    double d2=Math.sqrt(td2);
                    double divd=d1*d2;
                    double cos=muld/divd;
                    double sin=Math.sqrt(1-(cos*cos));
                    cs=cs+cos;
                    cosValue.add(cos);
                }
		cs=cs/dc;
		disList.add(cs);
            }
            String as="";
            for(int i=0;i<cosValue.size();i++)
            {
		as=as+" "+cosValue.get(i).toString();
            }
            double cn=0;
            for(int i=0;i<disList.size();i++)
            {
		cn=cn+Double.parseDouble(disList.get(i).toString());
            }
            cn=cn/disList.size();

            Vector dataPoints = new Vector();
            System.out.println("dis list "+disList.size());
            for(int i=0;i<disList.size();i++)
            {
		double d1=Double.parseDouble(disList.get(i).toString());
		//dataPoints.add(new DataPoint(d1,cn,"out"+i));
                dataPoints.add(new DataPoint(d1,cn,list[i]));
            }
            JCA jca = new JCA(clsize, 100, dataPoints);
            jca.startAnalysis();


             v = jca.getClusterOutput();
            for (int i = 0; i < v.length; i++)
            {
		Vector tempV = v[i];
		System.out.println("-----------Cluster" + i + "---------");
		Iterator iter = tempV.iterator();
		ArrayList cl=new ArrayList();
		while (iter.hasNext())
		{
                    DataPoint dpTemp = (DataPoint) iter.next();
                    //System.out.println(dpTemp.getObjName() );
                    System.out.println(dpTemp.getObjName());//.replace("out","") );
                    cl.add(dpTemp.getObjName());//.replace("out",""));
		}

            }

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
        return v;
    }
    
    public void kmeanDisplay(Vector v[],String ft[],int clsSize)
    {
        String topic[] = cls1.split("#");
        try
        {
            DefaultTableModel dm1=(DefaultTableModel)jTable1.getModel();

            for (int i = 0; i < v.length; i++)
            {
                Vector v1=new Vector();
                //v1.add("C"+(i+1));
                v1.add(topic[i].substring(1));
                String cc="";
                
                for(int j=0;j<v[i].size();j++)
                    cc=cc+v[i].get(j).toString()+",";

                cc=cc.substring(0,cc.lastIndexOf(","));
                v1.add(cc);
                dm1.addRow(v1);
            }
            int row1=dm1.getRowCount();


            System.out.println("------- kmeans------");
            int ac1=0;
             for(int i=0;i<row1;i++)
             {
                 String sg=dm1.getValueAt(i, 1).toString();
                 sg = sg.replaceAll("[^\\p{L}]", "");
                 sg=sg.replace(".pdf","#");
                 String sd[]=sg.split("#");

                 int cn=0;
                 int g=0;
                 ArrayList al=new ArrayList();
                 al.add(sd[0]);
                  for(int k1=1;k1<sd.length;k1++)
                  {
                      String sa1=sd[k1];
                      if(al.contains(sa1))
                      {
                          cn++;
                      }
                       else
                      {
                          al.add(sa1);
                      }

                  }
                 ac1=ac1+cn+1;
                // System.out.println("ag "+sg + " :  "+cn);
             }

             System.out.println("km === "+ac1);
           double d1=(Double.parseDouble(String.valueOf(ac1))/Double.parseDouble(String.valueOf(ft.length)))*50;
           DecimalFormat df=new DecimalFormat();
           
           //jLabel4.setText(String.valueOf(d1));
           //jLabel4.setText(df.format(d1));
           k = df.format(d1).toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void somDisplay(Vector v[],String ft[],int clsSize)
    {
        String topic[] = cls2.split("#");
        try
        {
            DefaultTableModel dm1=(DefaultTableModel)jTable2.getModel();

            for (int i = 0; i < v.length; i++)
            {
                Vector v1=new Vector();
                //v1.add("C"+(i+1));
                v1.add(topic[i].substring(1));
                String cc="";
                
                for(int j=0;j<v[i].size();j++)
                    cc=cc+v[i].get(j).toString()+",";

                cc=cc.substring(0,cc.lastIndexOf(","));
                v1.add(cc);
                dm1.addRow(v1);
            }
            int row1=dm1.getRowCount();


            System.out.println("------- som------");
            int ac1=0;
             for(int i=0;i<row1;i++)
             {
                 String sg=dm1.getValueAt(i, 1).toString();
                 sg = sg.replaceAll("[^\\p{L}]", "");
                 sg=sg.replace("txt","#");
                 String sd[]=sg.split("#");

                 int cn=0;
                 int g=0;
                 ArrayList al=new ArrayList();
                 al.add(sd[0]);
                  for(int k1=1;k1<sd.length;k1++)
                  {
                      String sa1=sd[k1];
                      if(al.contains(sa1))
                      {
                          cn++;
                      }
                       else
                      {
                          al.add(sa1);
                      }

                  }
                 ac1=ac1+cn+1;
                // System.out.println("ag "+sg + " :  "+cn);
             }
            System.out.println("som === "+ac1);
          
            double d1=(Double.parseDouble(String.valueOf(ac1))/Double.parseDouble(String.valueOf(ft.length)))*100;
           DecimalFormat df=new DecimalFormat();
           
           //jLabel4.setText(String.valueOf(d1));
           //jLabel6.setText(df.format(d1));
          o = df.format(d1).toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void kmeans(Vector v[],String ft[],int clsSize)
    {
        try
        {
            DefaultTableModel dm1=(DefaultTableModel)jTable1.getModel();

            for (int i = 0; i < v.length; i++)
            {
                Vector vv=new Vector();
		Vector tempV = v[i];
		//System.out.println("-----------Cluster" + i + "---------");
		Iterator iter = tempV.iterator();
		ArrayList cl=new ArrayList();
                vv.add("C"+(i));
		while (iter.hasNext())
		{
                    DataPoint dpTemp = (DataPoint) iter.next();
                    //System.out.println(dpTemp.getObjName() );
                    System.out.println(dpTemp.getObjName());//.replace("out","") );
                    cl.add(dpTemp.getObjName());//.replace("out",""));

		}
                vv.add(cl);
                dm1.addRow(vv);

            }
            int row1=dm1.getRowCount();


            System.out.println("------- kmeans------");
            int ac1=0;
             for(int i=0;i<row1;i++)
             {
                 String sg=dm1.getValueAt(i, 1).toString();
                 sg = sg.replaceAll("[^\\p{L}]", "");
                 sg=sg.replace("txt","#");
                 String sd[]=sg.split("#");

                 int cn=0;
                 int g=0;
                 ArrayList al=new ArrayList();
                 al.add(sd[0]);
                  for(int k1=1;k1<sd.length;k1++)
                  {
                      String sa1=sd[k1];
                      if(al.contains(sa1))
                      {
                          cn++;
                      }
                       else
                      {
                          al.add(sa1);
                      }

                  }
                 ac1=ac1+cn+1;
                // System.out.println("ag "+sg + " :  "+cn);
             }

           double d1=(Double.parseDouble(String.valueOf(ac1))/Double.parseDouble(String.valueOf(ft.length)))*100;
           DecimalFormat df=new DecimalFormat();
           
           //jLabel4.setText(String.valueOf(d1));
           jLabel4.setText(df.format(d1));

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void SOM(File fe,double cs[],int sm)
    {
        try
        {

            ArrayList al=new ArrayList();

            for(int i=0;i<cs.length;i++)
            {
                if(!(al.contains(cs[i])))
                {
                     al.add(cs[i]);
                }
			//System.out.println(cs[i]);
            }

            DefaultTableModel dm=(DefaultTableModel)jTable2.getModel();

            File files[]=fe.listFiles();
            for(int i=0;i<al.size();i++)
            {
                double sg=Double.parseDouble(al.get(i).toString());

                System.out.println("cluster "+i);
                Vector v=new Vector();
                v.add("Cluster "+i);
                String ss="";
                for(int j=0;j<cs.length;j++)
                {
                    // System.out.println(cs[j]);
                     if(sg==cs[j])
                     {
                         ss=ss+files[j].getName()+" , ";
                         System.out.println(files[j].getName());
                     }
                 }
                 ss=ss.substring(0, ss.lastIndexOf(","));
                 v.add(ss);
                 dm.addRow(v);
                 System.out.println("---------");
            }
            
            int sm1=0;
            int row4=dm.getRowCount();
             for(int i=0;i<row4;i++)
             {
                 String sg=dm.getValueAt(i, 1).toString();
                 sg = sg.replaceAll("[^\\p{L}]", "");
                 sg=sg.replace("txt","#");
                 String sd[]=sg.split("#");

                 int cn=0;
                 int g=0;
                 ArrayList al1=new ArrayList();
                 al1.add(sd[0]);
                  for(int k1=1;k1<sd.length;k1++)
                  {
                      String sa1=sd[k1];
                      if(al1.contains(sa1))
                      {
                          cn++;
                      }
                       else
                      {
                          al1.add(sa1);
                      }

                  }
                 sm1=sm1+cn+1;
                // System.out.println("ag "+sg + " :  "+cn);
             }
             int ft=fe.list().length;
             double d4=(Double.parseDouble(String.valueOf(sm1))/Double.parseDouble(String.valueOf(ft)))*100;
             double sc=d4/100;
            double n4=(sc/Double.parseDouble(String.valueOf(ft)))*Double.parseDouble(String.valueOf(ft))*Double.parseDouble(String.valueOf(ft));
            double p4=Math.log(n4/((sm-1)))+1;
            double d5=n4*p4;

            DecimalFormat df=new DecimalFormat();
            jLabel6.setText(df.format(d4));
            
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String getFileList(){
        String fileList = "";
        File folder = new File("content");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
        if (file.isFile()) {
            fileList+=file.getName()+"#";
        }
    }       
        return fileList;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OTMMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OTMMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OTMMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OTMMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OTMMFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    public javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}
