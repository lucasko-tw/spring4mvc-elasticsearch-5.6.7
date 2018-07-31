package org.iii.service;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.iii.dao.es.ElasticsearchDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class ElasticsearchService {

	@Autowired
	ElasticsearchDAO ES_DAO;

	public JSONArray getUsers() {
		int from = 0;
		int size = 100;

		JSONArray array = new JSONArray();
		SearchHits hits = ES_DAO.getSearchHits("users", "ntust", from, size);
		for (SearchHit hit : hits.getHits()) {
			JSONObject userJSON = new JSONObject(hit.getSourceAsString());
			array.put(userJSON);
		}
		return array;
	}

}
