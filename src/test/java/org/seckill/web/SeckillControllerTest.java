package org.seckill.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml",
	"classpath:spring/spring-web.xml",
	"classpath:spring/spring-aop.xml"})
public class SeckillControllerTest {
	
	@Test
	public void testExecute() {
		SeckillController sc = new SeckillController();
		sc.execute(1001L, 13829382938L, "23456734dsdgfh");
	}

}
