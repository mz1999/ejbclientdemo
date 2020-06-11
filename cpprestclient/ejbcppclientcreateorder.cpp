#include <cpprest/http_client.h>

using namespace web;
using namespace web::http;
using namespace web::http::client;

#include <iostream>
#include <string>
using namespace std;

int main(int argc, char *argv[])
{
    // Check the number of parameters
    if (argc != 4)
    {
        // Tell the user how to run the program
        cerr << "Usage: " << argv[0] << " userId commodityCode count " << endl;
        return 1;
    }

    http_client client(U("http://localhost:8080"));

    string form_body = "";
    form_body.append("userId=").append(argv[1]).append("&");
    form_body.append("commodityCode=").append(argv[2]).append("&");
    form_body.append("count=").append(argv[3]);

    http_request req(methods::POST);
    req.set_request_uri(U("/business-ejb/services/business/createOrder"));
    req.headers().set_content_type(U("application/x-www-form-urlencoded"));
    req.set_body(form_body);

    client.request(req).then([](http_response response) {
                           printf("Received response status code:%u\n", response.status_code());
                           response.extract_string().then([](string content) {
                               cout << content << endl;
                           });
                       })
        .wait();

    return 0;
}