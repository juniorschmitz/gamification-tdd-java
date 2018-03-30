import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ArmazenamentoTest {

	private ArmazenamentoG armazenamento;
	private ArrayList<Usuario> usuarios;
	private String caminhoarquivo;
	
	@Before
	public void setUp() throws IOException {
		caminhoarquivo = "C:\\Users\\Junior\\Desktop\\novoteste.txt";
		armazenamento = new ArmazenamentoG(caminhoarquivo);
	}
	
	@Test
	public void testLeitura() throws IOException {
		usuarios = armazenamento.leArquivo();
		assertEquals("usuario1", usuarios.get(0).getNome());
		assertEquals("usuario2", usuarios.get(1).getNome());
		assertEquals("usuario3", usuarios.get(2).getNome());
	}
	
	@Test
	public void testArmazenaNovoUsuario() throws IOException {
		Usuario user = new Usuario("usuario4");
		armazenamento.registraNovoUser(user);
		usuarios = armazenamento.leArquivo();
		user.adicionaPontos("bananona", 500);
		assertEquals("usuario4", usuarios.get(4).getNome());
	}
	
	@Test
	public void testAdicionaPontosUsuario() throws IOException {
		Usuario usuario = new Usuario("joao");
		armazenamento.registraNovoUser(usuario);
		armazenamento.adicionaPontosUsuario(usuario, 100, "estrela");
		assertEquals("O usuário joao possuí 100 pontos do tipo estrela", armazenamento.retornaPontos(usuario, "estrela"));
		armazenamento.adicionaPontosUsuario(usuario, 200, "bananaaaaa");
		assertEquals("O usuário joao possuí 200 pontos do tipo estrela", armazenamento.retornaPontos(usuario, "bananaaaaa"));
	}
	
//	@Test
//	public void testRecuperaPontosEstrelaUsuario() {
//		usuarios.get(0).adicionaPontos("estrela", 100);
//		armazenamento.armazenaUsuario(usuarios.get(0));
//		assertEquals("O usuário joao possuí 100 pontos do tipo estrela", armazenamento.retornaPontos(usuarios.get(0), "estrela"));
//	}
//	
//	@Test
//	public void testRecuperaPontosMoedaUsuario() {
//		usuarios.get(0).adicionaPontos("moeda", 200);
//		armazenamento.armazenaUsuario(usuarios.get(0));
//		assertEquals("O usuário joao possuí 200 pontos do tipo moeda", armazenamento.retornaPontos(usuarios.get(0), "moeda"));
//	}
	
}
