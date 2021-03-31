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
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
     * 搜索
     *
     * @param request
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
}
