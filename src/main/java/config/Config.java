package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import daoimp.sqlt;
import spit.Spittle;



@Configuration
@ComponentScan(basePackages="spweb2.controller") 
public class Config {
	
	@Bean(name="sp1",initMethod="init",destroyMethod="destory")
	@Scope("singleton")
	@Primary
	public sqlt Sqlt(){
		return new sqlt(das());
	}
	@Bean("dataSource")
	public DataSource das(){
		DataSource das=null;
		Properties proper=new Properties();
		try {
			File f=new File("ds.txt");
			
			System.out.println("path:"+f.getAbsolutePath());
			proper.load(new ClassPathResource("Properties.properties").getInputStream());
			das=BasicDataSourceFactory.createDataSource(proper);
		} catch (FileNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return das;
	}
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext("application.xml");
		AnnotationConfigApplicationContext Annocontext=
				new AnnotationConfigApplicationContext(Config.class);
		sqlt sq=context.getBean(sqlt.class);
		//sq.signUp("testuseer1", "password2");
		//MainController conl=context.getBean(MainController.class);
//		try {
//			sq.descom();
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
		for(Spittle i : sq.getSpittle()){
			System.out.println(i.getId()+":"+i.getType()+":"+i.getUsername());
		}
		//sq.commit();
//		if(conl==null){
//			System.out.println("null");
//		}
	}
}
