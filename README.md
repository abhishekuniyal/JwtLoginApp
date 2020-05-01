# JwtLoginApp
This application can be used for the Stateless Jwt authentication.

STeps to run:
1.) run using java -jar 
2) Use postman to for 
  POST: http://localhost:8080/authenticate
  Body: {"userName":"abhi",
          "userPass":"abhi"
          }
 3.) Authenticated successfully and will recieve a JWT with 10 days Expiry time.
 
 4.) Hit a Get request on "http://localhost:8080/dashboard" with below header
      "Authentication": "Bearer <TOKEN_RECIEVD>"
