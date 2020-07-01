package collection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhenL
 * @Description:
 * 文件: 位置随意,数据格式: id, name, gender, score, address
 *
 *  读取出数据之后,放到对象中User
 *
 *  在将User放入集合中
 *
 *  集合中的User,按照name分组,且每个组内的年龄还要排序
 *
 *  - 如: 北京,男 ; 上海,女
 */
public class ListDemo {
	public static void main(String[] args) {
		// 1.
		List<String> result = readFile("C:\\Users\\94061\\IdeaProjects\\java-basic\\src\\main\\java\\collection\\test.log");
		for (String s : result) {
			System.out.println(s);
		}
		// 2.
		List<User> users = parse(result);

		// 2.1 group
		Map<String, List<User>> groupResult = groupByName(users);

		// Map<String,Map<String,List<User>>> -> List<GroupQuan>

		// 2.1 打印结果
		printGroupResult(groupResult);

		// 2.2 sort
		Map<String, List<User>> newGroupResult = sort(groupResult);

		System.out.println("排序后:");
		printGroupResult(newGroupResult);

	}

	/**
	 * 1.读文件
	 */
	private static List<String> readFile(String path) {
		// 1.
		List<String> result = new ArrayList<>();


		// 2.无
		File file = new File(path);

		// 3.
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String val = null;
			while ((val = reader.readLine()) != null) {
				result.add(val);
			}
			return result;
		} catch (Exception ex) {
			System.out.println("为啥出错,");
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * 2.对数据进行解析
	 */
	private static List<User> parse(List<String> resultData) {
		List<User> users = new ArrayList<>();

		for (String datum : resultData) {
			String[] splitArr = datum.split(",");
			// id
			Integer id = Integer.parseInt(splitArr[0]);
			System.out.println(id);
			// name
			String name = splitArr[1];
			System.out.println(name);
			// age
			Integer age = Integer.parseInt(splitArr[2]);
			System.out.println(age);
			User user = new User(id, name, age);
			System.out.println(user.toString());
			users.add(user);
		}

		return users;
	}

	/**
	 * 3、按照name进行分组
	 * @param users
	 * @return
	 */
	private static Map<String, List<User>> groupByName(List<User> users) {
		Map<String, List<User>> result = new HashMap<>();
		for (User user : users) {
			String userName = user.getName();
			if (result.containsKey(userName)) {
				result.get(userName).add(user);
			} else {
				List<User> userList = new ArrayList<>();
				userList.add(user);
				result.put(userName, userList);
			}
		}
		return result;
	}

	/**
	 * 打印map中的数据
	 * @param groupResult
	 */
	private static void printGroupResult(Map<String, List<User>> groupResult) {
		for (Map.Entry<String, List<User>> entry : groupResult.entrySet()) {
			String groupName = entry.getKey();
			List<User> users = entry.getValue();
			System.out.println(groupName);
			for (User user : users) {
				System.out.println("\t" + user);
			}

			System.out.println("\n===========================\n");
		}
	}

	/**
	 * 将每个组合中的user按照年龄排序排序
	 * @param groupResult
	 * @return
	 */
	private static Map<String, List<User>> sort(Map<String, List<User>> groupResult) {
		Map<String, List<User>> result = new HashMap<>();

		for (Map.Entry<String, List<User>> entry : groupResult.entrySet()) {
			String groupName = entry.getKey();

			// 原始的user 列表
			List<User> users = entry.getValue();

			// 排序后的 user 列表
			List<User> sortedUsers = sortUser(users);

			result.put(groupName, sortedUsers);
		}

		return result;
	}

	/**
	 * 将每个组内的user按照年龄重新排序
	 * @param users
	 * @return
	 */
	private static List<User> sortUser(List<User> users) {
		for (int i = 0; i < users.size(); i++) {
			for (int j = 0; j < i; j++) {
				User u1 = users.get(i);
				User u2 = users.get(j);
				if (u1.getAge() < u2.getAge()) {
					users.set(i, u2);
					users.set(j, u1);
				}
			}
		}
		return users;
	}
}
