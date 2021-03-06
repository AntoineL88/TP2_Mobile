// Initializes the `clients` service on path `/clients`
const createService = require('feathers-nedb');
const createModel = require('../../models/clients.model');
const hooks = require('./clients.hooks');
const filters = require('./clients.filters');

module.exports = function () {
  const app = this;
  const Model = createModel(app);
  const paginate = app.get('paginate');

  const options = {
    name: 'clients',
    Model,
    paginate
  };

  // Initialize our service with any options it requires
  app.use('/clients', createService(options));

  // Get our initialized service so that we can register hooks and filters
  const service = app.service('clients');

  service.hooks(hooks);

  if (service.filter) {
    service.filter(filters);
  }
};
