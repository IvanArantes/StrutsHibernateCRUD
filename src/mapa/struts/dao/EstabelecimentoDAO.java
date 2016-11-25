package mapa.struts.dao;

import java.util.List;

import mapa.struts.domain.Estabelecimento;


public interface EstabelecimentoDAO {

	public void saveOrUpdateEstabelecimento(Estabelecimento estabelecimento);
	public List<Estabelecimento> listEstabelecimento();
	public Estabelecimento listEstabelecimentoById(Long estabelecimentoId);
	public void deleteEstabelecimento(Long estabelecimentoId);
}
