package com.yjm.storm.config;

import java.util.List;

import com.yjm.storm.model.dto.QnueEvent;


public class HappeningFormat  {
	
	private List<QnueEvent> formatDefinition;
	private List<QnueEvent> preFormatDefinitions;
	private List<QnueEvent> postFormatDefinitions;
	private List<QnueEvent> happeningSourceIds;
	
	
	public List<QnueEvent> getFormatDefinition() {
		return formatDefinition;
	}
	public void setFormatDefinition(List<QnueEvent> formatDefinition) {
		this.formatDefinition = formatDefinition;
	}
	public List<QnueEvent> getPreFormatDefinitions() {
		return preFormatDefinitions;
	}
	public void setPreFormatDefinitions(List<QnueEvent> preFormatDefinitions) {
		this.preFormatDefinitions = preFormatDefinitions;
	}
	public List<QnueEvent> getPostFormatDefinitions() {
		return postFormatDefinitions;
	}
	public void setPostFormatDefinitions(List<QnueEvent> postFormatDefinitions) {
		this.postFormatDefinitions = postFormatDefinitions;
	}
	public List<QnueEvent> getHappeningSourceIds() {
		return happeningSourceIds;
	}
	public void setHappeningSourceIds(List<QnueEvent> happeningSourceIds) {
		this.happeningSourceIds = happeningSourceIds;
	}
		
}
