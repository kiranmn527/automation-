package com.cucumber.testng.utilities.misc_utils;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonToPojo {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String packageName="com.sample";
        File inputJson= new File("src\\main\\java\\com\\cucumber\\testng\\utilities\\misc_utils\\"+File.separator+"buildingsPost.json");

        File outputPojoDirectory=new File("src\\main\\java\\"+File.separator);
        outputPojoDirectory.mkdirs();
        try {
            new JsonToPojo().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Encountered issue while converting to pojo: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{
        JCodeModel codeModel = new JCodeModel();

        URL source = inputJson;

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return true;
            }
            public SourceType getSourceType(){
                return SourceType.JSON;
            }
        };
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(codeModel, className, packageName, source);

        codeModel.build(outputPojoDirectory);
    }

}