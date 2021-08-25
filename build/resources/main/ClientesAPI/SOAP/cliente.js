var soap = require('soap')
var url = 'http://localhost:7000/ws/?wsdl'
//var args = {arg0:'https://www.geeksforgeeks.org/convert-java-object-to-json-string-using-gson/' , arg1: 'admin'};
var args = {arg0:'https://github.com/' , arg1: 'admin'};
soap.createClient(url,function(err,client){
    client.registrarEnlace(args, function(err, result, rawResponse, soapHeader, rawRequest) {
        console.log(result)
    })
    
})

