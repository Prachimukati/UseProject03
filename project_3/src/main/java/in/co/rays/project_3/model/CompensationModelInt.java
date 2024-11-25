package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto. CompensationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface CompensationModelInt {
	
	
	public long add( CompensationDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete( CompensationDTO dto)throws ApplicationException;
	public void update( CompensationDTO dto)throws ApplicationException,DuplicateRecordException;
	public  CompensationDTO findByPK(long pk)throws ApplicationException;
	public  CompensationDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search( CompensationDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search( CompensationDTO dto)throws ApplicationException;
	public List getRoles( CompensationDTO dto)throws ApplicationException;


}
