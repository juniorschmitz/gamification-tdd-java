import java.io.IOException;

public class Placar {
	private Armazenamento armazenamento;
	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public String registraPontos(Usuario usuario, int quantidade, String tipoponto) throws IOException {
		usuario.adicionaPontos(tipoponto, quantidade);
		armazenamento.registraNovoUser(usuario);
		return quantidade + " pontos do tipo " + tipoponto + " adicionados para o usuario " + usuario.getNome();
	}

	public String retornaPontosUsuario(Usuario usuario) {
		String aux = "Usuario " + usuario.getNome() + " Possui a seguinte pontuacao: " + armazenamento.retornaTodosPontosUser(usuario);
		return aux;
	}

	public String retornaRanking() throws UsuarioSemPontosRegistradosException, IOException {
		String aux = "Placar: ";
		aux += armazenamento.todosTiposEValoresPontos();
		return aux;
	}

	public void registraNovoUserNoPlacar(Usuario usuario) throws IOException {
		this.armazenamento.registraNovoUser(usuario);
	}
}
