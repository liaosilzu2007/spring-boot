package com.lzumetal.springboot.elasticsearch.test;

import com.lzumetal.springboot.elasticsearch.EsBootstrap;
import com.lzumetal.springboot.elasticsearch.config.Constants;
import com.lzumetal.springboot.elasticsearch.entity.Account;
import com.lzumetal.springboot.elasticsearch.entity.AccountRequest;
import com.lzumetal.springboot.utils.BeanMapUtil;
import com.lzumetal.springboot.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.max.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.min.ParsedMin;
import org.elasticsearch.search.aggregations.metrics.percentiles.ParsedPercentiles;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentile;
import org.elasticsearch.search.aggregations.metrics.stats.ParsedStats;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.ParsedTopHits;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author liaosi
 * @date 2021-03-31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EsBootstrap.class})
@Slf4j
public class ClientAccountServiceTest {

    @Autowired
    private RestHighLevelClient highLevelClient;


    /**
     * 判断索引是否存在
     */
    @Test
    public void existsIndexTest() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest();
        getIndexRequest.indices(Constants.INDEX_NAME);
        boolean exists = highLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        log.info("判断索引是否存在|exists={}", exists);
    }


    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void createIndexTest() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(Constants.INDEX_NAME);
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 3) // 分片数
                .put("index.number_of_replicas", 2) // 副本数
        );
        CreateIndexResponse response = highLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        log.info("创建索引|Acknowledged={},ShardsAcknowledged={}", response.isAcknowledged(), response.isShardsAcknowledged());

    }


    /**
     * 删除索引
     *
     * @throws IOException
     */
    @Test
    public void deleteIndexTest() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
        deleteIndexRequest.indices(Constants.INDEX_NAME);
        AcknowledgedResponse delete = highLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        log.info("删除索引|Acknowledged={}", delete.isAcknowledged());
    }


    /**
     * 向索引中新增一个文档
     */
    @Test
    public void addDocTest() throws IOException {
        String str = "{\"account_number\":1,\"balance\":39225,\"firstname\":\"Amber\",\"lastname\":\"Duke\",\"age\":32,\"gender\":\"M\",\"address\":\"880 Holmes Lane\",\"employer\":\"Pyrami\",\"email\":\"amberduke@pyrami.com\",\"city\":\"Brogan\",\"state\":\"IL\"}\n";
        Account account = JsonUtils.fromJSON(str, Account.class);
        IndexRequest indexRequest = new IndexRequest()
                .index(Constants.INDEX_NAME)
                .type(Constants.TYPE)
                .id(String.valueOf(account.getAccount_number()))
                .source(BeanMapUtil.objectToStringMap(account));
        IndexResponse indexResponse = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        //响应信息
        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        long version = indexResponse.getVersion();
        String result = indexResponse.getResult().getLowercase();
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        log.info("新增文档|index={}, type={}, id={}, version={}, result={}, shardInfo={}",
                index, type, id, version, result, shardInfo);
    }


    /**
     * 根据index_name, type, id获取一个文档
     */
    @Test
    public void getDocTest() throws IOException {
        GetRequest getRequest = new GetRequest()
                .index(Constants.INDEX_NAME)
//                .index(Constants.INDEX_NAME + "*")
                .type(Constants.TYPE)
                .id("1");
        GetResponse response = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        log.info("获取文档|{}", response.getSource());
    }


    /**
     * 更新一个文档
     *
     * @throws IOException
     */
    @Test
    public void updateDocTest() throws IOException {
        String str = "{\"account_number\":1,\"balance\":38000,\"firstname\":\"Amber\",\"lastname\":\"Duke\",\"age\":32,\"gender\":\"M\",\"address\":\"880 Holmes Lane\",\"employer\":\"Pyrami\",\"email\":\"amberduke@pyrami.com\",\"city\":\"Brogan\",\"state\":\"IL\"}\n";
        Account account = JsonUtils.fromJSON(str, Account.class);
        UpdateRequest updateRequest = new UpdateRequest()
                .index(Constants.INDEX_NAME)
                .type(Constants.TYPE)
                .id("1")
                .doc(BeanMapUtil.objectToStringMap(account));
        UpdateResponse response = highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        log.info("更新文档|{}", response);
    }


    /**
     * 批量操作
     */
    @Test
    public void bulkTest() throws IOException {
        String str = "{\"account_number\":1,\"balance\":38000,\"firstname\":\"Amber\",\"lastname\":\"Duke\",\"age\":32,\"gender\":\"M\",\"address\":\"880 Holmes Lane\",\"employer\":\"Pyrami\",\"email\":\"amberduke@pyrami.com\",\"city\":\"Brogan\",\"state\":\"IL\"}\n";
        UpdateRequest updateRequest = new UpdateRequest()
                .index(Constants.INDEX_NAME)
                .type(Constants.TYPE)
                .id("1")
                .doc(BeanMapUtil.objectToStringMap(JsonUtils.fromJSON(str, Account.class)));

        String str2 = "{\"account_number\":6,\"balance\":5686,\"firstname\":\"Hattie\",\"lastname\":\"Bond\",\"age\":36,\"gender\":\"M\",\"address\":\"671 Bristol Street\",\"employer\":\"Netagy\",\"email\":\"hattiebond@netagy.com\",\"city\":\"Dante\",\"state\":\"TN\"}\n";
        Account account = JsonUtils.fromJSON(str2, Account.class);
        IndexRequest indexRequest = new IndexRequest()
                .index(Constants.INDEX_NAME)
                .type(Constants.TYPE)
                .id(String.valueOf(account.getAccount_number()))
                .source(BeanMapUtil.objectToStringMap(account));

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(updateRequest)
                .add(indexRequest);
        BulkResponse response = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        for (BulkItemResponse bulkItemResponse : response) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();
            if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                    || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                IndexResponse indexResponse = (IndexResponse) itemResponse;
                //TODO 新增成功的处理

            } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                //TODO 修改成功的处理

            } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                //TODO 删除成功的处理
            }
        }
    }


    /**
     * 搜索文档（Doc）
     */
    @Test
    public void searchTest() {
        AccountRequest request = new AccountRequest();
        request.setFrom(0);
        request.setSize(10);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        /* = 查询 */
        String gender = request.getGender();
        if (gender != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("gender", gender));
        }

        /* in 查询 */
        List<Integer> ages = request.getAges();
        if (!CollectionUtils.isEmpty(ages)) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("age", ages));
        }

        /* rang 查询 */
        Long startBalance = request.getStartBalance();
        if (startBalance != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("balance").gte(startBalance));
        }
        Long endBalance = request.getEndBalance();
        if (endBalance != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("balance").lte(endBalance));
        }

        String addressKey = request.getAddressKey();
        if (addressKey != null) {
            //使用match query进行搜索时，会对你输入的关键词进行分词。
            boolQueryBuilder.must(QueryBuilders.matchQuery("address", addressKey));
        }

        /* like 查询*/
        String preAddress = request.getPreAddress();
        if (preAddress != null) {
            boolQueryBuilder.must(QueryBuilders.prefixQuery("address", preAddress));
        }

        /* 或查询 */
        String nameKey = request.getNameKey();
        if (nameKey != null) {
            BoolQueryBuilder shouldQuery = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery("firstname", nameKey))
                    .should(QueryBuilders.termQuery("lastname", nameKey));
            boolQueryBuilder.must(shouldQuery);
        }

        /* ！= 查询 */
        String notState = request.getNotState();
        if (notState != null) {
            boolQueryBuilder.mustNot(QueryBuilders.termsQuery("state", notState));
        }

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        String[] fields = request.getFields();
//        if (fields != null) {
//            //指定需要查询的字段，以及不需要查询的字段
//            searchSourceBuilder.fetchSource(fields, null);
//        }
        searchSourceBuilder.query(boolQueryBuilder);

        //分页
        searchSourceBuilder.from(request.getFrom());
        searchSourceBuilder.size(request.getSize());

        //指定排序
        //searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        /* 排序的类型必须是 integer 类型 或者 date 类型，否则会报一个异常：Fielddata is disabled on text fields by default.... */
