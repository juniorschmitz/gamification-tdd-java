import java.io.IOException;

public class Placar {
	private Armazenamento armazenamento;
	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public String registraPontos(Usuario usuario, int quantidade, String tipoponto) throws IOException {
		armazenamento.adicionaPontosUsuario(usuario, quantidade, tipoponto);
		return quantidade + " pontos do tipo " + tipoponto + " adicionados para o usuario " + usuario.getNome();
	}

	public String retornaPontosUsuario(Usuario usuario) {
		String aux = "Usuario " + usuario.getNome() + " Possui a seguinte pontuacao: " + armazenamento.retornaTodosPontosUser(usuario);
		return aux;
	}

	public String retornaRanking() {
		String aux = "Placar: ";
		aux += armazenamento.todosTiposEValoresPontos();
		return aux;
	}
}
