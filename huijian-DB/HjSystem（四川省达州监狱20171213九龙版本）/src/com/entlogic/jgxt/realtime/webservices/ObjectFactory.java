
package com.entlogic.jgxt.realtime.webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.entlogic.jgxt.realtime.webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegistResponse_QNAME = new QName("http://webServices.realtime.jgxt.entlogic.com/", "registResponse");
    private final static QName _Invoke_QNAME = new QName("http://webServices.realtime.jgxt.entlogic.com/", "invoke");
    private final static QName _InvokeResponse_QNAME = new QName("http://webServices.realtime.jgxt.entlogic.com/", "invokeResponse");
    private final static QName _Test_QNAME = new QName("http://webServices.realtime.jgxt.entlogic.com/", "test");
    private final static QName _TestResponse_QNAME = new QName("http://webServices.realtime.jgxt.entlogic.com/", "testResponse");
    private final static QName _Regist_QNAME = new QName("http://webServices.realtime.jgxt.entlogic.com/", "regist");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.entlogic.jgxt.realtime.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Invoke }
     * 
     */
    public Invoke createInvoke() {
        return new Invoke();
    }

    /**
     * Create an instance of {@link TestResponse }
     * 
     */
    public TestResponse createTestResponse() {
        return new TestResponse();
    }

    /**
     * Create an instance of {@link Test }
     * 
     */
    public Test createTest() {
        return new Test();
    }

    /**
     * Create an instance of {@link InvokeResponse }
     * 
     */
    public InvokeResponse createInvokeResponse() {
        return new InvokeResponse();
    }

    /**
     * Create an instance of {@link RegistResponse }
     * 
     */
    public RegistResponse createRegistResponse() {
        return new RegistResponse();
    }

    /**
     * Create an instance of {@link Regist }
     * 
     */
    public Regist createRegist() {
        return new Regist();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.realtime.jgxt.entlogic.com/", name = "registResponse")
    public JAXBElement<RegistResponse> createRegistResponse(RegistResponse value) {
        return new JAXBElement<RegistResponse>(_RegistResponse_QNAME, RegistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Invoke }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.realtime.jgxt.entlogic.com/", name = "invoke")
    public JAXBElement<Invoke> createInvoke(Invoke value) {
        return new JAXBElement<Invoke>(_Invoke_QNAME, Invoke.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvokeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.realtime.jgxt.entlogic.com/", name = "invokeResponse")
    public JAXBElement<InvokeResponse> createInvokeResponse(InvokeResponse value) {
        return new JAXBElement<InvokeResponse>(_InvokeResponse_QNAME, InvokeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Test }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.realtime.jgxt.entlogic.com/", name = "test")
    public JAXBElement<Test> createTest(Test value) {
        return new JAXBElement<Test>(_Test_QNAME, Test.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.realtime.jgxt.entlogic.com/", name = "testResponse")
    public JAXBElement<TestResponse> createTestResponse(TestResponse value) {
        return new JAXBElement<TestResponse>(_TestResponse_QNAME, TestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Regist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.realtime.jgxt.entlogic.com/", name = "regist")
    public JAXBElement<Regist> createRegist(Regist value) {
        return new JAXBElement<Regist>(_Regist_QNAME, Regist.class, null, value);
    }

}
