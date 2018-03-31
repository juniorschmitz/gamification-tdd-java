import java.io.IOException;

public interface Armazenamento {
	public String retornaPontos(Usuario usuario, String tipo);
	public String retornaTodosPontosUser(Usuario usuario);
	public void registraNovoUser(Usuario user) throws IOException;
	public String todosTiposEValoresPontos() throws UsuarioSemPontosRegistradosException, IOException;
}
