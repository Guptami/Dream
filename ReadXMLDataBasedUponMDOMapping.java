package Excel;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLDataBasedUponMDOMapping {


	public static void main(String args[]) throws SQLException {
		Long start=System.currentTimeMillis();
		String Query="SELECT OBJECTTYPE,SAPFIELD,FIELDNAME FROM MDOMAPPING WHERE ISNULL(fieldname,'')<>'' and fieldname!='NULL'";
		CustomUtility utility=new CustomUtility();
		List<Map<String,String>> listData=utility.executeQuery(Query, "");
	//	System.out.println(listData);
		
	
		
		String objType = "78578";
		
		List<String> sapField = getSAPFILED(listData);
		HashMap<String, String> xmlData = getXMLData(sapField);
		System.out.println("");
		System.out.println("XMLDATA"+xmlData);
		
		List<String> insertQuery = new ArrayList();
		List<String> columnName = new ArrayList<String>();
		List<String> columnData = new ArrayList<String>();
		for(String l : sapField ) {
			if(xmlData.containsKey(l)) {
				columnName.add(l);
				columnData.add(xmlData.get(l));
			}
		}
		
		String queryColumnName = String.join(",", columnName);
		String queryColumnData = String.join("','", columnData);
		System.out.println("Column Name "+String.join(",", columnName));
		System.out.println("Column Data  "+String.join("','", columnData));
		
		String dataQuery="Insert into DYN_"+objType+"01_"+objType+"("+queryColumnName+") VALUES ("+queryColumnData+")";
		System.out.println(dataQuery);
		long end=System.currentTimeMillis();
		System.out.println("Time taken to read XML and create query for database " + ((end-start)/1000)+" s");

	}


	private static List<String> getSAPFILED(List<Map<String,String>> listData){
		List<String> sapField = new ArrayList<String>();
		for(Map map : listData) {
			sapField.add(map.get("SAPFIELD").toString());
		}
	//	System.out.println(sapField);
		return sapField;
	}
	
	private static HashMap<String, String> getXMLData (List<String> sapFields ){
		List<String> dataQueryList = new ArrayList<String>();
	 	HashMap<String, String> xmlData = new HashMap();
		
		 try {
				File xmlFile = new File("D:\\MDO_S_A\\Develop_SA\\Integration.xml");
				DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docbuildFactory.newDocumentBuilder();
				Document document = docBuilder.parse(xmlFile);
					if (document.hasChildNodes()) {
					//HashMap<String,String> xmlData  =	printNode(document.getChildNodes());
					System.out.println(printNode(document.getChildNodes(),xmlData));
					xmlData = printNode(document.getChildNodes(),xmlData);
				}

		    } catch (Exception e) {
				e.printStackTrace();
		    }
		
		return xmlData;
		
	}
	
	 private static HashMap<String,String> printNode(NodeList nodeList,HashMap<String, String> xmlData) {
		    for (int i = 0; i < nodeList.getLength(); i++) {
			Node tempNode = nodeList.item(i);
			// Ensure that the node is Element node
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// print node name and value
			if (tempNode.hasChildNodes()) {
					// Check if child nodes present.
					xmlData =	printNode(tempNode.getChildNodes(),xmlData);
				}
				if(!tempNode.getNodeName().equalsIgnoreCase("ProjectList")) {
					xmlData.put(tempNode.getNodeName(), tempNode.getTextContent());
				}
			}

		    }
		    return  xmlData;
		  }
	
}

