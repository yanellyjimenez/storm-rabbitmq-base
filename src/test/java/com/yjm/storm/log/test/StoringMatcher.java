package com.yjm.storm.log.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Values;

import com.yjm.storm.config.Configs;
import com.yjm.storm.log.FieldNames;
import com.yjm.storm.log.IndexerBolt;
import com.yjm.storm.log.LogEntry;

public class StoringMatcher {

	private static class StoringMatchers extends BaseMatcher<Values>{
		
		private TopologyContext context; 
		
		public StoringMatchers(){
			
		}
		
		private final List<Values> objects = new ArrayList<Values>();
		@Override
		public boolean matches(Object item)
		{
			if( item instanceof Values)
			{
				objects.add((Values)item);
				return true;
			}
			return false;
		}
		
		@Override
		public void describeTo( Description description)
		{
			description.appendText("any integer");
		}
		
		
		@SuppressWarnings("unused")
		public Values getLastValue()
		{
			return objects.remove(0);
		}
	}
	
	@Test
	public void testIndexing() throws IOException{
		//Config, ensure we are in debug mode
		Map config = new HashMap();
		config.put(Configs.TOPOLOGY_DEBUG, true);
		Node node = NodeBuilder.nodeBuilder().local(true).node();
		Client client = node.client();
		/*
		final OutputCollector collector = context.mock(OutputCollector.class);
		IndexerBolt bolt = new IndexerBolt();
		bolt.prepare(config, null, collector);
		
		final LogEntry entry = getEntry();
		
		final Tuple tuple = getTuple();
		final StoringMatcher matcher = new StoringMatcher();
		context.checking(new Expectations(){{
			oneOf(tuple).getValueByField(FieldNames.LOG_ENTRY);
		will(returnValue(value));	
		}}	);
		
		bolt.execute(tuple);
		context.assertIsSatisfied();
		
		String id = (String)matcher.getLastValue.get(1);
		
		GetResponse response = client.prepareGet(IndexerBolt.INDEX_NAME, IndexerBolt.INDEX_TYPE, id)
				.execute()
				.actionGet();
		//assertTrue(response.getId());
		*/
	}
	
	
}
