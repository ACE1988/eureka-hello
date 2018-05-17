package com.eureka.hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modification History <p> Date        Name                    Reason for Change ----------
 * ----------------------  ------------------ 2018/1/11    刘节                 Created
 */
@RestController
//@RequestMapping(value = "/api-a-url", produces = "application/json")
public class HelloController {

  private final Logger logger = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  private DiscoveryClient client;

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String index() {
    ServiceInstance instance = client.getLocalServiceInstance();
    logger.info("/hello ,host:", instance.getHost() + " , service_id:" + instance.getServiceId());
    return "Hello World";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
    logger.info("a={},b={}", a, b);
    return "Hello World";
  }

  @RequestMapping(value = "/hello1", method = RequestMethod.GET)
  public String hello(@RequestParam String name) {
    return "Hello " + name;
  }

  @RequestMapping(value = "/hello2", method = RequestMethod.GET)
  public String hello(@RequestHeader String name, @RequestHeader Integer age) {
    return new User(name, age).toString();
  }

  @RequestMapping(value = "/hello3", method = RequestMethod.POST)
  public String hello(@RequestBody User user) {
    return "Hello " + user.getName() + " , " + user.getAge();
  }


}
