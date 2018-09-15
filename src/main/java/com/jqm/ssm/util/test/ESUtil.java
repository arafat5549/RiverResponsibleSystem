package com.jqm.ssm.util.test;

/**
 * Created by Administrator on 2018/7/19.
 * ElasticSeatch工具类
 * Spark测试
 */
public class ESUtil {
    /*
    private static TransportClient client = null;

    //获取客户端
    public static  TransportClient getClient() {
        if(client!=null){
            return client;
        }

        Settings settings = Settings.EMPTY;//Settings.builder().put("cluster.name", "tf").build();
        try {
              client =      new PreBuiltTransportClient(settings)
                     // .addTransportAddress(new InetSocketTransportAddress(InetAddress.getLocalHost(),9200))
                     // .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9200))
                   // .addTransportAddress(new InetSocketTransportAddress(InetAddress.getLocalHost(),9300))
                   .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
                    ;
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300))
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host3"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
    //
    public static String addIndex(String index,String type,ESDoc Doc){
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", Doc.getId());
        hashMap.put("title", Doc.getTitle());
        hashMap.put("describe", Doc.getDescribe());
        hashMap.put("author", Doc.getAuthor());
        IndexResponse response = getClient().prepareIndex(index, type).setSource(hashMap).execute().actionGet();
        return response.getId();
    }
    //
    public static Map<String, Object> search(String key, String index, String type, int start, int row){
        SearchRequestBuilder builder = getClient().prepareSearch(index);

        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .preTags("<font color='red'>")
                .postTags("</font>")
                .field("title")
                .field("describe");
        builder.highlighter(highlightBuilder)
                .setTypes(type)
                .setFrom(start)
                .setSize(row);
//        builder.setTypes(type);
//        builder.setFrom(start);
//        builder.setSize(row);
//        //设置高亮字段名称
//        //builder.highlighter(highlightBuilder)
//        builder.addHighlightedField("title");
//        builder.addHighlightedField("describe");
//        //设置高亮前缀
//        builder.setHighlighterPreTags("<font color='red' >");
//        //设置高亮后缀
//        builder.setHighlighterPostTags("</font>");
        builder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        if(StringUtils.isNotBlank(key)){
//			builder.setQuery(QueryBuilders.termQuery("title",key));
            builder.setQuery(QueryBuilders.multiMatchQuery(key, "title","describe"));
        }
        builder.setExplain(true);
        SearchResponse searchResponse = builder.get();

        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits();
        Map<String, Object> map = new HashMap<String,Object>();
        SearchHit[] hits2 = hits.getHits();
        map.put("count", total);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit searchHit : hits2) {
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            HighlightField highlightField = highlightFields.get("title");
            Map<String, Object> source = searchHit.getSource();
            if(highlightField!=null){
                Text[] fragments = highlightField.fragments();
                String name = "";
                for (Text text : fragments) {
                    name+=text;
                }
                source.put("title", name);
            }
            HighlightField highlightField2 = highlightFields.get("describe");
            if(highlightField2!=null){
                Text[] fragments = highlightField2.fragments();
                String describe = "";
                for (Text text : fragments) {
                    describe+=text;
                }
                source.put("describe", describe);
            }
            list.add(source);
        }
        map.put("dataList", list);
        return map;
    }


    public static void main(String[] args) throws Exception {
        Spark.get("/", (request, response) -> {
            SearchResponse searchResponse =
                    ESUtil.getClient().prepareSearch("music").setTypes("lyrics").execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("songs", hits);

            return new ModelAndView(attributes, "index.mustache");
        }, new MustacheTemplateEngine());
        Spark.get(
                "/search",
                (request, response) -> {
                    SearchRequestBuilder srb = client.prepareSearch("music")
                            .setTypes("songs");

                    HighlightBuilder hlb1 = new HighlightBuilder()
                            .preTags("<font color='red'>")
                            .postTags("</font>")
                            .field("lyrics")
                            .field("artist");
                    srb.highlighter(hlb1);

                    String lyricParam = request.queryParams("query");
                    QueryBuilder lyricQuery = null;
                    if (lyricParam != null && lyricParam.trim().length() > 0) {
                        lyricQuery = QueryBuilders.matchQuery("lyrics",lyricParam);
                    }
                    String artistParam = request.queryParams("artist");
                    QueryBuilder artistQuery = null;
                    if (artistParam != null && artistParam.trim().length() > 0) {
                        artistQuery = QueryBuilders.matchQuery("artist",artistParam);
                    }

                    if (lyricQuery != null && artistQuery == null) {
                        srb.setQuery(lyricQuery); //.addHighlightedField("lyrics", 0, 0);
                    } else if (lyricQuery == null && artistQuery != null) {
                        srb.setQuery(artistQuery);
                    } else if (lyricQuery != null && artistQuery != null) {
                        srb.setQuery(QueryBuilders.boolQuery().must(artistQuery).must(lyricQuery));
                        //srb.setQuery(artistQuery).setQuery(lyricQuery);
                        //srb.setQuery(QueryBuilders.multiMatchQuery(key, "title","describe"));
                        //srb.setQuery( QueryBuilders.andQuery(artistQuery, lyricQuery));//.addHighlightedField("lyrics", 0, 0);
                    }

                    SearchResponse searchResponse = srb.execute().actionGet();

                    SearchHit[] hits = searchResponse.getHits().getHits();

                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("songs", hits);


                    System.out.println( Arrays.deepToString(hits));

                    return new ModelAndView(attributes, "index.mustache");
                }, new MustacheTemplateEngine());
        Spark.get("/add", (request, response) -> {
            return new ModelAndView(new HashMap(), "add.mustache");
        }, new MustacheTemplateEngine());
        Spark.post("/save", (request, response) -> {
            StringBuilder json = new StringBuilder("{");
            json.append("\"name\":\""+request.raw().getParameter("name")+"\",");
            json.append("\"artist\":\""+request.raw().getParameter("artist")+"\",");
            json.append("\"year\":"+request.raw().getParameter("year")+",");
            json.append("\"album\":\""+request.raw().getParameter("album")+"\",");
            json.append("\"lyrics\":\""+request.raw().getParameter("lyrics")+"\"}");

            IndexRequest indexRequest = new IndexRequest("music", "lyrics",
                    UUID.randomUUID().toString());
            indexRequest.source(json.toString());
            IndexResponse esResponse = client.index(indexRequest).actionGet();

            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "index.mustache");
        }, new MustacheTemplateEngine());
    }
    */

}
