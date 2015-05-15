package com.yjm.storm.config;

import java.util.Map;

public class HappeningEventDataMapping {
	
	private String id;
	private String happeningId;
	private String eventId;
	private Map<String, Object> mapping;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHappeningId() {
		return happeningId;
	}
	public void setHappeningId(String happeningId) {
		this.happeningId = happeningId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Map<String, Object> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, Object> mapping) {
		this.mapping = mapping;
	}
	
	

}
