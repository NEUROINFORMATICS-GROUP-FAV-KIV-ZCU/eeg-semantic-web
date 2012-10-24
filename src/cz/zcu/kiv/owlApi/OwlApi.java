package cz.zcu.kiv.owlApi;

import java.io.IOException;
import java.io.InputStream;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 * Interface for OwlApi.
 *
 * @author Dominik Šmíd
 */
public interface OwlApi {

    /**
     * Transformation to semantic standards.
     *
     * @param syntax Semantic syntax (rdf = RDF/XML, owl = OWL/XML or ttl = Turtle).
     * @return InputStream with the selected syntax.
     */
    public InputStream convertToSemanticWeb(String syntax) throws IOException, OWLOntologyCreationException, OWLOntologyStorageException;
}
