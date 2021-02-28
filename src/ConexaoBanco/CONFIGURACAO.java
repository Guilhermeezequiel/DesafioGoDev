package ConexaoBanco;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Guilherme
 */
public class CONFIGURACAO {

    final private String driver = "com.mysql.jdbc.Driver";
    Connection CONEXAO;
    private final String T_Host = "localhost";
    private final String T_Porta = "3306";
    private final String T_Senha = "";
    private final String T_User = "root";

    public static void main(String[] args) {

        CONFIGURACAO ESTA_TELA = new CONFIGURACAO();//INICIALIZO FAZENDO A PRIMEIRO A VERIFICAÇÃO ANTES DE ABRIR A TELA
        if (ESTA_TELA.CONEXAO_SERVIDOR(ESTA_TELA.T_Host, ESTA_TELA.T_Porta, ESTA_TELA.T_User, ESTA_TELA.T_Senha)){
            ESTA_TELA.CONEXAO_DATABASE(ESTA_TELA.T_Host, ESTA_TELA.T_Porta, ESTA_TELA.T_User, ESTA_TELA.T_Senha);
            ESTA_TELA.CRIA_DATA_BASE_COMPLETO();
            ESTA_TELA.FECHA_CONEXAO();
            System.out.println("BANCO CRIADO/INICIADO COM SUCESSO!");
        }

    }

    private boolean CONEXAO_SERVIDOR(String Servidor, String Porta, String usuario, String Senha) {
        boolean com = false;
        try {
            Class.forName(driver);
            CONEXAO = DriverManager.getConnection("jdbc:mysql://" + Servidor + ":" + Porta + "",
                    usuario, Senha);
            com = true;
        } catch (ClassNotFoundException Fonte) {
            System.out.println("Driver nao localizado");
        } catch (SQLException ignored) {
        }
        return com;
    }

    private void CONEXAO_DATABASE(String Servidor, String Porta, String usuario, String Senha) {
        try {
            Class.forName(driver);
            CONEXAO = DriverManager.getConnection("jdbc:mysql://" + Servidor + ":" + Porta
                    + "/db_goDev", usuario, Senha);
        } catch (ClassNotFoundException Fonte) {
            System.out.println("Driver nao localizado");
        } catch (SQLException ignored) {
        }
    }

    private void FECHA_CONEXAO() {
        try {
            CONEXAO.close();
        } catch (SQLException fech) {
            System.out.println("Erro ao fechar conexao com o banco de dados");
        }
    }

    private void CRIA_DATA_BASE_COMPLETO() {
        CRIA_BANCO();
        CRIA_TABELAS();
    }

    private void CRIA_BANCO() {
        PreparedStatement stm;
        String sql = "CREATE DATABASE IF NOT EXISTS db_goDev";
        try {
            stm = CONEXAO.prepareStatement(sql);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "Erro");
        }
//        FECHA_CONEXAO();
    }

    private void CRIA_TABELAS() {
        CONEXAO_DATABASE(T_Host, T_Porta, T_User, T_Senha);
        CRIA_TABELA_PESSOAS();
        CRIA_TABELA_SALAS() ;
        CRIA_TABELA_CAFE() ;
        FECHA_CONEXAO();
    }

    private void CRIA_TABELA_PESSOAS() {
        PreparedStatement stm;
        String sql = "CREATE TABLE  IF NOT EXISTS tb_pessoas ("
                + " id INT( 11 ) NOT NULL AUTO_INCREMENT ,"
                + " nome VARCHAR( 50 ) NOT NULL , "
                + " PRIMARY KEY ( id ) "
                + " ) ENGINE = InnoDB";
        try {
            stm = CONEXAO.prepareStatement(sql);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "Erro");
        }
    }

    private void CRIA_TABELA_SALAS() {
        PreparedStatement stm;
        String sql = "CREATE TABLE  IF NOT EXISTS tb_salas ("
                + " id INT( 11 ) NOT NULL AUTO_INCREMENT ,"
                + " nome VARCHAR( 50 ) NOT NULL , "
                + " lotacao INT( 11 ) NOT NULL , "
                + " PRIMARY KEY ( id ) "
                + " ) ENGINE = InnoDB";
        try {
            stm = CONEXAO.prepareStatement(sql);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "Erro");
        }
    }

    private void CRIA_TABELA_CAFE() {
        PreparedStatement stm;
        String sql = "CREATE TABLE  IF NOT EXISTS tb_cafe ("
                + " id INT( 11 ) NOT NULL AUTO_INCREMENT ,"
                + " nome VARCHAR( 50 ) NOT NULL , "
                + " lotacao INT( 11 ) NOT NULL , "
                + " PRIMARY KEY ( id ) "
                + " ) ENGINE = InnoDB";
        try {
            stm = CONEXAO.prepareStatement(sql);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "Erro");
        }
    }
}
