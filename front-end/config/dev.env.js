'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  API: '"http://localhost:3000/api"',
  STRIPE_PUBLIC_KEY: '"pk_test_LgROF2ukcNIc3P3I3p4Nq31v"'
})
