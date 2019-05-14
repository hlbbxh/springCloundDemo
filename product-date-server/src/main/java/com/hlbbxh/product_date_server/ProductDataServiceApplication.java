package com.hlbbxh.product_date_server;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * @author 启动类， 考虑到要做集群。 所以让用户自己输入端口，推荐 8001，8002，8003.但 是每次测试都要输入端口号又很麻烦，所以做了个
 *         Future类，如果5秒不输入，那么就默认使用 8001端口。
 */
@SpringBootApplication
@EnableEurekaClient // eureka-client 代表客户端 此时启动后 就可以通过接口简单的访问数据了
public class ProductDataServiceApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 0;
		int defaultPort = 8001;
		Future<Integer> future = ThreadUtil.execAsync(() -> {
			int p = 0;
			System.out.println("请于5秒钟内输入端口号, 推荐  8001 、 8002  或者  8003，超过5秒将默认使用 " + defaultPort);
			Scanner scanner = new Scanner(System.in);
			while (true) {
				String strPort = scanner.nextLine();
				if (!NumberUtil.isInteger(strPort)) {
					System.err.println("只能是数字");
					continue;
				} else {
					p = Convert.toInt(strPort);
					scanner.close();
					break;
				}
			}
			return p;
		});
		try {
			port = future.get(5, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			port = defaultPort;
		}

		if (!NetUtil.isUsableLocalPort(port)) {
			System.err.printf("端口%d被占用了，无法启动%n", port);
			System.exit(1);
		}
		// 启动spring boot 项目
		new SpringApplicationBuilder(ProductDataServiceApplication.class).properties("server.port=" + port).run(args);
	}
}