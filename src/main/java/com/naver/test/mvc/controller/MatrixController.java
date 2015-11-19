package com.naver.test.mvc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mvc")
public class MatrixController {
	private final Logger logger = LoggerFactory.getLogger(MatrixController.class);

	/**
	 * /mat/matrix;a=2;b=3;c=4
	 * 
	 * @param matrixVars
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mat/{matrix}", method = RequestMethod.GET)
	public String showPortfolioValues(@MatrixVariable Map<String, String> matrixVars) {

		logger.info("Values which are: {}", matrixVars.toString());

		return matrixVars.toString();
	}

	/**
	 * /mat1/owners/42;age=20;height=180/pets/21;color=brown,white;age=5
	 * 
	 * @param ownerVars
	 * @param petVars
	 * @return
	 */
	@RequestMapping(value = "/mat1/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
	public String showPortfolioValues1(@PathVariable String ownerId,
			@MatrixVariable(pathVar = "ownerId") Map<String, String> ownerVars, @PathVariable String petId,
			@MatrixVariable(pathVar = "petId") Map<String, String> petVars) {

		logger.info("ownerId: {}", ownerId);
		logger.info("Values which are: {}", ownerVars.toString());
		logger.info("petId: {}", petId);
		logger.info("Values which are: {}", petVars.toString());

		return ownerVars.toString() + "/n" + petVars.toString();
	}

	/**
	 * /mat2/owners/42/pets/21?ownerAge=20&ownerHeight=180&petColor=
	 * brown&petColor=white&petAge=5
	 * 
	 * @param ownerVars
	 * @param petVars
	 * @return
	 */
	@RequestMapping(value = "/mat2/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
	public String showPortfolioValues2(HttpServletRequest request, @PathVariable String ownerId,
			@PathVariable String petId) {
		Map<String, String> resultMap = new HashMap<String, String>();

		Map<String, String[]> param = request.getParameterMap();

		logger.info("ownerId: {}", ownerId);
		logger.info("petId: {}", petId);
		for (Object key : param.keySet()) {
			String keyStr = (String) key;
			String[] value = (String[]) param.get(keyStr);
			logger.info(keyStr + " : " + Arrays.toString(value));
			resultMap.put(keyStr, Arrays.toString(value));
		}

		return resultMap.toString();
	}
}
