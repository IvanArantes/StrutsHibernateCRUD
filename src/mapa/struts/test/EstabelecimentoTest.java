package mapa.struts.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.SaslException;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.validator.AssertFalse;
import org.junit.Before;
import org.junit.Test;

import mapa.struts.dao.EstabelecimentoDAOImpl;
import mapa.struts.domain.Estabelecimento;

public class EstabelecimentoTest {
    SessionFactory sessionFactory;
    EstabelecimentoDAOImpl db;
    Estabelecimento estabelecimento;
    List<Estabelecimento> listEstabelecimento = new ArrayList<Estabelecimento>();

    @Before
    public void init() {
        BasicConfigurator.configure();
        db = new EstabelecimentoDAOImpl();
        estabelecimento = new Estabelecimento();
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/mapa");
        config.setProperty("hibernate.connection.username", "postgres");
        config.setProperty("connection.password", "root");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        config.setProperty("hibernate.current_session_context_class", "thread");
        config.setProperty("hibernate.show_sql", "false");
        config.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        config.setProperty("hbm2ddl.auto", "update");
        config.setProperty("connection.autocommit", "true");
        config.addAnnotatedClass(Estabelecimento.class);

        sessionFactory = config.configure().buildSessionFactory();

    }

    public Session getSession() throws SaslException {
        Session session;
        session = sessionFactory.getCurrentSession();
        return session;
    }
    
    @Test
    public void testListar() throws SaslException {

        initSession();
        estabelecimento.setCodigo("listarteste");
        estabelecimento.setDescricao("listarteste");
        estabelecimento.setSituacao("listarteste");
        db.saveOrUpdateEstabelecimento(estabelecimento);
        listEstabelecimento = (ArrayList<Estabelecimento>) db.listEstabelecimento();
        commitTransaction();
        assertNotNull(listEstabelecimento);
    }

    @Test
    public void testInserir() throws SaslException {
        initSession();
        estabelecimento.setCodigo("10");
        estabelecimento.setDescricao("teste");
        estabelecimento.setSituacao("ativo");
        estabelecimento.setCep("79332090");
        estabelecimento.setCidade("oioi");
        estabelecimento.setBairro("centro");
        estabelecimento.setRua("Rua da Paz");
        estabelecimento.setUf("MS");
        db.saveOrUpdateEstabelecimento(estabelecimento);
        commitTransaction();
    }
   /* @Test
    public void testUpdate() throws SaslException{
        boolean achouNoBanco = false;
    
        initSession();

        estabelecimento.setCodigo("update");
        db.saveOrUpdateEstabelecimento(estabelecimento);
        commitTransaction();
       
        initSession();
        estabelecimento.setCodigo("updateRealizado");
        db.saveOrUpdateEstabelecimento(estabelecimento);;
        commitTransaction();
       
        initSession();
        listEstabelecimento = (ArrayList<Estabelecimento>) db.listEstabelecimento();

        for (int i = 0; i < listEstabelecimento.size(); i++) {
            if (listEstabelecimento.get(i).getCodigo().equals("updateRealizado")) {
                achouNoBanco = true;
            }
        }
        assertTrue(achouNoBanco);
    }*/
    @Test
    public void testDeletar() throws SaslException {
        boolean achouNoBanco = false;

        initSession();
        estabelecimento.setCodigo("10");
        db.saveOrUpdateEstabelecimento(estabelecimento);
        commitTransaction();

        initSession();
        getSession().delete(estabelecimento);
        commitTransaction();
        
        initSession();
        listEstabelecimento = (ArrayList<Estabelecimento>) db.listEstabelecimento();

        for (int i = 0; i < listEstabelecimento.size(); i++) {
            if (listEstabelecimento.get(i).getCodigo().equals("deletarteste")) {
                achouNoBanco = true;
            }
        }
        commitTransaction();
        assertFalse(achouNoBanco);

    }

    public void initSession() throws SaslException {
        db.setSession(getSession());
        db.setTransaction(getSession().beginTransaction());
    }
    public void commitTransaction() throws HibernateException, SaslException {
        getSession().getTransaction().commit();
    }

}