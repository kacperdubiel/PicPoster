<template>
  <div>
    <navbar-component />

    <div id="start-page">
      <div class="start-view-container">
        <div id="login-container" class="shadow-box">
          <div class="start-view-component">
            <login-component></login-component>
          </div>
        </div>
        <div id="register-container" class="shadow-box">
          <div class="start-view-component">
            <register-component></register-component>
          </div>
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
  #start-page {
      display: flex;
      justify-content: center;
      align-items: center;
  }

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
     flex-grow: 1;
  }
  .start-view-container{
    display: flex;
    justify-content: space-evenly;
    
    width: 1000px;
  }

  #login-container {
    display: flex;
    justify-content: center;

    background-color: white;
    border-radius: 10px;
    padding: 50px 20px;
    flex-grow: 1;
    margin-right: 70px;
  }

  #register-container {
    display: flex;
    justify-content: center;
    
    background-color: white;
    border-radius: 10px;
    padding: 50px 20px;
    flex-grow: 1;
  }
</style>