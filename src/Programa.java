import java.sql.*;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        
        boolean tudoOk = false;

        /*INICIA CONEXAO*/

        Connection CONEXAO = null;
        ResultSet rs;
        try {
            String driverName = "com.mysql.jdbc.Driver";

            String T_Host = "localhost";
            String T_Porta = "3306";
            String T_Senha = "";
            String T_User = "root";

            Class.forName(driverName);
            CONEXAO = DriverManager.getConnection("jdbc:mysql://" + T_Host + ":" + T_Porta
                    + "/db_goDev", T_User, T_Senha);
            tudoOk = true;
        } catch (ClassNotFoundException Fonte) {
                System.out.println("Driver nao localizado");
        } catch (SQLException Fonte) {
            System.err.println("Erro: "+Fonte);
        }

        /*TERMINA CONEXAO*/
        
        if(tudoOk){
            Scanner in = new Scanner(System.in);
            int opcao = 0;
            int opcao1;
            int cont = 0;
            int contPessoa;
            int contSala;
            int contCafe;
            int contCafe1;
            int pessoaConsultada;
            String sql;


            System.out.println("*** BEM VINDO AO PROGRAMA GODEV ***");
            System.out.println();
            while(opcao != 3) {
                System.out.println("O que você deseja fazer?");
                System.out.println();
                System.out.println("1 - Cadastrar");
                System.out.println("2 - Consultar");
                System.out.println("3 - Sair");
                opcao = in.nextInt();

                while (opcao != 1 && opcao != 2 && opcao != 3) {
                    System.out.println("Opção inválida, tente novamente.");
                    opcao = in.nextInt();
                }
                switch (opcao){
                    case 1:
                        System.out.println("Certo, e o que você deseja cadastrar?");
                        System.out.println();
                        System.out.println("1 - Pessoas");
                        System.out.println("2 - Salas");
                        System.out.println("3 - Espaço de Café");
                        System.out.println("4 - Voltar ao menu anterior");
                        opcao1 = in.nextInt();
                        while (opcao1 != 1 && opcao1 != 2 && opcao1 != 3 && opcao1 != 4) {
                            System.out.println("Opção inválida, tente novamente.");
                            opcao1 = in.nextInt();
                        }
                        switch (opcao1){
                            case 1:
                                opcao1 = 0;
                                while(opcao1 != 2) {
                                    System.out.println("Digite o nome da pessoa que deseja cadastrar:");
                                    in.nextLine();
                                    String nomePessoa = in.nextLine();
                                    sql = "INSERT INTO tb_pessoas ("
                                            + " nome) VALUES (?)";
                                    try {
                                        PreparedStatement stmt = CONEXAO.prepareStatement(sql);
                                        stmt.setString(1, nomePessoa);
                                        stmt.execute(); //executa comando
                                        stmt.close();

                                    } catch (SQLException u) {
                                        throw new RuntimeException(u);
                                    }

                                    System.out.println("Deseja cadastrar mais alguém?");
                                    System.out.println("1 - SIM");
                                    System.out.println("2 - NÃO");
                                    opcao1 = in.nextInt();
                                    cont++;

                                    while (opcao1 != 1 && opcao1 != 2) {
                                        System.out.println("Opção inválida, tente novamente.");
                                        opcao1 = in.nextInt();
                                    }
                                }
                                break;
                            case 2:
                                opcao1 = 0;
                                while(opcao1 != 2) {
                                    System.out.println("Digite o nome da sala que deseja cadastrar:");
                                    in.nextLine();
                                    String nomeSala = in.nextLine();
                                    System.out.println("Agora digite a capacidade total de lotação desta sala:");
                                    int lotacao = in.nextInt();
                                    sql = "INSERT INTO tb_salas ("
                                            + " nome, lotacao) VALUES (?, ?)";
                                    try {
                                        PreparedStatement stmt = CONEXAO.prepareStatement(sql);
                                        stmt.setString(1, nomeSala);
                                        stmt.setInt(2, lotacao);
                                        stmt.execute(); //executa comando
                                        stmt.close();

                                    } catch (SQLException u) {
                                        throw new RuntimeException(u);
                                    }

                                    System.out.println("Deseja cadastrar mais alguma sala?");
                                    System.out.println("1 - SIM");
                                    System.out.println("2 - NÃO");
                                    opcao1 = in.nextInt();
                                    cont++;

                                    while (opcao1 != 1 && opcao1 != 2) {
                                        System.out.println("Opção inválida, tente novamente.");
                                        opcao1 = in.nextInt();
                                    }
                                }
                                break;
                            case 3:
                                sql = "SELECT COUNT(tb_cafe.nome) AS cafe FROM tb_cafe";
                                try {
                                    PreparedStatement stmt = CONEXAO.prepareStatement(sql);
                                    rs = stmt.executeQuery(sql);
                                    rs.next();
                                    contCafe = rs.getInt("cafe");
                                } catch (SQLException u) {
                                    throw new RuntimeException(u);
                                }

                                if(contCafe >= 2){
                                    System.out.println("Você já cadastrou o limite máximo de espaços de café");
                                }else{
                                    System.out.println("Digite o nome do primeiro espaço de café:");
                                    in.nextLine();
                                    String nomeCafe1 = in.nextLine();
                                    System.out.println("Agora digite a capacidade total de lotação deste espaço:");
                                    int lotacao1 = in.nextInt();
                                    sql = "INSERT INTO tb_cafe ("
                                            + " nome, lotacao) VALUES (?, ?)";
                                    try {
                                        PreparedStatement stmt = CONEXAO.prepareStatement(sql);
                                        stmt.setString(1, nomeCafe1);
                                        stmt.setInt(2, lotacao1);
                                        stmt.execute(); //executa comando
                                        stmt.close();
                                        cont++;

                                    } catch (SQLException u) {
                                        throw new RuntimeException(u);
                                    }

                                    System.out.println("Digite o nome do segundo espaço de café:");
                                    in.nextLine();
                                    String nomeCafe2 = in.nextLine();
                                    System.out.println("Agora digite a capacidade total de lotação deste espaço:");
                                    int lotacao2 = in.nextInt();
                                    sql = "INSERT INTO tb_cafe ("
                                            + " nome, lotacao) VALUES (?, ?)";
                                    try {
                                        PreparedStatement stmt = CONEXAO.prepareStatement(sql);
                                        stmt.setString(1, nomeCafe2);
                                        stmt.setInt(2, lotacao2);
                                        stmt.execute(); //executa comando
                                        stmt.close();

                                    } catch (SQLException u) {
                                        throw new RuntimeException(u);
                                    }
                                }
                                break;
                            case 4:
                                break;
                        }

                        break;
                    case 2:
                        try {
                            sql = "SELECT"
                                    + "(SELECT COUNT(tb_pessoas.nome) FROM tb_pessoas) AS pessoas,"
                                    + "(SELECT COUNT(tb_salas.nome) FROM tb_salas) AS sala,"
                                    + "(SELECT COUNT(tb_cafe.nome) FROM tb_cafe) AS cafe";

                            PreparedStatement stmt = CONEXAO.prepareStatement(sql);
                            rs = stmt.executeQuery(sql);
                            rs.next();
                            contPessoa = rs.getInt("pessoas");
                            contSala = rs.getInt("sala");
                            contCafe1 = rs.getInt("cafe");

                            if(contPessoa == 0 || contSala == 0 || contCafe1 == 0){
                                System.err.println("Cadastre ao menos 1 pessoa, 1 sala e os espaços de café");
                                System.out.println();
                            }else{
                                String[][] consultaPessoa = new String[contPessoa][5];
                                String[][] listaSalas = new String[contSala][2];
                                String[][] listaCafe = new String[2][2];
                                System.out.println();
                                opcao1 = 0;
                                cont = 0;

                                try {
                                    sql = "SELECT * FROM tb_pessoas";

                                    stmt = CONEXAO.prepareStatement(sql);
                                    rs = stmt.executeQuery();
                                    int line = 0;
                                    while (rs.next()) {
                                        consultaPessoa[line][0] = rs.getString("nome");
                                        line++;
                                    }
                                } catch (SQLException u) {
                                    throw new RuntimeException(u);
                                }

                                try {
                                    sql = "SELECT * FROM tb_salas";

                                    stmt = CONEXAO.prepareStatement(sql);
                                    rs = stmt.executeQuery();
                                    int line = 0;
                                    while (rs.next()) {
                                        listaSalas[line][0] = rs.getString("nome");
                                        listaSalas[line][1] = String.valueOf(rs.getInt("lotacao"));
                                        line++;
                                    }
                                } catch (SQLException u) {
                                    throw new RuntimeException(u);
                                }

                                try {
                                    sql = "SELECT * FROM tb_cafe";

                                    stmt = CONEXAO.prepareStatement(sql);
                                    rs = stmt.executeQuery();
                                    int line = 0;
                                    while (rs.next()) {
                                        listaCafe[line][0] = rs.getString("nome");
                                        listaCafe[line][1] = String.valueOf(rs.getInt("lotacao"));
                                        line++;
                                    }
                                } catch (SQLException u) {
                                    throw new RuntimeException(u);
                                }


                                int temMetadeAluno;

                                int qtdPessoasPorSala = consultaPessoa.length / listaSalas.length;
                                int qtdPessoasPorCafe = consultaPessoa.length / 2;

                                if(qtdPessoasPorSala == 2){
                                    temMetadeAluno = 1;
                                }else if (qtdPessoasPorSala == 1){
                                    temMetadeAluno = 1;
                                }else{
                                    temMetadeAluno = qtdPessoasPorSala / 2;
                                }

                                for (int i = 0; i < listaSalas.length; i++){
                                    for (int j = 0; j < qtdPessoasPorSala; j++){
                                        consultaPessoa[cont][1] = listaSalas[i][0];
                                        if(qtdPessoasPorCafe <= cont){
                                            consultaPessoa[cont][3] = listaCafe[0][0];
                                            consultaPessoa[cont][4] = listaCafe[1][0];
                                        }else{
                                            consultaPessoa[cont][3] = listaCafe[1][0];
                                            consultaPessoa[cont][4] = listaCafe[0][0];
                                        }
                                        if(j == temMetadeAluno && temMetadeAluno != 0){
                                            if((i+1) != listaSalas.length){
                                                consultaPessoa[cont][2] = listaSalas[i+1][0];
                                            }else{
                                                if(i == 0){
                                                    consultaPessoa[cont][2] = listaSalas[i][0];
                                                }else{
                                                    consultaPessoa[cont][2] = listaSalas[i-1][0];
                                                }

                                            }
                                        }else{
                                            consultaPessoa[cont][2] = listaSalas[i][0];
                                        }
                                        cont++;
                                    }
                                }

                                int resto = consultaPessoa.length - cont;
                                contSala = 0;

                                for (int i = 0; i < resto; i++){
                                    consultaPessoa[cont][1] = listaSalas[contSala][0];
                                    consultaPessoa[cont][2] = listaSalas[contSala][0];
                                    consultaPessoa[cont][3] = listaCafe[0][0];
                                    consultaPessoa[cont][4] = listaCafe[1][0];
                                    contSala++;
                                    cont++;
                                }

                                while(opcao1 != 4) {
                                    System.out.println("Certo! E o que você deseja consultar?");
                                    System.out.println();
                                    System.out.println("1 - Consultar Pessoas");
                                    System.out.println("2 - Consultar Salas");
                                    System.out.println("3 - Consultar Espaço de Café");
                                    System.out.println("4 - Voltar ao menu anterior");
                                    opcao1 = in.nextInt();

                                    while (opcao1 != 1 && opcao1 != 2 && opcao1 != 3 && opcao1 != 4) {
                                        System.out.println("Opção inválida, tente novamente.");
                                        opcao1 = in.nextInt();
                                    }

                                    switch (opcao1){

                                        case 1:
                                            System.out.println("Certo, digite o número correspondente a pessoa que você deseja consultar?");
                                            for (int i = 0; i < consultaPessoa.length; i++){
                                                System.out.println(i+1 + " - " + consultaPessoa[i][0]);
                                            }
                                            pessoaConsultada = in.nextInt();
                                            System.out.println("Nome: "+ consultaPessoa[pessoaConsultada-1][0] + ". Primeiro Período: "+consultaPessoa[pessoaConsultada-1][1]+
                                                    ". Segundo Período: "+consultaPessoa[pessoaConsultada-1][2] + ". Primeiro Intervalo: " +consultaPessoa[pessoaConsultada-1][3]+
                                                    ". Segundo Intervalo: "+consultaPessoa[pessoaConsultada-1][4]);
                                            System.out.println();
                                            System.out.println();
                                            break;

                                        case 2:
                                            System.out.println("Certo, digite o número correspondente a sala que você deseja consultar?");
                                            for (int i = 0; i < listaSalas.length; i++){
                                                System.out.println(i+1 + " - " + listaSalas[i][0]);
                                            }
                                            pessoaConsultada = in.nextInt();
                                            for(int i = 0; i < consultaPessoa.length; i++){
                                                if(consultaPessoa[i][1].equals(listaSalas[pessoaConsultada-1][0]) && consultaPessoa[i][2].equals(listaSalas[pessoaConsultada-1][0])){
                                                    System.out.println("Nome: "+consultaPessoa[i][0]+" ficará nesta sala no primeiro e no segundo período. ");
                                                }
                                                if(consultaPessoa[i][1].equals(listaSalas[pessoaConsultada-1][0]) && !consultaPessoa[i][2].equals(listaSalas[pessoaConsultada-1][0])){
                                                    System.out.println("Nome: "+consultaPessoa[i][0]+" ficará nesta sala apenas no primeiro período");
                                                }
                                                if(!consultaPessoa[i][1].equals(listaSalas[pessoaConsultada-1][0]) && consultaPessoa[i][2].equals(listaSalas[pessoaConsultada-1][0])){
                                                    System.out.println("Nome: "+consultaPessoa[i][0]+" ficará nesta sala apenas no segundo período");
                                                }
                                            }
                                            System.out.println();
                                            System.out.println();
                                            break;

                                        case 3:
                                            System.out.println("Certo, digite o número correspondente a sala de café que você deseja consultar?");
                                            System.out.println("1 - "+ listaCafe[0][0]);
                                            System.out.println("2 - "+ listaCafe[1][0]);

                                            pessoaConsultada = in.nextInt();

                                            for(int i = 0; i < consultaPessoa.length; i++){
                                                if(consultaPessoa[i][3].equals(listaCafe[pessoaConsultada-1][0]) && consultaPessoa[i][4].equals(listaCafe[pessoaConsultada-1][0])){
                                                    System.out.println("Nome: "+consultaPessoa[i][0]+" fará o primeiro e o segundo intervalo na "+consultaPessoa[i][3]);
                                                }
                                                if(consultaPessoa[i][3].equals(listaCafe[pessoaConsultada-1][0]) && !listaCafe[pessoaConsultada-1][0].equals(consultaPessoa[i][4])){
                                                    System.out.println("Nome: "+consultaPessoa[i][0]+" fará o primeiro intervalo neste espaço");
                                                }
                                                if(!consultaPessoa[i][3].equals(listaCafe[pessoaConsultada-1][0]) && consultaPessoa[i][4].equals(listaCafe[pessoaConsultada-1][0])){
                                                    System.out.println("Nome: "+consultaPessoa[i][0]+" fará o segundo intervalo neste espaço");
                                                }
                                            }
                                            System.out.println();
                                            System.out.println();
                                            break;

                                        case 4:
                                            break;
                                        default:
                                            throw new IllegalStateException("Opção inválida: " + opcao1);
                                    }
                                }
                            }


                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 3:
                        System.out.println("Até mais!");
                        break;
                }
            }
        }else{
            System.err.println("ERRO: Iniciar primeiro o serviço de CONFIGURACAO");
        }
    }
}