//        searchSourceBuilder.sort(new FieldSortBuilder("account_number").order(SortOrder.ASC));

        SearchRequest searchRequest = new SearchRequest()
                .indices(Constants.INDEX_NAME)
                .source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus restStatus = searchResponse.status();
            if (restStatus != RestStatus.OK) {
                log.error("查询结果异常|" + restStatus);
                return;
            }

            SearchHits searchHits = searchResponse.getHits();
            long totalHits = searchHits.totalHits;
            log.info("搜索结果|totalHits={}", totalHits);
            if (totalHits > 0) {
                for (SearchHit hit : searchHits.getHits()) {
                    String source = hit.getSourceAsString();
                    log.info("搜索结果|{}", source);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //=================================聚合查询================================
    //1.Metric 聚合分析

    /**
     * stats 统计员工总数、员工工资最高值、员工工资最低值、员工平均工资、员工工资总和
     */
    public Object aggregationStats() {
        String responseResult = "";
        try {
            // 设置聚合条件
            AggregationBuilder aggr = AggregationBuilders.stats("salary_stats").field("salary");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            // 设置查询结果不返回，只返回聚合结果
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status()) || aggregations != null) {
                // 转换为 Stats 对象
                ParsedStats aggregation = aggregations.get("salary_stats");
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                log.info("count：{}", aggregation.getCount());
                log.info("avg：{}", aggregation.getAvg());
                log.info("max：{}", aggregation.getMax());
                log.info("min：{}", aggregation.getMin());
                log.info("sum：{}", aggregation.getSum());
                log.info("-------------------------------------------");
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将返回响应对象Json串
            responseResult = response.toString();
        } catch (IOException e) {
            log.error("", e);
        }
        return responseResult;
    }

    /**
     * min 统计员工工资最低值
     */
    public Object aggregationMin() {
        String responseResult = "";
        try {
            // 设置聚合条件
            AggregationBuilder aggr = AggregationBuilders.min("salary_min").field("salary");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status()) || aggregations != null) {
                // 转换为 Min 对象
                ParsedMin aggregation = aggregations.get("salary_min");
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                log.info("min：{}", aggregation.getValue());
                log.info("-------------------------------------------");
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将返回响应对象Json串
            responseResult = response.toString();
        } catch (IOException e) {
            log.error("", e);
        }
        return responseResult;
    }

    /**
     * max 统计员工工资最高值
     */
    public Object aggregationMax() {
        String responseResult = "";
        try {
            // 设置聚合条件
            AggregationBuilder aggr = AggregationBuilders.max("salary_max").field("salary");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status()) || aggregations != null) {
                // 转换为 Max 对象
                ParsedMax aggregation = aggregations.get("salary_max");
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                log.info("max：{}", aggregation.getValue());
                log.info("-------------------------------------------");
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将返回响应对象Json串
            responseResult = response.toString();
        } catch (IOException e) {
            log.error("", e);
        }
        return responseResult;
    }

    /**
     * avg 统计员工工资平均值
     */
    public Object aggregationAvg() {
        String responseResult = "";
        try {
            // 设置聚合条件
            AggregationBuilder aggr = AggregationBuilders.avg("salary_avg").field("salary");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status()) || aggregations != null) {
                // 转换为 Avg 对象
                ParsedAvg aggregation = aggregations.get("salary_avg");
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                log.info("avg：{}", aggregation.getValue());
                log.info("-------------------------------------------");
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将返回响应对象Json串
            responseResult = response.toString();
        } catch (IOException e) {
            log.error("", e);
        }
        return responseResult;
    }

    /**
     * sum 统计员工工资总值
     */
    public Object aggregationSum() {
        String responseResult = "";
        try {
            // 设置聚合条件
            SumAggregationBuilder aggr = AggregationBuilders.sum("salary_sum").field("salary");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status()) || aggregations != null) {
                // 转换为 Sum 对象
                ParsedSum aggregation = aggregations.get("salary_sum");
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                log.info("sum：{}", String.valueOf((aggregation.getValue())));
                log.info("-------------------------------------------");
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将返回响应对象Json串
            responseResult = response.toString();
        } catch (IOException e) {
            log.error("", e);
        }
        return responseResult;
    }

    /**
     * count 统计员工总数
     */
    public Object aggregationCount() {
        String responseResult = "";
        try {
            // 设置聚合条件
            AggregationBuilder aggr = AggregationBuilders.count("employee_count").field("salary");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status()) || aggregations != null) {
                // 转换为 ValueCount 对象
                ParsedValueCount aggregation = aggregations.get("employee_count");
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                log.info("count：{}", aggregation.getValue());
                log.info("-------------------------------------------");
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将返回响应对象Json串
            responseResult = response.toString();
        } catch (IOException e) {
            log.error("", e);
        }
        return responseResult;
    }

    /**
     * percentiles 统计员工工资百分位
     */
    public Object aggregationPercentiles() {
        String responseResult = "";
        try {
            // 设置聚合条件
            AggregationBuilder aggr = AggregationBuilders.percentiles("salary_percentiles").field("salary");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status()) || aggregations != null) {
                // 转换为 Percentiles 对象
                ParsedPercentiles aggregation = aggregations.get("salary_percentiles");
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Percentile percentile : aggregation) {
                    log.info("百分位：{}：{}", percentile.getPercent(), percentile.getValue());
                }
                log.info("-------------------------------------------");
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将返回响应对象Json串
            responseResult = response.toString();
        } catch (IOException e) {
            log.error("", e);
        }
        return responseResult;
    }


    //2. bulket 聚合分析

    /**
     * 按岁数进行聚合分桶
     */
    public Object aggrBucketTerms() {
        try {
            AggregationBuilder aggr = AggregationBuilders.terms("age_bucket").field("age");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(10);
            searchSourceBuilder.aggregation(aggr);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status())) {
                // 分桶
                Terms byCompanyAggregation = aggregations.get("age_bucket");
                List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
                // 输出各个桶的内容
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Terms.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
                log.info("-------------------------------------------");
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 按工资范围进行聚合分桶
     */
    public Object aggrBucketRange() {
        try {
            AggregationBuilder aggr = AggregationBuilders.range("salary_range_bucket")
                    .field("salary")
                    .addUnboundedTo("低级员工", 3000)
                    .addRange("中级员工", 5000, 9000)
                    .addUnboundedFrom("高级员工", 9000);
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status())) {
                // 分桶
                Range byCompanyAggregation = aggregations.get("salary_range_bucket");
                List<? extends Range.Bucket> buckets = byCompanyAggregation.getBuckets();
                // 输出各个桶的内容
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Range.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
                log.info("-------------------------------------------");
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 按照时间范围进行分桶
     */
    public Object aggrBucketDateRange() {
        try {
            AggregationBuilder aggr = AggregationBuilders.dateRange("date_range_bucket")
                    .field("birthDate")
                    .format("yyyy")
                    .addRange("1985-1990", "1985", "1990")
                    .addRange("1990-1995", "1990", "1995");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status())) {
                // 分桶
                Range byCompanyAggregation = aggregations.get("date_range_bucket");
                List<? extends Range.Bucket> buckets = byCompanyAggregation.getBuckets();
                // 输出各个桶的内容
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Range.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
                log.info("-------------------------------------------");
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 按工资多少进行聚合分桶
     */
    public Object aggrBucketHistogram() {
        try {
            AggregationBuilder aggr = AggregationBuilders.histogram("salary_histogram")
                    .field("salary")
                    .extendedBounds(0, 12000)
                    .interval(3000);
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status())) {
                // 分桶
                Histogram byCompanyAggregation = aggregations.get("salary_histogram");
                List<? extends Histogram.Bucket> buckets = byCompanyAggregation.getBuckets();
                // 输出各个桶的内容
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Histogram.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
                log.info("-------------------------------------------");
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 按出生日期进行分桶
     */
    public Object aggrBucketDateHistogram() {
        try {
            AggregationBuilder aggr = AggregationBuilders.dateHistogram("birthday_histogram")
                    .field("birthDate")
                    .interval(1)
                    .dateHistogramInterval(DateHistogramInterval.YEAR)
                    .format("yyyy");
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status())) {
                // 分桶
                Histogram byCompanyAggregation = aggregations.get("birthday_histogram");

                List<? extends Histogram.Bucket> buckets = byCompanyAggregation.getBuckets();
                // 输出各个桶的内容
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Histogram.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
                log.info("-------------------------------------------");
            }
        } catch (IOException e) {
            log.error("", e);
        }

        return null;
    }


    //Metric 与 Bucket 聚合分析

    /**
     * topHits 按岁数分桶、然后统计每个员工工资最高值
     */
    public Object aggregationTopHits() {
        try {
            AggregationBuilder testTop = AggregationBuilders.topHits("salary_max_user")
                    .size(1)
                    .sort("salary", SortOrder.DESC);
            AggregationBuilder salaryBucket = AggregationBuilders.terms("salary_bucket")
                    .field("age")
                    .size(10);
            salaryBucket.subAggregation(testTop);
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(salaryBucket);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest("mydlq-user");
            request.source(searchSourceBuilder);
            // 执行请求
            SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
            // 获取响应中的聚合信息
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status())) {
                // 分桶
                Terms byCompanyAggregation = aggregations.get("salary_bucket");
                List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
                // 输出各个桶的内容
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Terms.Bucket bucket : buckets) {
                    log.info("桶名：{}", bucket.getKeyAsString());
                    ParsedTopHits topHits = bucket.getAggregations().get("salary_max_user");
                    for (SearchHit hit:topHits.getHits()){
                        log.info(hit.getSourceAsString());
                    }
                }
                log.info("-------------------------------------------");
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

}