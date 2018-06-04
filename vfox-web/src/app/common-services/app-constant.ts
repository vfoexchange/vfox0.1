export class Configuration {

    //*****************************API Auth *****************************/
    public ClientId: string = '123456';
    public Authorization: string = 'Basic MTIzNDU2OmRmdmJhZWZ2YWRlZnZhYw==';
    public ApiUrl:string = 'http://192.0.0.20:7080/vfox/';
    public HomeNavPageUrl: string = '/#/login';
    public AppMode: string = 'DEV'; //For PROD set 'PROD' mode;

    //*****************************User Roles *****************************/
    public ADVISOR: string = 'advisor';
    public ADMIN: string = 'admin';
    public CLIENT: string = 'client';


    public SESSION_TOKEN_REFRESH_TIME:number = 20000;
    //*****************************URL Access Permission *****************************/
    public restrictedPageForAdmin: string[] = ['/dashboard/','/dashboard/selectservices','/dashboard/insurance','/dashboard/addclient'];
    public restrictedPageForADVISOR: string[] =  ['/dashboard/','/dashboard/selectservices','/dashboard/insurance','/dashboard/addclient'];
    public restrictedPageForUser: string[] =  ['/dashboard/','/dashboard/insurance'];




    //*****************************Api URLs *****************************/
    public API_LOGIN_URL: string = 'user/auth';
    public API_REGISTER_URL: string = 'add/user';
    public API_GET_USER: string = 'fetch/user';
    public API_SERVICE_PROVIDER: string = 'get/serviceProviders';
    public API_USER_SERVICE: string = 'get/advisor/services';
    public API_UPDATE_SERVICE: string = 'update/advisor/services';


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

//*****************************User Roles *****************************/
    public RolesList = [
        {
            key: 'ADMIN',
            value: 'Admin'
        },{
            key: 'ADVISOR',
            value: 'Advisor'
        }, {
            key: 'CLIENT',
            value: 'Client'
        }
    ];


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
    'bill_pay': false,
    'insurance': false,
    'investment': false,
    'welth_manage': false,
    'tax_mitigation': false,
    'asset_protection': false,
    'trust_services': false,
    'business_valuation': false,
    'cost_remediation': false,
    'business_transition': false
    };
    
    public footerMenu: any = {
        'bill_pay_menu': false,
        'insurance_menu': false,
        'investment_menu': false,
        'welth_manage_menu': false,
        'tax_mitigation_menu': false,
        'asset_protection_menu': false,
        'trust_services_menu': false,
        'business_valuation_menu': false,
        'cost_remediation_menu': false,
        'business_transition_menu': false
        };

}