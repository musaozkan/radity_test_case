package service;

import model.*;
import service.Formatter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

public class VelocityRenderer {

    public static String renderInvoice(Invoice invoice, Client client, Company company) throws Exception {
        Properties props = new Properties();
        props.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine velocityEngine = new VelocityEngine(props);
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("template/invoice_template.vm");

        VelocityContext context = new VelocityContext();

        context.put("invoice", invoice);
        context.put("address", invoice.getBillingAddress());
        context.put("items", invoice.getItems());
        context.put("client", client);
        context.put("company", company);

        context.put("formatter", new Formatter());

        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        return writer.toString();
    }

}
