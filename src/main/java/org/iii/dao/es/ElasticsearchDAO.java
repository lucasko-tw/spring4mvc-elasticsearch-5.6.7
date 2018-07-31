package org.iii.dao.es;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.JSONObject;

public class ElasticsearchDAO {

	private TransportClient client;

	@SuppressWarnings("resource")
	public ElasticsearchDAO(String HOST_IP, int HOST_PORT, String CLUSTER_NAME) {

		Settings esSettings = Settings.builder().put("cluster.name", CLUSTER_NAME).build();

		try {
			client = new PreBuiltTransportClient(esSettings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(HOST_IP), HOST_PORT));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SearchHits getSearchHits(String index, String type, int from, int size) {
		FieldSortBuilder sortBuilder = SortBuilders.fieldSort("age")
				.order(SortOrder.DESC);

		SearchRequestBuilder search = client.prepareSearch(index).setTypes(type)
				.addSort(sortBuilder).setFrom(from)
				.setSize(size);

		SearchResponse response = search.execute().actionGet();
		SearchHits hits = response.getHits();
		return hits;
	}

	public void saveJson(String index, String type, JSONObject json, String _id) {
		// System.out.println(esJSON);
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		bulkRequest.add(client.prepareIndex(index, type, _id).setSource(json.toString()));

		BulkResponse bulkResponse = bulkRequest.get();

	}

}
