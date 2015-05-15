package com.yjm.storm.bolt;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class WordBolt extends BaseBasicBolt {

	private static final long serialVersionUID = 1314408152431265092L;
	
//For holding words and counts
    Map<String, Integer> counts = new HashMap<String, Integer>();

    //execute is called to process tuples
    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
      //Get the word contents from the tuple
      String word = tuple.getString(0);
      System.out.println("Word: "+word);
      //Have we counted any already?
      Integer count = counts.get(word);
      if (count == null)
        count = 0;
      //Increment the count and store it
      count++;
      counts.put(word, count);
      
      //Emit the word and the current count
      System.out.println("collector.emit(new Values(word, count));");
      collector.emit(new Values(word, count));
      System.out.println(collector.toString());
    }

    //Declare that we will emit a tuple containing two fields; word and count
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields("word", "count"));
    }
  }
