package in.co.rays.project_3.dto;
import java.util.Date;

public class CompensationDTO extends BaseDTO{
	
	private String staffMember;
	private int paymentAmount;
	private Date dateApplied;
	private String state;

	public String getStaffMember() {
		return staffMember;
	}
	public void setStaffMember(String staffMember) {
		this.staffMember = staffMember;
	}
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
