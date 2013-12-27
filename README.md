# aws-product-api-rest-client - Scala client for the Amazon Product Advertising API.

With this API you will be able to access the Amazon Product Advertising REST API from Scala.
It includes Request Signatures which can be a bit "boring" to implement on your own.
Also it utilize [Dispatch](https://github.com/dispatch/reboot) library (Scala asynchronous HTTP library build
on top of Java [async-http-client](https://github.com/AsyncHttpClient/async-http-client)).

Basic features included:
- request signature;
- basic params validation for each available operation request;
- asynchronous HTTP requests handling;
- return data as native scala.xml.Elem;
- simple API usage via "onSuccess" and "onFailure" callbacks;

Todos:
- publish .jar file;
- unit tests;

## Quick Start

```bash
git clone https://github.com/pavelbogomolenko/aws-product-api-rest-client.git
sbt
console
```
And finally some Scala code:

```scala

import scala.collection.immutable.SortedMap
import main.scala.aws.product.api.rest.controller.AwsProduct

AwsProduct.operationAction(
  "BrowseNodeLookup", //operation name
  "com", //locale
  "[AWS_PUBLIC_KEY]",
  "[AWS_PRIVATE_KEY]",
  SortedMap( //required params for selected operation name
    "BrowseNodeId" -> 301668
  ),
  s => println(s), // "onSuccess" callback function
  f => f.printStackTrace() // "onFailure" callback function
)

```

Also some code samples are included in sources. You can find them main.scala.aws.product.api.Main.

## API Documentation

Please refer to official [Amazon's documentation](http://docs.aws.amazon.com/AWSECommerceService/latest/DG/Welcome.html)

## License

Copyright (c) 2013 Pavlo Bogomolenko. All rights reserved.

This software uses [GPL licence](http://www.gnu.org/licenses/gpl.html)