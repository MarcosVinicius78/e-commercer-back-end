// package com.ecommerce.config;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Set;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
// import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
// import org.springframework.http.HttpMethod;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;

// import com.ecommerce.entity.Product;
// import com.ecommerce.entity.ProductCategory;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.metamodel.EntityType;

// @Configuration
// public class MyDataRestConfig implements RepositoryRestConfigurer {

//     private EntityManager entityManager;

//     public MyDataRestConfig(EntityManager entityManager){
//         this.entityManager = entityManager;
//     }

//     @Override
//     public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

//         HttpMethod[] theUnsupportedActions = { HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST };

//         config.getExposureConfiguration()
//                 .forDomainType(Product.class)
//                 .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
//                 .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

//         config.getExposureConfiguration()
//                 .forDomainType(ProductCategory.class)
//                 .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
//                 .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

//         cors.addMapping("/api/**").allowedOrigins("http://localhost:4200");

//         exposeIds(config);
//     }


//     private void exposeIds(RepositoryRestConfiguration config) {
//         Set<EntityType<?>> entities = this.entityManager.getMetamodel().getEntities();

//         List<Class> entityClasses = new ArrayList<>();

//         for (EntityType entity : entities) {
//             entityClasses.add(entity.getJavaType());
//         }
        
//         Class[] domainClasses = entityClasses.toArray(new Class[0]);
//         config.exposeIdsFor(domainClasses);
//     }

// }
