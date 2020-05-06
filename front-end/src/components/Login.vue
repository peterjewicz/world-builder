<template>
  <div class="login">
    <Header />
    <div class="login-inner">
      <h2>LOGIN</h2>
      <h4 class="error" v-if="loginError">Your Username Or Password Is Incorrect</h4>
      <input v-model="username" type="text" name="username" class="username" placeholder="Username"/>
      <input v-model="password" type="password" name="password" class="password" placeholder="Password"/>
      <button class="primary large" v-on:click="handleLogin" type="submit">Login</button>
      <p><router-link v-bind:to="'create'">Create a New Account?</router-link></p>
    </div>
  </div>
</template>

<script>

import Header from './global/HeaderFrontend.vue';
const axios = require('axios');
const api = process.env.API;

export default {
  name: 'Login',
  components: {
    Header
  },
  data () {
    return {
      username: '',
      password: '',
      loginError: false
    }
  },
  methods: {
    handleLogin() {
      this.loginError = false; // hide it so there's feedback each time
      axios.post(api + '/login', {
        username: this.username,
        password: this.password
      })
        .then((response) => {
          // Holds the token for future logins
          if (response.data.body === 'success') {
            localStorage.setItem('token', response.data.token);
            localStorage.setItem('email', response.data.email);
            this.$router.push('dashboard');
          } else {
            this.loginError = true;
          }
        })
        .catch(() => {
          this.loginError = true;
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '../styles/main';

  .login {
    height: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    background: $lightGray;
    align-items: center;

    h2 {margin: 0; margin-bottom: 20px}
    .login-inner {
      max-width: 420px;
    }
  }

</style>
