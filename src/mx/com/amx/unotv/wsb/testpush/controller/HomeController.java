/**
 * 
 */
package mx.com.amx.unotv.wsb.testpush.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.unotv.wsb.testpush.bo.PushBO;
import mx.com.amx.unotv.wsb.testpush.controller.exception.ControllerException;

/**
 * @author Jesus A. Macias Benitez
 *
 */


@Controller
@RequestMapping("pushController")
public class HomeController {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	PushBO  pushBO;
	
	
	@RequestMapping(value = "/printLog", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public void printLog(@RequestParam(name = "json") String json) throws ControllerException {
		logger.info("--- printLog [ pushController ] ---");
		logger.debug("--- json---");
		logger.debug(""+json.toString());
		
		try {
			pushBO.printLog(json);
		} catch (Exception e) {
			logger.error("Exception findAll  [ CategoriaController ]: ", e);
			throw new ControllerException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/push", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public void push(@RequestParam("json") String json) throws ControllerException {
		logger.info("--- push [ pushController ] ---");
		logger.debug("--- json---");
		logger.debug(""+json.toString());
		
		
		try {
			pushBO.push(json);
		} catch (Exception e) {
			logger.error("Exception push  [ pushController ]: ", e);
			throw new ControllerException(e.getMessage());
		}
	}

}
