package net.sourceforge.beanoa.example.xml.apache;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public final class XalanXPathExample {

	public static void main(String...args){
	
		try{
			final XPath _xpath = XPathFactory.newInstance().newXPath();
			
			final BetwixtExample _apache = new BetwixtExample();
			NodeList _nodes = (NodeList)_xpath.evaluate("/customer[age='28']", new InputSource(new StringReader(_apache.toXML())), XPathConstants.NODESET);
			doPrint(_nodes);
			
			_nodes = (NodeList)_xpath.evaluate("/customer/age/text()", new InputSource(new StringReader(_apache.toXML())), XPathConstants.NODESET);
			doPrint(_nodes);
			
			_nodes = (NodeList)_xpath.evaluate("attribute::customer/age", new InputSource(new StringReader(_apache.toXML())), XPathConstants.NODESET);
			doPrint(_nodes);
			
			_nodes = (NodeList)_xpath.evaluate("child::customer/age", new InputSource(new StringReader(_apache.toXML())), XPathConstants.NODESET);
			doPrint(_nodes);
			
			//Teste 2
			final XPath _xpath2 = XPathFactory.newInstance().newXPath();
			
			final String _xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
"<AddressesTypes>" +
  "<type>"+
    "<code>2</code>"+
    "<description>RESIDENCIAL</description>"+
  "</type>"+
  
  "<type>"+
    "<code>2</code>"+
    "<description>COMERCIAL</description>"+
  "</type>"+
  
  "<type>"+
    "<code>3</code>"+
    "<description>CORRESPONDENCIA</description>"+
  "</type>"+
"</AddressesTypes>";
			final InputSource _xsource = new InputSource(new StringReader(_xml));
			//_nodes = (NodeList)_xpath2.evaluate("/AddressesTypes/type[code='2']", _xsource, XPathConstants.NODESET);
			_nodes = (NodeList)_xpath2.evaluate("/AddressesTypes/type/code/text()", _xsource, XPathConstants.NODESET);
			System.out.println("xpath 2");
			doPrint(_nodes);
			final Node _node = _nodes.item(0);
			final Node _found = search(_node, "/AddressesTypes/type", new StringBuilder());
			System.out.println(XPathAPI.selectSingleNode(_found, "description/text()").getNodeValue());
			//final Node _node = (Node)_xpath2.evaluate("/AddressesTypes/type[code='2']", _xsource, XPathConstants.NODE);
			
			//System.out.println("length: " + org.apache.xpath.XPathAPI.selectNodeList(_node, "description/text()").getLength());
			
			//System.out.println("NODE " + _node.);
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}
	
	public static void doPrint(final NodeList nlist){
		
		if(nlist.getLength() == 0){
			System.out.println("no results!");
		}else {
			
			for(int _index = 0; _index < nlist.getLength(); _index++){
				final Node _node = nlist.item(_index);
				
				System.out.println("child length = " + _node.getChildNodes().getLength());
				System.out.println("nodeValue    = " + _node.getNodeValue());
				System.out.println("nodeName     = " + _node.getNodeName());
				System.out.println(parentsPath(_node, ""));
				System.out.println("Search:");
				System.out.println(search2(_node, "/AddressesTypes", new StringBuilder()));
				System.out.println(search2(_node, "/AddressesTypes/type", new StringBuilder()));
				System.out.println(search2(_node, "/AddressesTypes/type/code", new StringBuilder()));
				System.out.println(search2(_node, "/", new StringBuilder()));//return #document
				System.out.println(search2(_node, "", new StringBuilder()));//return #document
			}
		}
	}
	
	public static String parentsPath(final Node node, String path){
		if(node.getParentNode() != null && node.getParentNode().getNodeType() != Node.DOCUMENT_NODE){
			path += parentsPath(node.getParentNode(), path) + "/" + node.getParentNode().getNodeName();
		}
		
		return path;
	}
	
	public static Node search2(final Node in, final String prefix, final StringBuilder path){
		

		Node _result = null;
		if(null!= in){
			_result = search2(in.getParentNode(), prefix, path);
			
			//is this the root node?
			if(in.getNodeType() != Node.DOCUMENT_NODE){
				path.append(in.getNodeName());
			}
			
			if(path.toString().equals(prefix) ||
					((prefix.equals("/") || prefix.equals("")) && in.getNodeType() == Node.DOCUMENT_NODE)){
				return in;
			}
			path.append("/");
		}
		
		return _result;
	}
	
	public static Node search(final Node in, final String prefix, StringBuilder path){
		
		final Node _parent = in.getParentNode();
		Node _result = _parent;
		if(null!= _parent){
			if(_parent.getNodeType() != Node.DOCUMENT_NODE){
				//button-up 
				_result = search(_parent, prefix, path);
				if(path.toString().equals(prefix)){
					return _result;
				} else {
					path.append("/" + _parent.getNodeName());
				}
			} else if(prefix.equals("/")) {
				return _parent;
			}
		}
		
		return _parent;
	}
}
