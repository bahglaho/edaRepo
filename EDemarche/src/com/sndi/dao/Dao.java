package com.sndi.dao;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.sndi.model.TOperateur;

public class Dao implements IDao {

	private SessionFactory sessionFactory;

	@Override
	public void addObject(Object object) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().save(object);

	}
	
	@Override
	public void persist(Object object) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().persist(object);

	}

	@Override
	public void updateObject(Object object) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().update(object);
	}
	
	@Override
	public void mergeObject(Object object) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().merge(object);
	}

	@Override
	public void deleteObject(Object Object) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().delete(Object);
	}

	@Override
	public Object getObjectById(int id, String objet) {
		// TODO Auto-generated method stub
		String query= "from" +" "+ objet + " "+ " where id =?";
		  List liste = getSessionFactory().getCurrentSession().createQuery(query).setParameter(0,id).list();
		  if (liste.size()==0){
		   return null;}
		return liste.get(0);
	}
	
	@Override
	public Object getFonctionStatutPosition(String fonction_code, String action) {
		// TODO Auto-generated method stub
		 
		
		  Query query = getSessionFactory().getCurrentSession().getNamedQuery("call FB_STATUT_POSITION");

	        query.setParameter("V_Fonction", fonction_code); 
	        query.setParameter("V_Action", action);
	         query.executeUpdate();
	         List result = query.list();
		  if (result.size()==0){
		   return null;}
	
		return result.get(0);
	}

	@Override
	public Object getObjectById(String id, String objet) {
		// TODO Auto-generated method stub
		String query= "from" +" "+ objet + " "+ " where id =?";
		  List liste = getSessionFactory().getCurrentSession().createQuery(query).setParameter(0,id).list();
		  if (liste.size()==0){
		   return null;}
		return liste.get(0);
	}

	@Override
	@Transactional
	public Object getObjectById(String id, Object objet) {
		
		Session session= getSessionFactory().getCurrentSession();
		Criteria crit = session.createCriteria((Class) objet);
		crit.add(Restrictions.eq("id", id));
		return (Object) crit.uniqueResult();
	}
	
	@Override
	public List<Object> getObjects(Object object) {
		// TODO Auto-generated method stub
		List list = getSessionFactory().getCurrentSession().createQuery("from"+" "+object).list();
			// TODO Auto-generated method stub
			return list ;
	}
	
	@Override
	public List<Object> getObjectLasy(Object object, Class businessInterface) {
		// TODO Auto-generated method stub
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("from"+" "+object);
		hqlQuery.append(addFetchingStategy(null, businessInterface));
		List list = getSessionFactory().getCurrentSession().createQuery("from"+" "+object).list();
			// TODO Auto-generated method stub
			return list ;
	}
	
	@Override
	public List<Object> getObjectLasy(String object, Class businessInterface) {
		// TODO Auto-generated method stub
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("from"+" "+object);
		hqlQuery.append(addFetchingStategy(null, businessInterface));
		List list = getSessionFactory().getCurrentSession().createQuery("from"+" "+object).list();
			// TODO Auto-generated method stub
			return list ;
	}
	
	@Override
	public List getObjects(String objet) {
		// TODO Auto-generated method stub
		String query = "from"+" "+objet;
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	@Override
	public List getObjects(String objet, List<String> columnList) {
		// TODO Auto-generated method stub
		String query = "FROM"+" "+objet;
		query += " ORDER BY "+columnList.get(0);
		for(int i=1; i<columnList.size(); i++){
			query += " , "+columnList.get(i);
		}

		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	@Override
	public List getObjectsByColumn(String objet, List<WhereClause>conditionList) {
		// TODO Auto-generated method stub
		String query = "FROM "+objet ;
		if(!conditionList.isEmpty()){
		query += " WHERE "+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+"'"+conditionList.get(0).getValeur()+"'";
		for(int i=1; i<conditionList.size(); i++){
			query += " AND "+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+"'"+conditionList.get(i).getValeur()+"'";			
		}
		}
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	@Override
	public List getObjectsByColumn(String objet, List<WhereClause>conditionList, List<String> columnList) {
		// TODO Auto-generated method stub
		String query = "FROM "+objet ;
		if(!conditionList.isEmpty()){
		query += " WHERE "+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+((conditionList.get(0).getValeur() == null)? "" : "'"+conditionList.get(0).getValeur()+"'");
		for(int i=1; i<conditionList.size(); i++){
			query += " AND "+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+((conditionList.get(i).getValeur() == null)? "" :"'"+conditionList.get(i).getValeur()+"'");			
		}}
		if(!columnList.isEmpty()){
		query += " ORDER BY "+columnList.get(0);
		for(int i=1; i<columnList.size(); i++){
			query += " , "+columnList.get(i);
		}}
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	@Override
	public List getObjectsByColumnNotQuote(String objet, List<WhereClause>conditionList, List<String> columnList) {
		// TODO Auto-generated method stub
		String query = "FROM "+objet ;
		if(!conditionList.isEmpty()){
			query += " WHERE "+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+((conditionList.get(0).getValeur() == null)? "" : ""+conditionList.get(0).getValeur()+"");
			for(int i=1; i<conditionList.size(); i++){
				query += " AND "+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+((conditionList.get(i).getValeur() == null)? "" :""+conditionList.get(i).getValeur()+"");			
			}}
		if(!columnList.isEmpty()){
			query += " ORDER BY "+columnList.get(0);
			for(int i=1; i<columnList.size(); i++){
				query += " , "+columnList.get(i);
			}}
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	@Override
	public List getObjectsByColumnnNotIn(String objet, List<WhereClause>conditionList, List<String> columnList, String notCondition,  List<String> notList) {
		// TODO Auto-generated method stub
		String query = "FROM "+objet ;
		if(!conditionList.isEmpty()){
		query += " WHERE "+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+((conditionList.get(0).getValeur() == null)? "" : "'"+conditionList.get(0).getValeur()+"'");
		for(int i=1; i<conditionList.size(); i++){
			query += " AND "+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+((conditionList.get(i).getValeur() == null)? "" :"'"+conditionList.get(i).getValeur()+"'");			
		}}
		if(!notList.isEmpty()){
			query += " AND "+notCondition+" NOT IN ('"+notList.get(0)+"'";
			for(int i=1; i<notList.size(); i++){
				query += " , '"+notList.get(i)+"'";
			}
			query +=")";
		}
		if(!columnList.isEmpty()){
		query += " ORDER BY "+columnList.get(0);
		for(int i=1; i<columnList.size(); i++){
			query += " , "+columnList.get(i);
		}}
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	@Override
	public List getObjectsByColumnnIn(String objet, List<WhereClause>conditionList, List<String> columnList, String inCondition,  List<String> inList) {
		// TODO Auto-generated method stub
		String query = "FROM "+objet ;
		if(!conditionList.isEmpty()){
		query += " WHERE "+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+((conditionList.get(0).getValeur() == null)? "" : "'"+conditionList.get(0).getValeur()+"'");
		for(int i=1; i<conditionList.size(); i++){
			query += " AND "+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+((conditionList.get(i).getValeur() == null)? "" :"'"+conditionList.get(i).getValeur()+"'");			
		}}
		if(!inList.isEmpty()){
			query += " AND "+inCondition+" IN ('"+inList.get(0)+"'";
			for(int i=1; i<inList.size(); i++){
				query += " , '"+inList.get(i)+"'";
			}
			query +=")";
		}
		if(!columnList.isEmpty()){
		query += " ORDER BY "+columnList.get(0);
		for(int i=1; i<columnList.size(); i++){
			query += " , "+columnList.get(i);
		}}
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	@Override
	public List getObjectsNotIn(String objet, List<String> columnList, String notCondition,  List<String> notList) {
		// TODO Auto-generated method stub
		String query = "FROM "+objet ;
		if(!notList.isEmpty()){
			query += " WHERE "+notCondition+" NOT IN ('"+notList.get(0)+"'";
			for(int i=1; i<notList.size(); i++){
				query += " , '"+notList.get(i)+"'";
			}
			query +=")";
		}
		if(!columnList.isEmpty()){
			query += " ORDER BY "+columnList.get(0);
			for(int i=1; i<columnList.size(); i++){
				query += " , "+columnList.get(i);
			}}
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	@Override
	public List getObjectsIn(String objet, List<String> columnList, String inCondition,  List<String> inList) {
		// TODO Auto-generated method stub
		String query = "FROM "+objet ;
		if(!inList.isEmpty()){
			query += " WHERE "+inCondition+" IN ('"+inList.get(0)+"'";
			for(int i=1; i<inList.size(); i++){
				query += " , '"+inList.get(i)+"'";
			}
			query +=")";
		}
		if(!columnList.isEmpty()){
			query += " ORDER BY "+columnList.get(0);
			for(int i=1; i<columnList.size(); i++){
				query += " , "+columnList.get(i);
			}}
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	@Override
	public int countTableByColumn(String tableName,String conditionColumn, List<WhereClause>conditionList) {
		// TODO Auto-generated method stub
		
		String query = "SELECT TO_NUMBER(count("+tableName+"."+conditionColumn+")) FROM "+tableName ;
		if(!conditionList.isEmpty()){
		query += " WHERE "+tableName+"."+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+"'"+conditionList.get(0).getValeur()+"'";
		for(int i=1; i<conditionList.size(); i++){
			query += " AND "+tableName+"."+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+"'"+conditionList.get(i).getValeur()+"'";			
		}
		}
		try {
			BigDecimal	bv = (BigDecimal) getSessionFactory().getCurrentSession()
					.createSQLQuery(query).uniqueResult();
			if (bv == null) {return 0;} else {return bv.intValue();}
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int countTableByColumnIn(String tableName,String conditionColumn, List<WhereClause>conditionList, String inCondition,  List<String> inList) {
		// TODO Auto-generated method stub
		
		String query = "SELECT TO_NUMBER(count("+tableName+"."+conditionColumn+")) FROM "+tableName ;
		if(!conditionList.isEmpty()){
			query += " WHERE "+tableName+"."+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+"'"+conditionList.get(0).getValeur()+"'";
			for(int i=1; i<conditionList.size(); i++){
				query += " AND "+tableName+"."+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+"'"+conditionList.get(i).getValeur()+"'";			
			}
			
			if(!inList.isEmpty()){
				query += " AND "+tableName+"."+inCondition+" IN ('"+inList.get(0)+"'";
				for(int i=1; i<inList.size(); i++){
					query += " , '"+inList.get(i)+"'";
				}
				query +=")";
			}
		}
		try {
			BigDecimal	bv = (BigDecimal) getSessionFactory().getCurrentSession()
					.createSQLQuery(query).uniqueResult();
			if (bv == null) {return 0;} else {return bv.intValue();}
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int countTableByColumnNotIn(String tableName,String conditionColumn, List<WhereClause>conditionList, String notCondition,  List<String> notList) {
		// TODO Auto-generated method stub
		
		String query = "SELECT TO_NUMBER(count("+tableName+"."+conditionColumn+")) FROM "+tableName ;
		if(!conditionList.isEmpty()){
			query += " WHERE "+tableName+"."+ conditionList.get(0).getColonne()+ conditionList.get(0).getComparateur()+"'"+conditionList.get(0).getValeur()+"'";
			for(int i=1; i<conditionList.size(); i++){
				query += " AND "+tableName+"."+ conditionList.get(i).getColonne()+ conditionList.get(i).getComparateur()+"'"+conditionList.get(i).getValeur()+"'";			
			}
			
			if(!notList.isEmpty()){
				query += " AND "+tableName+"."+notCondition+" NOT IN ('"+notList.get(0)+"'";
				for(int i=1; i<notList.size(); i++){
					query += " , '"+notList.get(i)+"'";
				}
				query +=")";
			}
		}
		try {
			BigDecimal	bv = (BigDecimal) getSessionFactory().getCurrentSession()
					.createSQLQuery(query).uniqueResult();
			if (bv == null) {return 0;} else {return bv.intValue();}
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int countDossierRegisseurCent() {
		// TODO Auto-generated method stub
		
		String query = "select TO_NUMBER(count(t.par_matricule)) from t_participations t where t.pa_paiment='N' and t.code_exp_imp in " + 
				"(select i.code_exp_imp from t_imputations i where i.statut_imp in('M8P','M9V','M1R','M9P'))";
		
		try {
			BigDecimal	bv = (BigDecimal) getSessionFactory().getCurrentSession()
					.createSQLQuery(query).uniqueResult();
			if (bv == null) {return 0;} else {return bv.intValue();}
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int getSeqNextVal(String seqName) {
		String query = "select "+seqName+".nextval from dual";
		
		try {
			BigDecimal	bv = (BigDecimal) getSessionFactory().getCurrentSession()
					.createSQLQuery(query).uniqueResult();
			
		
				return bv.intValue();
		
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void getSeqIncrement(String seqName, int increment) {
	String query = "alter sequence "+seqName+" increment by "+increment;
		try {
			getSessionFactory().getCurrentSession()
					.createSQLQuery(query);
		
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author User
	 *
	 */

	
	@Override
	@Transactional
	public String getCodeTable(String pseudo, int tailleChar, int size,
			String tableName, String column) {
		// Methode cr�ation d'un id code alphanumrique chronologique d'une
				// ligne de table ds la BD
				// SELECT MAX(to_number( SUBSTR(PAR_MATRICULE, 2, 10))) FROM T_PARTICIPANTS;

				String query = "SELECT MAX(TO_NUMBER(SUBSTR(" + column + ", "
						+ (tailleChar + 1) + ", " + (size)
						+ "))) FROM " + tableName + " WHERE "+column+" LIKE '"+pseudo+"%'";
				
				try {
					BigDecimal	bv = (BigDecimal) getSessionFactory().getCurrentSession()
							.createSQLQuery(query).uniqueResult();
					
					String tC = String.valueOf(size-tailleChar);
					if (bv == null) {
						//int numOrdT = i+1;
						String numOrd= String.format("%0"+tC+"d", 1);
						System.out.println("///////Verification requette V null");
						System.out.println("///////Verification requette V null et pseudo = "+pseudo);
						String s = pseudo + numOrd;
						System.out.println("///////Verification requette V null et pseudo + numrd = "+s);
						return s;
					} else {
						Integer v = bv.intValue();
						v = v +1;
						String numOrd= String.format("%0"+tC+"d", v);
						System.out.println("///////Verification requette V non null");
						String s = pseudo + numOrd;
						return s;
					}
				} catch (HibernateException e) {
					e.printStackTrace();
					return "0000";
				}
				// sess.close();

	}
	
	
	@Transactional
	public TOperateur getUser(String login){
		/*String query = "SELECT * FROM " + "T_Employes" + " a  WHERE a." + key + " ='"
				+ id + "' ";
		TEmployes A = (TEmployes)getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(TEmployes.class).uniqueResult();
		return A;*/
		Session session= getSessionFactory().getCurrentSession();
		Criteria crit = session.createCriteria(TOperateur.class);
		crit.add(Restrictions.eq("opeLogin", login));
		//System.out.println("Login de connexion Opérateur : Login "+((TOperateur) crit.uniqueResult()).getOpeLogin()+" Nom "+((TOperateur) crit.uniqueResult()).getOpeNom());
		return (TOperateur) crit.uniqueResult();	
	}
	
			protected String addFetchingStategy(String association, 
					 Class businessInterface)
		{
		StringBuilder hqlQuery = new StringBuilder ();
		
		//	Association formatting
		if (association == null)
		{
		association = "";
		}
		else
		{
		//	Fetch on the association
		hqlQuery.append(" left join fetch ");
		hqlQuery.append(association);
		
		association += ".";
		}
		
		//	Get properties
		PropertyDescriptor[] descriptors = 
		   BeanUtils.getPropertyDescriptors(businessInterface);
		
		//	Add fetching criterion
		String fetchAssociation;
		for (PropertyDescriptor descriptor : descriptors)
		{	
		//	Exclusion cases
		Class type = descriptor.getPropertyType();
		
		if ((type.isPrimitive() == true) ||
		(type.getCanonicalName().startsWith("java") == true))
		{
		continue;
		}
		
		//	Recursive fetching
		fetchAssociation = association + descriptor.getName();
		hqlQuery.append(addFetchingStategy(fetchAssociation, type));
		
		}
		
		return hqlQuery.toString();
		
		}
		
		

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}
