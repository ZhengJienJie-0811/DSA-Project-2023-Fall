package com.myproject;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class myServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 從前端獲取使用者輸入的數字
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        try {
            // 將字符串轉換為數字
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);

            // 執行運算
            int result = num1 + num2;

            // 將結果返回至前端
            response.getWriter().println("Result: " + result);
        } catch (NumberFormatException e) {
            // 處理轉換失敗的情況
            response.getWriter().println("Invalid input. Please enter valid numbers.");
        }
    }
}
