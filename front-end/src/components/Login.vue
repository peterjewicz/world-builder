<template>
  <div class="login">
    <h2>Login</h2>
    <h4 v-if="loginError">Your Username Or Password Is Incorrect</h4>
    <input v-model="username" type="text" name="username" class="username" placeholder="Username"/>
    <input v-model="password" type="text" name="password" class="password" placeholder="Password"/>
    <button v-on:click="handleLogin" type="submit">Login</button>
  </div>
</template>

<script>

const axios = require('axios');
const api = process.env.API;

export default {
  name: 'HelloWorld',
  data () {
    return {
      username: '',
      password: '',
      loginError: false
    }
  },
  methods: {
    handleLogin() {
      axios.post(api + '/login', {
        username: this.username,
        password: this.password
      })
        .then((response) => {
          // Holds the token for future logins
          if (response.data.body === 'success') {
            console.log(response.data.token);
          } else {
            this.loginError = true;
          }
        })
        .catch(() => {
          this.loginError = true;
        })
        .then(function () {
          // always executed
        });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
