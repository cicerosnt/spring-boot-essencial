package br.cicerosnt.project3.adapter;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

public class Project2ConfigureAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
        //phmar.setFallbackPageable(new PageRequest(0, 5));
        argumentResolvers.add(phmar);
    }
}
