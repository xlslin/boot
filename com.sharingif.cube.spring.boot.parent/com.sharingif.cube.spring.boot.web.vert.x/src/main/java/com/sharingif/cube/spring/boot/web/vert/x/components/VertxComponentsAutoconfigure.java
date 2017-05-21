package com.sharingif.cube.spring.boot.web.vert.x.components;

import com.sharingif.cube.communication.view.MultiViewResolver;
import com.sharingif.cube.communication.view.ViewResolver;
import com.sharingif.cube.core.exception.handler.MultiCubeExceptionHandler;
import com.sharingif.cube.core.handler.adapter.DefaultMappingHandlerAdapter;
import com.sharingif.cube.core.handler.adapter.HandlerAdapter;
import com.sharingif.cube.core.handler.adapter.HandlerMethodArgumentResolver;
import com.sharingif.cube.core.handler.adapter.MultiHandlerMethodAdapter;
import com.sharingif.cube.core.handler.bind.support.BindingInitializer;
import com.sharingif.cube.core.handler.mapping.HandlerMapping;
import com.sharingif.cube.core.handler.mapping.MultiHandlerMapping;
import com.sharingif.cube.core.handler.mapping.RequestMappingHandlerMapping;
import com.sharingif.cube.web.exception.handler.WebCubeExceptionHandler;
import com.sharingif.cube.web.handler.adapter.PathVariableMethodArgumentResolver;
import com.sharingif.cube.web.vert.x.exception.handler.VertXExceptionResolver;
import com.sharingif.cube.web.vert.x.handler.adapter.JsonHandlerMethodArgumentResolver;
import com.sharingif.cube.web.vert.x.request.VertXRequestInfoResolver;
import com.sharingif.cube.web.vert.x.view.VertXJsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * VertxComponentsAutoconfigure
 * 2017/5/20 下午11:47
 * @author Joly
 * @version v1.0
 * @since v1.0
 */
@Configuration
public class VertxComponentsAutoconfigure {

    @Bean("pathVariableMethodArgumentResolver")
    public PathVariableMethodArgumentResolver createPathVariableMethodArgumentResolver() {
        PathVariableMethodArgumentResolver pathVariableMethodArgumentResolver = new PathVariableMethodArgumentResolver();

        return pathVariableMethodArgumentResolver;
    }

    @Bean(name="jsonHandlerMethodArgumentResolver")
    public JsonHandlerMethodArgumentResolver createJsonHandlerMethodArgumentResolver() {
        JsonHandlerMethodArgumentResolver jsonHandlerMethodArgumentResolver = new JsonHandlerMethodArgumentResolver();

        return jsonHandlerMethodArgumentResolver;
    }

    @Bean("argumentResolvers")
    public List<HandlerMethodArgumentResolver> createArgumentResolvers(
            PathVariableMethodArgumentResolver pathVariableMethodArgumentResolver
            ,JsonHandlerMethodArgumentResolver jsonHandlerMethodArgumentResolver
            ) {
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<HandlerMethodArgumentResolver>();
        argumentResolvers.add(pathVariableMethodArgumentResolver);
        argumentResolvers.add(jsonHandlerMethodArgumentResolver);

        return argumentResolvers;
    }

    @Bean("vertXRequestInfoResolver")
    public VertXRequestInfoResolver createVertXRequestInfoResolver() {
        VertXRequestInfoResolver vertXRequestInfoResolver = new VertXRequestInfoResolver();

        return vertXRequestInfoResolver;
    }

    @Bean("requestMappingHandlerMapping")
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        handlerMapping.setUseSuffixPatternMatch(false);

