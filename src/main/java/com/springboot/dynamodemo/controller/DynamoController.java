package com.springboot.dynamodemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dynamodemo.model.CatalogItem;
import com.springboot.dynamodemo.service.DynamoService;

@RestController
@RequestMapping(path = "/dynamo/")
public class DynamoController {

	@Autowired
	DynamoService dynamoService;

	@RequestMapping(method = RequestMethod.POST, path = "create")
	public CatalogItem save(@Valid @RequestBody CatalogItem catalogItem) {
		return dynamoService.save(catalogItem);
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	public CatalogItem save(@PathVariable String id) {
		return dynamoService.load(id);
	}
}
