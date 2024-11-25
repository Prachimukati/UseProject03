package in.co.rays.project_3.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.PortfolioManagementDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PortfolioManagementModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "PortfolioManagementCtl", urlPatterns = { "/ctl/PortfolioManagementCtl" })
public class PortfolioManagementCtl extends BaseCtl {
	
	@Override
	protected void preload(HttpServletRequest request) {

	
		Map<Integer, String> map = new HashMap();
		map.put(1, "High");
		map.put(2, "Medium");
		map.put(3, "Low");
		
		
		request.setAttribute("rktl", map);
	    
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

	


		if (DataValidator.isNull(request.getParameter("portfolioName"))) {
			request.setAttribute("portfolioName", PropertyReader.getValue("error.require", " portfolioName"));

			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("initialInvestmentAmount"))) {
			request.setAttribute("initialInvestmentAmount", PropertyReader.getValue("error.require", " initialInvestmentAmount"));

			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("riskToleranceLevel"))) {
			request.setAttribute("riskToleranceLevel", PropertyReader.getValue("error.require", " riskToleranceLevel"));

			pass = false;
		}
		
		
		if (DataValidator.isNull(request.getParameter("investmentStrategy"))) {
			request.setAttribute("investmentStrategy", PropertyReader.getValue("error.require", "investmentStrategy"));
			pass = false;
		}

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		PortfolioManagementDTO dto = new PortfolioManagementDTO();

		System.out.println(request.getParameter("dob"));

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setPortfolioName(DataUtility.getString(request.getParameter("portfolioName")));
		dto.setInitialInvestmentAmount(DataUtility.getInt(request.getParameter("initialInvestmentAmount")));
		dto.setRiskToleranceLevel(DataUtility.getString(request.getParameter("riskToleranceLevel")));
		dto.setInvestmentStrategy(DataUtility.getString(request.getParameter("investmentStrategy")));
		
		
		
		

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		PortfolioManagementModelInt model = ModelFactory.getInstance().getPortfolioManagementModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			PortfolioManagementDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}	
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		PortfolioManagementModelInt model = ModelFactory.getInstance().getPortfolioManagementModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			PortfolioManagementDTO dto = (PortfolioManagementDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			PortfolioManagementDTO dto = (PortfolioManagementDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.PORTFOLIOMANAGEMENT_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PORTFOLIOMANAGEMENT_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PORTFOLIOMANAGEMENT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PORTFOLIOMANAGEMENT_VIEW;
	}

}
