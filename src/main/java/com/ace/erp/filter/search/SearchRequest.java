/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.filter.search;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ace.erp.entity.sys.Searchable;
import com.ace.erp.exception.SearchException;
import com.ace.erp.utils.SearchOperator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Project_Name: smart-erp
 * File: SearchRequest
 * User: denghp
 * Date: 10/16/13
 * Time: 3:03 PM
 */
public final class SearchRequest extends Searchable {


    private final Map<String, SearchFilter> searchFilterMap = Maps.newHashMap();
    /**
     * 使用这个的目的是保证拼sql的顺序是按照添加时的顺序
     */
    private final List<SearchFilter> searchFilters = Lists.newArrayList();

    private Pageable page;

    private Sort sort;

    private boolean converted;

    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>)
     */
    public SearchRequest(final Map<String, Object> searchParams) {
        this(searchParams, null, null);
    }

    public SearchRequest() {
        this(null, null, null);
    }

    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>)
     */
    public SearchRequest(final Map<String, Object> searchParams, final Pageable page) {
        this(searchParams, page, null);
    }

    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>)
     */
    public SearchRequest(final Map<String, Object> searchParams, final Sort sort) throws SearchException {
        this(searchParams, null, sort);
    }


    /**
     * <p>根据查询参数拼Search<br/>
     * 查询参数格式：property_op=value 或 customerProperty=value<br/>
     * customerProperty查找规则是：1、先查找domain的属性，2、如果找不到查找domain上的SearchPropertyMappings映射规则
     * 属性、操作符之间用_分割，op可省略/或custom，省略后值默认为custom，即程序中自定义<br/>
     * 如果op=custom，property也可以自定义（即可以与domain的不一样）,
     * </p>
     *
     * @param searchParams 查询参数组
     * @param page         分页
     * @param sort         排序
     */
    public SearchRequest(final Map<String, Object> searchParams, final Pageable page, final Sort sort)
            throws SearchException {

        toSearchFilters(searchParams);

        //merge(sort, page);
    }


    private void toSearchFilters(final Map<String, Object> searchParams) throws SearchException {
        if (searchParams == null || searchParams.size() == 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            addSearchFilter(SearchFilterHelper.newCondition(key, value));
        }
    }


    @Override
    public Searchable addSearchParam(final String key, final Object value) throws SearchException {
        addSearchFilter(SearchFilterHelper.newCondition(key, value));
        return this;
    }

    @Override
    public Searchable addSearchParams(Map<String, Object> searchParams) throws SearchException {
        toSearchFilters(searchParams);
        return this;
    }


//    @Override
//    public Searchable addSearchFilter(final String searchProperty, final SearchOperator operator, final Object value) {
//        SearchFilter searchFilter = SearchFilterHelper.newCondition(searchProperty, operator, value);
//        return addSearchFilter(searchFilter);
//    }

    /**
    @Override
    public Searchable addSearchFilter(SearchFilter searchFilter) {
        if (searchFilter == null) {
            return this;
        }
        if (searchFilter instanceof Condition) {
            Condition condition = (Condition) searchFilter;
            String key = condition.getKey();
            searchFilterMap.put(key, condition);
        }
        int index = searchFilters.indexOf(searchFilter);
        if(index != -1) {
            searchFilters.set(index, searchFilter);
        } else {
            searchFilters.add(searchFilter);
        }
        return this;

    }
    **/


    @Override
    public Searchable addSearchFilters(Collection<? extends SearchFilter> searchFilters) {
        if (CollectionUtils.isEmpty(searchFilters)) {
            return this;
        }
        for (SearchFilter searchFilter : searchFilters) {
            addSearchFilter(searchFilter);
        }
        return this;
    }

    @Override
    public Searchable or(final SearchFilter first, final SearchFilter... others) {
        //addSearchFilter(SearchFilterHelper.or(first, others));
        return this;
    }

    @Override
    public Searchable and(final SearchFilter first, final SearchFilter... others) {

        //addSearchFilter(SearchFilterHelper.and(first, others));
        return this;
    }



