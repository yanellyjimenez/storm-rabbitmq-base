package com.yjm.storm.topology;

import storm.trident.state.StateFactory;
import backtype.storm.topology.TopologyBuilder;

import com.yjm.storm.rabbitmq.RabbitMQSpout;

public class ConfigurableTopologyBuilder {

	StateFactory f;
	RabbitMQSpout spout;
	
	
	TopologyBuilder builder = new TopologyBuilder();

	
}
