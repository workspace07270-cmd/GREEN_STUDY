package mapper;

import java.util.List;
import java.util.Map;

import dto.CarDTO;

public interface CarMapper {

	List<CarDTO> selectAllCar();

	int insertCar(CarDTO newCar);

	int deleteCar(String string);

	int updateCar(CarDTO newCar);

	List<CarDTO> selectBrandCar(String[] arr);

	List<Map<String, Object>> selectBrandModelCount();

}



