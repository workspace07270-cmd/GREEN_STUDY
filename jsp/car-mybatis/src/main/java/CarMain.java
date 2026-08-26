import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;

import dto.CarDTO;
import service.CarService;

public class CarMain {

	public static void main(String[] args) {
		CarService service = CarService.getInstance();
		//자동차 전체 조회
//		List<CarDTO> list = service.selectAllCar();
//		list.forEach(item -> System.out.println(item));
		//자동차 데이터 추가
//		CarDTO newCar = new CarDTO("60", "Genesis", "G80", 
//				2022, 15000, 65000000, "2020-05-21 00:00:00");
//		try {
//			int result = service.insertCar(newCar);
//			System.out.println("추가 결과 : " + result);
//		}catch (PersistenceException e) {
//			System.out.println("아이디값이 중복되었습니다.");
//		}
		//자동차 데이터 삭제
//		int result = service.deleteCar("60");
//		System.out.println("삭제 결과 : " + result);
		//자동차 데이터 수정
//		CarDTO newCar = new CarDTO("1", "Genesis", "G80", 
//				2022, 15000, 65000000, "2020-05-21 00:00:00");
//		int result = service.updateCar(newCar);
//		System.out.println("수정 결과 : " + result);
		
		//자동차 제조사명으로 검색(BMW, Honda, Kia)
//		String[] arr = {"BMW", "Honda", "Kia"};
//		List<CarDTO> list = service.selectBrandCar(arr);
//		list.forEach(item -> System.out.println(item));
		
		//제조사별 자동차 모델 개수
//		List<Map<String, Object>> list = service.selectBrandModelCount();
//		list.forEach(item ->{
//			System.out.println(item.get("brand_name") 
//					+ " / " + item.get("brand_count"));
//		});
		//2022~2025 자동차 데이터 조회
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startYear", 2022);
		map.put("endYear", 2025);
		
		List<CarDTO> list = service.selectYearCar(map);
		list.forEach(item -> System.out.println(item));
	}

}







