package com.meal.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.meal.model.MealService;
import com.meal.model.MealVO;


/**
 * Servlet implementation class MealServlet
 */
@WebServlet("/MealServlet")
public class MealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MealServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
        
        // git test
        
        // git test2
        
        // git test3
        
        // git test4



        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("getOne_For_Display".equals(action))
		{
			List<String> errorMsgs = new LinkedList<>();
			
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String str = request.getParameter("mealNo");
				if (str == null || (str.trim()).length() == 0) 
				{
					errorMsgs.add("no input");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/meal/select_page.jsp");
					failureView.forward(request, response);
					return;//�{�����_
				}
				
//				String mealNo = str;
//				try 
//				{
//					
//				} catch (Exception e)
//				{
//					
//				}
				
				/***************************2.�}�l�d�߸��*****************************************/


				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(str);
				

				if (mealVO == null)
				{
					errorMsgs.add("no this meal");
				}
				
				if(!errorMsgs.isEmpty())
				{
					RequestDispatcher failureView = request
							.getRequestDispatcher("/meal/select_page.jsp");
					failureView.forward(request, response);
					return;//�{�����_
				}
				
				/***************************3.  (Send the Success view) *************/
				
				

				request.setAttribute("mealVO", mealVO);
				String url = "/meal/listOneMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("bbbbbbbxxxxx " + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/meal/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String mealNo = request.getParameter("mealNo");
				
				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(mealNo);
				
				request.setAttribute("mealVO", mealVO);
				String url = "/meal/update_meal_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/meal/listAllMeals.jsp");
				failureView.forward(request, response);
			}

		}
		
		if ("update".equals(action))
		{
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
System.out.println("------  xxx");
			try {
				System.out.println("null"+request.getParameter("mealNo"));
				
				String mealNo = request.getParameter("mealNo").trim();
				String cinemaNo = request.getParameter("cinemaNo").trim();
				
System.out.println("------  xxx  yyy");
				
				String mealName = request.getParameter("mealName");
				String mealNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mealName == null || mealName.trim().length() == 0) {
					errorMsgs.add("餐點名稱 請勿空白");
				} else if(!mealName.trim().matches(mealNameReg)) { 
					errorMsgs.add("餐點名稱: 只能是中,英文字母,數字和_,且長度必須是2~10之間");
	            }
				
				
System.out.println("------  bbb1");
				String mealFood = request.getParameter("mealFood");
				
				Integer mealPrice = null;
				try {
					mealPrice = new Integer(request.getParameter("mealPrice").trim());
				} catch (NumberFormatException e) {
					mealPrice = 0;
					errorMsgs.add("價錢請填數字");
				}
		
System.out.println("------  bbb2");
				String mealPhoto = request.getParameter("mealPhoto");
				
				String photoExtension = request.getParameter("photoExtension");

				String photoTitle = request.getParameter("photoTitle");
				
				Integer mealActive = null;
				try {
					mealActive = new Integer(request.getParameter("mealActive").trim());
				} catch (NumberFormatException e) {
					mealActive = 0;
					errorMsgs.add("上架狀態請填數字");
				}

System.out.println("------  zzz");
				
				
				MealVO mealVO = new MealVO();
				mealVO.setCINEMA_NO(cinemaNo);
				mealVO.setMEAL_NAME(mealName);
				mealVO.setMEAL_FOOD(mealFood);
				mealVO.setMEAL_PRICE(mealPrice);
				mealVO.setMEAL_PHOTO(null);
				mealVO.setEXTENSION(photoExtension);
				mealVO.setPHOTO_TITLE(photoTitle);
				mealVO.setMEAL_ACTIVE(mealActive);
				
				
				if (!errorMsgs.isEmpty())
				{
					request.setAttribute("mealVO", mealVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/meal/update_meal_input.jsp");
					failureView.forward(request, response);
					return;
				}
System.out.println("------  zzz 5");		
				MealService mealSvc = new MealService();
				
				System.out.println("----- mealVO 1-----");
				mealVO = mealSvc.updateMeal(mealNo, cinemaNo, mealName, mealFood, mealPrice, null, photoExtension, photoTitle, mealActive);
				System.out.println("----- mealVO 2-----");

						
				request.setAttribute("mealVO", mealVO);
				String url = "/meal/listOneMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/meal/update_meal_input.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("insert".equals(action))
		{
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String mealNo = request.getParameter("mealNo").trim();
				String cinemaNo = request.getParameter("cinemaNo").trim();
				
				String mealName = request.getParameter("mealName");
				String mealNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mealName == null || mealName.trim().length() == 0) {
					errorMsgs.add("餐點名稱 請勿空白");
				} else if(!mealName.trim().matches(mealNameReg)) { 
					errorMsgs.add("餐點名稱: 只能是中,英文字母,數字和_,且長度必須是2~10之間");
	            }
				
				String mealFood = request.getParameter("mealFood");
				
				Integer mealPrice = null;
				try {
					mealPrice = new Integer(request.getParameter("mealPrice").trim());
				} catch (NumberFormatException e) {
					mealPrice = 0;
					errorMsgs.add("價錢請填數字");
				}
		
				String mealPhoto = request.getParameter("mealPhoto");
				
				String photoExtension = request.getParameter("photoExtension");

				String photoTitle = request.getParameter("photoTitle");
				
				Integer mealActive = null;
				try {
					mealActive = new Integer(request.getParameter("mealActive").trim());
				} catch (NumberFormatException e) {
					mealActive = 0;
					errorMsgs.add("上架狀態請填數字");
				}


				MealVO mealVO = new MealVO();
				mealVO.setCINEMA_NO(cinemaNo);
				mealVO.setMEAL_NAME(mealName);
				mealVO.setMEAL_FOOD(mealFood);
				mealVO.setMEAL_PRICE(mealPrice);
				mealVO.setMEAL_PHOTO(null);
				mealVO.setEXTENSION(photoExtension);
				mealVO.setPHOTO_TITLE(photoTitle);
				mealVO.setMEAL_ACTIVE(mealActive);
				
				
				if (!errorMsgs.isEmpty())
				{
					request.setAttribute("mealVO", mealVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/meal/update_meal_input.jsp");
					failureView.forward(request, response);
					return;
				}
				MealService mealSvc = new MealService();
				
				System.out.println("----- mealVO 1-----");
				mealVO = mealSvc.addMeal(cinemaNo, mealName, mealFood, mealPrice, null, photoExtension, photoTitle, mealActive);
				System.out.println("----- mealVO 2-----");

						
				request.setAttribute("mealVO", mealVO);
				String url = "/meal/listOneMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/meal/update_meal_input.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("delete".equals(action))
		{
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mealNo = request.getParameter("mealNo");
				
				MealService mealSvc = new MealService();
				mealSvc.deleteMeal(mealNo);
				
				String url = "/meal/listAllMeals.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗"+ e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/meal/listAllMeals.jsp");
				
				failureView.forward(request, response);
			}
			
		}
		

		
	}

}
