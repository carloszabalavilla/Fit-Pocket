package edu.fpdual.webapplication;

public class GlobalInfo {
    /*
     * URL de la aplicacion
     */
    public static final String URL_PROYECTO = "/ProyectoPrueba";
    /*
     * URL de los .jsp
     */
    public static final String URL_INDEX = URL_PROYECTO + "/";
    public static final String URL_JSP = URL_PROYECTO + "/jsp";
    public static final String URL_JSP_HOME = URL_JSP + "/common/home.jsp";
    public static final String URL_JSP_LOGIN = URL_JSP + "/login/login.jsp";
    public static final String URL_JSP_CONTROLPANEL = URL_JSP + "/admin/controlPanel.jsp";
    public static final String URL_JSP_NEWADMIN = URL_JSP + "/admin/adminManager.jsp";
    public static final String URL_JSP_PROFILE = URL_JSP + "/common/profile.jsp";
    public static final String URL_JSP_RESTOREPASSWORD = URL_JSP + "/login/restorePassword.jsp";
    public static final String URL_JSP_REGISTER = URL_JSP + "/login/register.jsp";
    public static final String URL_JSP_LISTS = URL_JSP + "/admin/database/lists.jsp";
    public static final String URL_JSP_ABOUTUS = URL_JSP + "/aboutUs.jsp";
    public static final String URL_JSP_CONTACTUS = URL_JSP + "/contactUs.jsp";
    public static final String URL_JSP_LISTBUDGET = URL_JSP +"/common/listBudget.jsp";
    public static final String URL_JSP_SUCCESS = URL_JSP +"/common/success.jsp";
    public static final String URL_JSP_ADDBUDGET = URL_JSP +"/common/addBudget.jsp";
    public static final String URL_JSP_ADDEXPENSE = URL_JSP +"/common/addExpense.jsp";
    public static final String URL_JSP_ADDINCOME = URL_JSP +"/common/addIncome.jsp";
    public static final String URL_JSP_BUDGETDATA = URL_JSP +"/common/budgetData.jsp";
    public static final String URL_JSP_CATEGORIES = URL_JSP +"/common/categories.jsp";
    public static final String URL_JSP_REPORTS = URL_JSP +"/common/reports.jsp";

    /*
     * Páginas de error
     */
    public static final String URL_JSP_ERROR403 = URL_JSP + "/error/error403.jsp";
    public static final String URL_JSP_ERROR404 = URL_JSP + "/error/error404.jsp";
    public static final String URL_JSP_ERROR500 = URL_JSP + "/error/error500.jsp";


    /*
     * URL de los servlets
     */
    public static final String URL_SERVLET_ = URL_PROYECTO;
    public static final String URL_SERVLET_LOGIN = URL_SERVLET_ + "/login-servlet";
    public static final String URL_SERVLET_ADMINMANAGER = URL_SERVLET_ + "/admin-manager-servlet";
    public static final String URL_SERVLET_PROFILE = URL_SERVLET_ + "/profile-servlet";
    public static final String URL_SERVLET_LOGOUT = URL_SERVLET_ + "/logout-servlet";
    public static final String URL_SERVLET_REGISTER = URL_SERVLET_ + "/register-servlet";
    public static final String URL_SERVLET_LISTS = URL_SERVLET_ + "/lists-servlet";
    public static final String URL_SERVLET_PINGLIST = URL_SERVLET_ + "/ping-list-servlet";
    public static final String URL_SERVLET_UPDATEPASSWORD = URL_SERVLET_ + "/update-password-servlet";
    public static final String URL_SERVLET_RESTOREPASSWORD = URL_SERVLET_ + "/restore-password-servlet";
    public static final String URL_SERVLET_ADDBUDGET = URL_SERVLET_ +"/create-budget-servlet";
    public static final String URL_SERVLET_LISTBUDGETS = URL_SERVLET_ +"/list-budget-servlet";
    public static final String URL_SERVLET_DELETEBUDGET = URL_SERVLET_ +"/delete-budget-servlet";
    public static final String URL_SERVLET_DELETEINCOME = URL_SERVLET_ +"/delete-income-servlet";
    public static final String URL_SERVLET_DELETEEXPENSE = URL_SERVLET_ +"/delete-expense-servlet";
    public static final String URL_SERVLET_BUDGETDATA = URL_SERVLET_ +"/see-budget";
    public static final String URL_SERVLET_ADDINCOME = URL_SERVLET_ +"/create-income-servlet";
    public static final String URL_SERVLET_ADDEXPENSE = URL_SERVLET_ +"/create-expense-servlet";
    public static final String URL_SERVLET_CONTACTUS = URL_SERVLET_ +"/contact-servlet";
    public static final String URL_SERVLET_REPORTINCOME = URL_SERVLET_ +"/report-servlet-income";

    public static final String URL_SERVLET_GENERATEREPORTPDF = URL_SERVLET_ + "/generate-report-servlet";

    /*
     * Variables globales
     */
    public static final String URL_FAVICON = URL_PROYECTO + "/img/icon/favicon.ico";
    public static final String URL_WEBTARGET = System.getenv().getOrDefault("FIT_API_URL", "http://127.0.0.1:8083/webService/webapi/");
    public static final String session = "session";

}
