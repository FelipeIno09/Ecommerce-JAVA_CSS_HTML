package com.spring.Ecommerce.controller;

import com.spring.Ecommerce.model.Usuario;
import jakarta.servlet.http.HttpSession; // Importante
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.spring.Ecommerce.model.Produto;
import com.spring.Ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/novo-produto")
    public ModelAndView novoProduto() {
        ModelAndView mv = new ModelAndView("cadastro_produto");
        mv.addObject("produto", new Produto());
        return mv;
    }

    @GetMapping("/admin/editar/{id}")
    public ModelAndView editarProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        return new ModelAndView("cadastro_produto").addObject("produto", produto);
    }

    @GetMapping("/admin/excluir/{id}")
    public String deletarproduto(@PathVariable long id) {
        produtoRepository.deleteById(id);
        return "redirect:/admin/painel";
    }

    // --- ROTA DO ADMIN (SÓ DEIXE ESTA VERSÃO) ---
    @GetMapping("/admin/painel")
    public ModelAndView painelAdmin(HttpSession session) {
        ModelAndView mv = new ModelAndView("admin_painel");
        mv.addObject("produtos", produtoRepository.findAll());

        // Pega usuario da sessão
        Usuario usuario = (Usuario) session.getAttribute("usuariologado");
        mv.addObject("usuariologado", usuario);

        return mv;
    }

    // --- ROTA DO CLIENTE (VITRINE) ---
    @GetMapping("/vitrine")
    public ModelAndView vitrine(HttpSession session) {
        ModelAndView mv = new ModelAndView("vitrine_produtos");
        mv.addObject("produtos", produtoRepository.findAll());
        mv.addObject("taxaDolar", 5.4);

        // Pega usuario da sessão para mostrar "Olá, Cliente"
        Usuario usuario = (Usuario) session.getAttribute("usuariologado");
        mv.addObject("usuariologado", usuario);

        return mv;
    }

    @GetMapping("/imagem/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> exibirImagem(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto != null && produto.getImagem() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(produto.getImagem());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/salvar-produto")
    public String salvar(@ModelAttribute Produto produto,
                         @RequestParam("file") MultipartFile arquivo) {
        try {
            if (arquivo.isEmpty()) {
                if (produto.getId() != null) {
                    produtoRepository.findById(produto.getId()).ifPresent(antigo -> {
                        produto.setImagem(antigo.getImagem());
                    });
                }
            } else {
                produto.setImagem(arquivo.getBytes());
            }
            produtoRepository.save(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/painel";
    }
}