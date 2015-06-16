package cat.udl.iaeste.intraweb.services;

import cat.udl.iaeste.intraweb.models.Company;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.xquery.*;

/**
 * Created by eduard on 21/05/15.
 */
public class XQueryHelper {
    private static final Logger log = Logger.getLogger(XQueryHelper.class.getName());

    private XQPreparedExpression expr;
    private XQConnection conn;

    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;

    static final String apiURL = "http://api.opencorporates.com";
    private String companiesXQ;

    public XQueryHelper(String company) {

        URL url = null;
        URLConnection urlconn = null;
        XQDataSource xqds = null;
        try {
            url = new URL(apiURL);
            urlconn = url.openConnection();
            urlconn.setReadTimeout(50000);
            xqds = (XQDataSource) Class.forName("net.sf.saxon.xqj.SaxonXQDataSource").newInstance();
            this.conn = xqds.getConnection();
            companiesXQ = "declare variable $doc := doc(\"https://api.opencorporates.com/v0.4/companies/search?q="
                    + company + "&amp;jurisdiction_code=es&amp;format=xml\");"
                    + "for $c in $doc//company\n"
                    + "where $c/jurisdiction-code =\"es\""
                    + "return \n"
                    + "<company>\n"
                    + "<name>{string($c/name)}</name>\n"
                    + "<headquarters>{string($c/registered-address-in-full)}</headquarters>\n"
                    + "<foundationDate>{data($c/created-at)}</foundationDate>\n"
                    + "<phoneNumber>{data($c/company-number)}</phoneNumber>\n"
                    + "</company>\n";
            this.expr = conn.prepareExpression(companiesXQ);
            this.jaxbContext = JAXBContext.newInstance(Company.class);
            this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Company> getCompanies() {
        ArrayList<Company> companies = new ArrayList<>();
        try {
            XQResultSequence rs = this.expr.executeQuery();
            while (rs.next()) {
                XQItem item = rs.getItem();
                Company company = (Company) jaxbUnmarshaller.unmarshal(item.getNode());
                companies.add(company);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            close();
        }
        return companies;
    }

    private void close() {
        try {
            this.expr.close();
            this.conn.close();
        } catch (XQException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

}