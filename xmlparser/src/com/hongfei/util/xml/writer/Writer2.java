package com.hongfei.util.xml.writer;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Writer2 {

	private String dataSet = "WA_SOURCE_0009";
	private String namespace = "S003";
	private String element = "B050023";
	private String r1 = "and";
	private String r2 = "eq";
	private String v1 = "13699666699";
	private String tagId = "A0001";
	private String tagDescription = "测试";
	private String t1 = "Z002062";
	private String t2 = "Z002063";
	private String date = "20180801-20180805";
	private String exportorId = "0001";
	
	
    public Document createDocument()
    {
    	Document document = DocumentHelper.createDocument();
        
        //公共Condition (映射关系)
        Element conditions = DocumentHelper.createElement("Conditions")
        		.addAttribute("Rel", r1);
        
        //公共ExportDate
        Element exportDate = DocumentHelper.createElement("ExportDate")
        		.addAttribute("Description", "example:YYYYMMDD")
        		.addAttribute("Value", date);
        
        //公共FieldSetting
        Element fieldSetting = DocumentHelper.createElement("FieldSetting");
        
        Element field1 = DocumentHelper.createElement("Field")
        		.addAttribute("Element", t1);
        
        Element expression1 = DocumentHelper.createElement("Expression")
        		.addAttribute("Function", "const");
        
        Element param1 = DocumentHelper.createElement("Param")
        		.addAttribute("Name", "element")
        		.addAttribute("Value", tagId);
        
        expression1.add(param1);
        field1.add(expression1);
        
        Element field2 = DocumentHelper.createElement("Field")
        		.addAttribute("Element", t2);
        
        Element expression2 = DocumentHelper.createElement("Expression")
        		.addAttribute("Function", "concat");
        
        Element param2_1 = DocumentHelper.createElement("Param")
        		.addAttribute("Name", "const")
        		.addAttribute("Value", tagId + "_");
        
        Element param2_2 = DocumentHelper.createElement("Param")
        		.addAttribute("Name", "const")
        		.addAttribute("Value", tagDescription);
        
        expression2.add(param2_1);
        expression2.add(param2_2);
        field2.add(expression2);
        
        fieldSetting.add(field1);
        fieldSetting.add(field2);
        
        // 拼装XML
    	Element policyFile = document.addElement("PolicyFile")
    			.addAttribute("Name", "DataExport")
    			.addAttribute("Description", "DataExportPolicy");
        
    	Element category = policyFile.addElement("Category")
    			  .addAttribute("Storage", "DB");
    			  
    	Element group = category.addElement("Group")
    			  .addAttribute("ExportType", "shi")
    			  .addAttribute("Enable", "true");
    	
    	
        //Exportor List
        for (int i = 0; i < 3; i++) 
        {
            Element exportor = DocumentHelper.createElement("Exportor")
            		.addAttribute("ExportorId", exportorId)
            		.addAttribute("DataSet", dataSet)
            		.addAttribute("Namespace", namespace)
            		.addAttribute("IsDistill", "true")
            		.addAttribute("Enable", "true")
            		.addAttribute("Desc", "")
            		.addComment("------------------------------------ " + dataSet + "  " + tagId +  " ------------------------------------ ");
            
            Element conditionsCp = conditions.createCopy();
            Element exportDateCp = exportDate.createCopy();
            Element fieldSettingCp = fieldSetting.createCopy();
            
            //Condition不同, 可添加Conditon set方法
            conditionsCp.addElement("Condition")
			            .addAttribute("Fmt", "")
			    		.addAttribute("Key", element)
			    		.addAttribute("Opr", r2)
			    		.addAttribute("Value", v1);
          
            exportor.add(conditionsCp);
            exportor.add(exportDateCp);
            exportor.add(fieldSettingCp);
            
            group.add(exportor);
		}
        
        return document;
    }
	
    public void write(Document document) throws IOException {

    	OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding("UTF-8");
    	
        try (FileWriter fileWriter = new FileWriter("D:/output2.xml")) {
            XMLWriter writer = new XMLWriter(fileWriter, format);
            writer.write( document );
            writer.close();
        }
    }
	
    
    public static void main(String[] args) throws IOException {
		
    	Writer2 writer = new Writer2();
    	
    	writer.write(writer.createDocument());
	}
    
}
