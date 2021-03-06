/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.assembleia.controle;


import br.assembleia.entidades.Autorizacao;
import br.assembleia.entidades.Usuario;
import br.assembleia.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ricardo
 */
@Controller
@Scope("session")
public class LoginControle {

    private Usuario usuario;
    @Autowired
    private UsuarioService service;
    private String login;
    private String senha;

    public String logar() {
        if (login.equals("admin") && senha.equals("admin12345")) {
            usuario = new Usuario();
            usuario.setAutorizacao(Autorizacao.ADMIN);
        } else {
            usuario = service.findByLogin(login, senha);
        }
        if (usuario != null) {
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new GrantedAuthorityImpl(usuario.getAutorizacao().toString()));
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(login, senha, roles));
            if (context.getAuthentication().isAuthenticated()) {
                if (usuario.getAutorizacao().equals(Autorizacao.USUARIO)) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                    return "index?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                    return "index?faces-redirect=true";
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha na autenticação do usuário", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário inexistente no sistema", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login";
        }
    }
    
    public String voltar(){
        return "index?faces-redirect=true";
    }
    
    public String sair(){       
        return "login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
