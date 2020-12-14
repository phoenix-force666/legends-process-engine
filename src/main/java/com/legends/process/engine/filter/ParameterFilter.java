package com.legends.process.engine.filter;

import com.alibaba.fastjson.JSON;
import com.legends.cloud.common.base.ComResp;
import com.legends.cloud.common.enums.CommonEnumCode;
import com.legends.cloud.common.utils.StringUtil;
import com.legends.config.ParameterServletRequestWrapper;
import com.legends.config.ParameterServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class ParameterFilter extends  OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        PrintWriter out=null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ParameterServletResponseWrapper responseWrapper = new ParameterServletResponseWrapper(response);
            String accept= request.getHeader("accept");

            boolean falg=(StringUtil.isNotNullOrEmpty(accept) && accept.contains(MediaType.APPLICATION_JSON_VALUE));
            //修改请求参数

            if((request.getMethod().equals(RequestMethod.POST.name())) && falg){
                ServletRequest  requestWrapper = new ParameterServletRequestWrapper(request);
                filterChain.doFilter(requestWrapper, responseWrapper);
                //只修改响应参数
            }else{
                filterChain.doFilter(request, responseWrapper);
            }
            ComResp comResp=new ComResp.Builder().data(JSON.parse(new String(responseWrapper.getResponseData()))).success().build();
            String resp=JSON.toJSONString(comResp);
            log.info("resp:{}",resp);
            out=response.getWriter();
            if(response.getStatus()==204){
                response.setStatus(200);
            }
            response.setContentLength(resp.getBytes().length);
            out.print(resp);

        }catch (Exception e){
            if(Objects.isNull(out)){
                try {
                    out=response.getWriter();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            ComResp comResp=new ComResp.Builder().code(CommonEnumCode.FAIL.getCode()).msg(e.getMessage()).build();
            String resp=JSON.toJSONString(comResp);
            response.setContentLength(resp.getBytes().length);
            out.print(resp);
        }finally {
            if(null!=out){
                out.flush();
                out.close();
            }
        }
    }

}
