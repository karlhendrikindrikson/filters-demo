# Filters Demo

This is a demo application that consists of a PostgreSQL database, Spring Boot backend and Angular 14 frontend.

### Setup

1. Navigate to the root of this project and run `docker compose up -d` to create the database container.
   - If successful, database will now be accessible at `localhost:5544`
   - The database is automatically populated using the changelogs located in `src/main/resources/db/changelog`.
   - To use a different database port, change the corresponding value in the `docker-compose.yml` and `src/main/resources/application.yml` files.
2. Run the `FiltersApplication.java` file to start the backend service.
   - If successful, the backend service will be running on port `8181`.
   - To use a different backend port, change the corresponding value in the `src/main/resources/application.yml` and `app/proxy.config.json` files.
3. Navigate to the `app` folder and run `npm install` to install frontend dependencies.
Then run `npm start` to start the frontend service.
   - If successful, the frontend service will be accessible from a browser at `http://localhost:4200`
   - To use a different frontend port, change the corresponding value in the `app/package.json` file under the `scripts -> start` section.

### Notes

The filter creation and details dialog can be used in modal/non-modal configurations.
The modal configuration adds a backdrop to the dialog and does not allow the user to interact with the background while the dialog is open.
The non-modal configuration does not use a backdrop and allows the user to interact with the background while the dialog is open.
Both configurations also allow the user to resize the dialog vertically.

By default, the modal configuration is used. To enable the non-modal configuration, visit the application page with the `useNonModalMode=true` query parameter.
(`http://localhost:4200/filters?useNonModalMode=true`).
