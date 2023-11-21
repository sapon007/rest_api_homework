package reqres.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static reqres.helpers.CustomApiListener.withCustomTemplates;

public class RestApiSpecs {

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            //.filter(withCustomTemplates())
            .addFilter(withCustomTemplates())
            .log(LogDetail.URI)
            .log(LogDetail.METHOD)
            .log(LogDetail.BODY)
            .build()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification response404Spec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(404)
            .build();

    public static ResponseSpecification response204Spec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(204)
            .build();
}
