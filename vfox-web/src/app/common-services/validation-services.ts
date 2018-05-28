import{FormGroup}from'@angular/forms';
import {Configuration }from './app-constant';


/* Custom Validation for Model Driven Form */

export class ValidationService {




static emailValidator(control: any) {
        // RFC 2822 compliant regex
        if (control.value === undefined || control.value === '' || control.value === null) {
            return null;
        } else {
            // /[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?/
            if (control.value.match(/^[a-zA-Z0-9_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/)) {
                return null;
            } else {
                return { 'invalidEmailAddress': true };
            }
        }
    }




    static passwordValidator(control: any) {
        // {8,12}           - Assert password is between 8 and 12 characters
        // (?=.*[0-9])       - Assert a string has at least one number
        if (control.value === null || control.value === undefined || control.value === '') {
            return null;
        } else if (control.value.match(/^(?=.*[0-9])(?=.*[ !#$%&'()*+,-./:;<=>?@[\]^_`{|}~])[a-zA-Z0-9 !#$%&'()*+,-./:;<=>?@[\]^_`{|}~]{8,15}$/)) {
            return null;
        } else {
            return { 'invalidPassword': true };
        }
    }


    static confirmPasswords(passwordKey: string, confirmPasswordKey: string) {
        return (group: FormGroup): { [key: string]: any } => {
            let password: any = group.controls[passwordKey];
            let confirmPassword: any = group.controls[confirmPasswordKey];

            if (password.value !== confirmPassword.value) {
                console.log('sdfd')
                return {
                    mismatchedPasswords: true
                };
            } else {
                return null;
            }
        };
    }

    static confirmEmails(emailKey: string, confirmEmaildKey: string) {
        return (group: FormGroup): { [key: string]: any } => {
            let email = group.controls[emailKey];
            let confirmEmail = group.controls[confirmEmaildKey];

            if (email.value !== confirmEmail.value) {
                return {
                    mismatchedEmails: true
                };
            } else {
                return null;
            }
        };
    }


    static whiteSpace(control: any) {
        console.log('Value====' + control.value);
        if (control.value === undefined || control.value === '') {
            return null;
        } else {
            if (control.value.trim() === '') {
                return { 'invalidInputValue': true };
            } else {
                return null;
            }
        }
    }

    static usernameValidator(control: any) {
        if (control.value === undefined || control.value === '') {
            return null;
        } else {
            if (control.value.match(/^[a-zA-Z0-9_.]{1,25}$/)) {
                return null;
            } else {
                return { 'invalidUsername': true };
            }
        }
    }

    static nameValidator(control: any) {
        if (control.value === undefined || control.value === '' || control.value === null) {
            return null;
        } else {
            if (control.value.match(/^[a-zA-Z ]*$/)) {
                return null;
            } else {
                return { 'invalidName': true };
            }
        }
    }

  

    static isValidDate(control: any) {
        let d: any = new Date(control.value);
        if (control.value === undefined || control.value === '') {
            return null;
        } else {
            if (!control.value.match(/^\d{4}-\d{2}-\d{2}$/)) {
                return { 'invalidDate': true };
            } else if (!((d) | 0)) {
                return { 'invalidDate': true };
            } else {
                return null;
            }
        }
    }

    static isSpecialChar(control: any) {
        if (control.value === undefined || control.value === '' || control.value === null) {
            return null;
        } else {
            if (control.value.match(/^[a-zA-Z0-9_.\-()\s]+$/)) {
                return null;
            } else {
                return { 'invalidChar': true };
            }
        }
    }



    static isValidNumber(control: any) {

        if (control.value === undefined || control.value === '' || control.value === null) {
            return null;
        } else {
            if (control.value.match(/^[0-9]*$/)) {
                return null;
            } else {
                return { 'invalidNumber': true };
            }
        }
    }



    static isCheckboxChecked(control: any) {
        if (control.value) {
            return null;
        } else {
            return { 'required': true };
        }
    }

    static isAlphanumeric(control: any) {
        if (control.value === undefined || control.value === null || control.value === '') {
            return null;
        }
        if (control.value.match(/^[A-Za-z\d\s]+$/)) {
            return null;
        } else {
            return { 'invalidCharacter': true };
        }

    }

    static isPastDate(control: any) {

        var date = new Date();
        var pastDate = new Date(control);
        var diff = (date.getTime() - pastDate.getTime()) / (1000 * 60 * 60 * 24);
        //  diff = Math.round(diff);
        console.log('date difference in microseconds', diff);
        if ((diff > 1)) {
            return { 'invalidDate': true };
        } else {
            return { 'invalidDate': false };
        }
    }

    static isValidNumberStartingNonZeroByValue(control: string) {
        if (control === undefined || control === '' || control === null) {
            return { 'invalidNumber': false };
        } else {
            if (control.match(/^[^0][0-9]*$/)) {
                return { 'invalidNumber': false };
            } else {
                return { 'invalidNumber': true };
            }
        }
    }

    static isValidNumberStartingNonZero(control: any) {
        if (control.value === undefined || control.value === '' || control.value === null) {
            return null;
        } else {
            if (control.value.match(/^[^0][0-9]*$/)) {
                return null;
            } else {
                return { 'invalidNumber': true };
            }
        }
    }
    static mobileValidatorByValue(control: any) {
        if (control === undefined || control === '' || control === null) {
            return { 'invalidMobileNumber': false };
        } else {
           if (control.match(/^[1-9][0-9]{8,11}$/)) {
                return { 'invalidMobileNumber': false };
            } else {
                return { 'invalidMobileNumber': true };
            }
        }
    }

    static mobileValidator(control: any) {
        if (control.value === undefined || control.value === '' || control.value === null) {
            return null;
        } else {
            if (control.value.match(/^[1-9][0-9]{8,11}$/)) {
                return null;
            } else {
                return { 'invalidMobileNumber': true };
            }
        }
    }
    static isNumberValidatorValue(control: any) {
        if (control === undefined || control === '' || control === null) {
            return { 'invalidNumber': false };
        } else {
            if (control.match('^([0-9]{10,10})$')) {
                return { 'invalidNumber': false };
            } else {
                return { 'invalidNumber': true };
            }
        }
    }

    static isCDSCodeValidByValue(control: any) {
        if (control === undefined || control === '' || control === null) {
            return { 'invalidNumber': false };
        } else {
            if (control.match('^([0-9]{4,14})$')) {
                return { 'invalidNumber': false };
            } else {
                return { 'invalidNumber': true };
            }
        }
    }
    //National Id Validation
    static isNationalIdValid(control: any) {
        if (control === undefined || control === '' || control === null) {
            return { 'invalidNationalId': false };
        } else {
            if (control.match(/^((19|20)\d\d)((0)[1-9]|1[012])((0)[1-9]|[12][0-9]|3[01])-\d{5}-\d{5}-\d{2}$/)) {
                return { 'invalidNationalId': false };
            } else {
                return { 'invalidNationalId': true };
            }
        }
    }



}

/* End of Custom Validation for Model Driven Form */
