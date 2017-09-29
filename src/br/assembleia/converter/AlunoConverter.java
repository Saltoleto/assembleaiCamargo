package br.assembleia.converter;

import br.assembleia.entidades.Aluno;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component("alunoConverter")
public class AlunoConverter implements Converter {

    @PersistenceContext(unitName = "WeHaveSciencePU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Aluno p = new Aluno();
        p = em.find(Aluno.class, Long.valueOf(value));
        return p;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
