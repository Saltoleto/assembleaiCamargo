package br.assembleia.converter;

import br.assembleia.entidades.Acervo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component("acervoConverter")
public class AcervoConverter implements Converter {

    @PersistenceContext(unitName = "WeHaveSciencePU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Acervo p = new Acervo();
        p = em.find(Acervo.class, Long.valueOf(value));
        return p;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
