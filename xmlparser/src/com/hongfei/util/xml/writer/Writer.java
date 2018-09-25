package com.hongfei.util.xml.writer;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Writer {

	private String dataSet = "WA_SOURCE_0009";
	private String element = "B050023";
	private String r1 = "and";
	private String n1 = "dl_cmp";
	private String r2 = "eq";
	private String v1 = "13699666699";
	private String op1 = "data";
	private String tagId = "A0001";
	private String tagDescription = "测试";
	private String t1 = "Z002062";
	private String t2 = "Z002063";
	
    public Document createDocument()
    {
    	Document document = DocumentHelper.createDocument();
        
        //公共Condition (映射关系)
        Element conditions = DocumentHelper.createElement("Conditions")
        		.addAttribute("relationship", r1)
        		.addAttribute("description", "");
        
        Element operation = DocumentHelper.createElement("Operation")
        		.addAttribute("name", n1)
        		.addAttribute("expect", r2)
        		.addAttribute("value", v1)
        		.addAttribute("description", "");
        
        Element operand = DocumentHelper.createElement("Operand")
        		.addAttribute("name", op1)
			    .addAttribute("value_len", "-1")
			    .addAttribute("value_start", "0")
			    .addAttribute("value_end", "-1");
        		
       operation.add(operand); 
       conditions.add(operation);
        
        //公共Action
        Element actions = DocumentHelper.createElement("Actions");
        
        Element action1 = DocumentHelper.createElement("Action")
        		.addAttribute("conditions", "true")
        		.addAttribute("name", "add")
        		.addAttribute("value", tagId)
        		.addAttribute("value_to", t1)
        		.addAttribute("sepatate", ",");
        
        Element action2 = DocumentHelper.createElement("Action")
        		.addAttribute("conditions", "true")
        		.addAttribute("name", "add")
        		.addAttribute("value", tagId + "_" + tagDescription)
        		.addAttribute("value_to", t2)
        		.addAttribute("sepatate", ",");

        actions.add(action1);
        actions.add(action2);
        
        // 拼装XML
    	Element labelsFile = document.addElement("LabelsFile")
    			.addAttribute("namespace", "YCL003")
    			.addAttribute("version", "1.0")
    			.addAttribute("buildtime", "")
    			.addAttribute("description", "");
        
        //Label List
        for (int i = 0; i < 3; i++) 
        {
        	Element labels = DocumentHelper.createElement("Labels").addAttribute("name", dataSet);
            Element label = DocumentHelper.createElement("Label");
            
            Element condition = conditions.createCopy();
            Element action = actions.createCopy();
            
            //Condition不同, 可添加Conditon set方法
            condition.element("Operation").element("Operand").addAttribute("value_from", element);
          
            label.add(condition);
            label.add(action);
            
            labels.addComment("------------------------------------ " + dataSet + "  " + tagId +  " ------------------------------------ ");
            labelsFile.add(labels);
            labels.add(label);
		}
        
        return document;
    }
	
    public void write(Document document) throws IOException {

    	OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding("UTF-8");
    	
        try (FileWriter fileWriter = new FileWriter("D:/output1.xml")) {
            XMLWriter writer = new XMLWriter(fileWriter, format);
            writer.write( document );
            writer.close();
        }
    }
	
    
    public static void main(String[] args) throws IOException {
		
    	Writer writer = new Writer();
    	
    	writer.write(writer.createDocument());
	}
    
}
