const products = require('./products/products.service.js');
const suppliers = require('./suppliers/suppliers.service.js');
const clients = require('./clients/clients.service.js');
module.exports = function () {
  const app = this; // eslint-disable-line no-unused-vars
  app.configure(products);
  app.configure(suppliers);
  app.configure(clients);
};
