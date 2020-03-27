import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.jayway.restassured.path.json.JsonPath.from;

import static io.restassured.RestAssured.given;


public class Request {

    @Before
    public void setup(){
        RestAssured.baseURI = "http://localhost:3000/";
    }
    public static Integer  getUserIdFromRequest(){
        Response response =
                given()
                        .log().all()
                        .when().get("/users").prettyPeek()
                        .then().extract().response();

        Random random = new Random();
        int rastgeleSayi = random.nextInt(Integer.parseInt(response.jsonPath().getString("id.size")));
        int randomId = response.jsonPath().get("["+rastgeleSayi+"].id");
        System.out.println(randomId);
        return randomId;
    }

    public static Integer getRequest(){

        Response response =
                given()
                        .log().all()
                .when()
                        .get("/users/").prettyPeek()
                .then()
                        .statusCode(200).extract().response();
       // int userId = Integer.parseInt(response.jsonPath().getString("[2].id"));
        int userIdSize = Integer.parseInt(response.jsonPath().getString("id.size()"));
        //System.out.println(userId);
        return userIdSize;
    }

    public static void postRequest(int userId){

        Response response=
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"id\": "+userId+" ,\n" +
                                "    \"first_name\": \"Roger\",\n" +
                                "    \"last_name\": \"Bacon\",\n" +
                                "    \"email\": \"rogerbacon12@yahoo.com\"\n" +
                                "}")
                        .log().all()
                        .when()
                        .post("/users/").prettyPeek()
                        .then()
                        .statusCode(201).extract().response();
    }
   /* public void testJsonPath1()

    {

        String responseAsString=

                given()
                .when()

                        .put("users/")

                        .then()

                        .extract().asString();

        List<Integer>idler=from(responseAsString).get("id");

        Random random=new Random();
        int index=random.nextInt(idler.size());
        System.out.println(index);
        Response response=given().when().get("http://jsonplaceholder.typicode.com/photos/"+index+"");
        System.out.println(response.body().prettyPrint());



    }*/

   /* public static void puttRequest(int userIdd){

        String responseAsString=

                given()
                        .when()

                        .put("users/")

                        .then()

                        .extract().asString();

        List<Integer>idler=from(responseAsString).get("id");
        Random random=new Random();
        int index=random.nextInt(idler.size());
        Response response=
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"id\": "+userIdd+" ,\n" +
                                "    \"first_name\": \"Rogerrrrrrrraaaa\",\n" +
                                "    \"last_name\": \"Bacon\",\n" +
                                "    \"email\": \"rogerbacon12@yahoo.com\"\n" +
                                "}")
                        .log().all()
                        .when()
                        .put("/users/"+userIdd+"/"+index+"").prettyPeek()
                        .then()
                        .statusCode(200).extract().response();

    }*/


    public static void putRequest() {
        {
            int rasgeleIdSec=getUserIdFromRequest();
            Response response =
                    given()
                            .contentType("application/json")
                            .body("{\n" +
                                    "    \"id\": "+rasgeleIdSec+",\n" +
                                    "    \"first_name\": \"Değişim\",\n" +
                                    "    \"last_name\": \"Bacon\",\n" +
                                    "    \"email\": \"rogerbacon12@yahoo.com\"\n" +
                                    "}")
                            .log().all()
                            .when()
                            .put("/users/"+rasgeleIdSec+"/").prettyPeek()
                            .then()
                            .statusCode(200).extract().response();
        }
    }

    public static void deleteRequest()
    {
        int userId=getUserIdFromRequest();

        Response response =
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"id\": "+userId+",\n" +
                                "    \"first_name\": \"Değişim\",\n" +
                                "    \"last_name\": \"Bacon\",\n" +
                                "    \"email\": \"rogerbacon12@yahoo.com\"\n" +
                                "}")
                        .log().all()
                        .when()
                        .delete("/users/"+userId+"/").prettyPeek()
                        .then()
                        .statusCode(200).extract().response();
    }

    @Test
    public void getScenario() {
        getRequest();
    }
    @Test
    public void postScenario(){
        int userId = getRequest() + 4;
        postRequest(userId);
    }
    @Test
    public void putScenario()
    {
        putRequest();

    }
    @Test
    public void deleteScenario()
    {
        deleteRequest();
    }
    /*@Test
    public void puttScenario()
    {
        int userIdd=getRequest();
        puttRequest(userIdd);
    }*/
}

