exportclassConfiguration {

    //*****************************API Auth *****************************/
    publicClientId:string = '123456';
    publicAuthorization:string = 'Basic MTIzNDU2OmRmdmJhZWZ2YWRlZnZhYw==';

    publicApiUrl:string = 'http://localhost:8080/';
    publicHomeNavPageUrl:string = '/#/login';

    publicAppMode:string = 'DEV'; //For PROD set 'PROD' mode;

    //*****************************User Roles *****************************/
    publicADVISOR:string = 'Advisor';
    publicADMIN:string = 'Admin';
    publicCLIENT:string = 'Client';


    publicSESSION_TOKEN_REFRESH_TIME:number = 20000;
    //*****************************URL Access Permission *****************************/
    publicrestrictedPageForAdmin:string[] = ['/dashboard/','/dashboard/selectservices','/dashboard/insurance'];
public restrictedPageForADVISOR: string[] = ['/dashboard/'];
public restrictedPageForUser: string[] = ['/dashboard/'] ;


//*****************************Api URLs *****************************/
public API_LOGIN_URL: string = 'user/auth';
public API_REGISTER_URL: string = 'add/user';
public API_SERVICE_PROVIDER: string = 'get/serviceProviders';


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

}