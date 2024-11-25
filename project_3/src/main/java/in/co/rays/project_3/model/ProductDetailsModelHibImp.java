package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.ProductDetailsDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class ProductDetailsModelHibImp implements ProductDetailsModelInt {
	
	@Override
	public long add(ProductDetailsDTO dto) throws ApplicationException, DuplicateRecordException {
		
		 ProductDetailsDTO existDto = null;
			
			Session session = HibDataSource.getSession();
			Transaction tx = null;
			try {

				tx = session.beginTransaction();

				session.save(dto);

				dto.getId();
				tx.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (tx != null) {
					tx.rollback();

				}
				throw new ApplicationException("Exception in ProductDetails Add " + e.getMessage());
			} finally {
				session.close();
			}


		return dto.getId();
	}

	@Override
	public void delete(ProductDetailsDTO dto) throws ApplicationException {

		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in ProductDetails Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(ProductDetailsDTO dto) throws ApplicationException, DuplicateRecordException {
		
		
		Session session = null;
		
		/*
		 * Transaction tx = null; ProductDetailsDTO exesistDto = findByLogin(dto.getLogin());
		 * 
		 * if (exesistDto != null && exesistDto.getId() != dto.getId()) { throw new
		 * DuplicateRecordException("Login id already exist"); }
		 * 
		 */		  Transaction tx = null;
		 

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in ProductDetails update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public ProductDetailsDTO findByPK(long pk) throws ApplicationException {
		
		
		Session session = null;
		ProductDetailsDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (ProductDetailsDTO) session.get(ProductDetailsDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public ProductDetailsDTO findByLogin(String login) throws ApplicationException {
		
		
		
		Session session = null;
		ProductDetailsDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ProductDetailsDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (ProductDetailsDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting ProductDetails by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ProductDetailsDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Banks list");
		} finally {
			session.close();
		}

		return list;
	}

	/*
	 * @Override public List list(int pageNo, int pageSize) throws
	 * ApplicationException { // TODO Auto-generated method stub return null; }
	 */
	@Override
	public List search(ProductDetailsDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		ArrayList<ProductDetailsDTO> list = null;
		try {
			session = HibDataSource.getSession();
			System.out.println("---------------------------------");
			Criteria criteria = session.createCriteria(ProductDetailsDTO.class);
			if (dto != null) {
				
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				
				  
				  if (dto.getName() != null && dto.getName().length() > 0) {
					  criteria.add(Restrictions.like("name", dto.getName() + "%"));
					  }
				  
				  
				  if (dto.getDescription() != null && dto.getDescription().length() > 0) {
					  criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
					  }
				  
				  if (dto.getPrice() > 0) {
					  criteria.add(Restrictions.eq("price", dto.getPrice()));
					}

			   if (dto.getDateOfPerchase() != null && dto.getDateOfPerchase().getTime() > 0) {
					criteria.add(Restrictions.eq("dateOfPerchase", dto.getDateOfPerchase()));
				}
			   
			   if (dto.getCategory() != null && dto.getCategory().length() > 0) {
					  criteria.add(Restrictions.like("category", dto.getCategory() + "%"));
					  }
			 
				
					
					
			}
					
					if (pageSize > 0) {
						pageNo = (pageNo - 1) * pageSize;
						criteria.setFirstResult(pageNo);
						criteria.setMaxResults(pageSize);
					}
					list = (ArrayList<ProductDetailsDTO>) criteria.list();
				} catch (HibernateException e) {
					throw new ApplicationException("Exception in ProductDetails search");
				} finally {
					session.close();
				}

		
		return list;
	}

	@Override
	public List search(ProductDetailsDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

	@Override
	public List getRoles(ProductDetailsDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}


}
