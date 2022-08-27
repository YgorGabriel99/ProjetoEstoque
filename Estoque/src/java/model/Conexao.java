package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name= "Conexao", urlPatterns = {"/Conexao"})
public class Conexao extends HttpServlet {
    
    private static Connection conexao = null;
    
    public static Connection criaConexao() throws SQLException {
       
        if(conexao == null) {
             try {
        
                Class.forName("com.mysql.jdbc.Driver");  
                System.out.println("Driver foi Carregado");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
                System.out.println("Conexao realizada com sucesso!");
            }
             catch(ClassNotFoundException e){
                  System.out.println("O Driver não foi localizado");
             }
        }
        return conexao;
    }
}