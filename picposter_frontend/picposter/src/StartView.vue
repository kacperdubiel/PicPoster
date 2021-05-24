<template>
  <div id="start-screen">
    <h1>PIC POSTER </h1>
    <login-component></login-component>
    <register-component></register-component>
  </div>
</template>

<script>

import LoginComponent from './components/LoginComponent.vue'
import RegisterComponent from './components/RegisterComponent.vue'

export default {
  name: 'StartView',
  components: {
    LoginComponent,
    RegisterComponent
  }
}
</script>

<style>
#start-screen {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>