package com.hongfei.util.xml.parser;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlParser
{

	public Document openFile(String filePath) throws DocumentException 
	{
	
		  File file = new File(filePath);
          SAXReader reader = new SAXReader();
      
          Document doc = reader.read(file);
         
          return doc;
	}
	
	
	
}
