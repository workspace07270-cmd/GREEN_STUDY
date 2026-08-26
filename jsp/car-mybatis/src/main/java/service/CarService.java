package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;

import config.DBManager;
import dto.CarDTO;
import mapper.CarMapper;

public class CarService {
	private static CarService instance = new CarService();
	private CarMapper mapper;
	
	private CarService() {	
		mapper = DBManager.getInstance().openSession().getMapper(CarMapper.class);		
	}
	
	public static CarService getInstance() {
		if(instance == null)
			instance = new CarService();
		return instance;
	}

	public List<CarDTO> selectAllCar() {
		return mapper.selectAllCar();
	}

	public int insertCar(CarDTO newCar) throws PersistenceException{
		return mapper.insertCar(newCar);
	}

	public int deleteCar(String string) {
		return mapper.deleteCar(string);
	}

	public int updateCar(CarDTO newCar) {
		return mapper.updateCar(newCar);
	}

	public List<CarDTO> selectBrandCar(String[] arr) {
		return mapper.selectBrandCar(arr);
	}

	public List<Map<String, Object>> selectBrandModelCount() {
		return mapper.selectBrandModelCount();
	}

	public List<CarDTO> selectYearCar(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		return null;
	}
}







