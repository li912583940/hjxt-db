
/*
 * 
 */

package com.daswebsrv;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

import com.entlogic.jgxt.realtime.webservices.RtInterface;

/**
 * This class was generated by Apache CXF 2.2
 * Sun Sep 24 14:30:34 CST 2017
 * Generated source version: 2.2
 * 
 */


@WebServiceClient(name = "DasWebSrv", 
                  wsdlLocation = "http://10.13.248.170:8081/DasWebSrv?wsdl",
                  targetNamespace = "http://10.13.248.170:8081/DasWebSrv.wsdl") 
public class DasWebSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://10.13.248.170:8081/DasWebSrv.wsdl", "DasWebSrv");
    public final static QName DasWebSrv = new QName("http://10.13.248.170:8081/DasWebSrv.wsdl", "DasWebSrv");
    static {
        URL url = null;
        try {
            url = new URL("http://10.13.248.170:8081/DasWebSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://10.13.248.170:8081/DasWebSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public DasWebSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DasWebSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DasWebSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns DasWebSrvPortType
     */
    @WebEndpoint(name = "DasWebSrv")
    public DasWebSrvPortType getDasWebSrv() {
        return super.getPort(DasWebSrv, DasWebSrvPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DasWebSrvPortType
     */
    @WebEndpoint(name = "DasWebSrv")
    public DasWebSrvPortType getDasWebSrv(WebServiceFeature... features) {
        return super.getPort(new QName("http://10.13.248.170:8081/DasWebSrv.wsdl", "DasWebSrv"), DasWebSrvPortType.class);
    }
    
}
