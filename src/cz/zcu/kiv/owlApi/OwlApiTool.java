package cz.zcu.kiv.owlApi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import cz.zcu.kiv.jenaBean.JenaBeanTool;
import java.io.ByteArrayInputStream;
import org.apache.log4j.Logger;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 * This class provides transformation from the InputStream, where is saved the rdf from
 * JenaBean, to semantic standard (RDF,OWL or Turtle). This class uses OwlAPi
 * tool.
 *
 * @author Dominik Šmíd
 */
public class OwlApiTool implements OwlApi {

    static Logger loggerOwlApi = Logger.getLogger(OwlApiTool.class);
    private InputStream input;
    private OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    private OWLOntology ontology;

    /**
     * Constructor OwlApi.
     *
     * @param jb Instance of JenaBean.
     */
    public OwlApiTool(JenaBeanTool jb) {
        input = jb.getOutputFromJenaBean();
        loggerOwlApi.info("OwlApi - start");
    }

    public InputStream convertToSemanticWeb(String syntax) throws IOException, OWLOntologyCreationException, OWLOntologyStorageException {
        OutputStream out = new ByteArrayOutputStream();
        InputStream output;
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        loggerOwlApi.info("Loading ontology.");
        ontology = manager.loadOntologyFromOntologyDocument(input);
        loggerOwlApi.info("Ontology is loaded.");

        if (syntax.equals("rdf") == true) {
            loggerOwlApi.info("Convert to RDF/XML.");
            manager.saveOntology(ontology, new RDFXMLOntologyFormat(), byos);
        }
        else if(syntax.equals("owl") == true) {
            loggerOwlApi.info("Convert to OWL/XML.");
            manager.saveOntology(ontology, new OWLXMLOntologyFormat(), byos);
        }
        else if(syntax.equals("ttl") == true) {
            loggerOwlApi.info("Convert to Turtle.");
            manager.saveOntology(ontology, new TurtleOntologyFormat(), byos);
        }
        else {
            loggerOwlApi.info("Error. Wrong parameter for the transformation. The output will be empty.");
        }
        loggerOwlApi.info("InputStream was created.");
        byte[] buf = byos.toByteArray();
        output = new ByteArrayInputStream(buf);
        out.flush();
        out.close();
        return output;
    }
}