import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public abstract class AbstractApiCRUD<T>  {
    private String baseUri;
    private String basePath;

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    private ValidatableResponse getResponse(){
        return given()
                .baseUri(baseUri)
                .basePath(basePath)
                .when().get()
                .then();
    }

    private ValidatableResponse getResponse(String param, Object value){
        return given()
                .baseUri(baseUri)
                .basePath(basePath)
                .param(param, value)
                .when().get()
                .then();
    }

    private Object getValue(BaseJsonPaths path){
        return getResponse()
                .statusCode(200)
                .extract().response().jsonPath().get(String.valueOf(path));
    }

    public Integer getTotalPages(){
        return (Integer) getValue(BaseJsonPaths.total_pages);
    }
    
    public Integer getTotal(){
        return (Integer) getValue(BaseJsonPaths.total);

    }

    protected List<T> getAllFromPage(Class<T> genericType, Integer page){
        return getResponse("page", page)
                .statusCode(200)
                .extract().response().jsonPath().getList("data", genericType);

    }

    protected List<T> getAll(Class<T> genericType){
        List<T> users = new ArrayList();
        IntStream.range(1, getTotalPages()+1)
                .forEach(p ->
                    users.addAll(getAllFromPage(genericType, p)));
        return users;
    }

    protected T getById(Class<T> genericType,Integer id){
        return getResponse("id", id)
                .statusCode(200)
                .extract().response().jsonPath().getObject("data", genericType);
    }
}

