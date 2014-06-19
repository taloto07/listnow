package com.listnow.hosting.service;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class ListnowPersistenceInitializer {
	@Inject
	ListnowPersistenceInitializer(PersistService service){
		service.start();
	}
}