//    @Override
//    public Searchable removeSearchFilter(final String searchProperty, final SearchOperator operator) {
//        this.removeSearchFilter(searchProperty + Condition.separator + operator);
//        return this;
//    }
    /**
     * @param key
     * @return
     */
    /**
    @Override
    public Searchable removeSearchFilter(final String key) {
        if (key == null) {
            return this;
        }

        SearchFilter searchFilter = searchFilterMap.remove(key);

        if (searchFilter == null) {
            searchFilter = searchFilterMap.remove(getCustomKey(key));
        }

        if (searchFilter == null) {
            return this;
        }

        searchFilters.remove(searchFilter);

        return this;
    }
    **/

   /* private String getCustomKey(String key) {
        return key + Condition.separator + SearchOperator.custom;
    }

    @Override
    public Searchable setPage(final Pageable page) {
        merge(sort, page);
        return this;
    }

    @Override
    public Searchable addSort(final Sort sort) {
        merge(sort, page);
        return this;
    }
    @Override
    public Searchable setPage(int pageNumber, int pageSize) {
        merge(sort, new PageRequest(pageNumber, pageSize));
        return this;
    }

    @Override
    public Searchable addSort(final Sort.Direction direction, final String property) {
        merge(new Sort(direction, property), page);
        return this;
    }
    **/


//    @Override
//    public <T> Searchable convert(final Class<T> entityClass) {
//        SearchableConvertUtils.convertSearchValueToEntityValue(this, entityClass);
//        markConverted();
//        return this;
//    }


    @Override
    public Searchable markConverted() {
        this.converted = true;
        return this;
    }


//    @Override
//    public Collection<SearchFilter> getSearchFilters() {
//        return Collections.unmodifiableCollection(searchFilters);
//    }

    @Override
    public boolean isConverted() {
        return converted;
    }

//    @Override
//    public boolean hasSearchFilter() {
//        return searchFilters.size() > 0;
//    }

    /**
    @Override
    public boolean hashSort() {
        return this.sort != null && this.sort.iterator().hasNext();
    }

    @Override
    public boolean hasPageable() {
        return this.page != null && this.page.getPageSize() > 0;
    }

    @Override
    public void removeSort() {
        this.sort = null;
        if (this.page != null) {
            this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), null);
        }
    }
    **/

    @Override
    public void removePageable() {
        this.page = null;
    }
   /**
    public Pageable getPage() {
        return page;
    }
    **/

    public Sort getSort() {
        return sort;
    }

//    @Override
//    public boolean containsSearchKey(String key) {
//        return
//                searchFilterMap.containsKey(key) ||
//                        searchFilterMap.containsKey(getCustomKey(key));
//    }

//    @Override
//    public Object getValue(String key) {
//        SearchFilter searchFilter = searchFilterMap.get(key);
//        if (searchFilter == null) {
//            searchFilter = searchFilterMap.get(getCustomKey(key));
//        }
//        if (searchFilter == null) {
//            return null;
//        }
//
//        if (searchFilter instanceof Condition) {
//            Condition condition = (Condition) searchFilter;
//            return condition.getValue();
//        }
//
//        return null;
//    }

       /**
    private void merge(Sort sort, Pageable page) {
        if (sort == null) {
            sort = this.sort;
        }
        if (page == null) {
            page = this.page;
        }

        //合并排序
        if (sort == null) {
            this.sort = page != null ? page.getSort() : null;
        } else {
            this.sort = (page != null ? sort.and(page.getSort()) : sort);
        }
        //把排序合并到page中
        if (page != null) {
            this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), this.sort);
        } else {
            this.page = null;
        }
    }
    **/


    @Override
    public Searchable addSearchFilter(String searchProperty, SearchOperator operator, Object value) throws SearchException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Searchable addSearchFilter(SearchFilter searchFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Searchable removeSearchFilter(String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Searchable removeSearchFilter(String searchProperty, SearchOperator operator) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<SearchFilter> getSearchFilters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasSearchFilter() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean containsSearchKey(String key) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> T getValue(String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Searchable setPage(java.awt.print.Pageable page) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Searchable setPage(int pageNumber, int pageSize) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Searchable addSort(Sort sort) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hashSort() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeSort() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasPageable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public java.awt.print.Pageable getPage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "searchFilterMap=" + searchFilterMap +
                ", page=" + page +
                ", sort=" + sort +
                '}';
    }
}
