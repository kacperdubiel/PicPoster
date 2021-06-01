<template>
  <div>
    <navbar-component />

    <div>
      <div class="start-view-container">
        <div class="start-view-component">
          <login-component></login-component>
        </div>
        <div class="start-view-component">
          <register-component></register-component>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarComponent from '../components/NavbarComponent.vue'
import LoginComponent from '../components/LoginComponent.vue'
import RegisterComponent from '../components/RegisterComponent.vue'
export default {
  name: 'StartView',
  components: {
    NavbarComponent,
    LoginComponent,
    RegisterComponent
  },
  methods: {
    redirectIfLogin(){
      if(localStorage.getItem('token')){
        this.$router.push(this.$route.query.redirect || '/wall')
      }
    }
  },
  created(){
    this.redirectIfLogin();
  }
}
</script>

<style>
  #start-logo{
    color: palevioletred;
    text-align: center;
    font-family: fantasy;
    margin-bottom: 3%;
    margin-top: 3%;
  }
  .start-view-component{
     width: 25%;
     margin-inline: 5%;
  }
  .start-view-container{
    display: flex;
    justify-content: center;
  }
</style>