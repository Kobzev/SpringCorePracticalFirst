package ua.kobzev.theatre.client;

import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * Created by kkobziev on 4/7/16.
 */
public class SoapClient {
    private static final String MESSAGE =
            "<message xmlns=\"http://ua.kobzev.theatre/soap\">Hello Web Service World</message>";
    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public static void main(String ... args){
        SoapClient soapClient = new SoapClient();
        soapClient.customSendAndReceive();
    }

    // send to the configured default URI
    public void simpleSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
    }

    // send to an explicit URI
    public void customSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/soap", source, result);
    }
}
