<template>
  <div class="Settings">
    <Header titleText="Settings"/>
    <h2>User Settings</h2>
    <p>Email: test@test.com</p>

    <h2>Billing</h2>
    <form action="/charge" method="post" id="payment-form">
    <div class="form-row">
      <label for="card-element">
        Credit or debit card
      </label>
      <div id="card-element">
        <!-- A Stripe Element will be inserted here. -->
      </div>

      <!-- Used to display form errors. -->
      <div id="card-errors" role="alert"></div>
    </div>

    <button>Submit Payment</button>
  </form>
  </div>
</template>

<script>
import Header from './includes/Header';
const axios = require('axios');
const api = process.env.API;

export default {
  name: 'Settings',
  components: {
    Header
  },
  data () {
    return {
    }
  },
  mounted() {
    const stripe = Stripe('pk_test_LgROF2ukcNIc3P3I3p4Nq31v');

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
        console.log(response.data.body.id)
      }).catch((e) => {
        console.log(e)
        console.log("SIGNUP ERROR")
      })
    }

    axios({
      url: api + '/user',
      method: 'get',
      headers: {'token': localStorage.getItem('token')}
    }).then(response => {
      console.log(response)
    }).catch(() => {
      console.log("User Doesn't Exist")
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .Settings {

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
