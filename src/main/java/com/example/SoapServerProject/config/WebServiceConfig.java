package com.example.SoapServerProject.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.ArrayList;
import java.util.List;

@EnableWs
@Configuration
@EnableAsync
public class WebServiceConfig extends WsConfigurerAdapter {

//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setPackagesToScan("com.example.SoapServerProject.web");
//        marshaller.setMtomEnabled(true);
//        return marshaller;
//    }
//
//    @Bean
//    @Override
//    public DefaultMethodEndpointAdapter defaultMethodEndpointAdapter() {
//        List<MethodArgumentResolver> argumentResolvers = new ArrayList<>();
//        argumentResolvers.add(methodProcessor());
//        List<MethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
//        returnValueHandlers.add(methodProcessor());
//        DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
//        adapter.setMethodArgumentResolvers(argumentResolvers);
//        adapter.setMethodReturnValueHandlers(returnValueHandlers);
//        return adapter;
//    }
//
//    @Bean
//    public MarshallingPayloadMethodProcessor methodProcessor() {
//        return new MarshallingPayloadMethodProcessor(marshaller());
//    }

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(500);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soap/*");
    }

    @Bean(name = "hotel")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema hotelsReservationSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("HotelsPort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace("http://localhost:8080");
        wsdl11Definition.setSchema(hotelsReservationSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema hotelsReservationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("hotel.xsd"));
    }
}
