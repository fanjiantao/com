package net.engining.profile.init;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.engining.profile.api.payload.UserStatus;
import net.engining.profile.entity.model.ProfileUser;
import net.engining.profile.entity.model.ProfileUserRole;
import net.engining.profile.init.support.TableInit;

@Service
public class ProfileUserInit implements TableInit{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void init() {
		
		ProfileUser user = new ProfileUser();
		user.setUsername("admin");
		user.setName("admin");
		user.setSalt("111111");
		user.setMobile("13000000000");
		user.setEmail("admin@admin.com");
		user.setMtnTimestamp(new Date());
		user.setMtnUser("System");
		user.setPassword(passwordEncoder.encodePassword("admin", user.getSalt()));
		user.setStatus(UserStatus.A);
		user.setPwdTries(0);
		
		try {
			user.setPwdExpDate(DateUtils.parseDate("2020-12-31", new String[]{"yyyy-MM-dd"}));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.persist(user);
		
		//初始化角色和用户对应关系
		ProfileUserRole userRole = new ProfileUserRole();
		userRole.setRoleId("ADMIN");
		userRole.setUserId(user.getId());
		em.persist(userRole);
		
		// 运营
		user = new ProfileUser();
		user.setUsername("operator");
		user.setName("运营小哥");
		user.setSalt("111111");
		user.setMobile("13000000001");
		user.setEmail("operator@admin.com");
		user.setMtnTimestamp(new Date());
		user.setMtnUser("System");
		user.setPassword(passwordEncoder.encodePassword("admin", user.getSalt()));
		user.setStatus(UserStatus.A);
		user.setPwdTries(0);
		
		try {
			user.setPwdExpDate(DateUtils.parseDate("2020-12-31", new String[]{"yyyy-MM-dd"}));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.persist(user);
		
		//初始化角色和用户对应关系
		userRole = new ProfileUserRole();
		userRole.setRoleId("Operation");
		userRole.setUserId(user.getId());
		em.persist(userRole);
		
		// 风控
		user = new ProfileUser();
		user.setUsername("risk");
		user.setName("风控小哥");
		user.setSalt("111111");
		user.setMobile("13000000001");
		user.setEmail("operator@admin.com");
		user.setMtnTimestamp(new Date());
		user.setMtnUser("System");
		user.setPassword(passwordEncoder.encodePassword("admin", user.getSalt()));
		user.setStatus(UserStatus.A);
		user.setPwdTries(0);
		
		try {
			user.setPwdExpDate(DateUtils.parseDate("2020-12-31", new String[]{"yyyy-MM-dd"}));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.persist(user);
		
		//初始化角色和用户对应关系
		userRole = new ProfileUserRole();
		userRole.setRoleId("RiskControl");
		userRole.setUserId(user.getId());
		em.persist(userRole);
	}
	
//	public static void main(String[] args) {
//		
//		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//		System.out.println(encoder.encodePassword("admin", "111111"));
//	}

}
