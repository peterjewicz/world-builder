<template>
  <div class="create">
    <Header />
    <div class="create-inner">
      <h2>Sign Up For a New Account</h2>
      <ul id="errors">
        <li v-for="error in errors">
          {{ error }}
        </li>
      </ul>
      <input v-model="username" type="text" name="username" class="username" placeholder="Username"/>
      <input v-model="email" type="text" name="email" class="email" placeholder="Email"/>
      <input v-model="password" type="password" name="password" class="password" placeholder="Password"/>
      <input v-model="passwordConfirm" type="password" name="password-confirm" class="password-confirm" placeholder="Confirm Password"/>
      <button class="primary large" v-on:click="handleSignup" type="submit">Sign Up!</button>
      <p><router-link v-bind:to="'login'">Already Have an Account?</router-link></p>
    </div>
  </div>
</template>

<script>
import Header from '../global/HeaderFrontend.vue';
const axios = require('axios');
const api = process.env.API;

export default {
  name: 'Create',
  components: {
    Header
  },
  data () {
    return {
      username: '',
      email: '',
      password: '',
      passwordConfirm: '',
      errors: []
    }
  },
  methods: {
    handleSignup() {
      this.errors = [];
      const validated = this._validateFields();

      if (validated.results) {
        axios.post(api + '/create-user', {
          username: this.username.toLowerCase(),
          email: this.email,
          password: this.password
        })
          .then((response) => {
            // Holds the token for future login
            if (!response.data.error) {
              localStorage.setItem('token', response.data.body);
              this.$router.push('dashboard');
            } else {
              this.errors.push(response.data.body);
            }
          })
          .catch((error) => {
            this.errors.push(error);
          })
          .then(function () {
            // always executed
          });
      } else {
        this.errors = validated.errors
      }
    },

    _validateFields() {
      let validated = {
        results: true,
        errors: []
      };

      if (this.username.trim().length === 0) {
        validated.results = false;
        validated.errors.push('Please Fill Out All Fields');
      }

      if (this.email.trim().length === 0) {
        validated.results = false;
        validated.errors.push('Please Fill Out All Fields');
      }

      if (this.password.trim().length === 0 || this.passwordConfirm.trim().length === 0) {
        validated.results = false
        validated.errors.push('Please Fill Out All Fields');
      }

      if (this.password.trim() !== this.passwordConfirm.trim()) {
        validated.results = false;
        validated.errors.push('Passwords Must Match');
      }

      return validated;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '../../styles/main';
  .create {
    height: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    background: $lightGray;
    align-items: center;

    h2 {margin: 0; margin-bottom: 20px}

    .create-inner {
      max-width: 420px;
    }

    #errors {
      color: red;
      list-style: none;
      padding: 0;
      margin-left: 0;
      text-transform: uppercase;
    }
  }
</style>
