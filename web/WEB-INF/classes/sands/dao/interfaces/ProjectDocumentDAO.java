/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.interfaces;

import java.util.ArrayList;
import model.ProjectDocument;

/**
 *
 * @author sandy
 */
public interface ProjectDocumentDAO {

    public void save(ProjectDocument projectdocument);

    public void delete(ProjectDocument projectdocument);

    public ArrayList<ProjectDocument> list(int offset);

    public void orderByDesc(String field);

    public void orderByAsc(String field);

    public void setMaxResults(int i);

    public ProjectDocument getById(long id);
}
