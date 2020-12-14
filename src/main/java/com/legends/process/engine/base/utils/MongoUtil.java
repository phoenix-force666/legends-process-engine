package com.legends.process.engine.base.utils;

import com.legends.cloud.common.utils.StringUtil;
import com.legends.process.engine.base.constant.HttpStatus;
import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @param <T>
 */
@Data
@Component
public class MongoUtil<T> {
    public Integer pageSize;
    private Integer currentPage;


    public void start(PageDomain pageDomain, Query query) {
        this.pageSize = pageDomain.getPageSize()==0 ? 10:this.pageSize;
        this.currentPage = pageDomain.getPageNum();
        query.limit(this.pageSize);
        query.skip((currentPage - 1) * pageSize);
        if(StringUtil.isNotNullOrEmpty(pageDomain.getOrderByColumn())){
            query.with(Sort.by(Sort.Direction.fromString(pageDomain.getIsAsc()),pageDomain.getOrderByColumn()));
        }
    }


    public TableDataInfo resp(List list,long count){
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        if(Objects.isNull(list)){
            rspData.setTotal(0);
        }else{
            rspData.setTotal(count);
        }
        return rspData;
    }


    /**
     * 用于模糊查询忽略大小写
     *
     * @param string
     * @return
     */
    public Pattern getPattern(String string) {
        Pattern pattern = Pattern.compile("^.*" + string + ".*$", Pattern.CASE_INSENSITIVE);
        return pattern;
    }
}
