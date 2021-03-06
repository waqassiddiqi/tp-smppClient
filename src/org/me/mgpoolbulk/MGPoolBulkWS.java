
package org.me.mgpoolbulk;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MGPoolBulkWS", targetNamespace = "http://MGPoolBulk.me.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MGPoolBulkWS {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<org.me.mgpoolbulk.StringArray>
     */
    @WebMethod(operationName = "GetServiceMsgs")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "GetServiceMsgs", targetNamespace = "http://MGPoolBulk.me.org/", className = "org.me.mgpoolbulk.GetServiceMsgs")
    @ResponseWrapper(localName = "GetServiceMsgsResponse", targetNamespace = "http://MGPoolBulk.me.org/", className = "org.me.mgpoolbulk.GetServiceMsgsResponse")
    @Action(input = "http://MGPoolBulk.me.org/MGPoolBulkWS/GetServiceMsgsRequest", output = "http://MGPoolBulk.me.org/MGPoolBulkWS/GetServiceMsgsResponse")
    public List<StringArray> getServiceMsgs(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

}
