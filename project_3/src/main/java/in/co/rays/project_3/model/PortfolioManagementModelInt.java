package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.PortfolioManagementDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface PortfolioManagementModelInt {
	
	
	public long add(PortfolioManagementDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(PortfolioManagementDTO dto)throws ApplicationException;
	public void update(PortfolioManagementDTO dto)throws ApplicationException,DuplicateRecordException;
	public PortfolioManagementDTO findByPK(long pk)throws ApplicationException;
	public PortfolioManagementDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(PortfolioManagementDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(PortfolioManagementDTO dto)throws ApplicationException;
	public List getRoles(PortfolioManagementDTO dto)throws ApplicationException;


}
