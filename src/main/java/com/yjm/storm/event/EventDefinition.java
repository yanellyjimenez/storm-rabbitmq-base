package com.yjm.storm.event;

import java.util.List;

import com.yjm.storm.config.HappeningFormat;

public class EventDefinition {

		
		private EventDataModel dataModel;
		private String validationRule;
		private List<HappeningFormat> happeningFormats;
		private String eventFormat;
		private String eventTarget;
		
		
		public EventDataModel getDataModel() {
			return dataModel;
		}
		public void setDataModel(EventDataModel dataModel) {
			this.dataModel = dataModel;
		}
		public String getValidationRule() {
			return validationRule;
		}
		public void setValidationRule(String validationRule) {
			this.validationRule = validationRule;
		}
		
		public String getEventFormat() {
			return eventFormat;
		}
		public void setEventFormat(String eventFormat) {
			this.eventFormat = eventFormat;
		}
		public String getEventTarget() {
			return eventTarget;
		}
		public void setEventTarget(String eventTarget) {
			this.eventTarget = eventTarget;
		}
		
		
		
		
		
		
	
}
