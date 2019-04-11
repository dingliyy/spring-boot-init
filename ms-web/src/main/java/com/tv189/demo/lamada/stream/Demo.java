/**
 *	
 * @author dingli02  
 * @date 2019/04/11 16:35
 */
package com.tv189.demo.lamada.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

/**
 *	
 * @author dingli02  
 * @date 2019/04/11 16:35
 */
public class Demo {
	public static void main(String[] args) {
		filter();
		System.out.println("---------------------------------------------------------");
		statistics();
		System.out.println("---------------------------------------------------------");
		limit();
		System.out.println("---------------------------------------------------------");
		map();
	}
	
	// 过滤
	private static void filter() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl", null);
		List<String> filtered = strings.stream().filter(string ->string != null && !string.isEmpty()).collect(Collectors.toList());
		System.out.println(JSON.toJSONString(filtered));
	}
	
	// 统计
	private static void statistics() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
	
		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("所有数之和 : " + stats.getSum());
		System.out.println("平均数 : " + stats.getAverage());
	}
	
	// 限制
	private static void limit() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<Integer> limited = numbers.stream().limit(3).collect(Collectors.toList());
		System.out.println(JSON.toJSONString(limited));
	}
	
	// 映射
	private static void map() {
		List<User> list = Arrays.asList(new User("dingli", 1), new User("dingli2", 0), new User("dingli3", 1));
		List<User> maped = list.stream().map(user -> user.resetSex()).collect(Collectors.toList());
		System.out.println(JSON.toJSONString(list));
		System.out.println(JSON.toJSONString(maped));
		
		
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// 获取对应的平方数
		List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		System.out.println(JSON.toJSONString(squaresList));
	}
	
	
}
