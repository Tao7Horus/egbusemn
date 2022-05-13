// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import common.Log;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import oracle.xml.parser.v2.XMLDocument;

public class UM_COV_ConiA011c
{
    public static void PutValues(final XMLDocument xmlDocument, final String[] array) {
        try {
            xmlDocument.selectNodes(array[1].trim(), (NSResolver)(XMLElement)xmlDocument.getDocumentElement()).item(0).appendChild(xmlDocument.createTextNode(array[0]).cloneNode(true));
        }
        catch (NullPointerException ex2) {}
        catch (Exception ex) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.PutValues() Exception : ");
            Log.errors("Exception 발생사유 : " + ex.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void licensePutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("LicenItem");
                final Node item = xmlDocument.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.licensePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + ex.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void dpPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("CeoItem");
                final Node item = xmlDocument.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + ex.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void facPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("FacItem");
                final Node item = xmlDocument.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Element element2 = xmlDocument.createElement(array2[0]);
            final NodeList selectNodes = xmlDocument.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xmlElement);
            Node node;
            if (array2[0] == "cc:PostCode.Identifier") {
                node = selectNodes.item(n3).appendChild(xmlDocument.createElement("Address.Details")).appendChild(xmlDocument.createElement(array2[0]));
            }
            else if (array2[0] == "cc:Address.Line1.Text") {
                node = xmlDocument.selectNodes("/gb:Gsacid/FacList/FacItem/Address.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Address.Line1.Text"));
            }
            else if (array2[0] == "cc:Address.Line2.Text") {
                node = xmlDocument.selectNodes("/gb:Gsacid/FacList/FacItem/Address.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Address.Line2.Text"));
            }
            else if (array2[0] == "cc:Telephone.Number.Text") {
                node = selectNodes.item(n3).appendChild(xmlDocument.createElement("Contact.Details")).appendChild(xmlDocument.createElement(array2[0]));
            }
            else if (array2[0] == "cc:Fax.Number.Text") {
                node = xmlDocument.selectNodes("/gb:Gsacid/FacList/FacItem/Contact.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Fax.Number.Text"));
            }
            else {
                node = selectNodes.item(n3).appendChild(element2);
            }
            final Element element3 = xmlDocument.createElement(array2[1]);
            element3.appendChild(xmlDocument.createTextNode(array[0]));
            node.appendChild(element3);
        }
        catch (Exception ex) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + ex.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void idPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("RepItem");
                final Node item = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Element element2 = xmlDocument.createElement(array2[0]);
            final NodeList selectNodes = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xmlElement);
            Node node;
            if (array2[0] == "cc:Department.Name") {
                node = selectNodes.item(n3).appendChild(xmlDocument.createElement("Employee.Details")).appendChild(xmlDocument.createElement(array2[0]));
            }
            else if (array2[0] == "cc:Employee.Name") {
                node = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Employee.Name"));
            }
            else if (array2[0] == "cc:Telephone.Number.Text") {
                node = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Telephone.Number.Text"));
            }
            else if (array2[0] == "cc:Fax.Number.Text") {
                node = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Fax.Number.Text"));
            }
            else if (array2[0] == "cc:Email.Address.Text") {
                node = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Email.Address.Text"));
            }
            else if (array2[0] == "Employee.Title.Name") {
                node = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Employee.Title.Name"));
            }
            else if (array2[0] == "cc:Person.Identifier") {
                node = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Person.Identifier"));
            }
            else if (array2[0] == "PCS.Number.Text") {
                node = xmlDocument.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("PCS.Number.Text"));
            }
            else {
                node = selectNodes.item(n3).appendChild(element2);
            }
            final Element element3 = xmlDocument.createElement(array2[1]);
            element3.appendChild(xmlDocument.createTextNode(array[0]));
            node.appendChild(element3);
        }
        catch (Exception ex) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + ex.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void jpPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("SupItem");
                final Node item = xmlDocument.selectNodes("/gb:Gsacid/SupList/SupItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Gsacid/SupList/SupItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + ex.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void maPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("ManItem");
                final Node item = xmlDocument.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + ex.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
}
