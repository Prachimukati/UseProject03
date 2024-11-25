package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PortfolioManagementDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PortfolioManagementModelHibImp implements PortfolioManagementModelInt {
	
	@Override
	public long add(PortfolioManagementDTO dto) throws ApplicationException, DuplicateRecordException {
		
		 PortfolioManagementDTO existDto = null;
			
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
				throw new ApplicationException("Exception in PortfolioManagement Add " + e.getMessage());
			} finally {
				session.close();
			}


		return dto.getId();
	}

	@Override
	public void delete(PortfolioManagementDTO dto) throws ApplicationException {

		
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
			throw new ApplicationException("Exception in PortfolioManagement Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(PortfolioManagementDTO dto) throws ApplicationException, DuplicateRecordException {
		
		
		Session session = null;
		
		/*
		 * Transaction tx = null; PortfolioManagementDTO exesistDto = findByLogin(dto.getLogin());
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
			throw new ApplicationException("Exception in PortfolioManagement update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public PortfolioManagementDTO findByPK(long pk) throws ApplicationException {
		
		
		Session session = null;
		PortfolioManagementDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (PortfolioManagementDTO) session.get(PortfolioManagementDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public PortfolioManagementDTO findByLogin(String login) throws ApplicationException {
		
		
		
		Session session = null;
		PortfolioManagementDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PortfolioManagementDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (PortfolioManagementDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting PortfolioManagement by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria(PortfolioManagementDTO.class);
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
	public List search(PortfolioManagementDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		ArrayList<PortfolioManagementDTO> list = null;
		try {
			session = HibDataSource.getSession();
			System.out.println("---------------------------------");
			Criteria criteria = session.createCriteria(PortfolioManagementDTO.class);
			if (dto != null) {
				
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				
				  
				  if (dto.getPortfolioName() != null && dto.getPortfolioName().length() > 0) {
					  criteria.add(Restrictions.like("portfolioName", dto.getPortfolioName() + "%"));
					  }
				  
				  
				  if (dto.getInitialInvestmentAmount() > 0) {
					  criteria.add(Restrictions.eq("initialInvestmentAmount", dto.getInitialInvestmentAmount()));
					}
				  
			   
			   if (dto.getRiskToleranceLevel() != null && dto.getRiskToleranceLevel().length() > 0) {
					  criteria.add(Restrictions.like("riskToleranceLevel", dto.getRiskToleranceLevel() + "%"));
					  }
			 
			   if (dto.getInvestmentStrategy() != null && dto.getInvestmentStrategy().length() > 0) {
					  criteria.add(Restrictions.like("investmentStrategy", dto.getInvestmentStrategy() + "%"));
					  }
					
					
			}
					
					if (pageSize > 0) {
						pageNo = (pageNo - 1) * pageSize;
						criteria.setFirstResult(pageNo);
						criteria.setMaxResults(pageSize);
					}
					list = (ArrayList<PortfolioManagementDTO>) criteria.list();
				} catch (HibernateException e) {
					throw new ApplicationException("Exception in PortfolioManagement search");
				} finally {
					session.close();
				}

		
		return list;
	}

	@Override
	public List search(PortfolioManagementDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

	@Override
	public List getRoles(PortfolioManagementDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}


}
