package view;

public class ModelAndView {
	//경로 접두사
	private static final String PREFIX = "/WEB-INF/views/";
	//경로 접미사
	private static final String SURFFIX = ".jsp";
	//이동할 페이지 경로
	private String path;
	//forward인지? redirect인지?
	private boolean redirect;

	public ModelAndView(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		//forward 일때만 적용
		if(!redirect)
			return PREFIX + path + SURFFIX;
		//redirect 일때만 적용
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
