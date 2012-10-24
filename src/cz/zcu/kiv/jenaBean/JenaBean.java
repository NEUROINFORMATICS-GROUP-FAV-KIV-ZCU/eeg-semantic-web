package cz.zcu.kiv.jenaBean;

import java.io.InputStream;

/**
 * Interface for JenaBeanTool.
 *
 * @author Dominik Šmíd
 */
public interface JenaBean {

    /**
     * Getr for output of this tool.
     *
     * @return Output of JenaBean, where is saved RDF.
     */
    public InputStream getOutputFromJenaBean();
}
