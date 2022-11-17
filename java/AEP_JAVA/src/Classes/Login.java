package Classes;

public class Login {
    static int id;
    public String nome,senha,cpf,email,telefone,data_nasc,sexo;
    public String cep,rua,bairro,estado,cidade,nende,complemento;
    static boolean cad=false,res=false;
    static String nomestatic;
    static String hora;
    public String descricao,aviso;
    public int idlocal;
    public String tempo;
                
    public static String getHora() {
        return hora;
    }

    public static void setHora(String hora) {
        Login.hora = hora;
    }
    
    public static boolean isCad() {
        return cad;
    }

    public static void setCad(boolean cad) {
        Login.cad = cad;
    }

    public static String getNomestatic() {
        return nomestatic;
    }

    public static void setNomestatic(String nomestatic) {
        Login.nomestatic = nomestatic;
    }

    public static boolean isRes() {
        return res;
    }

    public static void setRes(boolean res) {
        Login.res = res;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Login.id = id;
    }
    
}
