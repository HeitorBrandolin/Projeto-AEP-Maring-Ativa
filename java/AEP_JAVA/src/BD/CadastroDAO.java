package BD;

import Classes.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadastroDAO {
    int num;
    
 
    public void salvarCadastro(Login l){
        String sql = "INSERT INTO cliente(nome_cliente,senha,cpf,email,telefone,data_nasc,sexo) values (?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, l.nome);
            pstm.setString(2, l.senha);
            pstm.setString(3, l.cpf);
            pstm.setString(4, l.email);
            pstm.setString(5, l.telefone);
            pstm.setString(6, l.data_nasc);
            pstm.setString(7, l.sexo);
            pstm.execute();  
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro cadastrar: "+e);
        } finally {
            try {
                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro cadastrar, encerrar conexão: "+e);
            }
        }

    }
    
    public void atualizarcli(Login l){
        String sql = "UPDATE cliente SET NOME_CLIENTE=?,EMAIL=?,SENHA=?,TELEFONE=?,SEXO=? WHERE IDCLIENTE = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, l.nome);
            pstm.setString(2, l.email);
            pstm.setString(3, l.senha);
            pstm.setString(4, l.telefone);
            pstm.setString(5, l.sexo);
            pstm.setInt(6, Login.getId());
            pstm.execute();  
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro cadastrar: "+e);
        } finally {
            try {
                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro cadastrar, encerrar conexão: "+e);
            }
        }
    }
    
    public void atualizarcliend(Login l){
        String sql = "UPDATE endereco_cliente SET CEP_CLIENTE=?,RUA_CLIENTE=?,BAIRRO_CLIENTE=?,ESTADO_CLIENTE=?,CIDADE_CLIENTE=?,NENDE_CLIENTE=?,COMPLEMENTO_CLIENTE=? WHERE ID_CLIENTE = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, l.cep);
            pstm.setString(2, l.rua);
            pstm.setString(3, l.bairro);
            pstm.setString(4, l.estado);
            pstm.setString(5, l.cidade);
            pstm.setString(6, l.nende);
            pstm.setString(7, l.complemento);
            pstm.setInt(8, Login.getId());
            pstm.execute();  
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro cadastrar: "+e);
        } finally {
            try {
                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro cadastrar, encerrar conexão: "+e);
            }
        }
    }
    
    public Login select() throws Exception{
        String stg = "SELECT * FROM cliente, endereco_cliente WHERE cliente.IDCLIENTE = ? && endereco_cliente.ID_CLIENTE= ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        Login resp = new Login();
        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(stg);
            pstm.setInt(1, Login.getId());
            pstm.setInt(2, Login.getId());
            ResultSet result = pstm.executeQuery();
            while ( result.next()){
                resp.nome = result.getString("NOME_CLIENTE");
                resp.cpf = result.getString("CPF");
                resp.email = result.getString("EMAIL");
                resp.senha = result.getString("SENHA");
                resp.telefone = result.getString("TELEFONE");
                resp.data_nasc = result.getString("DATA_NASC");
                resp.sexo = result.getString("SEXO");
                resp.cep = result.getString("CEP_CLIENTE");
                resp.rua = result.getString("RUA_CLIENTE");
                resp.bairro = result.getString("BAIRRO_CLIENTE");
                resp.estado = result.getString("ESTADO_CLIENTE");
                resp.cidade = result.getString("CIDADE_CLIENTE");
                resp.nende = result.getString("NENDE_CLIENTE");
                resp.complemento = result.getString("COMPLEMENTO_CLIENTE");
            }
            
        }catch (SQLException e){}
        return resp;            
    }     
    
    public void reserva(Login l){
        String sql = "INSERT INTO agenda(DISPONIBILIDADE,TEMPO_ALOCACAO,ID_CLIENTE,ID_CENTRO) values (?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, "INDISPONIVEL");
            pstm.setString(2, Login.getHora());
            pstm.setInt(3, Login.getId());
            pstm.setInt(4, l.idlocal);
            pstm.execute();  
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro cadastrar: "+e);
        } finally {
            try {
                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro cadastrar, encerrar conexão: "+e);
            }
        }

    }
    
    public Login consultaRES() throws Exception{
        String stg = "SELECT * FROM agenda, endereco_centro,centro WHERE agenda.ID_CLIENTE = ? && agenda.ID_CENTRO = centro.IDCENTRO && centro.IDCENTRO = endereco_centro.ID_CENTRO";
        Connection conn = null;
        PreparedStatement pstm = null;
        Login resp = new Login();
        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(stg);
            pstm.setInt(1, Login.getId());
            ResultSet result = pstm.executeQuery();
            while ( result.next()){
                resp.nome = result.getString("NOME");
                resp.descricao = result.getString("DESCRICAO");
                resp.aviso = result.getString("AVISO");
                resp.cep = result.getString("CEP_CENTRO");
                resp.rua = result.getString("RUA_CENTRO");
                resp.bairro = result.getString("BAIRRO_CENTRO");
                resp.estado = result.getString("ESTADO_CENTRO");
                resp.cidade = result.getString("CIDADE_CENTRO");
                resp.nende = result.getString("NENDE_CENTRO");
                resp.complemento = result.getString("COMPLEMENTO_CENTRO");
                resp.tempo = result.getString("TEMPO_ALOCACAO");                
            }
            
        }catch (SQLException e){}
        return resp;  
    }
        
    public void maximoid(){
        String idcli = "SELECT MAX(IDCLIENTE) FROM cliente";
        Connection conn = null;
        PreparedStatement pstm = null;
        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(idcli);
            ResultSet result = pstm.executeQuery();
            while ( result.next()){
                num = result.getInt("MAX(IDCLIENTE)");
            }
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro cadastrar: "+e);}
        
    }
    
    public Login dados(int num) throws Exception{
        String stg = "SELECT * FROM centro, endereco_centro WHERE centro.IDCENTRO = ? && endereco_centro.ID_CENTRO= ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        Login resp = new Login();
        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(stg);
            pstm.setInt(1, num);
            pstm.setInt(2, num);
            ResultSet result = pstm.executeQuery();
            while ( result.next()){
                resp.nome = result.getString("NOME");
                resp.descricao = result.getString("DESCRICAO");
                resp.aviso = result.getString("AVISO");
                resp.cep = result.getString("CEP_CENTRO");
                resp.rua = result.getString("RUA_CENTRO");
                resp.bairro = result.getString("BAIRRO_CENTRO");
                resp.estado = result.getString("ESTADO_CENTRO");
                resp.cidade = result.getString("CIDADE_CENTRO");
                resp.nende = result.getString("NENDE_CENTRO");
                resp.complemento = result.getString("COMPLEMENTO_CENTRO");
            }
            
        }catch (SQLException e){}
        return resp;  
    }
    
    public boolean verificaRES(int a) throws Exception{
        String sql = "Select * from agenda where ID_CLIENTE = ? ";

        Connection conn = null;
        PreparedStatement pstm = null;
        boolean resp = false;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, a);

            ResultSet result = pstm.executeQuery();
            if ( result.next() )
                resp = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuárioDAO: " + e);
        }
        return resp;            
    }
    
     public void salvarCadastroEnd(Login l){
        String sql = "INSERT INTO endereco_cliente(cep_cliente,rua_cliente,bairro_cliente,estado_cliente,cidade_cliente,nende_cliente,complemento_cliente,id_cliente) values (?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        maximoid();
        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, l.cep);
            pstm.setString(2, l.rua);
            pstm.setString(3, l.bairro);
            pstm.setString(4, l.estado);
            pstm.setString(5, l.cidade);
            pstm.setString(6, l.nende);
            pstm.setString(7, l.complemento);
            pstm.setInt(8, num);
            pstm.execute();  
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro cadastrar: "+e);
        } finally {
            try {
                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro cadastrar, encerrar conexão: "+e);
            }
        }

    }
     
    /*
    public boolean autenticaUsuarioBoleano(Login login) throws Exception{
        String sql = "Select * from login where login = ?  and senha = ? ";

        Connection conn = null;
        PreparedStatement pstm = null;
        boolean resp = false;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, login.login);
            pstm.setString(2, login.senha);

            ResultSet result = pstm.executeQuery();
            if ( result.next() )
                resp = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuárioDAO: " + e);
        }
        return resp;            
    }  */

    public Login autenticaUsuario(Login login) throws Exception{
        String sql = "Select * from cliente where (email= ? and senha= ? ) or (cpf= ? and senha= ? )";

        Connection conn = null;
        PreparedStatement pstm = null;
        Login resp = new Login();

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, login.email);
            pstm.setString(2, login.senha);
            pstm.setString(3, login.cpf);
            pstm.setString(4, login.senha);
            ResultSet result = pstm.executeQuery();
            while ( result.next()){
                Login.setId(result.getInt("IDCLIENTE"));
                Login.setRes(verificaRES(result.getInt("IDCLIENTE")));
                resp.nome = result.getString("NOME_CLIENTE");
                resp.cpf = result.getString("CPF");
                resp.email = result.getString("EMAIL");
                resp.senha = result.getString("SENHA");
                
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuárioDAO: " + e);
        }
        return resp;            
    } 
}
