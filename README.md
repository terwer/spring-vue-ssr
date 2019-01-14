Spring Boot Vue Ssr
======================================

A Vue.js Server side rendering with Spring Boot using Java 8 Nashorn Script Engine.

Main view is isomorphic: HTML is initially rendered on the server with Nashorn by utilizing `renderToString`. All interactive DOM manipulations are handled by Vue.js directly in the browser.

### How to launch

```sh
mvn spring-boot:run
```

### How to build JavaScript

```sh
npm install

npm run build
```
