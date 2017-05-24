package net.engining.profile.init;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.engining.profile.api.enums.RoleEnum;
import net.engining.profile.entity.model.ProfileRole;
import net.engining.profile.entity.model.ProfileRoleAuth;
import net.engining.profile.init.support.TableInit;

@Service
public class ProfileRoleInit implements TableInit{

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 管理员角色权限
	 */
	private List<String> adminRoleAuths = Arrays.asList("WorkflowManage","CompanyList","InstList","AgencyList","MerchantManage",
														"MerchantInfoChangeLog","CustomerMessage","LoanBatchFacility","CashCreditLimit",
														"ReturnOrderAudit","OfflineCash","OperateLedger","ReturningRecords","SmsTemplate",
														"SmsList","ProductAdd","ProductQuery","ProductRuleModify","RiskRuleModify","ReportGenerator","ReportDownload",
														"ProfileUser","ProfileRole","SystemLimitModel","PersonalLimitModel","Orders",
														"RuleFileUploadManage","RuleGroupManage","net.engining.workflow.src.war.productManage.productConfirm",
														"net.engining.workflow.src.war.productManage.updateProduct","net.engining.workflow.src.war.productManage.riskControlUpdate",
														"net.engining.workflow.src.war.productManage.riskControlConfirm",
														"net.engining.workflow.src.war.productManage.productBuilt",
														"net.engining.workflow.srv.war.merchantcase.firstapprove",
														"net.engining.workflow.srv.war.merchantcase.secondapprove",
														"net.engining.workflow.srv.war.signed.manual",
														"net.engining.workflow.srv.war.signed.review",
														"net.engining.workflow.srv.war.signed.firstCreditConfirm",
														"net.engining.workflow.srv.war.signed.secondCreditConfirm");
	
	/**
	 * 运营管理角色
	 */
	private List<String> operRoleAuths = Arrays.asList("WorkflowManage","CompanyList","InstList","AgencyList","MerchantManage",
													"MerchantInfoChangeLog","CustomerMessage","LoanBatchFacility","CashCreditLimit",
													"ReturnOrderAudit","OfflineCash","OperateLedger","ReturningRecords","SmsTemplate",
													"SmsList","ProductAdd","ProductQuery","ProductRuleModify","ReportGenerator","ReportDownload",
													"net.engining.workflow.src.war.productManage.productConfirm",
													"net.engining.workflow.src.war.productManage.updateProduct",
													"net.engining.workflow.src.war.productManage.productBuilt",
													"net.engining.workflow.srv.war.merchantcase.firstapprove");

	/**
	 * 风控角色
	 */
	private List<String> riskControlAuths = Arrays.asList("WorkflowManage","ProductQuery","RiskRuleModify","ReportGenerator","ReportDownload",
														"SystemLimitModel","PersonalLimitModel","Orders",
														"RuleFileUploadManage","RuleGroupManage","net.engining.workflow.src.war.productManage.riskControlUpdate",
														"net.engining.workflow.src.war.productManage.riskControlConfirm",
														"net.engining.workflow.srv.war.merchantcase.secondapprove",
														"net.engining.workflow.srv.war.signed.manual",
														"net.engining.workflow.srv.war.signed.review",
														"net.engining.workflow.srv.war.signed.firstCreditConfirm",
														"net.engining.workflow.srv.war.signed.secondCreditConfirm");

	/**
	 * 个人用户权限
	 */
	private List<String> personlAuths = Arrays.asList(RoleEnum.PERSONAL.toString(),"net.engining.workflow.srv.war.signed.patch","net.engining.workflow.srv.war.signed.sign");
	
	/**
	 * 商户权限
	 */
	private List<String> merchantAuths = Arrays.asList(RoleEnum.MERCHANT.toString());
	
	
	@Transactional
	@Override
	public void init() {
		
		//初始化管理员用户
		ProfileRole admin = new ProfileRole();
		admin.setRoleId("ADMIN");
		admin.setRoleName("系统管理员");
		admin.fillDefaultValues();
		em.persist(admin);
		
		ProfileRole operation = new ProfileRole();
		operation.setRoleId("Operation");
		operation.setRoleName("运营操作");
		operation.fillDefaultValues();
		em.persist(operation);
		
		ProfileRole riskControl = new ProfileRole();
		riskControl.setRoleId("RiskControl");
		riskControl.setRoleName("风险控制");
		riskControl.fillDefaultValues();
		em.persist(riskControl);
		
		//初始化其他角色
		for(RoleEnum role : RoleEnum.values()){
			ProfileRole roleInfo = new ProfileRole();
			roleInfo.setRoleId(role.toString());
			roleInfo.setRoleName(role.getDesc());
			em.persist(roleInfo);
		}
		
		//初始化权限
		for (String s : adminRoleAuths){
			ProfileRoleAuth adminAuth = new ProfileRoleAuth();
			adminAuth.setRoleId(admin.getRoleId());
			adminAuth.setAuthority(s);
			em.persist(adminAuth);
		}
		
		for (String s : operRoleAuths){
			ProfileRoleAuth operAuth = new ProfileRoleAuth();
			operAuth.setRoleId(operation.getRoleId());
			operAuth.setAuthority(s);
			em.persist(operAuth);
		}
		
		for (String s : riskControlAuths){
			ProfileRoleAuth riskControlAuth = new ProfileRoleAuth();
			riskControlAuth.setRoleId(riskControl.getRoleId());
			riskControlAuth.setAuthority(s);
			em.persist(riskControlAuth);
		}
		
		for (String s : personlAuths){
			ProfileRoleAuth personalAuth = new ProfileRoleAuth();
			personalAuth.setRoleId(RoleEnum.PERSONAL.toString());
			personalAuth.setAuthority(s);
			em.persist(personalAuth);
		}
		
		for (String s : merchantAuths){
			ProfileRoleAuth merchantAuth = new ProfileRoleAuth();
			merchantAuth.setRoleId(RoleEnum.MERCHANT.toString());
			merchantAuth.setAuthority(s);
			em.persist(merchantAuth);
		}
		
		//初始化销售权限
		ProfileRoleAuth yrsSaleManAuth = new ProfileRoleAuth();
		yrsSaleManAuth.setRoleId(RoleEnum.YRS_SALEMAN.toString());
		yrsSaleManAuth.setAuthority(RoleEnum.YRS_SALEMAN.toString());
		em.persist(yrsSaleManAuth);
		
		ProfileRoleAuth cstSaleManAuth = new ProfileRoleAuth();
		cstSaleManAuth.setRoleId(RoleEnum.CST_SALEMAN.toString());
		cstSaleManAuth.setAuthority(RoleEnum.CST_SALEMAN.toString());
		em.persist(cstSaleManAuth);
		
		ProfileRoleAuth mthSaleManAuth = new ProfileRoleAuth();
		mthSaleManAuth.setRoleId(RoleEnum.MTH_SALEMAN.toString());
		mthSaleManAuth.setAuthority(RoleEnum.MTH_SALEMAN.toString());
		em.persist(mthSaleManAuth);
	}
}
