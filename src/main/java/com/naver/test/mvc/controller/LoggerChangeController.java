package com.naver.test.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.test.orm.mapper.BoardUserMapper;

import ch.qos.logback.classic.LoggerContext;

@Controller
@RequestMapping("/logger")
public class LoggerChangeController {
	private final Logger logger = LoggerFactory.getLogger(LoggerChangeController.class);

	@Autowired
	private BoardUserMapper boardUserMapper;

	@RequestMapping("save")
	@ResponseBody
	public String save(@RequestBody List<LocalLogger> localLoggerList) {

		for (LocalLogger log : localLoggerList) {
			logger.info(log.getName() + log.getLevel());

			ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
					.getLogger(log.getName());
			logger.setLevel(ch.qos.logback.classic.Level.toLevel(log.getLevel()));
		}

		return "/spring-test/logger/list";
	}

	@RequestMapping("list")
	public String list(Model model) {
		List<LocalLogger> resultList = new ArrayList<LocalLogger>();

		ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		LoggerContext loggerContext = rootLogger.getLoggerContext();
		List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();

		for (ch.qos.logback.classic.Logger logger : loggerList) {
			LocalLogger localLogger = new LocalLogger();
			localLogger.setName(logger.getName());
			localLogger.setLevel(logger.getLevel() == null ? "" : logger.getLevel().toString());
			resultList.add(localLogger);
		}

		model.addAttribute("loggerList", resultList);

		return "logger";
	};

	@RequestMapping("dbtest")
	@ResponseBody
	public String dbtest() {

		boardUserMapper.getBoardUserList();

		return "success";
	};

	public static class LocalLogger {
		private String name;
		private String level;

		public LocalLogger() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}
	}
}
