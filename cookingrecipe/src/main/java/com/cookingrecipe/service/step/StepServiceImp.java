package com.cookingrecipe.service.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookingrecipe.dao.StepDAOImp;
import com.cookingrecipe.entity.Step;

@Service
public class StepServiceImp implements IStepService{

	private static Logger log = LoggerFactory.getLogger(StepServiceImp.class);
	
	@Autowired
	private StepDAOImp stepDAO;
	
	@Transactional(readOnly = false)
	@Override
	public void save(Step step) throws Exception {
		log.info("Processing in StepServiceImp...");
		try {
			System.out.println(step);
			stepDAO.save(step);
			log.info("Save step successful!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
