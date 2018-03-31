import java.io.IOException;
import java.util.ArrayList;

public class ArmazenamentoMock implements Armazenamento{
	private ArrayList<Usuario> usuarios;

	public ArmazenamentoMock() {
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public void registraNovoUser(Usuario user) throws IOException {
		if(usuarios.contains(user)) return;
		else this.usuarios.add(user);
	}

	@Override
	public String retornaPontos(Usuario usuario, String tipo) {
		if(usuarios.contains(usuario))
			return "O usuario " + usuario.getNome() + " possui " + usuario.quantidadePontosDeUmTipo(tipo) + " pontos do tipo " + tipo;
		return "Usuario nao contem pontos deste tipo";
	}
	
	@Override
	public String retornaTodosPontosUser(Usuario usuario) {
		if(usuarios.contains(usuario))
			return usuario.todosTiposEValoresPontos();
		return "usuario nao encontrado";
	}

	@Override
	public String todosTiposEValoresPontos() throws UsuarioSemPontosRegistradosException {
		String aux = "";
		for(int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			aux += usuario.getNome() + " ";
			aux += usuario.todosTiposEValoresPontos() + " ";
		}
		return aux;
	}

}
