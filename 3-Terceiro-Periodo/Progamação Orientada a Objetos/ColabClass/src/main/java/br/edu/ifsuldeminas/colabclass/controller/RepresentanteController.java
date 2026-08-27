package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RepresentanteController {

    @GetMapping("/representante")
    public String painelRepresentante() {

        return "painel-representante";

    }

}