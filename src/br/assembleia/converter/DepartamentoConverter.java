package br.assembleia.converter;

import br.assembleia.entidades.Departamento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component("departamentoConverter")
public class DepartamentoConverter implements Converter {

    @PersistenceContext(unitName = "WeHaveSciencePU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Departamento p = new Departamento();
        p = em.find(Departamento.class, Long.valueOf(value));
        return p;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
