package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbcp2.managed.BasicManagedDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import daoimp.sqlt;
import spit.Spittle;

@Configuration
@ComponentScan("spweb2.controller") 
public class Config {
	
	@Bean(name="sp1",initMethod="init",destroyMethod="destory")
	@Scope("singleton")
	@Primary
	public sqlt Sqlt(){
		return new sqlt(das());
	}
	@Bean
	public DataSource das(){
		DataSource das=null;
		Properties proper=new Properties();
		try {
			proper.load(new FileInputStream(
					new File("src/main/resources/Properties.properties")));
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
		//MainController conl=context.getBean(MainController.class);
		for(Spittle i : sq.getSpittle()){
			System.out.println(i.getId()+":"+i.getType()+":"+i.getUsername());
		}
//		if(conl==null){
//			System.out.println("null");
//		}
	}
}
