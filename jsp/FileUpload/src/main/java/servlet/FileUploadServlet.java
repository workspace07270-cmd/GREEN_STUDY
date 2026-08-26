package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/fileUpload.do")
@MultipartConfig(maxRequestSize = 1024*1024*50 ,maxFileSize = 1024*1024*5)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// c:\fileupload <- 해당 경로에 업로드한 파일 저장
		// 해당 경로가 있는지 체크
		File root = new File("c:\\fileupload"); 
		// 해당 경로가 없으면 경로에 해당하는 폴더를 생성
		if(!root.exists()) {
			System.out.println("파일 업로드할 폴더 및 경로 생성");
			root.mkdirs();
		}
		
		// 파일 업로드 처리
		Iterator<Part> it = request.getParts().iterator();
		
		while(it.hasNext()) {
			Part p = it.next();
			if(p.getSubmittedFileName() != null && !p.getSubmittedFileName().isEmpty()) {
				System.out.println(p.getName());
				System.out.println(p.getSize());
				System.out.println(p.getSubmittedFileName());
				//파일 쓰기
				p.write(root.getAbsolutePath() + "\\" + p.getSubmittedFileName());
			}else {
				//받은 내용이 파일이 아닐때
				System.out.println(p.getName() + " / " + request.getParameter(p.getName()) );
			}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}