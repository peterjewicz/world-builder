<template>
  <div class="Settings">
    <Header titleText="Settings" homeActive="true"/>

    <div class="maxWidthWrap">
      <div class="settingsRow">
        <h2>User Settings</h2>
        <p>Email: test@test.com</p>
      </div>

      <div class="settingsRow">
        <h2>Billing</h2>
        <template v-if="!activeCustomer">
          <form action="/charge" method="post" id="payment-form">
            <div class="form-row">
              <label for="card-element">
                Credit or debit card.
              </label>
              <div id="card-element">
                <!-- A Stripe Element will be inserted here. -->
              </div>

              <!-- Used to display form errors. -->
              <div id="card-errors" role="alert"></div>
            </div>

            <button class="button primary">Submit Payment</button>
          </form>
        </template>
        <template v-else>
          <p>You've already subscribed</p>
          <p>You can unsubscribe anytime, but will lose access too all but your first world</p>
          <button v-on:click="unsubscribeUser" class="primary">Unsubscribe</button>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './includes/Header';
const axios = require('axios');
const api = process.env.API;
const STRIPE_PUBLIC_KEY = process.env.STRIPE_PUBLIC_KEY;

export default {
  name: 'Settings',
  components: {
    Header
  },
  data () {
    return {
      activeCustomer: false,
      stripeToken: '',
      subToken: ''
    }
  },
  mounted() {
    // eslint-disable-next-line
    const stripe = Stripe(STRIPE_PUBLIC_KEY);

    // Create an instance of Elements.
    const elements = stripe.elements();

    const style = {
      base: {
        color: '#32325d',
        lineHeight: '18px',
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSmoothing: 'antialiased',
        fontSize: '16px',
        '::placeholder': {
          color: '#aab7c4'
        }
      },
      invalid: {
        color: '#fa755a',
        iconColor: '#fa755a'
      }
    };

    // Add an instance of the card Element into the `card-element` <div>.
    const card = elements.create('card', {style});

    // Add an instance of the card Element into the `card-element` <div>.
    card.mount('#card-element');

    card.addEventListener('change', ({error}) => {
      const displayError = document.getElementById('card-errors');
      if (error) {
        displayError.textContent = error.message;
      } else {
        displayError.textContent = '';
      }
    });

    // Create a token or display an error when the form is submitted.
    const form = document.getElementById('payment-form');
    form.addEventListener('submit', async (event) => {
      event.preventDefault();

      const {token, error} = await stripe.createToken(card);

      if (error) {
        // Inform the customer that there was an error.
        const errorElement = document.getElementById('card-errors');
        errorElement.textContent = error.message;
      } else {
        // Send the token to your server.
        stripeTokenHandler(token);
      }
    });

    const stripeTokenHandler = (token) => {
      // Insert the token ID into the form so it gets submitted to the server
      const form = document.getElementById('payment-form');
      const hiddenInput = document.createElement('input');
      hiddenInput.setAttribute('type', 'hidden');
      hiddenInput.setAttribute('name', 'stripeToken');
      hiddenInput.setAttribute('value', token.id);
      form.appendChild(hiddenInput);
      // Submit the form
      axios({
        url: api + '/billing/signup',
        method: 'post',
        data: {
          stripeToken: token
        },
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        // TODO actually set the shit here tokens and sub
        alert('Signup Successfull');
        this.activeCustomer = true;
      }).catch((e) => {
        console.log('SIGNUP ERROR')
        alert('There was a problem charging your card. Please try again.')
      })
    }

    axios({
      url: api + '/user',
      method: 'get',
      headers: {'token': localStorage.getItem('token')}
    }).then(response => {
      console.log(response.data.body)
      if (response.data.body.stripeToken) {
        this.activeCustomer = true;
        this.stripeToken = response.data.body.stripeToken;
        this.subToken = response.data.body.subToken;
      }
    }).catch(() => {
      console.log('User Doesn\'t Exist')
      // TODO redirec to login
    })
  },
  methods: {
    unsubscribeUser() {
      axios({
        url: api + '/billing/unsubscribe',
        method: 'post',
        data: {
          subToken: this.subToken,
          stripeToken: this.stripeToken
        },
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        alert('Subscription Cancelled');
        this.activeCustomer = false;
        this.subToken = '';
        this.stripeToken = '';
      }).catch((e) => {
        console.log(e)
        console.log('SIGNUP ERROR')
        alert('There was a problem cancelling your subscription. Please try again or contact support')
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .Settings {

    .settingsRow {
      border-bottom: 2px solid #dedede;
      padding-bottom: 10px;
    }
    form {
      width: 320px;
      margin: 0 auto;

      button {
        margin-top: 25px;
      }
    }
  }

  .StripeElement {
    background-color: white;
    height: 40px;
    padding: 10px 12px;
    border-radius: 4px;
    border: 1px solid transparent;
    box-shadow: 0 1px 3px 0 #e6ebf1;
    -webkit-transition: box-shadow 150ms ease;
    transition: box-shadow 150ms ease;
    width: 320px;
  }

  .StripeElement--focus {
    box-shadow: 0 1px 3px 0 #cfd7df;
  }

  .StripeElement--invalid {
    border-color: #fa755a;
  }

  .StripeElement--webkit-autofill {
    background-color: #fefde5 !important;
  }
</style>
