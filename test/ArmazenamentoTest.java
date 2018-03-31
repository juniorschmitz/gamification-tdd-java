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
		caminhoarquivo = "test/test1.txt";
		armazenamento = new ArmazenamentoG(caminhoarquivo);
	}
	
	@Test
	public void testLeitura() throws IOException {
		usuarios = armazenamento.getUsuarios();
		assertEquals("usuario1", usuarios.get(0).getNome());
		assertEquals("usuario2", usuarios.get(1).getNome());
		assertEquals("usuario3", usuarios.get(2).getNome());
	}
	
	@Test
	public void testArmazenaNovoUsuario() throws IOException {
		Usuario user = new Usuario("usuario4");
		user.adicionaPontos("bananona", 500);
		armazenamento.registraNovoUser(user);
		usuarios = armazenamento.getUsuarios();
		assertEquals("usuario4", usuarios.get(4).getNome());
	}
	
	@Test
	public void testAdicionaPontosUsuario() throws IOException {
		Usuario usuario = new Usuario("joao");
		usuario.adicionaPontos("estrela", 100);
		usuario.adicionaPontos("bananaaaaa", 200);
		armazenamento.registraNovoUser(usuario);
		assertEquals("O usuario joao possui 100 pontos do tipo estrela", armazenamento.retornaPontos(usuario, "estrela"));
		assertEquals("O usuario joao possui 200 pontos do tipo bananaaaaa", armazenamento.retornaPontos(usuario, "bananaaaaa"));
	}
	
	@Test
	public void testRecuperaPontosEstrelaUsuario() throws IOException {
		usuarios = armazenamento.getUsuarios();
		usuarios.get(0).adicionaPontos("estrela", 100);
		armazenamento.armazenaUsuario(usuarios.get(0));
		assertEquals("O usuario usuario1 possui 300 pontos do tipo estrela", armazenamento.retornaPontos(usuarios.get(0), "estrela"));
	}
	
	@Test
	public void testRecuperaPontosMoedaUsuario() throws IOException {
		usuarios = armazenamento.getUsuarios();
		usuarios.get(0).adicionaPontos("moeda", 200);
		armazenamento.armazenaUsuario(usuarios.get(0));
		assertEquals("O usuario usuario1 possui 500 pontos do tipo moeda", armazenamento.retornaPontos(usuarios.get(0), "moeda"));
	}
	
}
