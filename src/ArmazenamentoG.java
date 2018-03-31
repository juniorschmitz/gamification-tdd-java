import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ArmazenamentoG implements Armazenamento {
	
	private ArrayList< Usuario> usuarios;
	private String caminhoarquivo;
	
	public ArmazenamentoG(String caminhoarquivo) throws IOException {
		this.caminhoarquivo = caminhoarquivo;
		this.usuarios = new ArrayList<Usuario>();
		usuarios = this.leArquivo();
	}
	
	public ArrayList<Usuario> retornaUsuariosComPonto(){
		ArrayList<Usuario> usuarioscompontos = new ArrayList<Usuario>();
		for(Usuario user : usuarios) {
			if(user.recebeuAlgumPonto()) usuarioscompontos.add(user);
		}
		return usuarioscompontos;
	}

	private ArrayList<Usuario> leArquivo() throws IOException {
		ArrayList<Usuario> _usuarios = new ArrayList<Usuario>();
		BufferedReader buff = new BufferedReader(new FileReader(this.caminhoarquivo));
		String linha = "";
		Usuario _user = new Usuario("aa");
		while(true) {
			if(linha != null) {
				String palavras[] = linha.split(" ");
				if(palavras[0].equals("user")) {
					_user = new Usuario(palavras[1]);
					_usuarios.add(_user);
				}
				else if(palavras[0].equals("ponto")) {
					_user.adicionaPontos(palavras[1], Integer.parseInt(palavras[2]));
					_usuarios.remove(_usuarios.indexOf(_user));
					_usuarios.add(_user);
				}
			}
			else break;
			linha = buff.readLine();
		}
		buff.close();
		return _usuarios;
	}
	
	public void registraNovoUser(Usuario user) throws IOException {
		this.usuarios.clear();
		this.usuarios = this.leArquivo();
		boolean existe = false;
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getNome().equalsIgnoreCase(user.getNome())){
				existe = true;
				usuarios.set(i, user);
				break;
			}
		}
		if(!existe) {
			this.usuarios.add(user);	
		}
		this.registraUsersArquivo();
	}
		
	private void registraUsersArquivo() throws IOException {
		FileWriter writer = new FileWriter(this.caminhoarquivo, false);
		PrintWriter	printer = new PrintWriter(writer);
		for(int i = 0; i < usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
			printer.printf("user ");
			printer.printf(user.getNome() + "%n");
			HashMap<String, Integer> pontos = user.getPontos();
			Set set = pontos.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()) {
				Map.Entry elemento = (Map.Entry) iterator.next();
				printer.printf("ponto ");
				printer.printf(elemento.getKey().toString() + " ");
				printer.printf(pontos.get(elemento.getKey()) + "%n");
			}
		}
		writer.close();
	}
	
	public String retornaPontos(Usuario usuario, String tipo) {
		if(usuarios.contains(usuario))
			return "O usuario " + usuario.getNome() + " possui " + usuario.quantidadePontosDeUmTipo(tipo) + " pontos do tipo " + tipo;
		return "Usuario nao contem pontos deste tipo";	
	}

	public void armazenaUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public ArrayList<Usuario> getUsuarios() throws IOException {
		this.usuarios = leArquivo();
		return this.usuarios;
	}

	@Override
	public String retornaTodosPontosUser(Usuario usuario) {
		if(usuarios.contains(usuario))
			return usuario.todosTiposEValoresPontos();
		return "usuario nao encontrado";
	}

	@Override
	public String todosTiposEValoresPontos() throws UsuarioSemPontosRegistradosException, IOException {
		usuarios.clear();
		usuarios = this.leArquivo();
		String aux = "";
		for(int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			if(usuario.recebeuAlgumPonto()) {
				aux += usuario.getNome() + " ";
				aux += usuario.todosTiposEValoresPontos() + " ";	
			}
		}
		return aux;
	}
	
}