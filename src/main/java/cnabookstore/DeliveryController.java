package cnabookstore;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class DeliveryController {

  @GetMapping("/circuitBreaker")
  @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
//          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
//          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
//          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
  })
  public String circuitBreakerTest(@RequestParam String isYn) throws InterruptedException {

   if (isYn.equals("Y")) {
    System.out.println("@@@ CircuitBreaker!!!");
    Thread.sleep(10000);
    //throw new RuntimeException("CircuitBreaker!!!");
   }

   System.out.println("$$$ SUCCESS!!!");
   return " SUCCESS!!!";
  }

  private String fallback(String isYn) {
   System.out.println("### fallback!!!");
   return "CircuitBreaker!!!";
  }
 }
