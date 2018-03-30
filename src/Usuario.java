import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Usuario {

	private String _nome;
	private HashMap<String, Integer> _pontos;
	
	public Usuario(String nome) {
		this._nome = nome;
		this._pontos = new HashMap<String, Integer>();
	}
	
	public void registraTipoPonto(String tipoponto) {
		if(_pontos.containsKey(tipoponto)) return;
		else _pontos.put(tipoponto, 0);
	}
	
	public String getNome() {
		return this._nome;
	}

	public void adicionaPontos(String tipoponto, int quantidadepontos) {
		if(!_pontos.containsKey(tipoponto)) {
			_pontos.put(tipoponto, quantidadepontos);
		}
		else {
			Integer novapontuacao = _pontos.get(tipoponto) + quantidadepontos;
			_pontos.replace(tipoponto, novapontuacao);
		}
		//System.out.println("PONTOS ADICIONADOS");
	}
	
	public boolean recebeuAlgumPonto() {
		Set set = _pontos.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry elemento = (Map.Entry) iterator.next();
			if(_pontos.get(elemento.getKey()) > 0) return true;
		}
		return false;
	}
	
	public int quantidadePontosDeUmTipo(String tipoponto) throws UsuarioSemPontosDeUmTipoException{
		if(_pontos.containsKey(tipoponto)) return _pontos.get(tipoponto);
		else throw new UsuarioSemPontosDeUmTipoException("Usuario nao possui pontos do tipo " + tipoponto);
	}
	
	public ArrayList<String> todosTiposPontosRegistrados() throws UsuarioSemPontosRegistradosException {
		if(_pontos.isEmpty()) throw new UsuarioSemPontosRegistradosException("Usuário não possuí pontos registrados!!");
		else {
			ArrayList<String> tiposdepontosencontrados = new ArrayList<>();
			Set set = _pontos.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()) {
				Map.Entry elemento = (Map.Entry) iterator.next();
				tiposdepontosencontrados.add(elemento.getKey().toString());
			}
			return tiposdepontosencontrados;
		}
	}
	
	public String todosTiposEValoresPontos() throws UsuarioSemPontosRegistradosException {
		if(_pontos.isEmpty()) throw new UsuarioSemPontosRegistradosException("Usuário não possuí pontos registrados!!");
		else {
			String aux = "";
			Set set = _pontos.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()) {
				Map.Entry elemento = (Map.Entry) iterator.next();
				aux += "Ponto: " + elemento.getKey().toString() + " Quantidade: " + elemento.getValue();
			}
			return aux;
		}
	}
	
	public HashMap<String, Integer> getPontos(){
		return _pontos;
	}

	public boolean contemPontosTipo(String tipopontos) {
		if(_pontos.containsKey(tipopontos)) return true;
		return false;
	}

}
