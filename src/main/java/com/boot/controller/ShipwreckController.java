package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

	@Autowired
	ShipwreckRepository shipwreckRepository;
	
	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<Shipwreck> list() {
		// return ShipwreckStub.list();
		return shipwreckRepository.findAll();
	}

	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		// return ShipwreckStub.create(shipwreck);
		return shipwreckRepository.saveAndFlush(shipwreck);	
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Shipwreck get(@PathVariable long id) {
		// return ShipwreckStub.get(id);
		return shipwreckRepository.findOne(id);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public Shipwreck update(@PathVariable long id, @RequestBody Shipwreck shipwreck) {
		// update does not work
		return ShipwreckStub.update(id, shipwreck);		 // still a mock
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable long id) {
		Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
		shipwreckRepository.delete(existingShipwreck);
		return existingShipwreck;		
	}

}
