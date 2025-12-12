package com.spring.Ecommerce.controller;

import com.spring.Ecommerce.model.Usuario;
import com.spring.Ecommerce.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    // Alias /login -> evita 404 caso alguem faça redirect:/login
    @GetMapping("/login")
    public String loginAlias() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "registro_usuario"; // templates/registro_usuario.html
    }

    @PostMapping("/cadastrar")
    public String formCadastro(Usuario usuario, RedirectAttributes attr) {
        usuarioRepository.save(usuario);
        attr.addFlashAttribute("mensagem", "Cadastro realizado com sucesso");
        return "redirect:/"; // volta pra tela de login
    }

    @PostMapping("/entrar")
    public String entrar(String email, String senha, RedirectAttributes attr, HttpSession session) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            session.setAttribute("usuariologado", usuario);

            if ("ADMIN".equals(usuario.getFuncao())) {
                // CORREÇÃO: rota correta é /admin/painel
                return "redirect:/admin/painel";
            } else {
                // rota da vitrine (padronizei para /vitrine)
                return "redirect:/vitrine";
            }
        } else {
            attr.addFlashAttribute("erro", "Email ou senha incorreta");
            return "redirect:/";
        }
    }

    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @ModelAttribute("usuariologado")
    public Usuario usuarioLogado(HttpSession session) {
        return (Usuario) session.getAttribute("usuariologado");
    }

}
