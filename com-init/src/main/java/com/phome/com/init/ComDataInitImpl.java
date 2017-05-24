package net.engining.profile.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.engining.profile.init.support.ParamInit;
import net.engining.profile.init.support.ProfileDataInit;
import net.engining.profile.init.support.ProfileTestDataInit;
import net.engining.profile.init.support.TableInit;

@Service
public class ProfileDataInitImpl implements ProfileDataInit{

	@Autowired(required=false)
	private List<TableInit> tableDataInits;
	
	@Autowired(required=false)
	private List<ParamInit> paramInits;
	
	@Autowired(required=false)
	private List<ProfileTestDataInit> testDataInits;
	
	@Value("#{env['initTestData']?: false}")
	private boolean initTestData;
	
	@Override
	public void init() {
		
		if(initTestData && testDataInits != null){
			for(ProfileTestDataInit testDataInit : testDataInits){
				testDataInit.init();
			}
		}
		
		if(paramInits != null){
			//初始化参数
			for(ParamInit init : paramInits){
				init.init();
			}
		}

		if(tableDataInits != null){
			//初始化表数据
			for(TableInit init : tableDataInits){
				init.init();
			}
		}

	}

}
