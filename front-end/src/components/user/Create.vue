<template>
  <div class="create">
    <div class="create-inner">
      <h2>Sign Up For a New Account</h2>
      <ul id="errors">
        <li v-for="error in errors">
          {{ error }}
        </li>
      </ul>
      <input v-model="username" type="text" name="username" class="username" placeholder="Username"/>
      <input v-model="email" type="text" name="email" class="email" placeholder="Email"/>
      <input v-model="password" type="text" name="password" class="password" placeholder="Password"/>
      <input v-model="passwordConfirm" type="text" name="password-confirm" class="password-confirm" placeholder="Confirm Password"/>
      <button class="primary large" v-on:click="handleSignup" type="submit">Sign Up!</button>
      <p><a href="#">Already Have an Account?</a></p>
    </div>
  </div>
</template>

<script>

const axios = require('axios');
const api = process.env.API;

export default {
  name: 'Create',
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
          username: this.username,
          email: this.email,
          password: this.password
        })
          .then(function (response) {
            // Holds the token for future logins
            console.log(response.data.body);
          })
          .catch(function (error) {
            console.log(error);
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
  .create {
    height: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    background: #eff3f6;
    align-items: center;

    .create-inner {
      max-width: 420px;
    }
  }
</style>
