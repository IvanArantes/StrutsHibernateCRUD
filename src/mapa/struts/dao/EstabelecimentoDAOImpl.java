package mapa.struts.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import mapa.struts.domain.Estabelecimento;


public class EstabelecimentoDAOImpl implements EstabelecimentoDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	public Session getSession(){
		return session;	
	}
	
	public void setSession(Session session){
		this.session = session;		
	}
	
		public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public void saveOrUpdateEstabelecimento(Estabelecimento estabelecimento) {
		try {
			session.saveOrUpdate(estabelecimento);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Used to delete a estabelecimento.
	 */
	@Override
	public void deleteEstabelecimento(Long estabelecimentoId) {
		try {
			Estabelecimento estabelecimento = (Estabelecimento) session.get(Estabelecimento.class, estabelecimentoId);
			session.delete(estabelecimento);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	/**
	 * Used to list all the estabelecimentos.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Estabelecimento> listEstabelecimento() {
		List<Estabelecimento> courses = null;
		try {
			courses = session.createQuery("from Estabelecimento").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	/**
	 * Used to list a single estabelecimento by Id.
	 */
	@Override
	public Estabelecimento listEstabelecimentoById(Long estabelecimentoId) {
		Estabelecimento estabelecimento = null;
		try {
			estabelecimento = (Estabelecimento) session.get(Estabelecimento.class, estabelecimentoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estabelecimento;
	}

	

	
    
}
