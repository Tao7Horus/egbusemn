// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import org.w3c.dom.Text;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import common.Log;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import oracle.xml.parser.v2.XMLDocument;

public class UM_COV_ConiA011c
{
    public static void PutValues(final XMLDocument doc, final String[] node) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            tmpNodeList.item(0).appendChild(doc.createTextNode(node[0]).cloneNode(true));
        }
        catch (NullPointerException ne) {}
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.PutValues() Exception : ");
            Log.errors("Exception Reason occurs : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void licensePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (index == 0) {
                findex = 0;
            }
            else {
                findex = index;
            }
            if (first == 1) {
                addEle = doc.createElement("LicenItem");
                final Node ageNode = doc.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xemt);
            nextNode = tmpNodeList.item(findex).appendChild(addEle);
            addEle = doc.createElement(nodeNameType[1]);
            addText = doc.createTextNode(node[0]);
            addEle.appendChild(addText);
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.licensePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void dpPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (index == 0) {
                findex = 0;
            }
            else {
                findex = index;
            }
            if (first == 1) {
                addEle = doc.createElement("CeoItem");
                final Node ageNode = doc.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xemt);
            nextNode = tmpNodeList.item(findex).appendChild(addEle);
            addEle = doc.createElement(nodeNameType[1]);
            addText = doc.createTextNode(node[0]);
            addEle.appendChild(addText);
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception Reason occurs : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void facPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (index == 0) {
                findex = 0;
            }
            else {
                findex = index;
            }
            if (first == 1) {
                addEle = doc.createElement("FacItem");
                final Node ageNode = doc.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xemt);
            if (nodeNameType[0] == "cc:PostCode.Identifier") {
                addEle = doc.createElement("Address.Details");
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
                addEle = doc.createElement(nodeNameType[0]);
                nextNode = nextNode.appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Address.Line1.Text") {
                addEle = doc.createElement("cc:Address.Line1.Text");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem/Address.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Address.Line2.Text") {
                addEle = doc.createElement("cc:Address.Line2.Text");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem/Address.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Telephone.Number.Text") {
                addEle = doc.createElement("Contact.Details");
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
                addEle = doc.createElement(nodeNameType[0]);
                nextNode = nextNode.appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Fax.Number.Text") {
                addEle = doc.createElement("cc:Fax.Number.Text");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem/Contact.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else {
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            addEle = doc.createElement(nodeNameType[1]);
            addText = doc.createTextNode(node[0]);
            addEle.appendChild(addText);
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception Reason occurs : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void idPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (index == 0) {
                findex = 0;
            }
            else {
                findex = index;
            }
            if (first == 1) {
                addEle = doc.createElement("RepItem");
                final Node ageNode = doc.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt);
            if (nodeNameType[0] == "cc:Department.Name") {
                addEle = doc.createElement("Employee.Details");
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
                addEle = doc.createElement(nodeNameType[0]);
                nextNode = nextNode.appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Employee.Name") {
                addEle = doc.createElement("cc:Employee.Name");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Telephone.Number.Text") {
                addEle = doc.createElement("cc:Telephone.Number.Text");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Fax.Number.Text") {
                addEle = doc.createElement("cc:Fax.Number.Text");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Email.Address.Text") {
                addEle = doc.createElement("cc:Email.Address.Text");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "Employee.Title.Name") {
                addEle = doc.createElement("Employee.Title.Name");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "cc:Person.Identifier") {
                addEle = doc.createElement("cc:Person.Identifier");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else if (nodeNameType[0] == "PCS.Number.Text") {
                addEle = doc.createElement("PCS.Number.Text");
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            else {
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
            }
            addEle = doc.createElement(nodeNameType[1]);
            addText = doc.createTextNode(node[0]);
            addEle.appendChild(addText);
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void jpPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (index == 0) {
                findex = 0;
            }
            else {
                findex = index;
            }
            if (first == 1) {
                addEle = doc.createElement("SupItem");
                final Node ageNode = doc.selectNodes("/gb:Gsacid/SupList/SupItem", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Gsacid/SupList/SupItem", (NSResolver)xemt);
            nextNode = tmpNodeList.item(findex).appendChild(addEle);
            addEle = doc.createElement(nodeNameType[1]);
            addText = doc.createTextNode(node[0]);
            addEle.appendChild(addText);
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception Reason occurs : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void maPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (index == 0) {
                findex = 0;
            }
            else {
                findex = index;
            }
            if (first == 1) {
                addEle = doc.createElement("ManItem");
                final Node ageNode = doc.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xemt);
            nextNode = tmpNodeList.item(findex).appendChild(addEle);
            addEle = doc.createElement(nodeNameType[1]);
            addText = doc.createTextNode(node[0]);
            addEle.appendChild(addText);
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception Reason occurs : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
}