        return handlerMapping;
    }

	@Bean("handlerMappings")
	@SuppressWarnings("rawtypes")
    public List<HandlerMapping> createHandlerMappings(
            RequestMappingHandlerMapping requestMappingHandlerMapping
            ) {
        List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();
        handlerMappings.add(requestMappingHandlerMapping);

        return handlerMappings;
    }

    @Bean("multiHandlerMapping")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MultiHandlerMapping createMultiHandlerMapping(List<HandlerMapping> handlerMappings) {
        MultiHandlerMapping multiHandlerMapping = new MultiHandlerMapping();
        multiHandlerMapping.setHandlerMappings(handlerMappings);

        return multiHandlerMapping;
    }

    @Bean("defaultMappingHandlerAdapter")
    public DefaultMappingHandlerAdapter createDefaultMappingHandlerAdapter(
            List<HandlerMethodArgumentResolver> argumentResolvers
            ,BindingInitializer bindingInitializer
            ) {
        DefaultMappingHandlerAdapter  defaultMappingHandlerAdapter = new DefaultMappingHandlerAdapter();
        defaultMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);
        defaultMappingHandlerAdapter.setBindingInitializer(bindingInitializer);

        return defaultMappingHandlerAdapter;
    }

	@Bean("handlerAdapters")
	@SuppressWarnings("rawtypes")
    public List<HandlerAdapter> createHandlerAdapters(
            DefaultMappingHandlerAdapter  defaultMappingHandlerAdapter
            ) {
        List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();
        handlerAdapters.add(defaultMappingHandlerAdapter);

        return handlerAdapters;
    }

    @Bean("multiHandlerMethodAdapter")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MultiHandlerMethodAdapter createHandlerAdapter(List<HandlerAdapter> handlerAdapters) {
        MultiHandlerMethodAdapter multiHandlerMethodAdapter = new MultiHandlerMethodAdapter();
        multiHandlerMethodAdapter.setHandlerAdapters(handlerAdapters);

        return multiHandlerMethodAdapter;
    }

    @Bean("webCubeExceptionHandlers")
    public List<WebCubeExceptionHandler> createWebCubeExceptionHandlers(
            WebCubeExceptionHandler webCubeExceptionHandler
            ) {
        List<WebCubeExceptionHandler> webCubeExceptionHandlers = new ArrayList<WebCubeExceptionHandler>(6);
        webCubeExceptionHandlers.add(webCubeExceptionHandler);

        return webCubeExceptionHandlers;
    }

    @Bean("vertxCubeExceptionHandler")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MultiCubeExceptionHandler createVertxCubeExceptionHandler(List<WebCubeExceptionHandler> webCubeExceptionHandlers) {

        MultiCubeExceptionHandler vertxCubeExceptionHandler = new MultiCubeExceptionHandler();
        vertxCubeExceptionHandler.setCubeExceptionHandlers(webCubeExceptionHandlers);

        return vertxCubeExceptionHandler;
    }

    @Bean("exceptionResolver")
    public VertXExceptionResolver createVertXExceptionResolver(MultiCubeExceptionHandler vertxCubeExceptionHandler) {
        VertXExceptionResolver exceptionResolver = new VertXExceptionResolver();
        exceptionResolver.setCubeExceptionHandler(vertxCubeExceptionHandler);

        return exceptionResolver;
    }

    @Bean("vertXJsonViewResolver")
    public VertXJsonViewResolver createVertXJsonViewResolver() {
        VertXJsonViewResolver vertXJsonViewResolver = new VertXJsonViewResolver();

        return vertXJsonViewResolver;
    }

    @SuppressWarnings("rawtypes")
	@Bean("viewResolvers")
    public List<ViewResolver> createViewResolvers(
            VertXJsonViewResolver vertXJsonViewResolver
            ) {
        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
        viewResolvers.add(vertXJsonViewResolver);

        return viewResolvers;
    }

    @Bean("multiViewResolver")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MultiViewResolver createmultiViewResolver(List<ViewResolver> viewResolvers) {
        MultiViewResolver multiViewResolver = new MultiViewResolver();
        multiViewResolver.setViewResolvers(viewResolvers);

        return multiViewResolver;
    }

}
