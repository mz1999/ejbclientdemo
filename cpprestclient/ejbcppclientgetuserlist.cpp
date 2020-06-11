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
    if (argc != 3)
    {
        // Tell the user how to run the program
        cerr << "Usage: " << argv[0] << " start end " << endl;
        return 1;
    }

    http_client client(U("http://localhost:8080"));

    uri_builder builder(U("/business-ejb/services/business/getUserList"));
    builder.append_query(U("start"), argv[1], true);
    builder.append_query(U("end"), argv[2], true);

    http_request req(methods::GET);
    req.set_request_uri(builder.to_uri());

    client.request(req).then([](http_response response) {
                           printf("Received response status code:%u\n", response.status_code());
                           response.extract_string().then([](string content) {
                               cout << content << endl;
                           });
                       })
        .wait();

    return 0;
}