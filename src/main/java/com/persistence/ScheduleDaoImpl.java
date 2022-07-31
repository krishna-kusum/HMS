package com.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.bean.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public Schedule getDoctorSchedule(int doctorId) {
		// TODO Auto-generated method stub
		Schedule schedule = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root",
				"wiley");
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM regular_schedule where doctor_id=?");) {

			preparedStatement.setInt(1, doctorId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("doctor_id");
				String name = resultSet.getString("name_of_doctor");
				String day = resultSet.getString("available_day");
				Time slot_start = resultSet.getTime("slot_start");
				Time slot_end = resultSet.getTime("slot_end");
				

				schedule = new Schedule(id,name,day,slot_start,slot_end);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return schedule;
	}

	@Override
	public boolean addDoctorSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		int rows = 0;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root",
				"wiley");
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO slot_start values(?,?,?,?,?)");) {

			preparedStatement.setInt(1, schedule.getDoctor_id());
			preparedStatement.setString(2, schedule.getName_of_doctor());
			preparedStatement.setString(3, schedule.getAvailable_day());
			preparedStatement.setTime(4, schedule.getSlot_start());
			preparedStatement.setTime(5, schedule.getSlot_end());
			

			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows>0)
			return true;
		else
			return false;
	}

	

	@Override
	public boolean removeDoctorSchedule(int doctorId) {
		// TODO Auto-generated method stub
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root",
				"wiley");
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM SCHEDULE where doctor_id=?");) {
			preparedStatement.setInt(1, doctorId);

			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
