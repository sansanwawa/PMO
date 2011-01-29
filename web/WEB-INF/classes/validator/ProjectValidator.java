/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import model.Project;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
//import org.springframework.util.StringUtils;

/**
 *
 * @author sandy
 */
public class ProjectValidator implements Validator {

    public boolean supports(Class clazz) {
      return Project.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
    }
}
