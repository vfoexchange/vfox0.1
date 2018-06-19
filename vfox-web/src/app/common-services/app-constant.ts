export class Configuration {

    //*****************************API Auth *****************************/
    public ClientId: string = '123456';
    public Authorization: string = 'Basic MTIzNDU2OmRmdmJhZWZ2YWRlZnZhYw==';
    public ApiUrl:string = 'http://localhost:8080/';
    //public ApiUrl:string = 'http://192.0.0.20:7070/vfox/';
    public HomeNavPageUrl: string = '/login';
    public AppMode: string = 'PROD'; //For PROD set 'PROD' mode;

    //*****************************User Roles *****************************/
    public ADVISOR: string = 'advisor';
    public ADMIN: string = 'admin';
    public CLIENT: string = 'client';


    public SESSION_TOKEN_REFRESH_TIME:number = 20000;
    //*****************************URL Access Permission *****************************/
    public restrictedPageForAdmin: string[] = ['/dashboard/','/dashboard/selectservices','/dashboard/addclient'];
    public restrictedPageForADVISOR: string[] =  ['/dashboard/','/dashboard/selectservices','/dashboard/service','/dashboard/addclient'];
    public restrictedPageForUser: string[] =  ['/dashboard/','/dashboard/service'];




    //*****************************Api URLs *****************************/
    public API_LOGIN_URL: string = 'user/auth';
    public API_REGISTER_URL: string = 'add/user';
    public API_CLIENT_REGISTER_URL: string = 'add/client';
    public API_EMAIL_VERIFY: string = 'user/verification';
    public API_GET_USER: string = 'fetch/user';
    public API_SERVICE_PROVIDER: string = 'get/serviceProviders';
    public API_USER_SERVICE: string = 'get/advisor/services';
    public API_UPDATE_SERVICE: string = 'update/advisor/services';
    public API_SERVICE_BILLING: string = 'update/provider/billing';


      //*****************************Api URLs Ends *****************************/

    public ADMIN_ROLE_ID: string = '1';
    public ADVISOR_ROLE_ID: string = '2';
    public CLIENT_ROLE_ID: string = '3';

    // Regex patterns
    public NUMBER_REGEX: string = '^[0-9]*$'; // eg: 123456
    public DECIMAL_UPTO_TWO_REGEX: string = '^((([0-9]{1,50}))(\.[0-9]{1,2})?$)'; // eg: 123.45
    public DATE_REGEX: string = '(0\d{1}|1[0-2])\/([0-2]\d{1}|3[0-1])\/(19|20)\d{2}';//'^((0?[1-9]|1[012])[/](0?[1-9]|[12][0-9]|3[01])[/](19|20)?[0-9]{2})*$'; //eg: 01/01/2017
    public ALPHANUMERIC_REGEX: string = '^[ a-zA-Z0-9_-]*$'; //eg: a123_g-n
    public EMAIL_REGEX: string = '[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}'; //eg: a@b.com
    public PHONE_REGEX: string = '^[0-9-+]*$';
    public ALPHABETS_REGEX: string = '^[ a-zA-Z]*$';



    public ServiceName: any = {
    'bill_pay': 'Bill Pay',
    'insurance': 'Insurance',
    'investment': 'Alternate Investment',
    'welth_manage': 'Wealth Management',
    'tax_mitigation': 'Tax Mitigation',
    'asset_protection': 'Asset Protection',
    'trust_services': 'Trust Services',
    'business_valuation': 'Business Valuation',
    'cost_remediation': 'Cost Remediation',
    'business_transition': 'Business Transition'
    };


    public ServiceStatus: any = {
    'bill_pay': true,
    'insurance': true,
    'investment': true,
    'welth_manage': true,
    'tax_mitigation': true,
    'asset_protection': true,
    'trust_services': true,
    'business_valuation': true,
    'cost_remediation': true,
    'business_transition': true
    };
    
    //*****************************Footer Menu *****************************/
    public footerMenu: any = {
        'bill_pay_menu': true,
        'insurance_menu': true,
        'investment_menu': true,
        'welth_manage_menu': true,
        'tax_mitigation_menu': true,
        'asset_protection_menu': true,
        'trust_services_menu': true,
        'business_valuation_menu': true,
        'cost_remediation_menu': true,
        'business_transition_menu': true
        };

}