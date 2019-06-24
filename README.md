# GraphEmailReader
Utility App for downloading and processing Excel email attachments

This utility app was created to download and process an Excel attachment from a Microsoft hosted email account.  The app utilize Microsoft Graph to communicate with the email account.

## Installation

Microsoft Azure Registration:

The application will need to be hosted and registered within your Azure environment.  More instructions can be [found here](https://docs.microsoft.com/en-us/azure/active-directory/develop/quickstart-register-app).

Properties File:

App requires a properties file for the following attributes
```
client.id = CLIENT_ID
user.name = EMAIL_USERNAME
user.pass = EMAIL_PASSWORD
```

## Usage example

Running Tests:

```sh
mvn test
```

## Contributing

1. Fork it (<https://github.com/yourname/yourproject/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
