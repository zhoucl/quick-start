package com.eboji.vo;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TTransportFactory;

public class ServerConfigVO {
	private int port;
	private int clientTimeout;
	private TProtocolFactory tProtocolFactory;
	private TTransportFactory tTransportFactory;
	private TMultiplexedProcessor tMultiplexedProcessor;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getClientTimeout() {
		return clientTimeout;
	}
	public void setClientTimeout(int clientTimeout) {
		this.clientTimeout = clientTimeout;
	}
	public TProtocolFactory gettProtocolFactory() {
		return tProtocolFactory;
	}
	public void settProtocolFactory(TProtocolFactory tProtocolFactory) {
		this.tProtocolFactory = tProtocolFactory;
	}
	public TTransportFactory gettTransportFactory() {
		return tTransportFactory;
	}
	public void settTransportFactory(TTransportFactory tTransportFactory) {
		this.tTransportFactory = tTransportFactory;
	}
	public TMultiplexedProcessor gettMultiplexedProcessor() {
		return tMultiplexedProcessor;
	}
	public void settMultiplexedProcessor(TMultiplexedProcessor tMultiplexedProcessor) {
		this.tMultiplexedProcessor = tMultiplexedProcessor;
	}
}
