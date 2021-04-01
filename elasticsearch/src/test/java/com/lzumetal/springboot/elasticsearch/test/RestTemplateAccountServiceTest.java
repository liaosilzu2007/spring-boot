package com.lzumetal.springboot.elasticsearch.test;

import com.lzumetal.springboot.elasticsearch.EsBootstrap;
import com.lzumetal.springboot.elasticsearch.config.Constants;
import com.lzumetal.springboot.elasticsearch.entity.Account;
import com.lzumetal.springboot.elasticsearch.entity.AccountRequest;
import com.lzumetal.springboot.utils.BeanMapUtil;
import com.lzumetal.springboot.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaosi
 * @date 2021-03-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EsBootstrap.class})
@Slf4j
public class RestTemplateAccountServiceTest {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 判断索引是否存在
     */
    @Test
    public void existsIndexTest() throws IOException {
        boolean exists = elasticsearchRestTemplate.indexExists(Constants.INDEX_NAME);
        log.info("判断索引是否存在|exists={}", exists);
    }


    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void createIndexTest() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("index.number_of_shards", 3); // 分片数
        map.put("index.number_of_replicas", 2); //副本数
        boolean create = elasticsearchRestTemplate.createIndex(Constants.INDEX_NAME, map);
        log.info("创建索引|create={}", create);

    }


    /**
     * 删除索引
     *
     * @throws IOException
     */
    @Test
    public void deleteIndexTest() throws IOException {
        boolean delete = elasticsearchRestTemplate.deleteIndex(Constants.INDEX_NAME);
        log.info("删除索引|delete={}", delete);
    }


    /**
     * 向索引中新增一个文档
     */
    @Test
    public void addDocTest() throws IOException {
        String str = "{\"account_number\":1,\"balance\":39225,\"firstname\":\"Amber\",\"lastname\":\"Duke\",\"age\":32,\"gender\":\"M\",\"address\":\"880 Holmes Lane\",\"employer\":\"Pyrami\",\"email\":\"amberduke@pyrami.com\",\"city\":\"Brogan\",\"state\":\"IL\"}\n";
        Account account = JsonUtils.fromJSON(str, Account.class);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(Constants.INDEX_NAME)
                .withType(Constants.TYPE)
                .withId(String.valueOf(account.getAccount_number()))
                .withObject(account)
                .build();
        String index = elasticsearchRestTemplate.index(indexQuery);
        //响应信息
        log.info("新增文档|index={}", index);
    }


    /**
     * 根据index_name, type, id获取一个文档
     */
    @Test
    public void getDocTest() throws IOException {
        GetQuery getQuery = GetQuery.getById(String.valueOf(1));
        Account account = elasticsearchRestTemplate.queryForObject(getQuery, Account.class);
        log.info("获取文档|{}", account);
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
        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName(Constants.INDEX_NAME)
                .withType(Constants.TYPE)
                .withId("1")
                .withUpdateRequest(updateRequest)
                .build();
        UpdateResponse response = elasticsearchRestTemplate.update(updateQuery);
        log.info("更新文档|{}", response);
    }


    /**
     * 批量操作
     */
    @Test
    public void bulkTest() throws IOException {
        List<Account> accounts = new ArrayList<>();
        String str = "{\"account_number\":1,\"balance\":38700,\"firstname\":\"Amber\",\"lastname\":\"Duke\",\"age\":32,\"gender\":\"M\",\"address\":\"880 Holmes Lane\",\"employer\":\"Pyrami\",\"email\":\"amberduke@pyrami.com\",\"city\":\"Brogan\",\"state\":\"IL\"}\n";
        Account account1 = JsonUtils.fromJSON(str, Account.class);
        accounts.add(account1);

        String str2 = "{\"account_number\":6,\"balance\":5686,\"firstname\":\"Hattie\",\"lastname\":\"Bond\",\"age\":36,\"gender\":\"M\",\"address\":\"671 Bristol Street\",\"employer\":\"Netagy\",\"email\":\"hattiebond@netagy.com\",\"city\":\"Dante\",\"state\":\"TN\"}\n";
        Account account2 = JsonUtils.fromJSON(str2, Account.class);
        accounts.add(account2);

        List<IndexQuery> indexQueryList = new ArrayList<>();
        for (Account account : accounts) {
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withIndexName(Constants.INDEX_NAME)
                    .withType(Constants.TYPE)
                    .withId(String.valueOf(account.getAccount_number()))
                    .withObject(account)
                    .build();
            indexQueryList.add(indexQuery);
        }
        elasticsearchRestTemplate.bulkIndex(indexQueryList);
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

        Integer offset = request.getFrom();
        Integer limit = request.getSize();
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices(Constants.INDEX_NAME)
                .withQuery(boolQueryBuilder)
                .withPageable(PageRequest.of(offset / limit, limit))
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC))
                .build();

        AggregatedPage<Account> page = elasticsearchRestTemplate.queryForPage(searchQuery, Account.class);
        log.info("搜索结果|TotalElements={}|data={}", page.getTotalElements(), page.getContent());

    }
}
