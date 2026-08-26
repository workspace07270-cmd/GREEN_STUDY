package view;

/**
 * 컨트롤러 실행 후 이동할 페이지 경로와 이동 방식(Forward/Redirect)을 저장하는 클래스입니다.
 */
public class ModelAndView {
	// JSP 파일들이 모여있는 경로 (접두사)
	private static final String PREFIX = "/WEB-INF/views/";
	// 확장자 (접미사)
	private static final String SURFFIX = ".jsp";
	
	// 이동할 파일명 또는 URL
	private String path;
	// true면 Redirect, false면 Forward 방식으로 이동합니다.
	private boolean redirect;

	public ModelAndView(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	/**
	 * 최종적인 이동 경로를 반환합니다.
	 * Forward일 경우 완성된 JSP 경로를, Redirect일 경우 그대로의 URL을 반환합니다.
	 */
	public String getPath() {
		// Forward 방식이면 '/WEB-INF/views/파일명.jsp' 형태로 완성해서 반환합니다.
		if(!redirect)
			return PREFIX + path + SURFFIX;
		
		// Redirect 방식이면 그대로 반환합니다.
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	@Override
	public String toString() {
		return "ModelAndView [path=" + path + ", redirect=" + redirect + "]";
	}
}