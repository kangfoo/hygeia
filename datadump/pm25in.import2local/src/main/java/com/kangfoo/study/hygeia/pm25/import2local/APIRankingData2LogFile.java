package com.kangfoo.study.hygeia.pm25.import2local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIRankingData2LogFile {

	private final static Logger logger = LoggerFactory
			.getLogger(APIRankingData2LogFile.class);

	private final static String all_citiesUrl = "http://www.pm25.in/api/querys/aqi_ranking.json?token=%s";

	private final static String token = "5j1znBVAsnSf5xQyNQyq";

//	public void importData() {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		try {
//
//			String urlstr = String.format(all_citiesUrl, token);
//
//			getData(httpclient, urlstr);
//
//		} catch (ClientProtocolException e) {
//			logger.error("", e);
//		} catch (IOException e) {
//			logger.error("", e);
//		} finally {
//			try {
//				httpclient.close();
//			} catch (IOException e) {
//				logger.error("", e);
//			}
//		}
//	}
//
//	private void getData(CloseableHttpClient httpclient, String urlstr)
//			throws IOException, ClientProtocolException {
//		HttpGet httpget = new HttpGet(urlstr);
//
//		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
//
//			public String handleResponse(final HttpResponse response)
//					throws ClientProtocolException, IOException {
//				int status = response.getStatusLine().getStatusCode();
//				if (status >= 200 && status < 300) {
//					HttpEntity entity = response.getEntity();
//					return entity != null ? EntityUtils.toString(entity) : null;
//				} else {
//					throw new ClientProtocolException(
//							"Unexpected response status: " + status);
//				}
//			}
//		};
//		String responseBody = httpclient.execute(httpget, responseHandler);
//
//		logger.info(responseBody);
//	}
//
//	public static void main(String[] args) {
//		APIRankingData2LogFile a = new APIRankingData2LogFile();
//		a.importData();
//	}
}
