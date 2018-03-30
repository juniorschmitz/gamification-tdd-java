import java.io.IOException;
import java.util.ArrayList;

public class ArmazenamentoMock implements Armazenamento{
	private ArrayList<Usuario> usuarios;

	public ArmazenamentoMock() {
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public void registraNovoUser(Usuario user) throws IOException {
		if(usuarios.contains(user)) throw new UsuarioJaCadastradoException("Usuario ja estava cadastrado!!");
		else this.usuarios.add(user);
	}
	
	@Override
	public void adicionaPontosUsuario(Usuario usuario, int pontos, String tipo) throws IOException {
		if(usuarios.contains(usuario)) {
			usuario.adicionaPontos(tipo, pontos);
		}
		else throw new UsuarioNaoCadastradoException("Usuario nao cadastrado!!");
	}

	@Override
	public String retornaPontos(Usuario usuario, String tipo) {
		if(usuarios.contains(usuario))
			return "O usuário " + usuario.getNome() + " possuí " + usuario.quantidadePontosDeUmTipo(tipo) + " pontos do tipo " + tipo;
		return "Usuário não contém pontos deste tipo";
	}
	
	@Override
	public String retornaTodosPontosUser(Usuario usuario) {
		if(usuarios.contains(usuario))
			return usuario.todosTiposEValoresPontos();
		return "usuario nao encontrado";
	}

	@Override
	public String retornaPlacar(String tipopontos) {
		String aux = "";
		for(int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			if(usuario.contemPontosTipo(tipopontos)) {
				aux += "User: " + usuario.getNome() + " Pontos: " + usuario.quantidadePontosDeUmTipo(tipopontos) + "; ";
			}
		}
		return aux;
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
