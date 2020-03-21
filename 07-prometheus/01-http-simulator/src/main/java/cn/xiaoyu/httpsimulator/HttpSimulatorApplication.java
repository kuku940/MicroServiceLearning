package cn.xiaoyu.httpsimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;

@Controller
@SpringBootApplication
@EnablePrometheusEndpoint
public class HttpSimulatorApplication implements ApplicationListener<ContextClosedEvent> {
	@Autowired
	private SimulatorOpts opts;
	private ActivitySimulator simulator;

	public static void main(String[] args) {
		SpringApplication.run(HttpSimulatorApplication.class, args);
	}

	@GetMapping(value = "/opts")
	public @ResponseBody String getOps() {
		return opts.toString();
	}

	@PostMapping(value = "/spike/{mode}")
	public @ResponseBody String setSpikeMode(@PathVariable("mode") String mode) {
		boolean result = simulator.setSpikeMode(mode);
		if (result) {
			return "ok";
		} else {
			return "wrong spike mode " + mode;
		}
	}

	@PostMapping(value = "error_rate/{error_rate}")
	public @ResponseBody String setErrorRate(@PathVariable("error_rate") int errorRate) {
		simulator.setErrorRate(errorRate);
		return "ok";
	}

	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}

	@Bean
	public CommandLineRunner schedulingRunner(TaskExecutor executor) {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {
				simulator = new ActivitySimulator(opts);
				executor.execute(simulator);
				System.out.println("Simulator thread started...");
			}
		};
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		simulator.shutdown();
		System.out.println("Simulator shutdown...");
	}
}
