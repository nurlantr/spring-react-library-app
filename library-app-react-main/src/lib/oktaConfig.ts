export const oktaConfig = {

    clientId: '0oaeb92uuik1z7Ah75d7',
    issuer: 'https://dev-54796993.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}