import java.io.IOException;

public interface Armazenamento {
	public void adicionaPontosUsuario(Usuario usuario, int pontos, String tipo) throws IOException;
	public String retornaPontos(Usuario usuario, String tipo);
	public String retornaTodosPontosUser(Usuario usuario);
	public String retornaPlacar(String tipopontos);
	public void registraNovoUser(Usuario user) throws IOException;
	public String todosTiposEValoresPontos() throws UsuarioSemPontosRegistradosException;
}
