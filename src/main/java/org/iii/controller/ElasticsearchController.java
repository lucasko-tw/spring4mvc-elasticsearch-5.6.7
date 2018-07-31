package org.iii.controller;

import org.iii.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api", produces = "application/json; charset=utf-8")
public class ElasticsearchController {

	@Autowired
	ElasticsearchService ES_SERVICE;

	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	public @ResponseBody String deleteMemberFromGroup(@RequestBody String body) {
		return ES_SERVICE.toString();
	}

	@RequestMapping(value = { "/users" }, method = { RequestMethod.GET })
	public @ResponseBody String getUsers() {
		return ES_SERVICE.getUsers().toString();
	}

}
