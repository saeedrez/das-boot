package com.boot.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

public class ShipwreckControllerTest {

	@InjectMocks
	ShipwreckController shipwreckController;
	
	@Mock
	ShipwreckRepository shipwreckRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testShipwreckGet() {
	
		Shipwreck sw = new Shipwreck();
		sw.setId(1L);
		when(shipwreckRepository.findOne(1L)).thenReturn(sw);
		Shipwreck shipwreck = shipwreckController.get(1L);
		
		verify(shipwreckRepository).findOne(1L);
		
		assertEquals(1L, shipwreck.getId().longValue());
		
		// hamcrest way of doing stuff
		assertThat(shipwreck.getId(), is(1l));
		
	}
}
