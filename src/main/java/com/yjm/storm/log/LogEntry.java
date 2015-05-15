package com.yjm.storm.log;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LogEntry {

	public  static Logger LOG = Logger.getLogger( LogEntry.class);
	
	private String source;
	private String type;
	private List<String> tags = new ArrayList<String>();
	private Map<String, String> fields = new HashMap<String, String>();
	private Date timestamp;
	private String sourceHost;
	private String sourcePath;
	private String message ="";
	private boolean filter = false;
	private NotificationDetails notifyAbout;
	private static String [] FORMATS = new String[]
			{ "yyyy-MM-dd 'T' HH:mm:ss:SSS" , "yyMMddHHmmssZ" };
		
	@SuppressWarnings("unchecked")
	public LogEntry(JSONObject json ){
	
		source = (String) json.get("@source");
		timestamp = parseDate( (String) json.get("@timestamp"));
		sourceHost = (String)json.get("@source_host");
		sourcePath = (String)json.get("@source_path");
		message = (String) json.get("@message");
		type = (String) json.get("@type");
		JSONArray array = (JSONArray)json.get("@tags");
		tags.addAll(array);
		JSONObject fields = (JSONObject)json.get("@fields");
		fields.putAll(fields);	
	}
	
	public Date parseDate(String value){
		
		for(int i=0;i < FORMATS.length;i++)
		{		
			SimpleDateFormat format = new SimpleDateFormat(FORMATS[i]);
			Date temp;
			try
			{
				temp = format.parse(value);
				if( temp != null )
				{					
					return temp;
				}
			}catch(ParseException e)
			{				
				LOG.error("parseDate - EXC",e);
			}			
		}
		LOG.error("parseDate - Error: No se parseo el TIMESTAMP del LOG ");
		return null;			
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON()
	{
		JSONObject json = new JSONObject();
		json.put("@source", source);
		json.put("@timestamp", DateFormat.getDateInstance().format(timestamp));
		json.put("@source_host", sourceHost);
		json.put("@source_path", sourcePath);
		json.put("@message", message);
		json.put("@type", type);
		JSONArray temp = new JSONArray();
		temp .addAll(tags);
		json.put("@tags", temp);		
		JSONObject fieldTemp = new JSONObject();
		fieldTemp.putAll(fields);
		json.put("@fields", fieldTemp);
		return json;
		
	}

	public static Logger getLOG() {
		return LOG;
	}

	public static void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSourceHost() {
		return sourceHost;
	}

	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public NotificationDetails getNotifyAbout() {
		return notifyAbout;
	}

	public void setNotifyAbout(NotificationDetails notifyAbout) {
		this.notifyAbout = notifyAbout;
	}

	public static String[] getFORMATS() {
		return FORMATS;
	}

	public static void setFORMATS(String[] fORMATS) {
		FORMATS = fORMATS;
	}
	
	
	
	
	
	
}
