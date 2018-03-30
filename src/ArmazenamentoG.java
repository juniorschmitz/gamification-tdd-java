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
	
	private ArrayList<Usuario> usuarios;
	private String caminhoarquivo;
	
	public ArmazenamentoG(String caminhoarquivo) throws IOException {
		this.caminhoarquivo = caminhoarquivo;
		this.usuarios = new ArrayList<Usuario>();
		usuarios = this.leArquivo();
	}

	public void adicionaPontosUsuario(Usuario usuario, int pontos, String tipo) throws IOException {
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getNome() == usuario.getNome()) {
				usuarios.get(i).adicionaPontos(tipo, pontos);
				salvaNovosPontosUsuario(usuarios.get(i));
			}
		}
//		if(usuarios.contains(usuario)) {
//			usuario.adicionaPontos(tipo, pontos);
//			salvaNovosPontosUsuario(usuario);
//		}
//		else throw new UsuarioNaoCadastradoException("Usuario nao cadastrado!!");
	}
	
	public void salvaNovosPontosUsuario(Usuario usuario) throws IOException {
		for(int i = 0; i < usuarios.size(); i++) {
//			boolean noterase = i == 0? false : true;
			registraUserArquivo(usuarios.get(i));
		}
	}
	
	public ArrayList<Usuario> retornaUsuariosComPonto(){
		ArrayList<Usuario> usuarioscompontos = new ArrayList<Usuario>();
		for(Usuario user : usuarios) {
			if(user.recebeuAlgumPonto()) usuarioscompontos.add(user);
		}
		return usuarioscompontos;
	}

	public ArrayList<Usuario> leArquivo() throws IOException {
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
//		this.usuarios = _usuarios;
		return _usuarios;
	}
	
	public void registraNovoUser(Usuario user) throws IOException {
		usuarios.clear();
		usuarios = this.leArquivo();
//		for(int i = 0; i < usuarios.size(); i++) {
//			Usuario usuario = usuarios.get(i);
//			System.out.println(usuario.getNome());
//		}
		if(usuarios.contains(user)) throw new UsuarioJaCadastradoException("Usuario ja estava cadastrado!!");
		else this.usuarios.add(user);
		this.registraUserArquivo(user);
//		else this.registraUserArquivo(user);
	}
	
	/*public void registraUserArquivo(Usuario user) throws IOException {
//		boolean noterase = usuarios.contains(user)? true : false;
		//ArrayList<Usuario> usuariosaux = leArquivo();
		//boolean noterase = usuariosaux.contains(user)? true : false;
		
		FileWriter writer = new FileWriter(this.caminhoarquivo, true);
		PrintWriter	printer = new PrintWriter(writer);
//		printer.printf("%n");
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
		writer.close();
	}*/
	
	public void registraUserArquivo(Usuario usuarioold) throws IOException {
		FileWriter writer = new FileWriter(this.caminhoarquivo, false);
		PrintWriter	printer = new PrintWriter(writer);
		for(int i = 0; i < usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
		//		printer.printf("%n");
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
				System.out.println(elemento.getKey().toString());
				System.out.println(pontos.get(elemento.getKey()));
			}
		}
		writer.close();
	}
	
	public String retornaPontos(Usuario usuario, String tipo) {
		if(usuarios.contains(usuario))
			return "O usuário " + usuario.getNome() + " possuí " + usuario.quantidadePontosDeUmTipo(tipo) + " pontos do tipo " + tipo;
		return "Usuário não contém pontos deste tipo";	
	}

	public void armazenaUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
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
