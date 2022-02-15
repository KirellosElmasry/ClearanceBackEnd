package com.church.clearance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.clearance.dao.UsersDao;
import com.church.clearance.entities.Users;
import com.church.clearance.model.ResultReturn;

@Service
public class LoginService {
	
	@Autowired
	private UsersDao usr;
	
	
	public ResultReturn getUserbyId(int id){
		
		ResultReturn result=new ResultReturn();
		
		Users user=usr.findOne(id);
		
		if(user !=null){
			
			result.getRes().put("code", 200);
			result.getRes().put("user",user);
			
			return result;
		}
		else{
			result.getRes().put("code", 4000);
			result.getRes().put("msg","user With Id "+id +" Not Exist");
			
			return result;
			
		}
	}	
		public ResultReturn getUserbyUserNamePassword(String userName,String password){
			
			ResultReturn result=new ResultReturn();
			
			if(userName == null || userName.equals("")){
				
				result.getRes().put("code", 4000);
				result.getRes().put("msg","userName Must be Entered ");
				
				return result;
			}
			else if(password == null || password.equals("")){
				
				result.getRes().put("code", 4000);
				result.getRes().put("msg","password Must be Entered ");
				
				return result;
			}
			
			Users user=usr.findUserByUserNameAndPass(userName, password);
			
			if(user !=null){
				
				result.getRes().put("code", 200);
				result.getRes().put("user",user);
				
				return result;
			}
			else{
				result.getRes().put("code", 4000);
				result.getRes().put("msg","userName Or password Incorrect ");
				
				return result;
				
			}
	}

}
