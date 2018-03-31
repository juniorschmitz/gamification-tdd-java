import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PlacarArmazenamentoIntegracao {

	private Placar placar;
	private Usuario usuario1;
	private Usuario usuario2;
	
	@Before
	public void setUp() throws IOException {
		String caminhoarquivo = "test/test2.txt";
		Armazenamento armazenamento = new ArmazenamentoG(caminhoarquivo);
		usuario1 = new Usuario("guerra");
		usuario2 = new Usuario("fernandes");
		placar = new Placar(armazenamento);
		placar.registraNovoUserNoPlacar(usuario1);
		placar.registraNovoUserNoPlacar(usuario2);
	}
	
	@Test
	public void testRegistraPontos() throws IOException {
		int quantidade = 10;
		String tipoponto = "estrela";
		assertEquals("10 pontos do tipo estrela adicionados para o usuario guerra", placar.registraPontos(usuario1, quantidade, tipoponto));
	}
	
	@Test
	public void testRetornaPontosUsuario() throws IOException {
		int quantidade = 10;
		String tipoponto = "estrela";
		placar.registraPontos(usuario1, quantidade, tipoponto);
		assertEquals("Usuario guerra Possui a seguinte pontuacao: Ponto: estrela Quantidade: 10", placar.retornaPontosUsuario(usuario1));
	}
	
	@Test
	public void testRetornaRanking() throws IOException {
		int quantidade = 20;
		String tipoponto = "estrela";
		placar.registraPontos(usuario2, quantidade, tipoponto);
		placar.registraPontos(usuario1, quantidade, tipoponto);
		String aux = placar.retornaRanking();
		assertEquals("Placar: guerra Ponto: estrela Quantidade: 20 fernandes Ponto: estrela Quantidade: 20 ", aux);
	}
	
	@Test
	public void testRetornaRankingUsuarioSemPontosNaoIncluido() throws IOException {
		int quantidade = 20;
		String tipoponto = "estrela";
		placar.registraPontos(usuario2, quantidade, tipoponto);
		placar.registraPontos(usuario1, quantidade, tipoponto);
		
		Usuario usuario3 = new Usuario("pedro");
		placar.registraNovoUserNoPlacar(usuario3);
		String aux = placar.retornaRanking();
		assertEquals("Placar: guerra Ponto: estrela Quantidade: 20 fernandes Ponto: estrela Quantidade: 20 ", aux);
	}

}
