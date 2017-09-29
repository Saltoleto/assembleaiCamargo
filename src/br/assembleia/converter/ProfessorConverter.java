package br.assembleia.converter;

import br.assembleia.entidades.Professor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component("professorConverter")
public class ProfessorConverter implements Converter {

    @PersistenceContext(unitName = "WeHaveSciencePU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Professor p = new Professor();
        p = em.find(Professor.class, Long.valueOf(value));
        return p;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
