*** Settings ***
Suite Setup       Initialize Random Variables
Library           Selenium2Library
Library           String

*** Variables ***
${url}            https://inventory.edu-netcracker.com/pages/registration.xhtml

*** Test Cases ***
testNewUser
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    ${userName1}
    loginPassword
    Select From List By Value    registerForm:role    RO
    Input Text    registerForm:email    userr.2@testmail.com
    Click Button    registerForm:j_idt26
    messegeSuccessfulRegistration
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    ${userName2}
    loginPassword
    Select From List By Value    registerForm:role    RO
    Input Text    registerForm:email    userr.3@testmail.com
    Click Button    registerForm:j_idt26
    messegeSuccessfulRegistration
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    ${userName3}
    loginPassword
    Select From List By Value    registerForm:role    RW
    Input Text    registerForm:email    userr.4@testmail.com
    Click Button    registerForm:j_idt26
    messegeSuccessfulRegistration
    Close All Browsers

testIncorrectUsernamePasswordEmail
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    Userr
    Input Password    registerForm:password    Password4
    Input Password    registerForm:confirmPassword    Password
    Input Text    registerForm:email    userr5@.com
    Click Button    registerForm:j_idt26
    Page Should Contain    Login must be alphanumeric string with length => 6 and <= 50.
    Page Should Contain    At least one non alpha numeric symbol must be in password
    Page Should Contain    Email address is incorrect.
    Close Browser
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    81354
    Input Password    registerForm:password    Password+
    Input Password    registerForm:confirmPassword    Password
    Input Text    registerForm:email    userr-6testmail.com
    Click Button    registerForm:j_idt26
    Page Should Contain    Login must be alphanumeric string with length => 6 and <= 50.
    Page Should Contain    At least one digit must be in password
    Page Should Contain    Email address is incorrect.
    Close Browser
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    User7
    Input Password    registerForm:password    Password4+
    Input Password    registerForm:confirmPassword    Password
    Input Text    registerForm:email    userr.7testmail.com
    Click Button    registerForm:j_idt26
    Page Should Contain    Login must be alphanumeric string with length => 6 and <= 50.
    Page Should Contain    Password must match confirm password.
    Page Should Contain    Email address is incorrect.
    Close Browser
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    User8
    Input Password    registerForm:password    Passwor
    Input Password    registerForm:confirmPassword    Password
    Input Text    registerForm:email    userr_testmail.
    Click Button    registerForm:j_idt26
    Page Should Contain    Login must be alphanumeric string with length => 6 and <= 50.
    Page Should Contain    Password length must me >= 8 and <= 50.
    Page Should Contain    Email address is incorrect
    Close All Browsers

testExistingUserName
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    ${userName4}
    loginPassword
    Input Text    registerForm:email    userr.2@testmail.com
    Click Button    registerForm:j_idt26
    messegeSuccessfulRegistration
    Open Browser    ${url}    chrome
    Input Text    registerForm:username    ${userName4}
    loginPassword
    Input Text    registerForm:email    userr.2@testmail.com
    Click Button    registerForm:j_idt26
    Page Should Contain    User with such login already exists.
    Page Should Contain    User with such email already exists.
    Close All Browsers

*** Keywords ***
Initialize Random Variables
    ${userName1}=    Generate random string    8    [LOWER]
    Set global variable    ${userName1}
    ${userName2}=    Generate random string    8    [LOWER]
    Set global variable    ${userName2}
    ${userName3}=    Generate random string    8    [LOWER]
    Set global variable    ${userName3}
    ${userName4}=    Generate random string    8    [LOWER]
    Set global variable    ${userName4}

PasswordKW
    Input Password    registerForm:password    ${password}
    Input Password    registerForm:confirmPassword    ${password}

loginPassword
    Input Password    registerForm:password    Password2+
    Input Password    registerForm:confirmPassword    Password2+

messegeSuccessfulRegistration
    Page Should Contain    You have successfully registered
    Page Should Contain    Use your credentials to login
