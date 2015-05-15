package com.yjm.storm.log;

import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.yjm.storm.config.Configs;

/**
 * @author: yanellyjm
 */
public class IndexerBolt extends BaseRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2173397649782253572L;
	
	public static final Logger LOG = LoggerFactory.getLogger(IndexerBolt.class);
	
	public static final String INDEX_NAME ="index";
			
	public static final String INDEX_TYPE  ="type";			
	
	private OutputCollector collector;
			
	private TopologyContext context;

	private Client client;
	/* 
	 * 
	 * (non-Javadoc)
	 * @see backtype.storm.task.IBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext, backtype.storm.task.OutputCollector)
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		
		Node node ;
		this.collector = collector;
		this.context = context;
		
		if( (Boolean)stormConf.get(Configs.TOPOLOGY_DEBUG) == null)
		{
			node = NodeBuilder.nodeBuilder().local(true).node();
			
			
		}else
		{
		   String clusterName = (String) stormConf.get(Configs.ELASTIC_CLUSTER_NAME);
		   if( clusterName == null)
		   {
			   clusterName = Configs.DEFAUILT_ELASTIC_CLUSTER;			 
		   }
		   node = NodeBuilder.nodeBuilder().clusterName(clusterName).node();
		}
		client = node.client();		
	}

	@Override
	public void execute(Tuple input) {

		 LogEntry  entry = (LogEntry)input.getValueByField(FieldNames.LOG_ENTRY);
		if( entry == null){
				LOG.error("Received null or incorrect value form tuple");
				return;
		}
		String toBeIndexed = entry.toJSON().toJSONString();
		IndexResponse response = client.prepareIndex(INDEX_NAME, INDEX_TYPE)
				.setSource(toBeIndexed)
				.execute()
				.actionGet();
		if(response == null)
		{
			LOG.error("Failed to index Tuple"+input.toString());
		}else{
			LOG.debug("Indexing success on Tuple"+input.toString());
			collector.emit( new Values(entry, response.getId()));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

	public Client getClient() {
		return client;
	}
	
	

}
