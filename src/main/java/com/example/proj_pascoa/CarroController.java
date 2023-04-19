package com.example.proj_pascoa;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CarroController {

    @RequestMapping(method = RequestMethod.POST, value = "/doCadastrar")
    public void cadastrar(HttpServletResponse response, HttpServletRequest request) throws IOException{
        Carro c = new Carro();
        var placa = request.getParameter("placa");
        var ano = Integer.parseInt(request.getParameter("ano"));
        var cor = request.getParameter("cor");
        var marca = request.getParameter("marca");

        c.setAno(ano);
        c.setPlaca(placa);
        c.setCor(cor);
        c.setMarcaModelo(marca);

        CarroDAO dao = new CarroDAO();
        dao.cadastrarCarro(c);

        response.setContentType("text/HTML");
        var writer = response.getWriter();

        writer.println(
            "<html><body>" + 
            "<h1>Carro Cadastrado</h1>"+
            "<p>Placa: " + c.getPlaca()+ "</p>"+
            "<p>Ano: " + c.getAno()+ "</p>"+
            "<p>Cor: " + c.getCor()+ "</p>"+
            "<p>Marca/Modelo: " + c.getMarcaModelo()+ "</p>"+
            "<a href='index.html'><button>Voltar</button></a> "+
            
            
            "</body></html>"    
        );

    }

    @RequestMapping(value = "/doListar", method = RequestMethod.GET)
    public void listarCarros(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        var dao = new CarroDAO();
        var writer = response.getWriter();
        var lista = dao.listarCarros();

        response.setContentType("text/HTML");

        writer.println("<html><body>");
        for(var c1:lista){
            writer.println("<h2>" + c1.getMarcaModelo() + "</h2>");
            writer.println("<p>" + c1.getPlaca() + "</p>");
            writer.println("<p>" + c1.getAno() + "</p>");
            writer.println("<p>" + c1.getCor() + "</p>");
            writer.println("<a href='doEditar?placa=" + c1.getPlaca() + "'><button>Editar</button></a>");
            writer.println("<a href='doExcluir?placa=" + c1.getPlaca() + "'><button>Excluir</button></a>");
        }
        writer.println("</body></html>");
    }

    @RequestMapping(value = "/doBuscar", method = RequestMethod.GET)
    public void buscarCarro(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var placa = request.getParameter("placa");
        var dao = new CarroDAO();

        Carro c = dao.buscarCarro(placa);

        var writer = response.getWriter();

        writer.println("<html><body>");
        if(c != null){
            writer.println("<h2>" + c.getMarcaModelo() + "</h2>");
            writer.println("<p>" + c.getPlaca() + "</p>");
            writer.println("<p>" + c.getAno() + "</p>");
            writer.println("<p>" + c.getCor() + "</p>");
        }else{
            writer.println("Carro nao encontrado");
        }
        writer.println("</body></html>");
    }


    @RequestMapping(value = "/doEditar", method = RequestMethod.GET)
    public void editarCarro(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var placa = request.getParameter("placa");
        var dao = new CarroDAO();
        Carro c = dao.buscarCarro(placa);

        var writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("<form action='/doAtualizar' method='post'> "+
        
        "<input type='hidden' name='placa' id='placaInput' value = '" + c.getPlaca() + "'>" +

        "<label for='anoInput'>Ano</label>" +
        "<input type='number' name='ano' id='anoInput' value = '" + c.getAno() + "'>" +

        "<label for='corInput'>Cor</label>"+
        "<input type='text' name='cor' id='corInput' value = '" + c.getCor() + "'>"+

        "<label for='MarcaInput'>Marca/Modelo</label>"+
        "<input type='text' name='marca' id='MarcaInput' value = '" + c.getMarcaModelo() + "'>"+

        "<button type='submit'>ENVIAR</button>"
        );


        writer.println("</form>" +
                "</body>" +
                "<html>");
    
    }

    @RequestMapping(value = "/doAtualizar", method = RequestMethod.POST)
    public void atualizarCarros(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var placa = request.getParameter("placa");
        var ano = Integer.parseInt(request.getParameter("ano"));
        var cor = request.getParameter("cor");
        var marca = request.getParameter("marca");

        var car = new Carro(placa, ano, cor, marca);
        var dao = new CarroDAO();

        dao.alterarCarro(car);
        
    }

    @RequestMapping(value = "/doExcluir", method = RequestMethod.GET)
    public void excluirCarro(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var placa = request.getParameter("placa");
        var dao = new CarroDAO();
        dao.excluirCarro(placa);
        

        
    }
}
