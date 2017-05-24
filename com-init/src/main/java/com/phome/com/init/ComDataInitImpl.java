package com.phome.com.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.phome.com.init.support.ComDataInit;
import com.phome.com.init.support.ComTestDataInit;
import com.phome.com.init.support.ParamInit;
import com.phome.com.init.support.TableInit;

@Service
public class ComDataInitImpl implements ComDataInit{

	@Autowired(required=false)
	private List<TableInit> tableDataInits;
	
	@Autowired(required=false)
	private List<ParamInit> paramInits;
	
	@Autowired(required=false)
	private List<ComTestDataInit> testDataInits;
	
	@Value("#{env['initTestData']?: false}")
	private boolean initTestData;
	
	@Override
	public void init() {
		
		if(initTestData && testDataInits != null){
			for(ComTestDataInit testDataInit : testDataInits){
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
